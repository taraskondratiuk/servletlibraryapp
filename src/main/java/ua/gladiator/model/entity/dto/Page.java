package ua.gladiator.model.entity.dto;

import java.util.*;

public class Page<T> {
    private List<T> pageContent;
    private Integer totalPages;
    private Integer pageSize;
    private Integer pageNumber;
    private Integer pageArr[];

    public Page(List<T> pageContent, Integer totalPages, Integer pageSize, Integer pageNumber) {
        this.pageContent = pageContent;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.pageArr = new Integer[totalPages];
        for (int i = 0; i < totalPages; i++) {
            this.pageArr[i] = i + 1;
        }
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer[] getPageArr() {
        return pageArr;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageContent=" + pageContent +
                ", totalPages=" + totalPages +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", pageArr=" + Arrays.toString(pageArr) +
                '}';
    }
}
