package com.learn.BOMServer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ByteArrayDecoder extends CumulativeProtocolDecoder {

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) {
        if (in.remaining() < 1) return false;

        byte[] data = new byte[in.remaining()];
        in.get(data);
        out.write(data);
        return true;
    }
}
