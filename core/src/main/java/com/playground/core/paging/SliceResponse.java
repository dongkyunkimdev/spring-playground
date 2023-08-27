package com.playground.core.paging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@NoArgsConstructor
public class SliceResponse<T> {

    private List<T> content;
    private long page;
    private int size;
    private boolean hasPrevious;
    private boolean hasNext;

    public SliceResponse(List<T> content, long page, int size, boolean hasPrevious, boolean hasNext) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
    }

    public static <T> SliceResponse<T> of(Slice<T> slice) {
        return new SliceResponse<>(
                slice.getContent(),
                slice.getNumber(),
                slice.getNumberOfElements(),
                slice.hasPrevious(),
                slice.hasNext());
    }

}
