
package com.face.tcp.handler.ch;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据丢包统计客户端处理
 *
 * @author baixuezhi
 * @date 2023/5/6
 */
@Slf4j
public class CollectDataClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("来自服务器的消息："+msg);
    }
}
