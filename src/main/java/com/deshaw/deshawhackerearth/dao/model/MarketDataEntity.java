package com.deshaw.deshawhackerearth.dao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MarketDataEntity {

	private LocalDate date;
	private SymbolEntity symbol;
	private Double open;
	private Double close;
	private Double low;
	private Double high;
	private BigDecimal volume;

	/**
	 * @return the date
	 */
	@Id
	@Column(nullable=false,unique=true)
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the symbol
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="symbol")
	public SymbolEntity getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(SymbolEntity symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the open
	 */
	@Column(nullable=true)
	public Double getOpen() {
		return open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * @return the close
	 */
	public Double getClose() {
		return close;
	}

	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(Double close) {
		this.close = close;
	}

	/**
	 * @return the low
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * @param low
	 *            the low to set
	 */
	public void setLow(Double low) {
		this.low = low;
	}

	/**
	 * @return the high
	 */
	public Double getHigh() {
		return high;
	}

	/**
	 * @param high
	 *            the high to set
	 */
	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * @return the volume
	 */
	public BigDecimal getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

}
