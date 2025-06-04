package com.learn.BOMServer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MessageParser {

    public static void parseOutPaqCDA(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int id = buffer.getInt();
        String account = readString(buffer, 20);
        String folio = readString(buffer, 20);
        long sent = buffer.getLong();
        long received = buffer.getLong();

        InMemoryDB.OUTPAQCDA.add(new InMemoryDB.OutPaqCDA(
            id, account, folio, new Date(sent), new Date(received)
        ));
        System.out.println("Saved OUTPAQCDA: " + id + ", " + account + ", " + folio);
    }

    public static void parseResponse(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int txnId = buffer.getInt();
        String account = readString(buffer, 20);
        String folio = readString(buffer, 20);
        long received = buffer.getLong();
        String status = readString(buffer, 10);
        String bankCode = readString(buffer, 10);

        InMemoryDB.RESPONSES.add(new InMemoryDB.Response(
            txnId, account, folio, new Date(received), status, bankCode
        ));
        System.out.println("Saved RESPONSE: " + txnId + ", " + account + ", " + status);
    }

    private static String readString(ByteBuffer buffer, int len) {
        byte[] bytes = new byte[len];
        buffer.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8).trim();
    }
}
