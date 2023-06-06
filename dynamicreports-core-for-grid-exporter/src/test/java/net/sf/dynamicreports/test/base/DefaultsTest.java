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
package net.sf.dynamicreports.test.base;

import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import net.sf.dynamicreports.report.base.datatype.DRDataType;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.defaults.Default;
import net.sf.dynamicreports.report.defaults.DefaultBinder;
import net.sf.dynamicreports.report.defaults.xml.XmlDynamicReports;


/**
 * Defaults tests.
 *
 * @author Ricardo Mariaca
 */
class DefaultsTest
{
	private Default load()
	{
		final InputStream is = DefaultsTest.class.getResourceAsStream("dynamicreports-defaults.xml");
		try
		{
			final Unmarshaller unmarshaller =
				JAXBContext.newInstance(XmlDynamicReports.class).createUnmarshaller();
			final JAXBElement<XmlDynamicReports> root =
				unmarshaller.unmarshal(new StreamSource(is), XmlDynamicReports.class);
			return DefaultBinder.bind(root.getValue());
		}
		catch(final JAXBException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
		return null;
	}
	
	@Test
	void test()
	{
		DefaultBinder.bind(null);
		final Default defaults = this.load();
		
		final DRFont font = defaults.getFont();
		Assertions.assertEquals("Arimo", font.getFontName());
		Assertions.assertEquals(15, font.getFontSize());
		Assertions.assertEquals("Arimo", font.getPdfFontName());
		Assertions.assertEquals("Identity-H", font.getPdfEncoding());
		Assertions.assertTrue(font.getPdfEmbedded());
		
		this.testDataType("BigDecimal", defaults.getBigDecimalType(), "#,###.00#",
			HorizontalTextAlignment.LEFT);
		this.testDataType("BigInteger", defaults.getBigIntegerType(), "#,###", HorizontalTextAlignment.LEFT);
		this.testDataType("Byte", defaults.getByteType(), "#,###", HorizontalTextAlignment.LEFT);
		this.testDataType("Double", defaults.getDoubleType(), "#,###.#", HorizontalTextAlignment.LEFT);
		this.testDataType("Float", defaults.getFloatType(), "#,###.#", HorizontalTextAlignment.LEFT);
		this.testDataType("Integer", defaults.getIntegerType(), "#,###", HorizontalTextAlignment.LEFT);
		this.testDataType("Long", defaults.getLongType(), "#,###", HorizontalTextAlignment.LEFT);
		this.testDataType("Short", defaults.getShortType(), "#,###", HorizontalTextAlignment.LEFT);
		this.testDataType("Date", defaults.getDateType(), "MM.dd.yyyy", HorizontalTextAlignment.LEFT);
		this.testDataType("DateYearToMonth", defaults.getDateYearToMonthType(), "MM.yyyy",
			HorizontalTextAlignment.LEFT);
		this.testDataType("DateYearToHour", defaults.getDateYearToHourType(), "MM.dd.yyyy h a",
			HorizontalTextAlignment.LEFT);
		this.testDataType("DateYearToMinute", defaults.getDateYearToMinuteType(), "MM.dd.yyyy h:mm a",
			HorizontalTextAlignment.LEFT);
		this.testDataType("DateYearToSecond", defaults.getDateYearToSecondType(), "MM.dd.yyyy h:mm:ss a",
			HorizontalTextAlignment.LEFT);
		this.testDataType("DateYearToFraction", defaults.getDateYearToFractionType(),
			"MM.dd.yyyy h:mm:ss,SSS a", HorizontalTextAlignment.LEFT);
		this.testDataType("DateYear", defaults.getDateYearType(), "yy", HorizontalTextAlignment.LEFT);
		this.testDataType("DateMonth", defaults.getDateMonthType(), "MM", HorizontalTextAlignment.LEFT);
		this.testDataType("DateDay", defaults.getDateDayType(), "d", HorizontalTextAlignment.LEFT);
		this.testDataType("TimeHourToMinute", defaults.getTimeHourToMinuteType(), "hh:mm a",
			HorizontalTextAlignment.LEFT);
		this.testDataType("TimeHourToSecond", defaults.getTimeHourToSecondType(), "hh:mm:ss a",
			HorizontalTextAlignment.LEFT);
		this.testDataType("TimeHourToFraction", defaults.getTimeHourToFractionType(), "hh:mm:ss,SSS a",
			HorizontalTextAlignment.LEFT);
		this.testDataType("Percentage", defaults.getPercentageType(), "#,###.00%",
			HorizontalTextAlignment.LEFT);
		this.testDataType("Boolean", defaults.getBooleanType(), null, HorizontalTextAlignment.RIGHT);
		this.testDataType("Character", defaults.getCharacterType(), null, HorizontalTextAlignment.RIGHT);
		this.testDataType("String", defaults.getStringType(), null, HorizontalTextAlignment.RIGHT);
	}
	
	private void testDataType(
		final String name, final DRDataType<?, ?> dataType, final String pattern,
		final HorizontalTextAlignment horizontalTextAlignment)
	{
		Assertions.assertEquals(pattern, dataType.getPattern(), name + " pattern");
		Assertions.assertEquals(
			horizontalTextAlignment,
			dataType.getHorizontalTextAlignment(),
			name + " horizontal alignment");
	}
}
