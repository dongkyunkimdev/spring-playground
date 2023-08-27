package com.playground.core.paging;

import org.springframework.data.domain.Slice;

import java.util.List;

public class SliceResponse<T> {

    private final List<T> content;
    private final long page;
    private final int size;
    private final boolean hasNext;

    public SliceResponse(List<T> content, long page, int size, boolean hasNext) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
    }

    public static <T> SliceResponse<T> of(Slice<T> slice) {
        return new SliceResponse<>(
                slice.getContent(),
                slice.getNumber(),
                slice.getNumberOfElements(),
                slice.hasNext());
    }

}
