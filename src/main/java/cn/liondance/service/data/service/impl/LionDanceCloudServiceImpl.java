package cn.liondance.service.data.service.impl;

import cn.liondance.service.data.service.LionDanceCloudService;
import cn.liondance.service.data.utils.BaiduUtils;
import cn.liondance.service.data.utils.LionDanceJdbcUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The type Lion dance cloud service.
 */
@Service
@AllArgsConstructor
public class LionDanceCloudServiceImpl implements LionDanceCloudService {

    @Override
    public void updateTextAudio() {

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
                        "`target` = '"+trans_result.getString("dst")+"',\n" +
                        "`target_type` = '"+parseObject.getString("to")+"' \n" +
                        "WHERE\n" +
                        "\t`id` = "+o.get("id").toString()+";");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    @Override
    public void updateTranslate(String tableName, String field) {
    }

    @Override
    public void updateAudio(String tableName, String field) {

    }


}