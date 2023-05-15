/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp;

import com.face.tcp.server.NettyServer;
import com.face.tcp.server.SaveMessageRunner;
import com.spaceon.common.util.LoadConfig;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务器
 *
 * @author baixuezhi
 * @date 2023/4/6
 */
public class Server {
    public static void main(String[] args) {
        Properties props = LoadConfig.getProps();
        int saveThNum = Integer.parseInt(props.getProperty("server.thread.num"));
        int bp = Integer.parseInt(props.getProperty("server.bind.port"));
        ExecutorService threadPool = new ThreadPoolExecutor(saveThNum+1, saveThNum+1, 30, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadName());
        threadPool.execute(() -> {
            NettyServer server = new NettyServer(bp);
            server.startNettyServer();
        });
        for (int i = 0; i < saveThNum; i++) {
            threadPool.execute(new SaveMessageRunner(i));
        }
    }
}
