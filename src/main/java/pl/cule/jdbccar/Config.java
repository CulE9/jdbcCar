package pl.cule.jdbccar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/cars?serverTimezone=UTC");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("root");
//        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");

//        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/jdbc_car");
//        dataSourceBuilder.username("postgres");
//        dataSourceBuilder.password("tomcat");
//        dataSourceBuilder.driverClassName(driver);
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}
