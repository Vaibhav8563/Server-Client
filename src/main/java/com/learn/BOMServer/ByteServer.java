package com.learn.BOMServer;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;

import java.net.InetSocketAddress;

public class ByteServer {
    public static void main(String[] args) throws Exception {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));
        acceptor.setHandler(new ServerHandler());
        acceptor.bind(new InetSocketAddress(9123));
        System.out.println("Server started on port 9123");
    }
}
