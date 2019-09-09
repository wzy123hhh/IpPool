package per.ippool.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import java.util.ResourceBundle;

public class sqlcon {

    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    private static BasicDataSource bsource= new BasicDataSource();
    private static QueryRunner queryRunner = new QueryRunner(bsource);

    static{
        //此对象是用于加载properties文件数据的
        ResourceBundle rb = ResourceBundle.getBundle("sql");
        driverClass = rb.getString("driverClass");
        url = rb.getString("url");
        username = rb.getString("name");
        password = rb.getString("password");

        //连接数据库
        bsource.setDriverClassName(driverClass);
        bsource.setUrl(url);
        bsource.setUsername(username);
        bsource.setPassword(password);

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static QueryRunner getQueryRunner(){
        return queryRunner;
    }
}
