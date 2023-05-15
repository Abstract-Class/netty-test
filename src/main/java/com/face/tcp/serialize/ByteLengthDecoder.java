/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp.serialize;

import com.face.tcp.constant.Common;
import com.face.tcp.utils.ObjectUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码（解决拼包）
 *
 * @author baixuezhi
 * @date 2023/4/23
 */
public class ByteLengthDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < Common.HEAD_LENGTH){
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0){
            ctx.close();
        }

        if (in.readableBytes() < dataLength){
            in.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];
        in.readBytes(data);
        //Object o = ObjectUtil.objDeSerialization(data);
        out.add(data);
    }
}
