/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp;

import com.face.tcp.client.NettyClient;
import com.spaceon.common.util.LoadConfig;

import java.util.Properties;
import java.util.concurrent.*;

/**
 * 客户端
 *
 * @author baixuezhi
 * @date 2023/4/6
 */
public class Client {
    public static void main(String[] args) {
        Properties props = LoadConfig.getProps();
        String ip = props.getProperty("client.server.ip");
        int port = Integer.parseInt(props.getProperty("client.server.port"));
        int thNum = Integer.parseInt(props.getProperty("client.thread.num"));
        ExecutorService threadPool = new ThreadPoolExecutor(thNum, thNum, 30, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadName());
        for (int i = 0; i < thNum; i++) {
            String name = System.getenv("USERNAME") + "-client-" + i;
            threadPool.execute(() -> {
                //NettyClient client = new NettyClient(name,"192.168.1.82",9999);
                NettyClient client = new NettyClient(name, ip, port);
                client.startNettyClient(Long.parseLong(props.getProperty("client.send.interval")));
            });
            try {
                Thread.sleep(Long.parseLong(props.getProperty("client.interval")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
