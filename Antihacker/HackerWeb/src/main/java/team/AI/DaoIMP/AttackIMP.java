package team.AI.DaoIMP;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import team.AI.Dao.AttackDao;
import team.AI.bean.InfoBean;
import team.AI.utils.DBUtiles;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttackIMP implements AttackDao {

    @Override
    public ArrayList<InfoBean> getAllInfo() {
        QueryRunner query = new QueryRunner(DBUtiles.getDataSource());
        InfoBean info = null;
        ArrayList<InfoBean> infoBeans = new ArrayList<>();
        try {
            ArrayList<Object[]> objects = (ArrayList<Object[]>) query.query("select * from redhat", new ArrayListHandler());

            for (Object[] object : objects) {
                info = new InfoBean(object[1].toString(),object[2].toString(),object[3].toString());
                info.setImageurl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3638429004,1717840478&fm=26&gp=0.jpg");
                info.setStatu("正常");

                infoBeans.add(info);
            }

            return infoBeans;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Object[]> getAllInfoss() {
        QueryRunner query = new QueryRunner(DBUtiles.getDataSource());
        InfoBean info = null;

        try {
            ArrayList<Object[]> objects = (ArrayList<Object[]>) query.query("select title,imageurl,url,times,content,status from redhat limit 100", new ArrayListHandler());

            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        AttackIMP attackIMP = new AttackIMP();
        String s = JSONObject.toJSONString(attackIMP.getAllInfo());
        System.out.println(s);
    }
}
