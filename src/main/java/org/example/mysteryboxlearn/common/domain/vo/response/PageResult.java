package org.example.mysteryboxlearn.common.domain.vo.response;

import java.util.List;

public class PageResult<T> {
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private List<T> content;

    public PageResult() {
    }

    public long getTotalElements() {
        return this.totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public int getSize() {
        return this.size;
    }

    public int getNumber() {
        return this.number;
    }

    public List<T> getContent() {
        return this.content;
    }

    public PageResult<T> setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PageResult<T> setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageResult<T> setSize(final int size) {
        this.size = size;
        return this;
    }

    public PageResult<T> setNumber(final int number) {
        this.number = number;
        return this;
    }

    public PageResult<T> setContent(final List<T> content) {
        this.content = content;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PageResult)) {
            return false;
        } else {
            PageResult<?> other = (PageResult) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getTotalElements() != other.getTotalElements()) {
                return false;
            } else if (this.getTotalPages() != other.getTotalPages()) {
                return false;
            } else if (this.getSize() != other.getSize()) {
                return false;
            } else if (this.getNumber() != other.getNumber()) {
                return false;
            } else {
                Object this$content = this.getContent();
                Object other$content = other.getContent();
                if (this$content == null) {
                    if (other$content != null) {
                        return false;
                    }
                } else if (!this$content.equals(other$content)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageResult;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        long $totalElements = this.getTotalElements();
        result = result * 59 + (int) ($totalElements >>> 32 ^ $totalElements);
        result = result * 59 + this.getTotalPages();
        result = result * 59 + this.getSize();
        result = result * 59 + this.getNumber();
        Object $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        long var10000 = this.getTotalElements();
        return "PageResult(totalElements=" + var10000 + ", totalPages=" + this.getTotalPages() + ", size=" + this.getSize() + ", number=" + this.getNumber() + ", content=" + String.valueOf(this.getContent()) + ")";
    }
}
