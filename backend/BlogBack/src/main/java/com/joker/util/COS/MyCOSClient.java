package com.joker.util.COS;

import com.joker.util.COSClientUtil;
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: Joker
 * @name: MyCOSClient
 * @Date: 2020/7/20 15:55
 * @Describe: The Cos client to use.
 **/
public class MyCOSClient {

    public COSClient client = new COSClientUtil().getClient();

    public void selectBucket() {

        //查询当前用户的所有bucket
        List<Bucket> buckets = client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName());
            System.out.println(bucket.getLocation());
        }
    }

    //上传文件
    public PutObjectResult uploadFile(File file, String objectKey, String bucketName) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, file);
        PutObjectResult putObjectResult = client.putObject(putObjectRequest);

        return putObjectResult;
    }

    public String getFileContainer(String objectKey, String bucketName) throws IOException {
        //获取该文件的流
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,objectKey);
        COSObject cosObject = client.getObject(getObjectRequest);
        COSObjectInputStream cosInputStream = cosObject.getObjectContent();
        StringBuilder result = new StringBuilder();
        byte[] bytes = new byte[cosInputStream.available()];
        int i = 0;
        while ((i = cosInputStream.read(bytes))!=-1) {
            result.append(new String(bytes, "UTF-8"));
            bytes = new byte[cosInputStream.available()];
        }
        return result.toString();
    }

//    //下载文件
//    public ObjectMetadata downloadFile(String objectKey, String localpath) {
//
//        //指定要传入哪个bucket
//        String bucketName = "article-1259719447";
//
//        //method1:流模式
////		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,objectKey);
////		COSObject cosObject = client.getObject(getObjectRequest);
////		COSObjectInputStream cosInputStream = cosObject.getObjectContent();
//
//
//        //method2:直接下载到本地
////		String outputFliePath = "C:\\Users\\JokEer\\Desktop\\file";
//        File downFile = new File(localpath+"\\"+objectKey);
//        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,objectKey);
//        ObjectMetadata dowObjectMeta = client.getObject(getObjectRequest, downFile);
//
//        return dowObjectMeta;
//
//    }

    public void updateFile(String oldKey, String newKey) {

        String bucketName = "article-1259719447";

        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName,oldKey);
        COSObject cosObject = client.getObject(getObjectRequest);
        COSObjectInputStream cosInputStream = cosObject.getObjectContent();
        deleteFile(oldKey);
        client.putObject(bucketName, newKey, cosInputStream, cosObject.getObjectMetadata());

    }

    public void deleteFile(String objectKey) {

        //指定要操作哪个bucket
        String bucketName = "article-1259719447";
        client.deleteObject(bucketName, objectKey);

    }

    public void select() {
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = "examplebucket-1250000000";
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
        // prefix表示列出的object的key以prefix开始
        listObjectsRequest.setPrefix("images/");
        // deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        do {
            try {
                objectListing = client.listObjects(listObjectsRequest);
            } catch (CosServiceException e) {
                e.printStackTrace();
                return;
            } catch (CosClientException e) {
                e.printStackTrace();
                return;
            }
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();

            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径key
                String key = cosObjectSummary.getKey();
                // 文件的etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();

            }

            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
    }

    public String getUrlByKey(String key) {

        String url = "https://filebed-1259719447.cos.ap-chengdu.myqcloud.com/"+key;
        return url;

    }

}
