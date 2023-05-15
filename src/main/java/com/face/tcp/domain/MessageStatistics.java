/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp.domain;

/**
 * 消息统计
 *
 * @author baixuezhi
 * @date 2023/5/6
 */
public class MessageStatistics extends Message {
    private static final long serialVersionUID = -8083143767238306020L;
    /**
     * 消息断号统计个数（判断丢包率）
     */
    private Integer breakNum;

    public Integer getBreakNum() {
        return breakNum;
    }

    public void setBreakNum(Integer breakNum) {
        this.breakNum = breakNum;
    }

    @Override
    public String toString() {
        return "MessageStatistics{" +
                "Addr=" + getContent() +
                ",LastDataNum=" + getNum() +
                ",BreakNum=" + breakNum +
                "}\r\n";
    }
}
