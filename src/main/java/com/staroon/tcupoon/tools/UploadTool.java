package com.staroon.tcupoon.tools;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.staroon.tcupoon.model.Config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    public static String uploadFile(Config tcupConfig, String localFilePath) {

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
                generateString() + "." + fileExtrend) : ("/" + tcupConfig.getCosPath() + "/" + today + "/" +
                generateString() + "." + fileExtrend);

        // 生成cos客户端
        COSClient cosClient = getCosClient(tcupConfig);

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

    public static COSClient getCosClient(Config tcupConfig) {

        COSCredentials cred = new BasicCOSCredentials(tcupConfig.getSecretId(), tcupConfig.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));

        return new COSClient(cred, clientConfig);
    }

    public static Map<String,String> getRegions(){
        Map<String,String> regions = new HashMap<>();

        regions.put("ap-beijing-1","北京一区（华北）");
        regions.put("ap-beijing","北京");
        regions.put("ap-shanghai","上海（华东）");
        regions.put("ap-guangzhou","广州（华南）");
        regions.put("ap-chengdu","成都（西南）");
        regions.put("ap-chongqing","重庆");
        regions.put("ap-singapore","新加坡");
        regions.put("ap-hongkong","香港");
        regions.put("na-toronto","多伦多");
        regions.put("eu-frankfurt","法兰克福");
        regions.put("ap-mumbai","孟买");
        regions.put("ap-seoul","首尔");
        regions.put("na-siliconvalley","硅谷");
        regions.put("na-ashburn","弗吉尼亚");
        regions.put("ap-bangkok","曼谷");
        regions.put("eu-moscow","莫斯科");
        regions.put("ap-tokyo","东京");

        return regions;
    }
    public static void main(String[] args) {

        Config tcupConfig = getConfig();
        System.out.println(tcupConfig.toString());

        // 待上传文件路径
        String localFilePath = "D:/User/Pictures/ICO/关机图标ico.ico";

        // 执行上传文件程序
        uploadFile(tcupConfig, localFilePath);
    }
}
