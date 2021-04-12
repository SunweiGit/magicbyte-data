package org.magicbyte.service.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.magicbyte.service.data.service.TencentCloudService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author sunwei
 */
@Slf4j
@Service
@AllArgsConstructor
public class TencentCloudServiceImpl implements TencentCloudService {

    private static String secretId = "AKIDmT3PGsEAXfaGfpqmZ75oPmwqdPCks8NM";
    private static String secretKey = "vbz7pqoY1FEcnxtomW2LPEmGfrobPxRm";

    public static void main(String[] args) {
        updateTextTranslate(getJdbcTemplate(), "en");
    }


    private static JdbcTemplate getJdbcTemplate() {
        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/magic_byte?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        configuration.setUsername("root");
        configuration.setPassword("1234567");
        DataSource dataSource = new HikariDataSource(configuration);
        return new JdbcTemplate(dataSource);
    }

    private static void updateTextTranslate(JdbcTemplate jdbcTemplate, String targeType) {
        jdbcTemplate.queryForList("SELECT * FROM text_translate WHERE target IS NULL").forEach(o -> {
            try {
                JSONObject jsonObject = textTranslate(o.get("source").toString(), o.get("source_type").toString(), targeType);
                log.error("jsonObject [ {} ]",jsonObject);
                JSONObject trans_result = JSONObject.parseObject(jsonObject.getJSONArray("trans_result").get(0).toString());
                Thread.sleep(1000L);
                jdbcTemplate.update("UPDATE `text_translate` \n" +
                        "SET " +
                        "`target` = '" + trans_result.getString("dst") + "',\n" +
                        "`target_type` = '" + targeType + "'\n" +
                        "WHERE\n" +
                        "\t`id` = '" + o.get("id").toString() + "';");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static JSONObject textTranslate(String sourceText, String source, String target) throws TencentCloudSDKException, NoSuchAlgorithmException, UnsupportedEncodingException {

        OkHttpClient client = new OkHttpClient();
        String appid = "20210411000772630";
        String pass = "8qyPSSnJ7HfZwxI9OVcT";
        String salt = "123456";
        String sign = appid + sourceText + salt + pass;
        sign= DigestUtils.md5DigestAsHex(sign.getBytes());
        Request request = new Request.Builder().url("https://fanyi-api.baidu.com/api/trans/vip/translate?q=" + URLEncoder.encode(sourceText,"UTF-8") + "&from=" + source + "&to=" + target + "&appid=20210411000772630&salt=" + salt + "&sign=" + sign).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // ... handle failed request
            }
            String responseBody = response.body().string();
            // ... do something with response
            return JSONObject.parseObject(responseBody);
        } catch (IOException e) {
            // ... handle IO exception
        }
        return new JSONObject();

    }

    private void insertTextTranslate(JdbcTemplate jdbcTemplate, String str, String source_type) {
        Arrays.stream(str.split("、")).forEach(o -> {
            if (StringUtils.isNotEmpty(o)) {
                jdbcTemplate.execute("INSERT INTO `text_translate` ( `source`, `target`, `source_type`, `target_type` )\n" +
                        "VALUES\n" +
                        "\t(  '" + o + "', NULL, source_type, NULL );");
            }
        });
    }

}
