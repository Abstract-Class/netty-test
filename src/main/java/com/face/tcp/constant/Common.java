/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp.constant;

import com.face.tcp.domain.Message;


import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * common
 *
 * @author baixuezhi
 * @date 2023/4/23
 */
public class Common {
    /**
     * int类型的字节数（编码端数据头指定4字节）
     */
    public static final int HEAD_LENGTH = 4;
    public static ConcurrentLinkedQueue<Message> MESSAGE_QUEUE = new ConcurrentLinkedQueue<>();
}
