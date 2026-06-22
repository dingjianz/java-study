package com.itheima.collectionDemo;

import java.util.ArrayList;

public class ListUtil {
    private ListUtil() {
    }

    public static <E> void addAll(MyArrayList<E> list, E... elements) {
        for (E element : elements) {
            list.add(element);
        }
    }
}
