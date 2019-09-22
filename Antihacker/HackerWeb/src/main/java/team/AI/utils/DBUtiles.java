package team.AI.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@SuppressWarnings("static-access")
public class DBUtiles {
    private static DruidDataSourceFactory dataSourceFactory = null;
    @SuppressWarnings("unused")
    private static DataSource dataSource = null;

    static {
        InputStream iStream = DBUtiles.class.getClassLoader().getResourceAsStream("database.properties");
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

    public static void main(String[] args) {
        DBUtiles dButils = new DBUtiles();
        dButils.getConnection();
        QueryRunner queryRunner = new QueryRunner(DBUtiles.getDataSource());
        try {
            Object[] aList = queryRunner.query("select * from user", new ArrayHandler());
            for (Object object : aList) {
                System.out.println(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

