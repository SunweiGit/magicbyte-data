package cn.liondance.service.data.controller;

import cn.liondance.service.data.config.GitAutoRefreshConfig;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Git controller.
 *
 * @author sunwei
 */
@RefreshScope
@RestController
@AllArgsConstructor
public class GitController {

  private final GitAutoRefreshConfig gitAutoRefreshConfig;

  /**
   * Auto show object.
   *
   * @return the object
   */
  @GetMapping(value = "autoShow")
  public Object autoShow() {
    return gitAutoRefreshConfig;
  }
}
