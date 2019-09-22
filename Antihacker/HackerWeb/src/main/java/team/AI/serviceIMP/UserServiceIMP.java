package team.AI.serviceIMP;

import team.AI.DaoIMP.UserIMP;
import team.AI.bean.UserBean;
import team.AI.service.UserService;

public class UserServiceIMP implements UserService {
    UserIMP loginIMP = new UserIMP();

    /*
        用户登陆
    */
    public UserBean login(UserBean userBean) {
        UserBean bean = loginIMP.login(userBean);
        return bean;
    }

    /*
        用户注册
    */
    public Boolean reg(UserBean userBean) {
        return loginIMP.reg(userBean);
    }

    /*
        判断手机号和邮箱是否存在
    */
    public Boolean isExistPhoneAndEmail(UserBean userBean) {
        return loginIMP.isExistPhoneAndEmail(userBean);
    }

    /*
        以邮箱查找用户的姓名
    */
    public UserBean emailFindPhone(UserBean userBean) {
        return loginIMP.emailFindPhone(userBean);
    }

    /*
        通过邮箱修改用户的密码
    */
    public int emailToUpdatePWD(UserBean userBean) {
        return loginIMP.emailToUpdatePWD(userBean);
    }
}
