package nagihan.testapp;


public class News {

    public String title;
    public String url;
    public String img;
    public String detail;

    public News(String title, String url, String img, String detail) {
        this.title = title;
        this.url= url;
        this.img = img;
        this.detail = detail;

    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
