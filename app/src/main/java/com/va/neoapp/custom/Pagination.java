package com.va.neoapp.custom;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Pagination {

    private int default_initialPageNumber = 1;
    private int default_defaultPageSize = 10;

    private int initialPageNumber = 1;
    //    private int lastPageNumber = -1;
    private int defaultPageSize = 10;
    private int totalRecords = 0;
    private boolean stop_pagination;
    private boolean isScrolled = false;

    private RecyclerView recyclerView;
    private PaginationListener paginationListener;

    public Pagination(int initialPageNumber, int defaultPageSize, RecyclerView recyclerView, PaginationListener paginationListener) {
        this.initialPageNumber = initialPageNumber;
        this.defaultPageSize = defaultPageSize;
        this.default_initialPageNumber = initialPageNumber;
        this.default_defaultPageSize = defaultPageSize;
        this.recyclerView = recyclerView;
        this.paginationListener = paginationListener;

        setListener();
    }

    public int getInitialPageNumber() {
        return initialPageNumber;
    }

    public int getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultValues() {
        this.initialPageNumber = default_initialPageNumber;
        this.defaultPageSize = default_defaultPageSize;
        stop_pagination = true;
        isScrolled = false;
        paginationListener.pageToCall(initialPageNumber, defaultPageSize);
    }


    private void setListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItemPosition = 0;
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                } else if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    firstVisibleItemPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                }

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 2&& firstVisibleItemPosition >= 0) {
                    if (!stop_pagination && !isScrolled) {
                        initialPageNumber++;
                        isScrolled = true;
                        paginationListener.pageToCall(initialPageNumber, defaultPageSize);
                    }
                }
            }
        });
    }

    public void stopPagination(int size) {
        stop_pagination = size % defaultPageSize != 0 || defaultPageSize == getTotalRecords();
    }

    public void setIsScrolled(boolean isScrolled) {
        this.isScrolled = isScrolled;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public interface PaginationListener {
        void pageToCall(int pageNumber, int PageSize);
    }

}
