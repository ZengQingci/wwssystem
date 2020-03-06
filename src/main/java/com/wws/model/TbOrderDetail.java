package com.wws.model;

public class TbOrderDetail {
    private Integer id;
    private Integer productId;
    private Integer orderId;
    private String producName;
    private double productPrice;
    private double discountRate;
    private Integer orderDetailNumber;
    private double subtotal;
    private String productMarque;
    private String returnNumber;
    private String orderlogisticsNumberv;
    private String replacementReason;
    private String detailStatus;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProducName() {
        return producName;
    }

    public void setProducName(String producName) {
        this.producName = producName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getProductMarque() {
        return productMarque;
    }

    public void setProductMarque(String productMarque) {
        this.productMarque = productMarque;
    }

    public String getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(String returnNumber) {
        this.returnNumber = returnNumber;
    }

    public String getOrderlogisticsNumberv() {
        return orderlogisticsNumberv;
    }

    public void setOrderlogisticsNumberv(String orderlogisticsNumberv) {
        this.orderlogisticsNumberv = orderlogisticsNumberv;
    }

    public String getReplacementReason() {
        return replacementReason;
    }

    public void setReplacementReason(String replacementReason) {
        this.replacementReason = replacementReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOrderDetailNumber() {
        return orderDetailNumber;
    }

    public void setOrderDetailNumber(Integer orderDetailNumber) {
        this.orderDetailNumber = orderDetailNumber;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }


}
