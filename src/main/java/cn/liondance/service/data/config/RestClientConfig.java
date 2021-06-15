package cn.liondance.service.data.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/** @author sunwei */
@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.elasticsearch.rest")
public class RestClientConfig {

  private String[] uris;
  private String username;
  private String password;

  @Bean
  public RestHighLevelClient elasticsearchClient()
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException,
          URISyntaxException {
    log.error("loading elasticsearch info uris{} username{} password{}", uris, username, password);
    SSLContext sslContext =
        SSLContextBuilder.create()
            .setProtocol(SSLConnectionSocketFactory.SSL)
            .loadTrustMaterial((x, y) -> true)
            .build();
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(
        AuthScope.ANY, new UsernamePasswordCredentials(username, password));
    HttpHost[] httpHost = new HttpHost[uris.length];
    for (int i = 0; i < uris.length; i++) {
      URI uri = URI.create(uris[i]);
      httpHost[i] = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
    }
    RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
    restClientBuilder.setHttpClientConfigCallback(
        httpClientBuilder ->
            httpClientBuilder
                /** 加载用户名密码 */
                .setDefaultCredentialsProvider(credentialsProvider)
                /** 不做SSL校验 */
                .setSSLHostnameVerifier((x, y) -> true)
                .setSSLContext(sslContext));
    return new RestHighLevelClient(restClientBuilder);
  }
}
