package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {

//    @Value("SPRING_DATASOURCE_URL")
//    private String datasourceUrl;

    public static void main (String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }


    @Bean
    public TimeEntryRepository getInMemoryTimeEntryRepository () {
        return new JdbcTimeEntryRepository(getDataSource());
    }

    //@Bean
    private DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));
        return dataSource;
    }
}
