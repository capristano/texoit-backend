package com.texoit.xpto.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByName;

/**
 * Entidade City
 * @author Cleiton
 */
@Entity
public class City {
	
	@Id
	@CsvBindByName(column = "ibge_id", required = true)
	private long ibgeId;
	
	@Column(nullable = false)
	@CsvBindByName(required = true)
	private String uf;
	
	@Column(nullable = false)
	@CsvBindByName(required = true)
	private String name;
	
	@CsvBindByName
	private boolean capital;
	
	@Column(nullable = false)
	@CsvBindByName(required = true)
	private BigDecimal lon;
	
	@Column(nullable = false)
	@CsvBindByName(required = true)
	private BigDecimal lat;
	
	@CsvBindByName(column = "no_accents")
	private String noAccents;
	
	@CsvBindByName(column = "alternative_names")
	private String alternativeNames;
	
	@CsvBindByName
	private String microregion;
	
	@CsvBindByName
	private String mesoregion;
	
	public long getIbgeId() {
		return ibgeId;
	}
	public void setIbgeId(long ibgeId) {
		this.ibgeId = ibgeId;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	public BigDecimal getLon() {
		return lon;
	}
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public String getNoAccents() {
		return noAccents;
	}
	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}
	public String getAlternativeNames() {
		return alternativeNames;
	}
	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}
	public String getMicroregion() {
		return microregion;
	}
	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}
	public String getMesoregion() {
		return mesoregion;
	}
	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}
}
