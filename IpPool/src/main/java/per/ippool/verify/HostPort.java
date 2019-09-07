package per.ippool.verify;

public class HostPort {
    private String ip;
    private String port;
    private boolean vaild;

    public HostPort() {
    }

    public HostPort(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public HostPort(String ip, String port, boolean vaild) {
        this.ip = ip;
        this.port = port;
        this.vaild = vaild;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isVaild() {
        return vaild;
    }

    public void setVaild(boolean vaild) {
        this.vaild = vaild;
    }

    @Override
    public String toString() {
        return "HostPort{" +
                "ip='" + ip + '\'' +
                ", port='" + port + "}";
    }
}
