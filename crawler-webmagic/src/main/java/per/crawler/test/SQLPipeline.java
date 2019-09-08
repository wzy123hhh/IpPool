package per.crawler.test;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.SQLException;
import java.util.List;

public class SQLPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {

        List<String> context = resultItems.get("context");
        List<String> url = resultItems.get("url");
        List<String> title = resultItems.get("title");
        List<String> time = resultItems.get("time");

        for (int i = 0; i <title.size() ; i++) {
            System.out.println("context : "+context.get(i)+" ||title : "+title.get(i)+"  ||time :  "+time.get(i));
            try {
                test.getQuery().insert("insert into redhat (title,content,times) values(?,?,?)",new ScalarHandler<Integer>(),
                        new Object[]{title.get(i),context.get(i),time.get(i)});
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
