
package com.face.tcp.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码（解决拼包）
 *
 * @author baixuezhi
 * @date 2023/5/5
 */
public class ByteLengthEncoder extends MessageToByteEncoder<byte[]> {
    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
        //int类型标明传送字节数
        int length = msg.length;
        out.writeInt(length);
        out.writeBytes(msg);
    }
}
