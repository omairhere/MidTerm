package com.example.midterm;

public class News {

    int id;
    String url;
    String descript;
    String header;
    String ref;

    public News(int id, String url, String descript, String header,String ref) {
        this.id = id;
        this.url = url;
        this.descript = descript;
        this.header = header;
        this.ref=ref;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescript() {
        return descript;
    }

    public String getHeader() {
        return header;
    }

    public String getRef() {
        return ref;
    }
}
