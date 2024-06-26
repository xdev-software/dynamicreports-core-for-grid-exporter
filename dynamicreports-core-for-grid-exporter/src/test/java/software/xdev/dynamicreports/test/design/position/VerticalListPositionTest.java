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
import software.xdev.dynamicreports.test.design.AbstractBandTest;


public class VerticalListPositionTest extends AbstractBandTest
{
	
	@Override
	public void configureReport(final ReportBuilder<?> rb)
	{
		rb.title(cmp.horizontalList(cmp.verticalList(
			cmp.vListCell(cmp.text("")),
			cmp.vListCell(cmp.text("")).heightFixed(),
			cmp.vListCell(cmp.text("")).widthFixedOnLeft().heightFixed(),
			cmp.vListCell(cmp.text("")).widthFixedOnLeft(),
			cmp.vListCell(cmp.text("")).widthFixedOnCenter().heightFixed(),
			cmp.vListCell(cmp.text("")).widthFixedOnCenter(),
			cmp.vListCell(cmp.text("")).widthFixedOnRight().heightFixed(),
			cmp.vListCell(cmp.text("")).widthFixedOnRight()), cmp.filler().setHeight(200)));
	}
	
	@Override
	protected void titleBandTest(final DRDesignBand band)
	{
		final DRDesignComponent component = band.getBandComponent();
		Assertions.assertTrue(component instanceof DRDesignList);
		DRDesignList list = (DRDesignList)component;
		Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
		Assertions.assertEquals(1, list.getComponents().size());
		this.componentPositionTest(list, 0, 0, 575, 200);
		Assertions.assertTrue(list.getComponents().get(0) instanceof DRDesignList);
		
		list = (DRDesignList)list.getComponents().get(0);
		Assertions.assertEquals(ListType.VERTICAL, list.getType());
		Assertions.assertEquals(8, list.getComponents().size());
		this.componentPositionTest(list, 0, 0, 575, 200);
		for(int i = 0; i < 8; i++)
		{
			Assertions.assertTrue(list.getComponents().get(i) instanceof DRDesignTextField);
		}
		
		this.componentPositionTest(list.getComponents().get(0), 0, 0, 575, 34);
		this.componentPositionTest(list.getComponents().get(1), 0, 34, 575, 16);
		this.componentPositionTest(list.getComponents().get(2), 0, 50, 100, 16);
		this.componentPositionTest(list.getComponents().get(3), 0, 66, 100, 34);
		this.componentPositionTest(list.getComponents().get(4), 237, 100, 100, 16);
		this.componentPositionTest(list.getComponents().get(5), 237, 116, 100, 34);
		this.componentPositionTest(list.getComponents().get(6), 475, 150, 100, 16);
		this.componentPositionTest(list.getComponents().get(7), 475, 166, 100, 34);
	}
}
