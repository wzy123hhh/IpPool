package per.ippool.verify;

import redis.clients.jedis.HostAndPort;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

/**
 * 验证代理是否可用
 */
public class VerifyProxy {
    static ExecutorService threads = Executors.newFixedThreadPool(120);

    public VerifyProxy(){

    }

    public Map<HostPort,Boolean> verifyProxy(List<HostPort> hostPortList){
        Map<HostPort,Boolean> hostPortBooleanMap = Collections.synchronizedMap(new HashMap<HostPort, Boolean>());
        CompletionService<Map<HostPort,Boolean>> cs= new ExecutorCompletionService<Map<HostPort, Boolean>>(this.threads);

        for (int i = 0; i < hostPortList.size(); i++) {
            cs.submit(new VerifyProxy.RequestCheck(hostPortList.get(i),hostPortBooleanMap));
        }

        for (int i = 0; i <hostPortList.size() ; i++) {

            try {
                Map var5 = cs.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return hostPortBooleanMap;
    }

    class RequestCheck implements Callable<Map<HostPort, Boolean>> {
        HostPort hostPort = null;
        Map<HostPort, Boolean> map = null;

        public RequestCheck(HostPort hostPort, Map<HostPort, Boolean> map) {
            this.hostPort = hostPort;
            this.map = map;
        }

        public RequestCheck() {
        }

        public Map<HostPort, Boolean> call() throws Exception {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(hostPort.getIp(),Integer.parseInt(hostPort.getPort())),2000);
                this.map.put(this.hostPort, true);
            } catch (IOException e) {
                this.map.put(this.hostPort, false);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return this.map;
        }

    }

}
