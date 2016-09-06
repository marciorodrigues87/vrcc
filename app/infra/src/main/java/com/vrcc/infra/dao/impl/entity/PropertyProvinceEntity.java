package com.vrcc.infra.dao.impl.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "property_province")
public class PropertyProvinceEntity {

	private long id;
	private PropertyEntity property;
	private String name;

	public PropertyProvinceEntity() {
	}

	public PropertyProvinceEntity(PropertyEntity property, String name) {
		this.property = property;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@ManyToOne(fetch = LAZY, optional = false)
	public PropertyEntity getProperty() {
		return property;
	}

	void setId(long id) {
		this.id = id;
	}

	void setName(String name) {
		this.name = name;
	}

	void setProperty(PropertyEntity property) {
		this.property = property;
	}

}
