
package com.face.tcp.handler;

import com.face.tcp.handler.sh.CollectDataServerHandler;
import com.face.tcp.handler.sh.ServerHandler;
import com.face.tcp.serialize.ObjectLengthDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;

/**
 *
 * @author baixuezhi
 * @date 2023/5/5
 */
public class ServerChildHandler extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LoggingHandler());
        ch.pipeline().addLast(new ObjectLengthDecoder());
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new CollectDataServerHandler());
    }
}
