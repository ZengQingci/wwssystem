package com.wws.model;

import java.util.Date;

public class TbProductInfo {
	private Integer id;
	private Integer typeId;
	private String infoNumber;
	private String infoName;
	private double infoCost;
	private double infoPrice;
	private double infoDiscount;
	private String pic;
	private String infoSpec;
	private String infoOrigin;
	private String infoBrand;
	private String infoHeat;
	private String intendedFor;
	private Integer infoStock;
	private double infoPostage;
	private Date updateTime;
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getInfoNumber() {
		return infoNumber;
	}
	public void setInfoNumber(String infoNumber) {
		this.infoNumber = infoNumber;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public double getInfoCost() {
		return infoCost;
	}
	public void setInfoCost(double infoCost) {
		this.infoCost = infoCost;
	}
	public double getInfoPrice() {
		return infoPrice;
	}
	public void setInfoPrice(double infoPrice) {
		this.infoPrice = infoPrice;
	}
	public double getInfoDiscount() {
		return infoDiscount;
	}
	public void setInfoDiscount(double infoDiscount) {
		this.infoDiscount = infoDiscount;
	}
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	public String getInfoSpec() {
		return infoSpec;
	}
	public void setInfoSpec(String infoSpec) {
		this.infoSpec = infoSpec;
	}
	public String getInfoOrigin() {
		return infoOrigin;
	}
	public void setInfoOrigin(String infoOrigin) {
		this.infoOrigin = infoOrigin;
	}
	public String getInfoBrand() {
		return infoBrand;
	}
	public void setInfoBrand(String infoBrand) {
		this.infoBrand = infoBrand;
	}
	public String getInfoHeat() {
		return infoHeat;
	}
	public void setInfoHeat(String infoHeat) {
		this.infoHeat = infoHeat;
	}
	public String getIntendedFor() {
		return intendedFor;
	}
	public void setIntendedFor(String intendedFor) {
		this.intendedFor = intendedFor;
	}
	public Integer getInfoStock() {
		return infoStock;
	}
	public void setInfoStock(Integer infoStock) {
		this.infoStock = infoStock;
	}
	
	public double getInfoPostage() {
		return infoPostage;
	}
	public void setInfoPostage(double infoPostage) {
		this.infoPostage = infoPostage;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
