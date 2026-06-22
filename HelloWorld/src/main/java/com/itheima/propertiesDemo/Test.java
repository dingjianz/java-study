package com.itheima.propertiesDemo;

import java.util.Map;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        /*
        properties：属性集合对象，继承自Hashtable<K,V>

        是一个双列集合，拥有 Map 集合所有的特点
        有一些特有的方法可以把集合中的数据，按照键值对的形式写到配置文件当中
        也可以把配置文件中的数据，读取到集合中来
         */

        // 1.创建集合的对象
        Properties prop = new Properties();

        // 2.添加数据
        // 细节：虽然我们可以往集合中添加任意类型的数据，但是Properties集合中，键和值通常是字符串
        /*
        put(K, V)                     继承自 Hashtable<Object, Object>       ❌  不推荐
        setProperty(String, String)   Properties 自己的方法  String, String   ✅  推荐
         内部会做强转为 String,如果你用 put 放了非字符串类型,取值时会出问题
         */
        prop.put("username", "zhangsan");
        prop.setProperty("age", "18");

        for (Object key : prop.keySet()) {
            Object value = prop.get(key);
            System.out.println(key + " = " + value);
        }
        // 3.把集合中的数据写到文件中
//        prop.store();
    }
}
