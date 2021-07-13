package com.professional.paginationsample.common;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager linearLayoutManager;
    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItemPosition;
    private boolean isAtLastItem;
    private boolean isNotAtBeginning;

    public PaginationScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = linearLayoutManager.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        isAtLastItem = (visibleItemCount + firstVisibleItemPosition) >= totalItemCount;
        isNotAtBeginning = firstVisibleItemPosition >= 0;

        if (!isLoading() && !isLastPage()) {
            if (isAtLastItem && isNotAtBeginning && totalItemCount >= getPageSize()) {
                loadMoreItems();

            }
        }

    }

    protected abstract void loadMoreItems();

    public abstract int getTotalPageCount();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    public abstract int getPageSize();
}
