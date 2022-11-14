package com.af.dateparser;

import org.junit.Test;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class Tester {

	public Parser parser = new Parser();
	public Calendar calendar = Calendar.getInstance();

	final int ONE = 1;
	final int TWO = 2;
	final int THREE = 3;
	final int FOUR = 4;
	final int FIVE = 5;
	final int TEN = 10;
	final int ELEVEN = 11;
	final int TWENTY = 20;
	final int TWENTY_ONE = 21;
	final int TWENTY_EIGHT = 28;
	final int TWENTY_NINE = 29;
	final int THIRTY_ONE = 31;
	final int TWO_THOUSAND_AND_TEN = 2010;
	final int TWO_THOUSAND_AND_NINETEEN = 2019;
	final int TWO_THOUSAND_AND_TWENTY = 2020;
	final int TWO_THOUSAND_AND_TWENTY_ONE = 2021;
	final int TWO_THOUSAND_AND_TWENTY_TOW = 2022;

	@Test
	public void test() {
		Parser parser = new Parser();
		Calendar calendar = Calendar.getInstance();
		List<DateGroup> parsed;
		Date date;
		final int CURRENT_DAY = calendar.get(Calendar.DAY_OF_MONTH);
		final int CURRENT_MONTH = calendar.get(Calendar.MONTH) + 1;
		final int CURRENT_YEAR = calendar.get(Calendar.YEAR);

		// test case 1, test 1
		testCase("28-Feb-2010", TWENTY_EIGHT, TWO, TWO_THOUSAND_AND_TEN, true);
		// test case 1, test 2
		testCase("1-Feb-2010", ONE, TWO, TWO_THOUSAND_AND_TEN, true);
		// test case 1, test 3
		testCase("29-Feb-2020", TWENTY_NINE, TWO, TWO_THOUSAND_AND_TWENTY, true);
		// test case 1, test 4
		testCase("29-Feb-2019", ONE, THREE, TWO_THOUSAND_AND_NINETEEN, false);
		// test case 1, test 5
		testCase("--1-Feb-2020", ONE, TWO, TWO_THOUSAND_AND_TWENTY, false);
		// test case 1, test 5
		testCase("30-Feb-2020", ONE, THREE, TWO_THOUSAND_AND_TWENTY, false);

		System.out.println("*************************************\n");

		// Test case 2, test 1.
		testCase("next Monday", TWENTY_ONE, ELEVEN, TWO_THOUSAND_AND_TWENTY_TOW, true);
		testCase("Next Monday", TWENTY_ONE, ELEVEN, TWO_THOUSAND_AND_TWENTY_TOW, true);
		testCase("Next Mon", TWENTY_ONE, ELEVEN, TWO_THOUSAND_AND_TWENTY_TOW, true);
		testCase("Next SUNDAY", TWENTY, ELEVEN, TWO_THOUSAND_AND_TWENTY_TOW, true);
		testCase("Next Today", CURRENT_DAY, CURRENT_MONTH, CURRENT_YEAR, false);

		System.out.println("*************************************\n");

		// Test case 3, test 1.
		testCase("1/1/2021", ONE, ONE, TWO_THOUSAND_AND_TWENTY_ONE, true);
		// Test case 3, test 2.
		testCase("1/31/2021", THIRTY_ONE, ONE, TWO_THOUSAND_AND_TWENTY_ONE, true);
		testCase("13/31/2021", CURRENT_DAY, CURRENT_MONTH, CURRENT_YEAR, false);
		// Test case 3, test 3.
		testCase("2020-04-20", TWENTY, FOUR, TWO_THOUSAND_AND_TWENTY, true);
		// Test case 3, test 4.
		testCase("1/32/2021", CURRENT_DAY, CURRENT_MONTH, CURRENT_YEAR, false);

		System.out.println("*************************************\n");

		// Test case 4, test 1.
		testCase("The day after 2021/10/4", FIVE, TEN, TWO_THOUSAND_AND_TWENTY_ONE, true);
		// Test case 4, test 2.
		testCase("The day after yesterday", CURRENT_DAY, CURRENT_MONTH, CURRENT_YEAR, true);
		// Test case 4, test 3.
		testCase("the day before tomorrow", CURRENT_DAY, CURRENT_MONTH, CURRENT_YEAR, true);
		// Test case 4, test 4.
		testCase("the day after tomorrow", CURRENT_DAY + 2, CURRENT_MONTH, CURRENT_YEAR, true);

		System.out.println("*************************************\n");

		parsed = parser.parse("2021/11/12");
		date = parsed.get(0).getDates().get(0);
		System.out.println("not a test " + date);
	}

	/**
	 * a Method that gets a string containing a date to parse, the expected date,
	 * and a boolean that indicates if the test should Pass(true)/Fail(False)
	 * 
	 * @param stringToBeParsed
	 * @param expectedDay
	 * @param expectedMonth
	 * @param expectedYear
	 * @param expectedResult
	 */
	private void testCase(String stringToBeParsed, int expectedDay, int expectedMonth, int expectedYear,
			boolean expectedResult) {
		List<DateGroup> parsed;
		Date date;
		parsed = this.parser.parse(stringToBeParsed);
		date = parsed.get(0).getDates().get(0);
		calendar.setTime(date);
		assertEquals(expectedDay, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(expectedMonth - 1, calendar.get(Calendar.MONTH));
		assertEquals(expectedYear, calendar.get(Calendar.YEAR));

		if (expectedResult) {
			System.out.println("test for String: " + stringToBeParsed + "\nresult date: " + date);
		} else {
			System.out.println("test has Failed Successfully: " + date);
		}
	}
	
	public Date testCase(String stringToBeParsed){
		List<DateGroup> parsed;
		Date date;
		parsed = this.parser.parse(stringToBeParsed);
		date = parsed.get(0).getDates().get(0);
		calendar.setTime(date);
		return date;
	}
}