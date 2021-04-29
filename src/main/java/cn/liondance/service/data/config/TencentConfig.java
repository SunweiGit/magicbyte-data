package cn.liondance.service.data.config;

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
@ConfigurationProperties(prefix = "tencent")
public class TencentConfig {
    private String secretId;
    private String secretKey;
}