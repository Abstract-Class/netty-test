/*
 * Copyright (c) 2003,成都天奥信息科技有限公司
 * All rights reserved.
 */
package com.face.tcp;

import com.spaceon.common.file.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 数据检查程序
 *
 * @author baixuezhi
 * @date 2023/5/8
 */
public class DataCheck {
    public static void main(String[] args) throws IOException {
        String client = "lenovo-client-9991";
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            File file = new File("C:\\Users\\lenovo\\Desktop\\test\\tcp-conn\\data2\\DATA_" + i + FileUtil.FILE_EXT_TXT);
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String data;
            while ((data = br.readLine()) != null) {
                if (data.contains(client)) {
                    String[] split = data.split("num");
                    String substring = data.substring(data.lastIndexOf("=")+1).replace("}","");
                    list.add(Integer.parseInt(substring));
                }
            }
            reader.close();
            br.close();
        }
        Collections.sort(list);

        FileWriter writer = new FileWriter(new File("C:\\Users\\lenovo\\Desktop\\test\\tcp-conn\\" + client));
        int x = -1;
        for (Integer i : list) {
            x++;
            writer.write(i + "\r\n");
        }
        writer.write("数值校验:"+(x==list.get(list.size()-1)));
        writer.close();
    }
}
