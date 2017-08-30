package xqy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import xqy.service.UserService;
import xqy.util.DataSource;
import xqy.util.ProductionDataSource;
import xqy.util.TestDataSource;

/**
 * Created by xqy on 17/8/30.
 */
@Configuration
public class DataConfig {
    @Bean(name="dataSource")
    @Profile("test")
    public DataSource getTestData(){
        return new TestDataSource();
    }

    @Bean(name="dataSource")
    @Profile("production")
    public DataSource getProductionData(){
        return new ProductionDataSource();
    }
}
