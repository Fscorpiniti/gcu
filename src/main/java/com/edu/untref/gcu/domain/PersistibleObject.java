package com.edu.untref.gcu.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistibleObject implements Serializable {

	private static final long serialVersionUID = -1712042570608239529L;
	
	@Id
	@Column(name = "id", length = 11, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}