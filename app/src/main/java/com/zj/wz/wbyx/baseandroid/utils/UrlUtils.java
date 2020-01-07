package com.zj.wz.wbyx.baseandroid.utils;


import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 * FileName: UrlUtils
 * Author: 曹伟
 * Date: 2019/9/26 14:05
 * Description:
 */

public class UrlUtils {
        public static class UrlEntity {
            /**
             * 基础url
             */
            public String baseUrl;
            /**
             * url参数
             */
            public Map<String, String> params;
        }

        /**
         * 解析url
         *
         * @param url
         * @return
         */
        public static UrlEntity parse(String url) {
            UrlEntity entity = new UrlEntity();
            if (url == null) {
                return entity;
            }
            url = url.trim();
            if (url.equals("")) {
                return entity;
            }
            String[] urlParts = url.split("\\?");
            entity.baseUrl = urlParts[0];
            //没有参数
            if (urlParts.length == 1) {
                return entity;
            }
            //有参数
            String[] params = urlParts[1].split("&");
            entity.params = new HashMap<>();
            for (String param : params) {
                String[] keyValue = param.split("=");
                entity.params.put(keyValue[0], keyValue[1]);
            }
            return entity;
        }



        /**
         * 测试
         *
         * @param args
         */
        public static void main(String[] args) {
            UrlEntity entity = parse("http:\\/\\/testh5.wobianmall.com\\/detail?proObj={\\\"goods_type\\\":\\\"2\\\",\\\"goods_id\\\":51,\\\"warehouse_id\\\":\\\"25\\\"}&warehouse_id=25");
            System.out.println(new Gson().toJson(entity.params.get("proObj")).substring(1,new Gson().toJson(entity.params.get("proObj")).length() - 1).replaceAll("\\\\",""));
            Map<String,String> map = new Gson().fromJson(new Gson().toJson(entity.params.get("proObj")).substring(1,new Gson().toJson(entity.params.get("proObj")).length() - 1).replaceAll("\\\\",""),Map.class);
            System.out.println(map);
        }
    }
