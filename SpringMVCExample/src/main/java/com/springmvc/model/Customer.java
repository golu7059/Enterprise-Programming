package com.springmvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customermvc")
public class Customer {
	
	@Id
	@Column(name = "custid")
	int id;
	
	@Column(name="custname")
	String custName;
	
	@Column(name="custaddress")
	String custAdd;

	@Column(name="custcity")
	String custCity;

	@Column(name="custmobile")
	String custMobile;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getCustName() { return custName; }
	public void setCustName(String custName) { this.custName = custName; }

	public String getCustAdd() { return custAdd; }
	public void setCustAdd(String custAdd) { this.custAdd = custAdd; }

	public String getCustCity() { return custCity; }
	public void setCustCity(String custCity) { this.custCity = custCity; }

	public String getCustMobile() { return custMobile; }
	public void setCustMobile(String custMobile) { this.custMobile = custMobile; }
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", custAdd=" + custAdd + ", custCity=" + custCity
				+ ", custMobile=" + custMobile + "]";
	}
}