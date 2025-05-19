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
package software.xdev.dynamicreports.test.design.report;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.variable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.design.base.DRDesignReport;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.design.DesignReportBuilder;


class ExceptionTest
{
	
	@Test
	void testComponentFixedWidth()
	{
		final ReportBuilder<?> rb1 = new DesignReportBuilder();
		rb1.title(cmp.text("").setFixedWidth(600));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("component fixed width exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		final ReportBuilder<?> rb2 = new DesignReportBuilder();
		rb2.title(cmp.horizontalList(cmp.text("").setFixedWidth(600)));
		try
		{
			new DRDesignReport(rb2.getReport());
			Assertions.fail("component fixed width exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		final ReportBuilder<?> rb3 = new DesignReportBuilder();
		rb3.title(cmp.horizontalFlowList(cmp.text("").setFixedWidth(600)));
		try
		{
			new DRDesignReport(rb3.getReport());
			Assertions.fail("component fixed width exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
	}
	
	@Test
	void testComponentMinimumWidth()
	{
		final ReportBuilder<?> rb1 = new DesignReportBuilder();
		rb1.title(cmp.text("").setMinWidth(600));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("component minimum width exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		final ReportBuilder<?> rb2 = new DesignReportBuilder();
		rb2.title(cmp.horizontalList(cmp.text("").setMinWidth(600)));
		try
		{
			new DRDesignReport(rb2.getReport());
			Assertions.fail("component fixed width exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		final ReportBuilder<?> rb3 = new DesignReportBuilder();
		rb3.title(cmp.horizontalFlowList(cmp.text("").setMinWidth(600)));
		try
		{
			new DRDesignReport(rb3.getReport());
			Assertions.fail("component fixed width exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
	}
	
	@Test
	void testVariable()
	{
		final TextColumnBuilder<Object> column = col.column("fieldName", Object.class);
		final ColumnGroupBuilder group = grp.group(column);
		final ReportBuilder<?> rb1 =
			new DesignReportBuilder().columns(column)
				.groupBy(group)
				.variables(variable("fieldName", Object.class, Calculation.SUM).setResetGroup(group)
					.setResetType(Evaluation.REPORT));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("variable exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		final ReportBuilder<?> rb2 = new DesignReportBuilder().columns(column)
			.groupBy(group)
			.variables(variable("fieldName", Object.class, Calculation.SUM).setResetType(Evaluation.GROUP));
		try
		{
			new DRDesignReport(rb2.getReport());
			Assertions.fail("variable exception");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
	}
}
