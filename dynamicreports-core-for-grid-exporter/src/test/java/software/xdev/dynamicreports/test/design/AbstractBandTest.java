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
package software.xdev.dynamicreports.test.design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.DRDesignReport;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.exception.DRException;


public abstract class AbstractBandTest
{
	
	@Test
	public void test()
	{
		final ReportBuilder<?> rb = new DesignReportBuilder();
		this.configureReport(rb);
		try
		{
			final DRDesignReport report = new DRDesignReport(rb.getReport());
			this.titleBandTest(report.getTitleBand());
			this.pageHeaderBandTest(report.getPageHeaderBand());
			this.pageFooterBandTest(report.getPageFooterBand());
			this.columnHeaderBandTest(report.getColumnHeaderBand());
			this.columnFooterBandTest(report.getColumnFooterBand());
			for(final DRDesignBand designBand : report.getDetailBands())
			{
				this.detailBandTest(designBand);
			}
			this.lastPageFooterBandTest(report.getLastPageFooterBand());
			this.summaryBandTest(report.getSummaryBand());
			this.noDataBandTest(report.getNoDataBand());
			this.backgroundBandTest(report.getBackgroundBand());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	protected void titleBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void pageHeaderBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void pageFooterBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void columnHeaderBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void columnFooterBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void detailBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void lastPageFooterBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void summaryBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void noDataBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void backgroundBandTest(final DRDesignBand band)
	{
		Assertions.assertNull(band);
	}
	
	protected void componentPositionTest(
		final DRDesignComponent component,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		Assertions.assertNotNull(component.getWidth());
		Assertions.assertEquals(Integer.valueOf(width), component.getWidth());
		Assertions.assertNotNull(component.getHeight());
		Assertions.assertEquals(Integer.valueOf(height), component.getHeight());
		Assertions.assertNotNull(component.getX());
		Assertions.assertEquals(Integer.valueOf(x), component.getX());
		Assertions.assertNotNull(component.getY());
		Assertions.assertEquals(Integer.valueOf(y), component.getY());
	}
	
	public abstract void configureReport(ReportBuilder<?> rb);
}
