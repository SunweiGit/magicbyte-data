package cn.liondance.service.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The type Data application.
 *
 * @author sunwei
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DataApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

}
