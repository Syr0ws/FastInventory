package com.github.syr0ws.fastinventory.common.pagination;

import com.github.syr0ws.fastinventory.common.CommonContextKeyEnum;

public class PaginationIdUtil {

    public static String getPaginationItemId(String paginationId) {

        if(paginationId == null) {
            throw new IllegalArgumentException("paginationId cannot be null");
        }

        return String.format("%s{%s}-%s", CommonContextKeyEnum.PAGINATION_ID, paginationId, CommonContextKeyEnum.PAGINATION_ITEM.name());
    }
}
