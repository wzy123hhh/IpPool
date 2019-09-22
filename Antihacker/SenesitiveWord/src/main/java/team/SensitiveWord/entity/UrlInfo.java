package team.SensitiveWord.entity;

public class UrlInfo {

    private String url;
    private String content;
    private String hits;
    private boolean Violation;
    private int type;

    @Override
    public String toString() {
        return "UrlInfo{" +
                "url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", hits=" + hits +
                ", Violation=" + Violation +
                ", type=" + type +
                '}';
    }

    public UrlInfo(String url, String content, String hits, boolean violation, int type) {
        this.url = url;
        this.content = content;
        this.hits = hits;
        Violation = violation;
        this.type = type;
    }

    public UrlInfo(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public boolean isViolation() {
        return Violation;
    }

    public void setViolation(boolean violation) {
        Violation = violation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
