package com.learn.BOMServer;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Arrays;

public class ServerHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) {
        byte[] data = (byte[]) message;
        if (data.length < 1) return;

        int msgCode = data[0] & 0xFF;

        switch (msgCode) {
        case 1: // Login
            session.setAttribute("loggedIn", true);
            System.out.println("Login received.");
            session.write(new byte[]{1, 1});  // example ACK: msgCode 1 + status 1 (success)
            break;

        case 2: // Password
            if (Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
                session.setAttribute("authenticated", true);
                System.out.println("Password accepted.");
                session.write(new byte[]{2, 1});
            } else {
                System.out.println("Password received but not logged in.");
                session.write(new byte[]{2, 0}); // failure ack
            }
            break;

        case 16: // Connection
            if (Boolean.TRUE.equals(session.getAttribute("authenticated"))) {
                session.setAttribute("connected", true);
                System.out.println("Connection established.");
                session.write(new byte[]{16, 1});
            } else {
                System.out.println("Connection received but not authenticated.");
                session.write(new byte[]{16, 0});
            }
            break;

        case 197: // OutPaqCDA
            if (Boolean.TRUE.equals(session.getAttribute("connected"))) {
                System.out.println("CDA_Request sent to BOM");
                System.out.println("OutPaqCDA bytes: " + Arrays.toString(Arrays.copyOfRange(data, 1, data.length)));
                session.write(new byte[]{(byte)197, 1});
            } else {
                System.out.println("Not connected. Ignoring OutPaqCDA.");
                session.write(new byte[]{(byte)197, 0});
            }
            break;

        case 27: // Response
            byte[] responseData = Arrays.copyOfRange(data, 1, data.length);
            System.out.println("Response received bytes: " + Arrays.toString(responseData));
            session.write(new byte[]{27, 1});
            break;

            default:
                System.out.println("Unknown message code: " + msgCode);
        }
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        cause.printStackTrace();
        session.closeNow();
    }
}
