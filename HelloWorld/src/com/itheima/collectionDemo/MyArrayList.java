package com.itheima.collectionDemo;

import java.util.Arrays;

/*
    当我在编写一个类的时候
    如果不确定类型
    那么这个类就可以定义为泛性类
 */
public class MyArrayList<E> {
    Object[] obj = new Object[3];
    int size;

    /*
      E: 表示的是不确定的类型，该类型在类后面已经定义过了
      e： 形参的名字，变量名
     */
    public boolean add(E e) {
        // 检查是否需要扩容
        if (size >= obj.length) {
            // 创建新数组，容量为原来的1.5倍
            int newCapacity = obj.length * 3 / 2 + 1;
            Object[] newArray = Arrays.copyOf(obj, newCapacity);
            obj = newArray;
        }
        obj[size] = e;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) obj[index];
    }

    @Override
    public String toString() {
        return "MyArrayList{" + "obj=" + Arrays.toString(obj) + "，size=" + size + "}";
    }
}
