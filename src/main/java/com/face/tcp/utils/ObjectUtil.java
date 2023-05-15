
package com.face.tcp.utils;

import java.io.*;

/**
 * @author baixuezhi
 * @date 2023/5/5
 */
public class ObjectUtil {
    /**
     * 对象序列化
     *
     * @return byte[]
     */
    public static byte[] objSerialization(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        bos.close();
        oos.close();
        return bos.toByteArray();
    }

    /**
     * 对象反序列化
     *
     * @param obj byte
     * @return Object
     */
    public static Object objDeSerialization(byte[] obj) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(obj);
        ObjectInputStream ois = new ObjectInputStream(bis);
        bis.close();
        ois.close();
        return ois.readObject();
    }
}
