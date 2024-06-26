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
package software.xdev.dynamicreports.test.design.position;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import org.junit.jupiter.api.Assertions;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.test.design.AbstractBandTest;


public class SubtotalPositionTest extends AbstractBandTest
{
	
	@Override
	public void configureReport(final ReportBuilder<?> rb)
	{
		final TextColumnBuilder<Integer> column3;
		
		rb.setShowColumnTitle(false)
			.columns(
				col.column("Column1", "field1", Integer.class),
				col.column("Column2", "field2", Integer.class),
				column3 = col.column("Column3", "field3", Integer.class))
			.subtotalsAtTitle(sbt.sum(column3))
			.subtotalsAtPageHeader(sbt.sum(column3))
			.subtotalsAtPageFooter(sbt.sum(column3))
			.subtotalsAtColumnHeader(sbt.sum(column3))
			.subtotalsAtColumnFooter(sbt.sum(column3))
			.subtotalsAtLastPageFooter(sbt.sum(column3))
			.subtotalsAtSummary(sbt.sum(column3), sbt.aggregate(column3, Calculation.AVERAGE));
	}
	
	@Override
	protected void titleBandTest(final DRDesignBand band)
	{
		this.testBand(band);
	}
	
	@Override
	protected void pageHeaderBandTest(final DRDesignBand band)
	{
		this.testBand(band);
	}
	
	@Override
	protected void pageFooterBandTest(final DRDesignBand band)
	{
		this.testBand(band);
	}
	
	@Override
	protected void columnHeaderBandTest(final DRDesignBand band)
	{
		this.testBand(band);
	}
	
	@Override
	protected void columnFooterBandTest(final DRDesignBand band)
	{
		this.testBand(band);
	}
	
	@Override
	protected void detailBandTest(final DRDesignBand band)
	{
		Assertions.assertNotNull(band);
	}
	
	@Override
	protected void lastPageFooterBandTest(final DRDesignBand band)
	{
		this.testBand(band);
	}
	
	@Override
	protected void summaryBandTest(final DRDesignBand band)
	{
		final DRDesignComponent component = band.getBandComponent();
		Assertions.assertTrue(component instanceof DRDesignList);
		DRDesignList list = (DRDesignList)component;
		Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
		Assertions.assertEquals(1, list.getComponents().size());
		this.componentPositionTest(list, 0, 0, 575, 32);
		Assertions.assertTrue(list.getComponents().get(0) instanceof DRDesignList);
		
		list = (DRDesignList)list.getComponents().get(0);
		Assertions.assertEquals(ListType.VERTICAL, list.getType());
		Assertions.assertEquals(2, list.getComponents().size());
		this.componentPositionTest(list, 383, 0, 192, 32);
		Assertions.assertTrue(list.getComponents().get(0) instanceof DRDesignTextField);
		Assertions.assertTrue(list.getComponents().get(1) instanceof DRDesignTextField);
		
		// column3
		this.componentPositionTest(list.getComponents().get(0), 383, 0, 192, 16);
		this.componentPositionTest(list.getComponents().get(1), 383, 16, 192, 16);
	}
	
	protected void testBand(final DRDesignBand band)
	{
		final DRDesignComponent component = band.getBandComponent();
		Assertions.assertTrue(component instanceof DRDesignList);
		final DRDesignList list = (DRDesignList)component;
		Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
		Assertions.assertEquals(1, list.getComponents().size());
		this.componentPositionTest(list, 0, 0, 575, 16);
		Assertions.assertTrue(list.getComponents().get(0) instanceof DRDesignTextField);
		
		// column3
		this.componentPositionTest(list.getComponents().get(0), 383, 0, 192, 16);
	}
}
