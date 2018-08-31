package com.company.project.model;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class MyPageInfo<T> {
    private int currentPage;
    private int totalPages;
    private boolean hasNextPage;
    private List<?> list;

    public MyPageInfo(PageInfo<?> pageInfo) {
        if (pageInfo == null) {
            list = new ArrayList<>();
            return;
        }
        try {
            this.totalPages = pageInfo.getPages();
            this.hasNextPage = pageInfo.isHasNextPage();
            this.currentPage = pageInfo.getPageNum();
            this.list = pageInfo.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
