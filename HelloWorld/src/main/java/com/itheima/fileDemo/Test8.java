package com.itheima.fileDemo;

import java.io.File;
import java.util.HashMap;

public class Test8 {
    public static void main(String[] args) {
        /*
          需求：统计一个文件夹中的每种文件的个数并打印。（忽略子文件夹）
               打印格式如下：
                           txt:3个
                           doc:4个
                           jpg:6个

                           计数器 File 递归
         */
        File src = new File("/Users/lijiaqi/Documents/Java学习/my-project/HelloWorld/src/com/itheima");
        HashMap<String, Integer> count = getCount(src);
        System.out.println(count);
    }

    public static HashMap<String, Integer> getCount(File src) {
        HashMap<String, Integer> map = new HashMap<>();
        File[] files = src.listFiles();
        if (files == null) {
            return map;
        }
        for (File f : files) {
            if (f.isFile()) {
                String name = f.getName();
                String[] arr = name.split("\\.");
                if (arr.length >= 2) {
                    String ext = arr[arr.length - 1];
                    if (map.containsKey(ext)) {
                        // 存在
                        int count = map.get(ext);
                        map.put(ext, count + 1);
                    } else {
                        // 不存在
                        map.put(ext, 1);
                    }
                }
            } else {
                // 判断 如果是文件夹 递归
                HashMap<String, Integer> sonMap = getCount(f);
                for (String key : sonMap.keySet()) {
                    if (map.containsKey(key)) {
                        int count = map.get(key);
                        map.put(key, count + sonMap.get(key));
                    } else {
                        map.put(key, sonMap.get(key));
                    }
                }
            }
        }
        return map;
    }
}
