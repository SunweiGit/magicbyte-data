package org.magicbyte.service.data.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunwei
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(ElasticsearchConfig.class)
@AllArgsConstructor
public class InitElasticsearchClient {

    private final ElasticsearchConfig elasticsearchConfig;

    @Bean
    public RestHighLevelClient initClient() {
        String[] hostname = elasticsearchConfig.getHostname();
        String scheme = elasticsearchConfig.getScheme();
        HttpHost[] hosts = new HttpHost[hostname.length];
        for (int i = 0; i < hostname.length; i++) {
            String[] arr = hostname[i].split(":");
            hosts[i] = new HttpHost(arr[0], Integer.parseInt(arr[1]), scheme);
        }
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(hosts));
        log.error("(^-^)(^-^)(^-^)(^-^)(^-^)(^-^)初始化RestHighLevelClient 完成 (^-^)(^-^)(^-^)(^-^)(^-^) {}", client.toString());

        return client;
    }
}
