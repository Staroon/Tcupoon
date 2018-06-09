package com.staroon.tcupoon.common;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/16
 * Time: 15:34
 */
public class UploadTool {

    public static void main(String[] args) {

        ConfigTool configTool = new ConfigTool();
        Config tcupConfig = configTool.getConfig();
        System.out.println(tcupConfig.toString());

        // 待上传文件路径
        String localFilePath = "D:/User/Pictures/ICO/关机图标ico.ico";

        // 执行上传文件程序
        new UploadTool().uploadFile(tcupConfig, localFilePath);
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public String generateString(int length) {
        final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
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
    public String uploadFile(Config tcupConfig, String localFilePath) {

        // 获取当前年月日
        Date now = new Date();
        String today = new SimpleDateFormat("yyyyMMdd").format(now);

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        File localFile = new File(localFilePath);
        // 获取文件名(含后缀)
        String fullFileName = localFile.getName();
        // 获取文件后缀
        String fileExtrend = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
        // 生成文件路径
        String cosFileName = tcupConfig.getCosPath().equals("") ? ("/" + today + "/" +
                generateString(12) + "." + fileExtrend) : ("/" + tcupConfig.getCosPath() + "/" + today + "/" +
                generateString(12) + "." + fileExtrend);

        // 初始化用户身份信息
        COSCredentials cred = new BasicCOSCredentials(tcupConfig.getSecretId(), tcupConfig.getSecretKey());
        // 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(tcupConfig.getRegion()));
        // 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = tcupConfig.getBucketName() + "-" + tcupConfig.getAppId();

        // 检测 Bucket 是否存在
        if (!cosClient.doesBucketExist(bucketName)) {
            cosClient.shutdown();
//            System.out.println("该Bucket不存在");
            return null;
        }

        PutObjectRequest objRequest = new PutObjectRequest(bucketName, cosFileName, localFile);

        PutObjectResult objResult = cosClient.putObject(objRequest);

        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
        // <bucketname>-<APPID>.cos.<region>.myqcloud.com + cosPath
        String outUrl = "https://" + tcupConfig.getBucketName() + "-" + tcupConfig.getAppId() +
                ".cos." + tcupConfig.getRegion() + ".myqcloud.com" + cosFileName;

        return outUrl;
    }
}
