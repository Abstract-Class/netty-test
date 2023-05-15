
package com.face.tcp.handler.sh;

import com.face.tcp.constant.Common;
import com.face.tcp.domain.Message;
import com.face.tcp.domain.MessageStatistics;
import com.spaceon.common.date.TimeUtils;
import com.spaceon.common.file.FileUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据丢包统计服务端处理
 *
 * @author baixuezhi
 * @date 2023/5/6
 */
@Slf4j
public class CollectDataServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * channel消息统计
     */
    private Map<Integer, MessageStatistics> count = new HashMap<>();

    public CollectDataServerHandler() {
        String path = FileUtil.WORK_DIR;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("新的连接：" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        try {
            MessageStatistics ms = count.get(ctx.channel().remoteAddress().hashCode());
            if (ms != null) {
                String path = FileUtil.WORK_DIR + "ConnStatisticsResult_" + TimeUtils.getYmByTime(System.currentTimeMillis()) + FileUtil.FILE_EXT_TXT;
                FileWriter fileWriter = new FileWriter(new File(path), true);
                fileWriter.write(count.get(ctx.channel().remoteAddress().hashCode()).toString());
                fileWriter.flush();
                fileWriter.close();
            }
            log.info(ctx.channel().remoteAddress() + ":连接状态-" + ctx.channel().isActive() + ":连接离线了！");
        } catch (Exception e) {
            log.error("执行连接统计数据存储时发生错误！");
            super.channelInactive(ctx);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Message) {
            Common.MESSAGE_QUEUE.offer((Message) msg);
            int hc = ctx.channel().remoteAddress().hashCode();
            MessageStatistics messageStatistics = count.get(hc);
            if (messageStatistics != null) {
                if ((messageStatistics.getNum() + 1) != ((Message) msg).getNum()) {
                    messageStatistics.setBreakNum(messageStatistics.getBreakNum() + 1);
                    messageStatistics.setNum(((Message) msg).getNum());
                    count.put(hc, messageStatistics);
                } else {
                    messageStatistics.setNum(((Message) msg).getNum());
                }
            } else {
                //初始化channel统计信息
                MessageStatistics ms = new MessageStatistics();
                ms.setContent(ctx.channel().remoteAddress().toString());
                ms.setNum(((Message) msg).getNum());
                ms.setBreakNum(0);
                count.put(hc, ms);
            }
        }
        //ctx.channel().writeAndFlush(msg.toString());
    }
}
