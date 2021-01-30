package org.magicbyte.service.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sunwei
 */
@Data
@Component
@ConfigurationProperties(prefix = "elastic-search")
public class ElasticsearchConfig {

    private String scheme;
    private String[] hostname;

}
