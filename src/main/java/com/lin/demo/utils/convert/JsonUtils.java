package com.lin.demo.utils.convert;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
 
public class JsonUtils {
 
    public static String str = "trainid=76&customerid=27&coursename=222222222222222&projectname=SC9820A&typeid=61&sproposaltime=2021-06-28+09%3A55%3A00&proposalcity=%E6%88%90%E9%83%BD&proposaladdress=%E5%A4%A9%E5%BA%9C%E4%B8%89%E8%A1%97&custname=Kreas&custphone=13888888888&traincomment=xxxxxxxx2&description=xxxxx1&srealtime=2021-06-28+10%3A50&realcity=%E4%B8%AD%E5%B1%B1&realaddress=xx&teachername=alex.lv&realteacher=yuanqiang.zhang&trainstatus=0&courseware=33475%2C33494";
 
    public static void main(String[] args) {
        String[] strs = str.split("&");
        JSONObject obj = new JSONObject();
        for (String each : strs) {
            String[] params = each.split("=");
            if (params.length == 1) {
                obj.put(params[0], 0);
            } else if (params.length == 2) {
                obj.put(params[0], URLDecoderString(params[1]));
            }
        }
        System.out.println(obj.toString());
    }
 
    private static String URLDecoderString(String str) {
        if (str == null) {
            return "";
        }
        try {
           return java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}