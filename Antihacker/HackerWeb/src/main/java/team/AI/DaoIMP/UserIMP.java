package team.AI.DaoIMP;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import team.AI.Dao.UserDao;
import team.AI.bean.UserBean;
import team.AI.utils.DBUtiles;

import java.sql.SQLException;

public class UserIMP implements UserDao {
    /*
        用户登陆，判断数据库中是否有此用户
    */
    public UserBean login(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "select * from user where (email='" + userBean.getEmail() + "' and password='" + userBean.getPassword() + "') or (phone='" + userBean.getPhone() + "' and password='" + userBean.getPassword() + "')";
        try {
            UserBean bean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class));
            if (bean != null) {
                return bean;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        用户注册
    */
    public Boolean reg(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "insert user (name,email,phone,password)values(?,?,?,?)";
        Object object[] = {userBean.getName(), userBean.getEmail(), userBean.getPhone(), userBean.getPassword()};
        try {
            int insert = 0;
            insert = runner.update(sql, object);
            if (insert != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        判断手机号和邮箱是否存在
    */
    public Boolean isExistPhoneAndEmail(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "select * from user where phone='" + userBean.getPhone() + "' or email='" + userBean.getEmail() + "'";
        try {
            Object[] objects = runner.query(sql, new ArrayHandler());
            if (objects.length == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        以邮箱查找用户的姓名
    */
    public UserBean emailFindPhone(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        String sql = "select * from user where email='" + userBean.getEmail() + "'";
        try {
            UserBean bean = runner.query(sql, new BeanHandler<UserBean>(UserBean.class));
            if (bean != null) {
                return bean;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        通过邮箱修改用户的密码
    */
    public int emailToUpdatePWD(UserBean userBean) {
        QueryRunner runner = new QueryRunner(DBUtiles.getDataSource());
        Object object[]={userBean.getPassword(),userBean.getEmail()};
        String sql = "update user set password=? where email=? ";
        try {
            int update = runner.update(sql, object);
            if(update!=0){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        UserBean userBean = new UserBean();
        //userBean.setPhone("");
        //userBean.setEmail("");
        //userBean.setPhone("17856002909");
        userBean.setPassword("123456");
        userBean.setEmail("319732708@qq.com");

        //       userBean.setPassword("123456");
//        userBean.setName("李梦可");
//        userBean.setEmail("1583214829@qq.com");
//        userBean.setPhone("17856002383");
//        userBean.setPassword("123456");
//
        UserIMP loginIMP = new UserIMP();
//        loginIMP.reg(userBean);

        int pwd = loginIMP.emailToUpdatePWD(userBean);

    }
}
