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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;

import org.junit.jupiter.api.Assertions;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.test.design.AbstractBandTest;


public class HorizontalListPositionTest extends AbstractBandTest
{
	
	@Override
	public void configureReport(final ReportBuilder<?> rb)
	{
		rb.setPageFormat(PageType.A2)
			.title(cmp.horizontalList(
				cmp.hListCell(cmp.text("").setHeight(23)),
				cmp.hListCell(cmp.text("")),
				cmp.hListCell(cmp.text("")).widthFixed(),
				cmp.hListCell(cmp.text("")).widthFixed().heightFixedOnTop(),
				cmp.hListCell(cmp.text("")).heightFixedOnTop(),
				cmp.hListCell(cmp.text("")).widthFixed().heightFixedOnMiddle(),
				cmp.hListCell(cmp.text("")).heightFixedOnMiddle(),
				cmp.hListCell(cmp.text("")).widthFixed().heightFixedOnBottom(),
				cmp.hListCell(cmp.text("")).heightFixedOnBottom()));
	}
	
	@Override
	protected void titleBandTest(final DRDesignBand band)
	{
		final DRDesignComponent component = band.getBandComponent();
		Assertions.assertTrue(component instanceof DRDesignList);
		final DRDesignList list = (DRDesignList)component;
		Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
		Assertions.assertEquals(9, list.getComponents().size());
		this.componentPositionTest(list, 0, 0, 1170, 23);
		for(int i = 0; i < 9; i++)
		{
			Assertions.assertTrue(list.getComponents().get(i) instanceof DRDesignTextField);
		}
		
		this.componentPositionTest(list.getComponents().get(0), 0, 0, 154, 23);
		this.componentPositionTest(list.getComponents().get(1), 154, 0, 154, 23);
		this.componentPositionTest(list.getComponents().get(2), 308, 0, 100, 23);
		this.componentPositionTest(list.getComponents().get(3), 408, 0, 100, 16);
		this.componentPositionTest(list.getComponents().get(4), 508, 0, 154, 16);
		this.componentPositionTest(list.getComponents().get(5), 662, 3, 100, 16);
		this.componentPositionTest(list.getComponents().get(6), 762, 3, 154, 16);
		this.componentPositionTest(list.getComponents().get(7), 916, 7, 100, 16);
		this.componentPositionTest(list.getComponents().get(8), 1016, 7, 154, 16);
	}
}
