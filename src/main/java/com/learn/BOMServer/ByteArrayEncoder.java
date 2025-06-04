package com.learn.BOMServer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ByteArrayEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) {
        byte[] data = (byte[]) message;
        IoBuffer buffer = IoBuffer.allocate(data.length).setAutoExpand(true);
        buffer.put(data);
        buffer.flip();
        out.write(buffer);
    }

    @Override
    public void dispose(IoSession session) {
    }
}
