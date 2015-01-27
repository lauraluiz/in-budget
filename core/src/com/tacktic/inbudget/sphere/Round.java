package com.tacktic.inbudget.sphere;

public class Round {
    private String id;
    private long version;
    private InternationalString name;
    private InternationalString slug;
    private InternationalString description;
    private String orderHint;
    private int externalId;

    public Round() {

    }

    public String getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public InternationalString getName() {
        return name;
    }

    public InternationalString getSlug() {
        return slug;
    }

    public InternationalString getDescription() {
        return description;
    }

    public String getOrderHint() {
        return orderHint;
    }

    public int getExternalId() {
        return externalId;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", name=" + name +
                ", slug=" + slug +
                ", description=" + description +
                ", orderHint='" + orderHint + '\'' +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
