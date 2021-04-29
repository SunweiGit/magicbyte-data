package cn.liondance.service.data.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * @author sunwei
 */
public class LionDanceUtils {

    public static File base64ToFile(File file, String base64) {
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            String partSeparator = ",";
            if (base64.contains(partSeparator)) {
                base64 = base64.split(partSeparator)[1];
            }
            byte[] bytes = Base64.getDecoder().decode(base64);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
