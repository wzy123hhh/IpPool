package per.crawler.test;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.omg.PortableInterceptor.INACTIVE;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MySqlPipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        Iterator<Map.Entry<String, Object>> iterator = resultItems.getAll().entrySet().iterator();

        //临时存储获得的数据
        ArrayList<String> urls = new ArrayList<String>();
        ArrayList<String> titles = new ArrayList<String>();
        ArrayList<String> context = new ArrayList<String>();
        ArrayList<String> times = new ArrayList<String>();

        int title_index = 0;
        int url_index = 0;
        int context_index = 0;
        int time_index = 0;

        ArrayList<String> temp = null;

        while (true) {
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (entry.getValue() instanceof Iterable) {
                    Iterable value = (Iterable) entry.getValue();
                    //获得key,放入对应的list
                    String key = entry.getKey();

                    Iterator sqlvalue = value.iterator();

                    if ("title".equals(key)) {
                        temp = titles;
                        while (sqlvalue.hasNext()){
                            temp.add((String) sqlvalue.next());
                            title_index++;
                        }

                    } else if ("url".equals(key)) {
                        temp = urls;
                        while (sqlvalue.hasNext()){
                            temp.add((String) sqlvalue.next());
                            url_index++;
                        }
                    } else if ("context".equals(key)) {
                        temp = context;
                        while (sqlvalue.hasNext()){
                            temp.add((String) sqlvalue.next());
                            context_index++;
                        }
                    } else if ("time".equals(key)) {
                        temp = times;
                        while (sqlvalue.hasNext()){
                            temp.add((String) sqlvalue.next());
                            time_index++;
                        }
                    }

                }else {

                }


            }
            break;
        }

        for (int i = 0; i < title_index; i++) {

            try {
                test.getQuery().insert("insert into redhat (title,content,times) values(?,?,?)",new ScalarHandler<Integer>(),
                        new Object[]{titles.get(i),context.get(i),times.get(i)});
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }



//        test.getQuery().insertBatch("insert into redhat (title,url,content,times) values(?,?,?,?)",new ScalarHandler<Integer>(),)

    }
}
