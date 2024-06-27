/*
 * dynamicreports-core-for-grid-exporter - dynamicreports-core-for-grid-exporter
 * Copyright © 2023 XDEV Software (https://xdev.software)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package software.xdev.dynamicreports.test.base;

import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.report.builder.datatype.BigDecimalType;
import software.xdev.dynamicreports.report.builder.datatype.StringType;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.exception.DRException;


class DataTypeTest
{
	@Test
	void detectTypeTest()
	{
		try
		{
			this.detectTypeTest(type.bigDecimalType(), BigDecimal.class);
			this.detectTypeTest(type.bigIntegerType(), BigInteger.class);
			this.detectTypeTest(type.byteType(), Byte.class);
			this.detectTypeTest(type.doubleType(), Double.class);
			this.detectTypeTest(type.floatType(), Float.class);
			this.detectTypeTest(type.integerType(), Integer.class);
			this.detectTypeTest(type.longType(), Long.class);
			this.detectTypeTest(type.shortType(), Short.class);
			this.detectTypeTest(type.dateType(), Date.class);
			this.detectTypeTest(type.dateYearToMonthType(), "DateYearToMonth");
			this.detectTypeTest(type.dateYearToHourType(), "DateYearToHour");
			this.detectTypeTest(type.dateYearToMinuteType(), "DateYearToMinute");
			this.detectTypeTest(type.dateYearToSecondType(), "DateYearToSecond");
			this.detectTypeTest(type.dateYearToFractionType(), "DateYearToFraction");
			this.detectTypeTest(type.dateYearType(), "DateYear");
			this.detectTypeTest(type.dateMonthType(), "DateMonth");
			this.detectTypeTest(type.dateDayType(), "DateDay");
			this.detectTypeTest(type.timeHourToMinuteType(), "TimeHourToMinute");
			this.detectTypeTest(type.timeHourToSecondType(), "TimeHourToSecond");
			this.detectTypeTest(type.timeHourToFractionType(), "TimeHourToFraction");
			this.detectTypeTest(type.percentageType(), "Percentage");
			this.detectTypeTest(type.booleanType(), Boolean.class);
			this.detectTypeTest(type.characterType(), Character.class);
			this.detectTypeTest(type.stringType(), String.class);
			this.detectTypeTest(type.stringType(), "Text");
			this.detectTypeTest(type.listType(), List.class);
			
			@SuppressWarnings("unused")
			final BigDecimalType bigDecimalType = type.detectType(BigDecimal.class);
			@SuppressWarnings("unused")
			final StringType stringType = type.detectType(String.class);
		}
		catch(final DRException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
	
	public <U, T extends U> void detectTypeTest(final DRIDataType<U, T> dataType, final Class<T> valueClass)
		throws DRException
	{
		this.detectTypeTest(dataType, valueClass.getSimpleName());
		Assertions.assertEquals(dataType.getClass(), type.detectType(valueClass.getName()).getClass());
		Assertions.assertEquals(dataType.getClass(), type.detectType(valueClass).getClass());
	}
	
	public <U, T extends U> void detectTypeTest(final DRIDataType<U, T> dataType, final String... dataTypes)
		throws DRException
	{
		for(final String stringDataType : dataTypes)
		{
			Assertions.assertEquals(dataType.getClass(), type.detectType(stringDataType).getClass());
			Assertions.assertEquals(dataType.getClass(), type.detectType(stringDataType.toLowerCase()).getClass());
			Assertions.assertEquals(dataType.getClass(), type.detectType(stringDataType.toUpperCase()).getClass());
		}
	}
	
	@Test
	void valueConversionTest()
	{
		this.valueConversionTest("BigDecimal", type.bigDecimalType(), 1000, "1,000.00");
		this.valueConversionTest("BigInteger", type.bigIntegerType(), 1000, "1,000");
		this.valueConversionTest("Byte", type.byteType(), 100, "100");
		this.valueConversionTest("Double", type.doubleType(), 1000.1, "1,000.1");
		this.valueConversionTest("Float", type.floatType(), 1000.1, "1,000.1");
		this.valueConversionTest("Integer", type.integerType(), 1000, "1,000");
		this.valueConversionTest("Long", type.longType(), 1000, "1,000");
		this.valueConversionTest("Short", type.shortType(), 1000, "1,000");
		
		final Calendar c = Calendar.getInstance();
		c.set(2010, 0, 2, 15, 5, 20);
		c.set(Calendar.MILLISECOND, 100);
		final Date date = c.getTime();
		this.valueConversionTest("Date", type.dateType(), date, "01/02/2010");
		this.valueConversionTest("DateYearToMonth", type.dateYearToMonthType(), date, "01/2010");
		this.valueConversionTest("DateYearToHour", type.dateYearToHourType(), date, "01/02/2010 3 PM");
		this.valueConversionTest("DateYearToMinute", type.dateYearToMinuteType(), date, "01/02/2010 3:05 PM");
		this.valueConversionTest("DateYearToSecond", type.dateYearToSecondType(), date, "01/02/2010 3:05:20 PM");
		this.valueConversionTest(
			"DateYearToFraction",
			type.dateYearToFractionType(),
			date,
			"01/02/2010 3:05:20,100 PM");
		this.valueConversionTest("DateYear", type.dateYearType(), date, "2010");
		this.valueConversionTest("DateMonth", type.dateMonthType(), date, "January");
		this.valueConversionTest("DateDay", type.dateDayType(), date, "02");
		this.valueConversionTest("TimeHourToMinute", type.timeHourToMinuteType(), date, "3:05 PM");
		this.valueConversionTest("TimeHourToSecond", type.timeHourToSecondType(), date, "3:05:20 PM");
		this.valueConversionTest("TimeHourToFraction", type.timeHourToFractionType(), date, "3:05:20,100 PM");
		
		this.valueConversionTest("Percentage", type.percentageType(), 0.89156, "89.16%");
		this.valueConversionTest("Boolean", type.booleanType(), true, "true");
		this.valueConversionTest("Character", type.characterType(), 'a', "a");
		this.valueConversionTest("String", type.stringType(), "text", "text");
	}
	
	private <U, T extends U> void valueConversionTest(
		final String name,
		final DRIDataType<U, T> dataType,
		final U value,
		final String stringValue)
	{
		Assertions.assertEquals(stringValue, dataType.valueToString(value, Locale.ENGLISH));
		
		try
		{
			final String stringResult = dataType.valueToString(value, Locale.ENGLISH);
			final U stringToValue = dataType.stringToValue(stringValue, Locale.ENGLISH);
			Assertions.assertTrue(
				stringToValue.getClass().equals(dataType.getValueClass()));
			Assertions.assertEquals(
				stringResult,
				dataType.valueToString(stringToValue, Locale.ENGLISH));
		}
		catch(final DRException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
}
