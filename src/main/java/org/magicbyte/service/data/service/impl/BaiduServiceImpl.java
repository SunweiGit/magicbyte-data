package org.magicbyte.service.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.magicbyte.service.data.config.BaiduConfig;
import org.magicbyte.service.data.entity.MinioObject;
import org.magicbyte.service.data.service.BaiduService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * The type Minio object service.
 *
 * @author sunwei
 */
@Service
@AllArgsConstructor
public class BaiduServiceImpl implements BaiduService {

    private final BaiduConfig baiduConfig;


    @Override
    public void putObject(MinioObject minioObject, MultipartFile file) throws Exception {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(baiduConfig.getGetTokenUrl());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("grant_type",baiduConfig.getGrantType());
            jsonObject.put("client_id",baiduConfig.getApiKey());
            jsonObject.put("client_secret",baiduConfig.getSecretKey());
            httpPost.setEntity(new StringEntity(jsonObject.toJSONString(), ContentType.APPLICATION_JSON));
            String responseBody = httpClient.execute(httpPost, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status < 200 || status >= 300) {
                    // ... handle unsuccessful request
                }
                HttpEntity entity = httpResponse.getEntity();
                System.out.println(entity);
                return entity != null ? EntityUtils.toString(entity) : null;
            });
            // ... do something with response

        } catch (IOException e) {
            // ... handle IO exception
        }
    }


}
