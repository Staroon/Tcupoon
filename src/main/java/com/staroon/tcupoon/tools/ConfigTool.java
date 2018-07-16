package com.staroon.tcupoon.tools;

import com.staroon.tcupoon.model.Config;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/21
 * Time: 8:21
 */
public class ConfigTool {

    private static Properties defaultConfig = new Properties();
    private static Logger logger=Logger.getLogger(ConfigTool.class);

    /**
     *   D:\Work\WorkSpace\Tcupoon\target
     * @return TcupHome
     */
    private static String getConfigPath() {
        Commons commons = new Commons();
        String tcupHome = commons.getTcupHome();
        return tcupHome + "/conf/config.properties";
    }

    public static Config getConfig() {
        Config tcupConfig = new Config();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getConfigPath()));
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

    public static void writeConfig(Config tcupConfig) {
        try {
            OutputStream writeConfig = new FileOutputStream(getConfigPath());

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
        Config tcupConfig = getConfig();
        System.out.println(tcupConfig.toString());
        tcupConfig.setCosPath("work");
        writeConfig(tcupConfig);
    }
}
