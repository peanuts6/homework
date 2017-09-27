package leader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import util.DataSource;
import util.ProductionDataSource;
import util.TestDataSource;

/**
 * Created by leader on 17/8/30.
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
