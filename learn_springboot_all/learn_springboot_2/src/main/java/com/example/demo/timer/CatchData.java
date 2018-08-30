package com.example.demo.timer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.*;

/**
 * 接收到数据 将数据存储到dataList中
 * 每分钟将dataList中的数据转移到MinuteDate中的dataList 并且清空dataList
 * 在MinuteDate中 解析dataList为result 计算result为data
 */


public class CatchData {

    private static CatchData this_CatchData;

    private CatchData() {

    }

    public static synchronized CatchData getInstance() {
        if (this_CatchData == null) {
            this_CatchData = new CatchData();
        }
        return this_CatchData;
    }

    //存储接收到数据
    private List<String> dataList = new ArrayList<>();

    //当前分钟接收到数据
    private List<MinuteData> momentDataList = new ArrayList<>();

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public List<MinuteData> getMomentDataList() {
        return momentDataList;
    }

    public void setMomentDataList(List<MinuteData> momentDataList) {
        this.momentDataList = momentDataList;
    }

    public void addData(String value) {
        this.dataList.add(value);
    }

    public void minute() {
        MinuteData md = new MinuteData(getDataList());
        setDataList(new ArrayList<>());
        md.initData();
        this.momentDataList.add(md);
    }


    public static void main(String[] args) {
        boolean isSuccess = false;
        String url ="http://www.sunprocnmfg.com:8002/iot/area/list";

//        String url = "http://localhost:8002/iot/area/list";
        HttpPost post = null;
        Map map = new HashMap();
        map.put("type", "add");
        map.put("no", "aqq123");
        map.put("name", "切割区12");
        map.put("x", "10,20,20,10");
        map.put("y", "30,10,20,20");
        map.put("workShopNo", "w1");
        map.put("baseStation", "10,100,17");
        List list = new ArrayList();
        list.add(map);
        Map data = new HashMap();
        data.put("data", list);

        try {
            HttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Authorization", "Basic bmVvd2F5Oe4lb3dheQ==");
            post.setHeader("stringToSign", "teststringToSign");

            // 构建消息实体
            StringEntity entity = new StringEntity(JSON.toJSONString(data), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            // 从响应对象中获取响应内容
            HttpEntity result = response.getEntity();
            System.out.println( EntityUtils.toString(result));
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                isSuccess = false;
            } else {
                int retCode = 0;
                // 返回码中包含retCode及会话Id
                for (Header header : response.getAllHeaders()) {
                    if (header.getName().equals("retcode")) {
                        retCode = Integer.parseInt(header.getValue());
                    }

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            if (post != null) {
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
