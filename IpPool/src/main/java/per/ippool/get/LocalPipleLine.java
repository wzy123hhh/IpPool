package per.ippool.get;

import per.ippool.verify.HostPort;
import per.ippool.verify.VerifyProxy;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class LocalPipleLine implements Pipeline {

    public void process(ResultItems resultItems, Task task) {

        VerifyProxy verify = new VerifyProxy();
        Map<HostPort,Boolean> hostPortBooleanMap = null;

        List<String> ips = resultItems.get("ip");
        List<String> ports = resultItems.get("port");
        List<HostPort> hostPortList = new ArrayList<HostPort>();
        HostPort temp = null;

        for (int i = 0; i <ips.size() ; i++) {
            temp =new HostPort(ips.get(i),ports.get(i));
            hostPortList.add(temp);
        }

        hostPortBooleanMap  = verify.verifyProxy(hostPortList);

        hostPortBooleanMap.forEach(new BiConsumer<HostPort, Boolean>() {
            public void accept(HostPort hostPort, Boolean aBoolean) {
                if(aBoolean==true)
                System.out.println(hostPort.toString());
            }
        });


    }
}
