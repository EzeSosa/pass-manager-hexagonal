package com.esosa.pass_manager_hexagonal.domain.extras;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomPage<T> {

    private final List<T> content;
    private final long totalElements;
    private final int totalPages;
    private final int pageNumber;

    public CustomPage(List<T> content, long totalElements, int totalPages, int pageNumber) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public <U> CustomPage<U> map(Function<T, U> mapper) {
        List<U> newContent = content.stream()
                .map(mapper)
                .collect(Collectors.toList());

        return new CustomPage<>(newContent, totalElements, totalPages, pageNumber);
    }

    public static CustomPageBuilder builder() {
        return new CustomPageBuilder();
    }

    public static class CustomPageBuilder<T> {

        private List<T> content;
        private long totalElements;
        private int totalPages;
        private int pageNumber;

        private CustomPageBuilder() {}

        public CustomPageBuilder content(List<T> content) {
            this.content = content;
            return this;
        }

        public CustomPageBuilder totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public CustomPageBuilder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public CustomPageBuilder pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public CustomPage build() {
            return new CustomPage<T>(content, totalElements, totalPages, pageNumber);
        }

    }

}