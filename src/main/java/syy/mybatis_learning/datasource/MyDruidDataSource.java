package syy.mybatis_learning.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class MyDruidDataSource extends PooledDataSourceFactory {
//    public MyDruidDataSource(){
//        this.dataSource = new DruidDataSource();
//    }
    private DataSource dataSource;

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public void setProperties(final Properties props) {
        try {
            this.dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (final RuntimeException e) {
            throw e;
        } catch (final Exception e) {
            throw new RuntimeException("init datasource error", e);
        }
    }
}
