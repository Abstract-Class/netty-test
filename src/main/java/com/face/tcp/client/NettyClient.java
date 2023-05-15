
package com.face.tcp.client;

import com.face.tcp.domain.Message;
import com.face.tcp.handler.ClientChildHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * netty 客户端
 *
 * @author baixuezhi
 * @date 2023/4/6
 */
@Slf4j
public class NettyClient {
    private int lines = 0;
    private String name;
    private final String HOST;
    private final Integer PORT;

    public NettyClient(String clientName, String host, Integer port) {
        name = clientName;
        HOST = host;
        PORT = port;
    }

    /**
     * 发送消息间隔毫秒数
     * @param ms ms
     */
    public void startNettyClient(Long ms) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        try {
            //启动类
            Bootstrap bootstrap = new Bootstrap();
            Bootstrap handler = bootstrap
                    .group(boss)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChildHandler());
            ChannelFuture future = handler
                    .connect(HOST, PORT)
                    .sync();
            log.info("Netty客户端连接服务器成功！！！");
            Channel channel = future.channel();
            while (channel.isActive()) {
                Thread.sleep(ms);
                Message message = new Message(name, lines);
                channel.writeAndFlush(message);
                //System.out.println(name + ":" + lines);
                lines++;
            }
            channel.closeFuture();
        } catch (Exception e) {
            log.error("客户端连接错误：" + e.getMessage());
        } finally {
            boss.shutdownGracefully();
            log.debug("客户端关闭");
        }
    }
}
