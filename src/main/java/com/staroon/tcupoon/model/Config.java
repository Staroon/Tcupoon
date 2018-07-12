package com.staroon.tcupoon.model;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/21
 * Time: 9:00
 */
public class Config {

    int copyUrl;
    int copyMdUrl;
    int noCopy;
    String secretId;
    String secretKey;
    String bucketName;
    String appId;
    String region;
    String cosPath;

    public int getCopyUrl() {
        return copyUrl;
    }

    public void setCopyUrl(int copyUrl) {
        this.copyUrl = copyUrl;
    }

    public int getCopyMdUrl() {
        return copyMdUrl;
    }

    public void setCopyMdUrl(int copyMdUrl) {
        this.copyMdUrl = copyMdUrl;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCosPath() {
        return cosPath;
    }

    public void setCosPath(String cosPath) {
        this.cosPath = cosPath;
    }

    public int getNoCopy() {
        return noCopy;
    }

    public void setNoCopy(int noCopy) {
        this.noCopy = noCopy;
    }

    @Override
    public String toString() {
        return "Config{" +
                "\n  copyUrl=" + copyUrl +
                "\n, copyMdUrl=" + copyMdUrl +
                "\n, noCopy=" + noCopy +
                "\n, secretId='" + secretId + '\'' +
                "\n, secretKey='" + secretKey + '\'' +
                "\n, bucketName='" + bucketName + '\'' +
                "\n, appId='" + appId + '\'' +
                "\n, region='" + region + '\'' +
                "\n, cosPath='" + cosPath + '\'' +
                '}';
    }
}
