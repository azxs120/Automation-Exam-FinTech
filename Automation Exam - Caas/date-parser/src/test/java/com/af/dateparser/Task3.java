package com.af.dateparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Task3 {
	public static void main(String[] args) {
		final int SYMBOL = 0;
		final int DATE = 1;
		final int PRICE = 2;
		final int VOLUME = 3;
		String line;
		String stocksPath = "../\\\\date-parser\\\\target\\\\classes\\\\stocks.csv";

		ArrayList<String[]> lines = new ArrayList<String[]>();
		ArrayList<Record> records = new ArrayList<Record>();
		Tester tester = new Tester();

		// get data from csv file
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(stocksPath));
			int i = 0;
			while ((line = bufferedReader.readLine()) != null) {
				 //while (i < 7) {
				line = bufferedReader.readLine();
				lines.add(line.split("," ));
				//System.out.println(lines.get(i));
				if ((i != 0)) {
					String symbol = lines.get(i)[SYMBOL];
					String volume = lines.get(i)[VOLUME];
					double price = Double.parseDouble(lines.get(i)[PRICE]);
					Date date = tester.testCase(lines.get(i)[DATE]);

					Record record = new Record(date, symbol, volume, price);
					records.add(record);
				}
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 3. two records or more with the same Symbol and Date, keep highest price only
		records = dropRecordsWithTheSameSymbolAndTheSameDate(records);

		// 4. sort by date oldest first
		records = sortArrayListByDate(records);

		// 5. save data to a csv file
		saveDataToCsvFile(lines, records);
	}

	/**
	 * a method that writes records data to a csv file lines is used for titles
	 * 
	 * @param lines
	 * @param records
	 */
	private static void saveDataToCsvFile(ArrayList<String[]> lines, ArrayList<Record> records) {
		File csvFile = new File("updated-stocks.csv");
		try {
			PrintWriter out = new PrintWriter(csvFile);

			// add the titles
			out.printf("%s, %s, %s, %s", lines.get(0)[0], lines.get(0)[1], lines.get(0)[2], lines.get(0)[3]);
			out.printf("\n");

			// add the recorders
			for (int index = 0; index < records.size(); index++) {
				out.printf("%s, %s, %f, %s", records.get(index).getSymbol(), records.get(index).getDateAsString(),
						records.get(index).getPrice(), records.get(index).getVolume());
				out.printf("\n");

			}

			// close the file
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * a method that looks for records with the same symbol and the same date and
	 * deletes the one with the lower price
	 * 
	 * @param records
	 * @return recodes arrayList
	 */
	private static ArrayList<Record> dropRecordsWithTheSameSymbolAndTheSameDate(ArrayList<Record> records) {
		// a record with the same symbol and the same date
		for (int i = 0; i < records.size(); i++) {
			for (int j = i + 1; j < records.size(); j++) {
				// parse the dates
				// a record with the same symbol and the same date
				if (records.get(i).getSymbol().equals(records.get(j).getSymbol())) {
					if (records.get(i).getDateAsString().equals(records.get(j).getDateAsString())) {
						// keep the record with the highest price only
						double indexIPrice = records.get(i).getPrice();
						double indexJPrice = records.get(j).getPrice();
						if (indexIPrice >= indexJPrice) {
							records.remove(j);
						} else {
							records.remove(i);
						}
					}
				}
			}

		}
		return records;
	}

	/**
	 * a method that sorts the array by date from oldest to newest
	 * 
	 * @param array list that contains arrays
	 * @return a sorted(by date) array list that contains arrays
	 */
	private static ArrayList<Record> sortArrayListByDate(ArrayList<Record> records) {
		Collections.sort(records);
		return records;
	}
}
