package com.staroon.tcupoon.tools;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.region.Region;
import com.staroon.tcupoon.model.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018-09-11
 * Time: 15:13:43
 * To change this template use File | Settings | File Templates.
 */
public class COSTool {

    public static COSClient getCosClient(Config tcupConfig, boolean isNoRegion) {
        COSCredentials cred = new BasicCOSCredentials(tcupConfig.getSecretId(), tcupConfig.getSecretKey());
        Region region = isNoRegion ? new Region("ap-beijing") : new Region(tcupConfig.getRegion());
        return new COSClient(cred, new ClientConfig(region));
    }

    public static List<String> getBuckets(Config tcupConfig) {
        List<String> buckets = new ArrayList<>();
        COSClient cosClient = getCosClient(tcupConfig, true);
        List<Bucket> bucketList = cosClient.listBuckets();
        for (Bucket bucket : bucketList) {
            buckets.add(bucket.getName());
        }

        cosClient.shutdown();
        return buckets;
    }

    public static Map<String, String> getRegions() {
        Map<String, String> regions = new HashMap<>();

        regions.put("ap-beijing-1", "北京一区（华北）");
        regions.put("ap-beijing", "北京");
        regions.put("ap-shanghai", "上海（华东）");
        regions.put("ap-guangzhou", "广州（华南）");
        regions.put("ap-chengdu", "成都（西南）");
        regions.put("ap-chongqing", "重庆");
        regions.put("ap-singapore", "新加坡");
        regions.put("ap-hongkong", "香港");
        regions.put("na-toronto", "多伦多");
        regions.put("eu-frankfurt", "法兰克福");
        regions.put("ap-mumbai", "孟买");
        regions.put("ap-seoul", "首尔");
        regions.put("na-siliconvalley", "硅谷");
        regions.put("na-ashburn", "弗吉尼亚");
        regions.put("ap-bangkok", "曼谷");
        regions.put("eu-moscow", "莫斯科");
        regions.put("ap-tokyo", "东京");

        return regions;
    }

    public static void main(String[] args) {
        System.out.println(getBuckets(ConfigTool.getConfig()));
    }
}
