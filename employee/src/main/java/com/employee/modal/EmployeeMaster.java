package com.employee.modal;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

@Entity
public class EmployeeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String name;
	@NotNull
	@Email
	private String email;

	@OneToMany(targetEntity = AddressMaster.class)
	@JoinColumn(name = "employeeID",updatable = false)
	private List<AddressMaster> addressMaster; 
	
	@Transient
	private List<String> address;
	
	@Transient
	private List<Integer> type;

	@Transient
	private List<Integer> addressID;
	

	public List<AddressMaster> getAddressMaster() {
		return addressMaster;
	}

	public void setAddressMaster(List<AddressMaster> addressMaster) {
		this.addressMaster = addressMaster;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	public List<Integer> getType() {
		return type;
	}

	public void setType(List<Integer> type) {
		this.type = type;
	}

	public List<Integer> getAddressID() {
		return addressID;
	}

	public void setAddressID(List<Integer> addressID) {
		this.addressID = addressID;
	}



}
