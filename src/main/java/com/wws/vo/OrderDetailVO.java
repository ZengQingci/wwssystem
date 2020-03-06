package com.wws.vo;

public class OrderDetailVO {
    //订单编号
    private String orderNumber;
    //地址
    private String address;
    //收货人姓名
    private String orderConsignee;
    //客户id
    private Integer customerId;
    //手机号码
    private String phone;
    //商品名字
    private String producName;
    //商品id
    private Integer productId;
    //折扣
    private double infoDiscount;
    //成本价
    private String infoCost;
    //销售价格
    private double infoPrice;
    //规格
    private String infoSpec;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderConsignee() {
        return orderConsignee;
    }

    public void setOrderConsignee(String orderConsignee) {
        this.orderConsignee = orderConsignee;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProducName() {
        return producName;
    }

    public void setProducName(String producName) {
        this.producName = producName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getInfoDiscount() {
        return infoDiscount;
    }

    public void setInfoDiscount(double infoDiscount) {
        this.infoDiscount = infoDiscount;
    }

    public String getInfoCost() {
        return infoCost;
    }

    public void setInfoCost(String infoCost) {
        this.infoCost = infoCost;
    }

    public double getInfoPrice() {
        return infoPrice;
    }

    public void setInfoPrice(double infoPrice) {
        this.infoPrice = infoPrice;
    }

    public String getInfoSpec() {
        return infoSpec;
    }

    public void setInfoSpec(String infoSpec) {
        this.infoSpec = infoSpec;
    }

}
