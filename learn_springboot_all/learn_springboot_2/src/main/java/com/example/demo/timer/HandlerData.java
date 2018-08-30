package com.example.demo.timer;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class HandlerData {



    public static void createData() {
        CatchData.getInstance().addData(getKey() + "---" + getValue());
    }

    public static String getKey() {
        Integer num = (int) (Math.random() * 10);
        return "KEY[" + num + "]";
    }

    public static String getValue() {
        Integer num = (int) (Math.random() * 10);
        return "VALUE[" + num + "]";
    }

    public static void handlerData() {
        System.out.println("结算前");
        printData(CatchData.getInstance().getDataList());
        //清除
        CatchData.getInstance().minute();
        System.out.println("getDataList:" + CatchData.getInstance().getDataList().size());

    }

    public static void printData(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }


}
