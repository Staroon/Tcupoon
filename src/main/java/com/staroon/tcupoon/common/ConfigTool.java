package com.staroon.tcupoon.common;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/21
 * Time: 8:21
 */
public class ConfigTool {
    String configFilePath = this.getClass().getClassLoader().getResource("").getPath() + "../../conf/config.properties";
    Properties defaultConfig = new Properties();

    public Config getConfig() {
        Config tcupConfig = new Config();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(configFilePath));
            defaultConfig.load(bufferedReader);
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int copyUrl = new Integer(defaultConfig.getProperty("copyUrl"));
        int copyMdUrl = new Integer(defaultConfig.getProperty("copyMdUrl"));
        int noCopy = copyUrl == copyMdUrl ? 1 : 0;

        tcupConfig.setCopyUrl(copyUrl);
        tcupConfig.setCopyMdUrl(copyMdUrl);
        tcupConfig.setSecretId(defaultConfig.getProperty("secretId"));
        tcupConfig.setSecretKey(defaultConfig.getProperty("secretKey"));
        tcupConfig.setBucketName(defaultConfig.getProperty("bucketName"));
        tcupConfig.setAppId(defaultConfig.getProperty("appId"));
        tcupConfig.setRegion(defaultConfig.getProperty("region"));
        tcupConfig.setCosPath(defaultConfig.getProperty("cosPath"));
        tcupConfig.setNoCopy(noCopy);

        return tcupConfig;
    }


    public void writeConfig(Config tcupConfig) {
        try {
            OutputStream writeConfig = new FileOutputStream(configFilePath);

            defaultConfig.setProperty("secretId", tcupConfig.getSecretId());
            defaultConfig.setProperty("secretKey", tcupConfig.getSecretKey());
            defaultConfig.setProperty("appId", tcupConfig.getAppId());
            defaultConfig.setProperty("bucketName", tcupConfig.getBucketName());
            defaultConfig.setProperty("region", tcupConfig.getRegion());
            defaultConfig.setProperty("cosPath", tcupConfig.getCosPath());
            defaultConfig.setProperty("copyUrl", String.valueOf(tcupConfig.getCopyUrl()));
            defaultConfig.setProperty("copyMdUrl", String.valueOf(tcupConfig.getCopyMdUrl()));

            defaultConfig.store(writeConfig, "Tcupoon Config File");
            writeConfig.flush();
            writeConfig.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConfigTool configTool = new ConfigTool();
        Config tcupConfig = configTool.getConfig();
        System.out.println(tcupConfig.toString());
        tcupConfig.setCosPath("blog/test");
        configTool.writeConfig(tcupConfig);
    }
}
