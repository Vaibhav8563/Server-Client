package com.learn.BOMServer;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.core.session.IoSession;

public class ByteArrayCodecFactory implements ProtocolCodecFactory {
    private final ProtocolEncoder encoder = new ByteArrayEncoder();
    private final ProtocolDecoder decoder = new ByteArrayDecoder();

    @Override
    public ProtocolEncoder getEncoder(IoSession session) {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) {
        return decoder;
    }
}
