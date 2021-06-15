package cn.liondance.service.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** @author sunwei The type Git auto refresh config. */
@Component
@Data
@ConfigurationProperties(prefix = "data")
public class GitAutoRefreshConfig {

  private String env;
  private UserInfo user;

  /** The type User info. */
  public static class UserInfo {
    private String username;

    private String password;

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
      return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
      this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
      return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
      this.password = password;
    }

    @Override
    public String toString() {
      return "UserInfo{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
  }
}
