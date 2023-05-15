/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp;

import java.util.concurrent.ThreadFactory;

/**
 * @author baixuezhi
 * @date 2023/4/18
 */
public class ThreadName implements ThreadFactory {
    int n=1;
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"Data-Push-"+ n++);
    }
}
