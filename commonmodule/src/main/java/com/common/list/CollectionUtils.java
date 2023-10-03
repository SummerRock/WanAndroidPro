package com.common.list;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    public static <T> boolean isListEmpty(@Nullable List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <T> int getListSize(List<T> list) {
        return list != null ? list.size() : 0;
    }

    @NonNull
    public static <T> List<T> getListOrDefault(List<T> inputList) {
        return inputList != null ? inputList : new ArrayList<>();
    }
}
