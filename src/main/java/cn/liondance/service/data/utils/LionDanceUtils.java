package cn.liondance.service.data.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

/**
 * The type Lion dance utils.
 *
 * @author sunwei
 */
@Slf4j
public class LionDanceUtils {

    /**
     * Base 64 to file.
     *
     * @param file   the file
     * @param base64 the base 64
     */
    public static void base64ToFile(File file, String base64) {
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
    }


    /**
     * Gets content type.
     *
     * @param suffix the suffix
     * @return the content type
     */
    public static String getContentType(String suffix) {
        JdbcTemplate jdbcTemplate = LionDanceJdbcUtils.getJdbcTemplate();
        String sql="SELECT content_type FROM http_content_type WHERE suffix='" + suffix + "' LIMIT 1";
        log.error("sql {}",sql);
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
