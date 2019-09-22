package team.AI.bean;

public class InfoBean {

    private String title;
    private String imageurl;
    private String content;
    private String times;
    private String statu;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public InfoBean() {
    }

    public InfoBean(String title, String content, String times) {
        this.title = title;
        this.content = content;
        this.times = times;
    }

    public InfoBean(String title, String imageurl, String content, String times, String statu) {
        this.title = title;
        this.imageurl = imageurl;
        this.content = content;
        this.times = times;
        this.statu = statu;
    }
}
