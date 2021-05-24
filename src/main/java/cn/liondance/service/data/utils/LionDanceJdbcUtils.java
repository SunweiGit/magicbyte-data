package cn.liondance.service.data.utils;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * The enum Crad status enum.
 *
 * @author sunwei
 */
@Builder
public class LionDanceJdbcUtils {

    public static JdbcTemplate getJdbcTemplate() {
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/liondance?characterEncoding=utf8&allowMultiQueries=true&verifyServerCertificate=false&serverTimezone=GMT%2B8&useSSL=false");
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setUsername("root");
        configuration.setPassword("Admin@1278300799");
        DataSource dataSource = new HikariDataSource(configuration);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }


}
