package com.common.list;

import androidx.annotation.Nullable;

import java.util.List;

public class CollectionUtils {

    public static <T> boolean isListEmpty(@Nullable List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> int getListSize(List<T> list) {
        return list != null ? list.size() : 0;
    }
}
