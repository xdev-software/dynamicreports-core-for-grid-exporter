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
package software.xdev.dynamicreports.test.jasper.crosstab;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

import org.junit.jupiter.api.Assertions;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.test.jasper.AbstractJasperTest;
import net.sf.jasperreports.crosstabs.base.JRBaseCrosstab;
import net.sf.jasperreports.crosstabs.type.CrosstabColumnPositionEnum;
import net.sf.jasperreports.crosstabs.type.CrosstabRowPositionEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;


/**
 * @author Ricardo Mariaca
 */
class CrosstabTest extends AbstractJasperTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
		final TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.setRepeatColumnHeaders(false)
			.setRepeatRowHeaders(false)
			.setColumnBreakOffset(100)
			.setIgnoreWidth(true)
			.setRunDirection(RunDirection.RIGHT_TO_LEFT)
			.rowGroups(ctab.rowGroup(column1))
			.columnGroups(ctab.columnGroup(column2))
			.measures(ctab.measure(column3, Calculation.SUM));
		
		rb.columns(column1, column2, column3).summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		final JRBaseCrosstab crosstab = (JRBaseCrosstab)this.getJasperReport().getSummary().getElementByKey("summary.crosstab1");
		Assertions.assertFalse(crosstab.isRepeatColumnHeaders());
		Assertions.assertFalse(crosstab.isRepeatRowHeaders());
		Assertions.assertEquals(100, crosstab.getColumnBreakOffset());
		Assertions.assertTrue(crosstab.getIgnoreWidth());
		Assertions.assertEquals(RunDirectionEnum.RTL, crosstab.getRunDirectionValue());
		Assertions.assertEquals(CrosstabRowPositionEnum.STRETCH, crosstab.getRowGroups()[0].getPositionValue());
		Assertions.assertEquals(CrosstabColumnPositionEnum.STRETCH, crosstab.getColumnGroups()[0].getPositionValue());
	}
}
