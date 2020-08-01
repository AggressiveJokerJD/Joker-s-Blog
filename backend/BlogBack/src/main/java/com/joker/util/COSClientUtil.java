package com.joker.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

/**
 * @author: Joker
 * @name: COSClientUtil
 * @Date: 2020/7/20 15:50
 * @Describe: The util class of Cos Client.
 **/
public class COSClientUtil {

    //初始化用户信息
    private static String secretId = "";
    private static String secretKey = "";
    private static COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);

    //设置bucket区域
    private static Region region = new Region("ap-chengdu");
    private static ClientConfig clientConfig = new ClientConfig(region);

    //虽然COSClient线程安全，但这里创建单例的client,为了防止程序资源耗尽
    private static COSClient client = null;

    //使用静态代码块,确保单例且线程安全
    static {
        client = new COSClient(cred, clientConfig);
    }

    //返回单例的COSClient
    public COSClient getClient() {
        return client;
    }


}
