package com.tacktic.inbudget;

import com.badlogic.gdx.utils.Json;
import com.tacktic.inbudget.sphere.Cart;
import com.tacktic.inbudget.sphere.ItemQueryResult;
import com.tacktic.inbudget.sphere.RoundQueryResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParseSphereObjects {
    private static final String ROUND_RESPONSE = "{\"offset\":0,\"count\":1,\"total\":1,\"results\":[{\"id\":\"3a08f46b-6433-4a77-aee6-7f6b66fbff71\",\"version\":5,\"name\":{\"en\":\"Round 1\",\"de\":\"Round 1\"},\"slug\":{\"en\":\"round1\",\"de\":\"round1\"},\"description\":{\"en\":\"<p><br></p>\",\"de\":\"<p><br></p>\"},\"ancestors\":[],\"orderHint\":\"0.000014166115576011996355660\",\"createdAt\":\"2014-11-21T23:12:37.601Z\",\"lastModifiedAt\":\"2014-11-22T16:23:04.333Z\",\"externalId\":\"1\"}]}";
    private static final String ITEM_RESPONSE = "{\"offset\":0,\"count\":2,\"total\":2,\"results\":[{\"masterVariant\":{\"id\":1,\"sku\":\"doggy\",\"prices\":[{\"value\":{\"currencyCode\":\"EUR\",\"centAmount\":3000}}],\"images\":[{\"url\":\"https://c69cde24af4ce4c3b2f9-365a76bdff6e1a5565d0c2643daf4adb.ssl.cf3.rackcdn.com/doggie-V9d1RBwM.png\",\"dimensions\":{\"w\":180,\"h\":185}}],\"attributes\":[]},\"id\":\"96614709-a500-495d-a9e0-e840031770b6\",\"version\":8,\"productType\":{\"typeId\":\"product-type\",\"id\":\"a82c50cd-4472-4f44-9293-69c93e5b6f51\"},\"name\":{\"en\":\"Doggy\"},\"categories\":[{\"typeId\":\"category\",\"id\":\"3a08f46b-6433-4a77-aee6-7f6b66fbff71\"}],\"slug\":{\"en\":\"doggy\"},\"variants\":[],\"searchKeywords\":{},\"hasStagedChanges\":false,\"published\":true,\"taxCategory\":{\"typeId\":\"tax-category\",\"id\":\"13c07b0a-894f-4c2c-bee4-ce44131c7f37\"},\"createdAt\":\"2014-11-22T13:39:33.959Z\",\"lastModifiedAt\":\"2014-11-25T16:27:37.171Z\"},{\"masterVariant\":{\"id\":1,\"sku\":\"googles\",\"prices\":[{\"value\":{\"currencyCode\":\"EUR\",\"centAmount\":55000}}],\"images\":[{\"url\":\"https://c69cde24af4ce4c3b2f9-365a76bdff6e1a5565d0c2643daf4adb.ssl.cf3.rackcdn.com/googles-vsfp1Yk_.png\",\"dimensions\":{\"w\":80,\"h\":80}}],\"attributes\":[]},\"id\":\"609d6335-18a2-417a-abb9-f6d1d7738d2c\",\"version\":10,\"productType\":{\"typeId\":\"product-type\",\"id\":\"a82c50cd-4472-4f44-9293-69c93e5b6f51\"},\"name\":{\"en\":\"Googles\"},\"categories\":[{\"typeId\":\"category\",\"id\":\"3a08f46b-6433-4a77-aee6-7f6b66fbff71\"}],\"slug\":{\"en\":\"googles\"},\"variants\":[],\"searchKeywords\":{},\"hasStagedChanges\":false,\"published\":true,\"taxCategory\":{\"typeId\":\"tax-category\",\"id\":\"13c07b0a-894f-4c2c-bee4-ce44131c7f37\"},\"createdAt\":\"2014-11-22T18:23:01.212Z\",\"lastModifiedAt\":\"2014-11-25T16:27:23.482Z\"}],\"facets\":{}}";
    private static final String CART_RESPONSE = "{\"type\":\"Cart\",\"id\":\"6fdbc6a1-b820-4037-b1a2-4dcd2923cac6\",\"version\":1,\"createdAt\":\"2015-01-27T10:16:47.802Z\",\"lastModifiedAt\":\"2015-01-27T10:16:47.802Z\",\"lineItems\":[],\"cartState\":\"Active\",\"totalPrice\":{\"currencyCode\":\"EUR\",\"centAmount\":0},\"inventoryMode\":\"None\",\"customLineItems\":[],\"discountCodes\":[]}";

    private Json json;

    @Before
    public void setUp() throws Exception {
        json = new Json();
        json.setIgnoreUnknownFields(true);
    }

    @Test
    public void parsesRoundQueryResponse() throws Exception {
        RoundQueryResult result = json.fromJson(RoundQueryResult.class, ROUND_RESPONSE);
        assertEquals(result.getTotal(), result.getResults().size);
        assertEquals(result.getResults().first().getExternalId(), 1);
    }

    @Test
    public void parsesItemQueryResponse() throws Exception {
        ItemQueryResult result = json.fromJson(ItemQueryResult.class, ITEM_RESPONSE);
        assertEquals(result.getTotal(), result.getResults().size);
        assertEquals(result.getResults().first().price().getAmount().doubleValue(), 30, 0);
    }

    @Test
    public void parsesCart() throws Exception {
        Cart cart = json.fromJson(Cart.class, CART_RESPONSE);
        assertEquals(cart.getVersion(), 1);
        assertEquals(cart.getTotalPrice().getAmount().doubleValue(), 0, 0);
    }
}
