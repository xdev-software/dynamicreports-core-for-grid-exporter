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
package software.xdev.dynamicreports.test.jasper;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRPrintElement;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.SubtotalBuilder;


public abstract class AbstractJasperPositionTest extends AbstractJasperTest
{
	
	protected void elementPositionTest(
		final String name,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		final JRPrintElement element = this.getElementAt(name, index);
		Assertions.assertEquals(width, element.getWidth());
		Assertions.assertEquals(height, element.getHeight());
		Assertions.assertEquals(x, element.getX());
		Assertions.assertEquals(y, element.getY());
	}
	
	// column detail
	protected void columnDetailPositionTest(
		final ColumnBuilder<?, ?> column,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getColumnDetailName(column), index, x, y, width, height);
	}
	
	// column title
	protected void columnTitlePositionTest(
		final ColumnBuilder<?, ?> column,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getColumnTitleName(column), index, x, y, width, height);
	}
	
	// subtotal label
	protected void subtotalLabelPositionTest(
		final SubtotalBuilder<?, ?> subtotal,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, x, y, width, height);
	}
	
	protected void subtotalLabelIndexPositionTest(
		final SubtotalBuilder<?, ?> subtotal,
		final int subtotalIndex,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(
			JasperTestUtils.getSubtotalLabelName(subtotal, subtotalIndex),
			index,
			x,
			y,
			width,
			height);
	}
	
	// subtotal
	protected void subtotalPositionTest(
		final SubtotalBuilder<?, ?> subtotal,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, x, y, width, height);
	}
	
	protected void subtotalIndexPositionTest(
		final SubtotalBuilder<?, ?> subtotal,
		final int subtotalIndex,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getSubtotalName(subtotal, subtotalIndex), index, x, y, width, height);
	}
	
	// group header title
	protected void groupHeaderTitlePositionTest(
		final GroupBuilder<?> group,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getHeaderTitleGroupName(group), index, x, y, width, height);
	}
	
	// group header
	protected void groupHeaderPositionTest(
		final GroupBuilder<?> group,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(JasperTestUtils.getHeaderGroupName(group), index, x, y, width, height);
	}
}
