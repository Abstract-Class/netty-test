
package com.face.tcp.domain;

import java.io.Serializable;

/**
 * 消息
 *
 * @author baixuezhi
 * @date 2023/5/5
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 8209159050697418793L;

    private String content;
    private Integer num;

    public Message() {
    }

    public Message(String content, Integer num) {
        this.content = content;
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content=" + content +
                ", num=" + num +
                "}\r\n";
    }
}
