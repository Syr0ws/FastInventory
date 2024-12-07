package com.github.syr0ws.craftventory.common.util;

public class IdUtil {

    public static String getItemId(String id) {
        return String.format("item-%s", id);
    }

    public static String getPaginationItemId(String paginationId) {
        return String.format("pagination{%s}-item", paginationId);
    }

    public static String getPaginationPreviousPageItemId(String paginationId) {
        return String.format("pagination{%s}-previous-page", paginationId);
    }

    public static String getPaginationNextPageItemId(String paginationId) {
        return String.format("pagination{%s}-next-page", paginationId);
    }
}
