package per.crawler.test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

public class test {
    private static BasicDataSource bSource = new BasicDataSource();
    private static QueryRunner qRunner = new QueryRunner(bSource);

    static {
        //连接数据库
        bSource.setDriverClassName("com.mysql.jdbc.Driver");
        bSource.setUrl("jdbc:mysql://10.128.186.48:3306/AI");
        bSource.setUsername("root");
        bSource.setPassword("ke188188");

        //最大连接数
//        bSource.setMaxActive(10);
        //最小空闲连接数
        bSource.setMinIdle(5);
        //最大空闲连接
        bSource.setMaxIdle(10);
        //初始化连接数
        bSource.setInitialSize(2);

    }
        /**
         *
         * @return
         */
        private static DataSource getConnection() {
            return bSource;
        }

        /**
         * 获取查询
         * @return QueryRunner
         */
        public static QueryRunner  getQuery() {
            return qRunner;
        }

    public static void main(String[] args) throws SQLException {
        Object insert = (Object) qRunner.insert("insert into user (id,email,name,password,phone) values(1,'319732708@qq.com','王智源','123456','17856002909')", new ScalarHandler<Integer>());
        System.out.println(insert);
    }
}
