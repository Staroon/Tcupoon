package com.staroon.tcupoon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018-07-11
 * Time: 17:23:34
 * To change this template use File | Settings | File Templates.
 */
public class Urls {

    private final StringProperty id;
    private final StringProperty url;
    private final StringProperty origPath;
    private final StringProperty uploadTime;

    public Urls() {
        this("1", null, null, null);
    }

    public Urls(String id, String url, String origPath, String uploadTime) {
        this.id = new SimpleStringProperty(id);
        this.url = new SimpleStringProperty(url);
        this.origPath = new SimpleStringProperty(origPath);
        this.uploadTime = new SimpleStringProperty(uploadTime);
    }

    public Urls(String url, String origPath, String uploadTime) {
        this.id = new SimpleStringProperty("1");
        this.url = new SimpleStringProperty(url);
        this.origPath = new SimpleStringProperty(origPath);
        this.uploadTime = new SimpleStringProperty(uploadTime);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getOrigPath() {
        return origPath.get();
    }

    public StringProperty origPathProperty() {
        return origPath;
    }

    public void setOrigPath(String origPath) {
        this.origPath.set(origPath);
    }

    public String getUploadTime() {
        return uploadTime.get();
    }

    public StringProperty uploadTimeProperty() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime.set(uploadTime);
    }

    @Override
    public String toString() {
        return "Urls{" +
                "id=" + id +
                ", url=" + url +
                ", origPath=" + origPath +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
