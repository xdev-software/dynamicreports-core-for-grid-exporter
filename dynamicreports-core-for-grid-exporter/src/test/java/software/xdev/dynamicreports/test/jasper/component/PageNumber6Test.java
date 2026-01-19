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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.report.exception.DRException;


class PageNumber6Test
{
	@Test
	void test()
	{
		try
		{
			report().summary(cmp.pageXofY().setFormatExpression("{1} {0}")).toJasperPrint();
			Assertions.fail("Wrong page number");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		try
		{
			report().summary(cmp.pageXofY().setFormatExpression("{1} {1}")).toJasperPrint();
			Assertions.fail("Wrong page number");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		try
		{
			report().summary(cmp.pageXofY().setFormatExpression("{0} {0}")).toJasperPrint();
			Assertions.fail("Wrong page number");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		try
		{
			report().summary(cmp.pageXofY().setFormatExpression("{0}")).toJasperPrint();
			Assertions.fail("Wrong page number");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		try
		{
			report().summary(cmp.pageXofY().setFormatExpression("{1}")).toJasperPrint();
			Assertions.fail("Wrong page number");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
	}
}
