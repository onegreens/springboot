package com.example.demo.timer;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class MinuteData {

    private Date time;

    private List<String> dataList = new ArrayList<>();

    private Map<String, Map<String, Integer>> result = new HashMap<>();

    private Map<String, String> data = new HashMap<>();

    public MinuteData() {
    }

    public MinuteData(List<String> dataList) {
        this.time = new Date();
        this.dataList = dataList;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public Map<String, Map<String, Integer>> getResult() {
        return result;
    }

    public void setResult(Map<String, Map<String, Integer>> result) {
        this.result = result;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void initData() {
        for (String str : getDataList()) {
            String[] strs = str.split("---");
            String key = strs[0];
            String value = strs[1];
            saveData(key, value);
        }
        over();
    }

    public void saveData(String key, String value) {
        if (result.get(key) == null) {
            Map<String, Integer> count = new HashMap<>();
            count.put(value, 1);
            result.put(key, count);
        } else {
            Map<String, Integer> count = result.get(key);
            if (count.get(value) == null) {
                count.put(value, 1);
            } else {
                count.put(value, count.get(value) + 1);
            }
            result.put(key, count);
        }

    }

    public void over() {
        for (String key : result.keySet()) {
            Map<String, Integer> this_value = result.get(key);
            Set<String> values = new HashSet<>();
            int max = 0;
            for (String valuekey : this_value.keySet()) {
                int count = this_value.get(valuekey);
                if (count > max) {
                    max = count;
                    values.clear();
                    values.add(valuekey);
                } else if (count == max) {
                    values.add(valuekey);
                }
            }

            data.put(key, JSON.toJSON(values).toString());
        }

    }
}
