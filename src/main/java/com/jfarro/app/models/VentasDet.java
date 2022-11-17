package com.jfarro.app.models;

public class VentasDet {
    private VentasCab ventasCab;
    private Product product;
    private int amount;
    private double price;

    public VentasCab getVentasCab() {
        return ventasCab;
    }

    public void setVentasCab(VentasCab ventasCab) {
        this.ventasCab = ventasCab;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
