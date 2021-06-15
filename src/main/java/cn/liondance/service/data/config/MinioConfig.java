package cn.liondance.service.data.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * The type Swagger config.
 *
 * @author sunwei
 */
@Component
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
  private String domain;
  private String accessKey;
  private String secretKey;

  /**
   * Create minio client minio client.
   *
   * @return the minio client
   */
  @Bean
  public MinioClient createMinioClient() {

    return MinioClient.builder().endpoint(domain).credentials(accessKey, secretKey).build();
  }
}
