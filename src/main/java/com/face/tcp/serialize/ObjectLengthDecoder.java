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
 * 对象解码（解决拼包）
 *
 * @author baixuezhi
 * @date 2023/5/5
 */
public class ObjectLengthDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()< Common.HEAD_LENGTH){
            return;
        }
        in.markReaderIndex();
        int length = in.readInt();
        if (length<0){
            ctx.close();
        }

        if (in.readableBytes()<length){
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        out.add(ObjectUtil.objDeSerialization(bytes));

    }
}
