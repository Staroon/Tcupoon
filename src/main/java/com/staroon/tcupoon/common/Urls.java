package com.staroon.tcupoon.common;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/7/5
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class Urls {
    int id;
    String url;
    String orig_path;
    String upload_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrig_path() {
        return orig_path;
    }

    public void setOrig_path(String orig_path) {
        this.orig_path = orig_path;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    @Override
    public String toString() {
        return "Urls{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", orig_path='" + orig_path + '\'' +
                ", upload_time='" + upload_time + '\'' +
                '}';
    }

    public Urls(int id, String url, String orig_path, String upload_time) {
        this.id = id;
        this.url = url;
        this.orig_path = orig_path;
        this.upload_time = upload_time;
    }

    public Urls(String url, String orig_path, String upload_time) {
        this.url = url;
        this.orig_path = orig_path;
        this.upload_time = upload_time;
    }
}
