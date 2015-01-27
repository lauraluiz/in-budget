package com.tacktic.inbudget.sphere;

public class Cart {
    private String id;
    private long version;
    private Money totalPrice;

    public Cart() {

    }

    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
