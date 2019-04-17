package com.epam.hrynyshyn.model;

import java.math.BigDecimal;

/**
 * @author by Roman Hrynyshyn
 * Created on 2019-04-17.
 */
public class ShoppingCartInfo {
    private int productCount;
    private BigDecimal total;

    public ShoppingCartInfo(int productCount, BigDecimal total) {
        this.productCount = productCount;
        this.total = total;
    }

    public int getProductCount() {
        return productCount;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
