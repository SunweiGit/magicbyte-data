package cn.liondance.service.data.service.impl;

import cn.liondance.service.data.config.LionDanceConfig;
import cn.liondance.service.data.service.LionDanceCloudService;
import cn.liondance.service.data.utils.BaiduUtils;
import cn.liondance.service.data.utils.LionDanceJdbcUtils;
import cn.liondance.service.data.utils.LionDanceUtils;
import com.alibaba.fastjson.JSONObject;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static cn.liondance.service.data.utils.BaiduUtils.speechSynthesis;
import static cn.liondance.service.data.utils.LionDanceMinioUtils.getMinioClient;
import static cn.liondance.service.data.utils.LionDanceUtils.getContentType;

/**
 * The type Lion dance cloud service.
 */
@Service
@AllArgsConstructor
public class LionDanceCloudServiceImpl implements LionDanceCloudService {

    private final MinioClient minioClient;


    @Override
    public void updateTextAudio() {
        JdbcTemplate jdbcTemplate = LionDanceJdbcUtils.getJdbcTemplate();
        String suffix = ".mp3";
        String bucket = "audio";
        String contentType = getContentType(suffix);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT id,source FROM text_audio WHERE target IS NULL or  target=''");
        list.forEach(o -> {
            try {
                String speechSynthesis = speechSynthesis(o.get("source").toString());
                JSONObject parseObject = JSONObject.parseObject(speechSynthesis);
                File file = File.createTempFile(o.get("id").toString(), suffix);
                LionDanceUtils.base64ToFile(file, parseObject.getString("data"));
                MinioClient minioClient = getMinioClient();
                String object = o.get("id").toString().replaceAll("/", "") + suffix;
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(object)
                        .stream(new FileInputStream(file), file.length(), -1)
                        .contentType(contentType)
                        .build());
                if (file.length()>100){
                    jdbcTemplate.update("UPDATE `text_audio` \n" +
                            "SET\n" +
                            "`target` = '" + LionDanceConfig.domain + "/minio" + "/" + bucket + "/" + object + "' \n" +
                            "WHERE\n" +
                            "\t`id` = '" + o.get("id") + "';");
                }
                file.delete();
            } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException | InternalException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateTextTranslate() {
        JdbcTemplate jdbcTemplate = LionDanceJdbcUtils.getJdbcTemplate();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT\tDISTINCT id,source,source_type FROM text_translate WHERE  target IS NULL");
        //Map<String, Object> systemConfigMap = jdbcTemplate.queryForMap("SELECT\tDISTINCT `key`,`value` FROM system_config");
        list.forEach(o -> {
            try {
                String translate = BaiduUtils.translate(o.get("source").toString(), "auto", "en", 0, 0, 0);
                JSONObject parseObject = JSONObject.parseObject(translate);
                JSONObject trans_result = JSONObject.parseObject(parseObject.getJSONArray("trans_result").get(0).toString());
                jdbcTemplate.update("UPDATE `text_translate` \n" +
                        "SET \n" +
                        "`target` = '" + trans_result.getString("dst") + "',\n" +
                        "`target_type` = '" + parseObject.getString("to") + "' \n" +
                        "WHERE\n" +
                        "\t`id` = " + o.get("id").toString() + ";");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void updateTranslate(String tableName, String field) {
        JdbcTemplate jdbcTemplate = LionDanceJdbcUtils.getJdbcTemplate();
        jdbcTemplate.queryForList("SELECT\t id," + field + " FROM " + tableName + "").forEach(o -> {
            JSONObject parseObject = JSONObject.parseObject(o.get(field).toString());
            jdbcTemplate.queryForList("SELECT target,target_type,source,source_type FROM text_translate WHERE source='" + parseObject.getString("zh_CN") + "'").forEach(o1 -> {
                parseObject.put(o1.get("target_type").toString(),o1.get("target"));
                parseObject.put(o1.get("source_type").toString(),o1.get("source"));
            });
            jdbcTemplate.execute("UPDATE "+tableName+"\n" +
                    "SET `"+field+"` = '" + parseObject.toJSONString() + "'\n" +
                    "WHERE\n" +
                    "\t`id` = '" + o.get("id").toString() + "'");
        });
    }

    @Override
    public void updateAudio(String tableName, String field) {
        JdbcTemplate jdbcTemplate = LionDanceJdbcUtils.getJdbcTemplate();
        jdbcTemplate.queryForList("SELECT\t id,sound," + field + " FROM " + tableName + "").forEach(o -> {
            JSONObject sound_parseObject = JSONObject.parseObject(o.get("sound").toString());
            JSONObject field_parseObject = JSONObject.parseObject(o.get(field).toString());
            field_parseObject.values().forEach(o1->{
                sound_parseObject.put(o1.toString(),jdbcTemplate.queryForObject("SELECT IFNULL(target,'')  FROM text_audio WHERE source='"+o1+"'",String.class));
            });
            jdbcTemplate.execute("UPDATE "+tableName+"\n" +
                    "SET `sound` = '" + sound_parseObject.toJSONString() + "'\n" +
                    "WHERE\n" +
                    "\t`id` = '" + o.get("id").toString() + "'");
        });
    }


}