package com.deshaw.deshawhackerearth.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SymbolEntity implements Serializable {
	
	private static final long serialVersionUID = -1329527120305548451L;
	private String symbol;
	private String name;
	private BigDecimal marketCap;
	private String sector;
	private String industry;

	/**
	 * @return the symbol
	 */
	@Id
	@Column(nullable=false,unique=true)
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the name
	 */
	@Column(nullable=false)
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the marketCap
	 */
	@Column(nullable=false)
	public BigDecimal getMarketCap() {
		return marketCap;
	}

	/**
	 * @param marketCap
	 *            the marketCap to set
	 */
	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}

	/**
	 * @return the sector
	 */
	@Column(nullable=false)
	public String getSector() {
		return sector;
	}

	/**
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * @return the industry
	 */
	@Column(nullable=false)
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry
	 *            the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, name, marketCap, sector, industry);
	}

}
