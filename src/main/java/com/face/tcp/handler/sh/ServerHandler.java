/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp.handler.sh;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 聊天的服务
 *
 * @author baixuezhi
 * @date 2023/5/5
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private Map<String, Channel> channelMap = new HashMap<String, Channel>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelMap.put(ctx.channel().remoteAddress().toString(), ctx.channel());
        log.info(ctx.channel().remoteAddress() + ":上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelMap.remove(ctx.channel().remoteAddress().toString());
        log.info(ctx.channel().remoteAddress() + ":离线");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //群转发
        channelMap.values().forEach((e) -> {
            if (e != ctx.channel()) {
                e.writeAndFlush(msg);
            }
        });
    }
}
