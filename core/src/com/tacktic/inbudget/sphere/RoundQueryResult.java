package com.tacktic.inbudget.sphere;

import com.badlogic.gdx.utils.Array;
import com.tacktic.inbudget.sphere.Round;

public class RoundQueryResult {
    private int offset;
    private int total;
    private int count;
    private Array<Round> results;

    public RoundQueryResult() {
    }

    RoundQueryResult(final int offset, final int total, final int count, final Array<Round> results) {
        this.offset = offset;
        this.total = total;
        this.count = count;
        this.results = results;
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

    public Array<Round> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "RoundQueryResult{" +
                "offset=" + offset +
                ", total=" + total +
                ", count=" + count +
                ", results=" + results +
                '}';
    }
}
