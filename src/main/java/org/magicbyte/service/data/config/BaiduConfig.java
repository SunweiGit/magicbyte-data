package org.magicbyte.service.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The type Swagger config.
 *
 * @author sunwei
 */
@Component
@Data
@ConfigurationProperties(prefix = "baidu")
public class BaiduConfig {
    private String appId;
    private String apiKey;
    private String secretKey;
    private String grantType;
    private String getTokenUrl;
}