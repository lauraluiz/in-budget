package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.utils.Array;

public class ItemQueryResult {
    private int offset;
    private int total;
    private int count;
    private Array<Item> results;

    public ItemQueryResult() {
    }

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public Array<Item> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ItemQueryResult{" +
                "offset=" + offset +
                ", total=" + total +
                ", count=" + count +
                ", results=" + results +
                '}';
    }
}
