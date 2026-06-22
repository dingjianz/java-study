package com.itheima.collectionDemo;


import java.util.ArrayList;

public class GenericsDemo2 {
    public static void main(String[] args) {
    /*
        泛型不具备继承性，但是数据具备继承性
     */
        ArrayList<Ye> list = new ArrayList<>();
        ArrayList<Fu> list1 = new ArrayList<>();
        ArrayList<Zi> list2 = new ArrayList<>();

        method(list);
        // 报错，因为泛型不具备继承性， method方法中的泛型参数为Ye
        // method(list1);
        // method(list2);

        // 不报错，数据具备继承性
        list.add(new Fu());
        list.add(new Zi());

    }

    public static void method(ArrayList<Ye> list) {
    }
}

class Ye {
}

class Fu extends Ye {
}

class Zi extends Fu {
}