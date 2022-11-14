package com.af.dateparser;

import java.util.Date;

/**
 * 
 * @author Yehezkel
 * a calls that holds the data for Record type
 *
 */
public class Record implements Comparable<Record> {
	private Date date;
	private String symbol, volume;
	private double price;
	
	public Record(Date date, String symbol, String volume, double price) {
		this.date = date;
		this.symbol = symbol;
		this.volume = volume;
		this.price = price;
	}

	@Override
	public int compareTo(Record record) {
		return date.compareTo(record.getDate());
	}

	public Date getDate() {
		return this.date;
	}
	
	public String getDateAsString() {
		return this.date.toString();
	}

	public double getPrice() {
		return this.price;
	}

	public String getSymbol() {
		return this.symbol;
	}

	@Override
	public String toString() {
		return "Record [date=" + date + ", symbol=" + symbol + ", volume=" + volume + ", price=" + price + "]";
	}

	public String getVolume() {
		return volume;
	}
}