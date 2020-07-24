package com.abhi41.recyclerviewadddelete;

public interface ItemClickListener {
    public void OnItemClick(int position, int isSelected);
    public void OnItemremove(int position, int isSelected);
}
