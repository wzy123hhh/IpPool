package team.SensitiveWord.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SqlUtil {

    private static DruidDataSourceFactory dataSourceFactory = null;
    @SuppressWarnings("unused")
    private static DataSource dataSource = null;
    private static QueryRunner queryRunner=new QueryRunner(dataSource);

    static {
        InputStream iStream = team.SensitiveWord.util.SqlUtil.class.getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        try {
            properties.load(iStream);
            dataSource = dataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static QueryRunner getQueryRunner(){
        if (queryRunner.getDataSource()==null){
            queryRunner=new QueryRunner(getDataSource());
        }
        return queryRunner;
    }

}
