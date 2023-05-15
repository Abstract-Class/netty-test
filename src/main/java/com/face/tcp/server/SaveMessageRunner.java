/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp.server;

import com.face.tcp.constant.Common;
import com.face.tcp.domain.Message;
import com.spaceon.common.file.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author baixuezhi
 * @date 2023/5/6
 */
public class SaveMessageRunner implements Runnable{

    private Integer fileId;

    public SaveMessageRunner(int fId) {
        fileId = fId;
        String path = FileUtil.WORK_DIR;

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public void run() {
        String path = FileUtil.WORK_DIR + "DATA_"+ fileId + FileUtil.FILE_EXT_TXT;
        try(FileWriter fw = new FileWriter(new File(path),true)) {
            while (true){
                //FileWriter fw = new FileWriter(new File(path));
                Message poll = Common.MESSAGE_QUEUE.poll();
                if (poll != null){
                    fw.write(poll.toString());
                    fw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
