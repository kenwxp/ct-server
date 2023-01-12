package com.cloudtimes.common.utils.file;

import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * 文件获取工具
 */
public class FileReadUtils {

    public static String readFileToString(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(filename);
        File file = resource.getFile();
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }

        byte[] byteArrays = baos.toByteArray();
        fis.close();
        baos.close();
        return new String(byteArrays);
    }

}
