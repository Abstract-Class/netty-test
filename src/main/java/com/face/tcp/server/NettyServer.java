
package com.face.tcp.server;

import com.face.tcp.handler.ServerChildHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * TCP server
 *
 * @author baixuezhi
 * @date 2023/4/6
 */
@Slf4j
public class NettyServer {
    private final Integer PORT;

    public NettyServer(Integer port) {
        PORT = port;
    }

    public void startNettyServer() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup woker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    //EventLoop = selector 检测 + thread 线程
                    .group(boss, woker)
                    //选择服务器的实现
                    .channel(NioServerSocketChannel.class)
                    //handler
                    .childHandler(new ServerChildHandler());
            ChannelFuture channelFuture = bootstrap
                    .bind(PORT)
                    .sync();
            log.info("Netty 服务启动成功！");
            channelFuture
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务运行出错：" + e.getMessage());
        } finally {
            boss.shutdownGracefully();
            woker.shutdownGracefully();
        }
    }
}
