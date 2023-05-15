/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp.handler;

import com.face.tcp.handler.ch.ClientHandler;
import com.face.tcp.handler.ch.CollectDataClientHandler;
import com.face.tcp.serialize.ObjectLengthEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 *
 *
 * @author baixuezhi
 * @date 2023/4/6
 */
public class ClientChildHandler extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        //编码 object转bytebuf
        nioSocketChannel.pipeline().addLast(new ObjectLengthEncoder());
        nioSocketChannel.pipeline().addLast(new StringDecoder());
        nioSocketChannel.pipeline().addLast(new CollectDataClientHandler());
    }
}
