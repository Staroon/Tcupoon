package com.staroon.tcupoon.tools;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.staroon.tcupoon.model.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.staroon.tcupoon.tools.COSTool.getCosClient;
import static com.staroon.tcupoon.tools.ConfigTool.getConfig;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/16
 * Time: 15:34
 */
public class UploadTool {

    /**
     * 返回一个定长(12位)的随机字符串(只包含大小写字母、数字)
     *
     * @return 随机字符串
     */
    private static String generateString() {
        final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 上传文件到指定Bucket的指定目录
     *
     * @param tcupConfig    配置属性
     * @param localFilePath 待上传文件路径
     * @return 文件在COS上的外链"url: <bucketname>-<APPID>.cos.<region>.myqcloud.com + cosPath"
     */
    public static String uploadFile(Config tcupConfig, String localFilePath) throws Exception{

        // 获取当前年月日
        Date now = new Date();
        String today = new SimpleDateFormat("yyyyMMdd").format(now);

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        File localFile = new File(localFilePath);

        if (!localFile.exists()) {
            throw new FileNotFoundException("待上传文件不存在！");
        }

        // 获取文件名(含后缀)
        String fullFileName = localFile.getName();
        // 获取文件后缀
        String fileExtrend = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
        // 生成文件路径
        String cosFileName = tcupConfig.getCosPath().equals("") ? ("/" + today + "/" +
                generateString() + "." + fileExtrend) : ("/" + tcupConfig.getCosPath() + "/" + today + "/" +
                generateString() + "." + fileExtrend);

        // 生成cos客户端
        COSClient cosClient = getCosClient(tcupConfig, false);

        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = tcupConfig.getBucketName() + "-" + tcupConfig.getAppId();

        PutObjectRequest objRequest = new PutObjectRequest(bucketName, cosFileName, localFile);
        cosClient.putObject(objRequest);

        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
        // <bucketname>-<APPID>.cos.<region>.myqcloud.com + cosPath
        String outUrl = "https://" + tcupConfig.getBucketName() + "-" + tcupConfig.getAppId() +
                ".cos." + tcupConfig.getRegion() + ".myqcloud.com" + cosFileName;

        return outUrl;
    }

    public static void main(String[] args) {

        Config tcupConfig = getConfig();
        System.out.println(tcupConfig.toString());

        // 待上传文件路径
//        String localFilePath = "D:/User/Pictures/ICO/关机图标ico.ico";
        String localFilePath = "C:/Users/hnzs/Pictures/fluidicon.png";

        // 执行上传文件程序
        try {
            System.out.println(uploadFile(tcupConfig, localFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
