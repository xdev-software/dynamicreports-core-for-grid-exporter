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

import software.xdev.dynamicreports.report.base.DRSubtotal;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.BaseSubtotalBuilder;


public final class JasperTestUtils
{
	private JasperTestUtils()
	{
	}
	
	// column detail
	public static String getColumnDetailName(final ColumnBuilder<?, ?> column)
	{
		return "detail.column_" + column.build().getName() + "1";
	}
	
	// column title
	public static String getColumnTitleName(final ColumnBuilder<?, ?> column)
	{
		return "columnHeader.column_" + column.build().getName() + ".title1";
	}
	
	// subtotal
	private static String getSubtotalName(final BaseSubtotalBuilder<?, ?> subtotal)
	{
		String band = null;
		final DRSubtotal<?> subtl = subtotal.getSubtotal();
		switch(subtl.getPosition())
		{
			case TITLE:
				band = "title";
				break;
			case PAGE_HEADER:
				band = "pageHeader";
				break;
			case PAGE_FOOTER:
				band = "pageFooter";
				break;
			case COLUMN_HEADER:
				band = "columnHeader";
				break;
			case COLUMN_FOOTER:
				band = "columnFooter";
				break;
			case GROUP_HEADER:
			case FIRST_GROUP_HEADER:
			case LAST_GROUP_HEADER:
				band = "subtotalGroupHeader";
				break;
			case GROUP_FOOTER:
			case FIRST_GROUP_FOOTER:
			case LAST_GROUP_FOOTER:
				band = "subtotalGroupFooter";
				break;
			case LAST_PAGE_FOOTER:
				band = "lastPageFooter";
				break;
			case SUMMARY:
				band = "summary";
				break;
			default:
				Assertions.fail("Subtotal position " + subtl.getPosition().name() + " not found");
				return null;
		}
		return band + ".column_" + subtl.getShowInColumn().getName() + ".subtotal";
	}
	
	public static String getSubtotalLabelName(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex)
	{
		return getSubtotalName(subtotal) + ".label" + subtotalIndex;
	}
	
	public static String getSubtotalName(final BaseSubtotalBuilder<?, ?> subtotal, final int subtotalIndex)
	{
		return getSubtotalName(subtotal) + subtotalIndex;
	}
	
	// group header title
	public static String getHeaderTitleGroupName(final GroupBuilder<?> group)
	{
		return "groupHeaderTitleAndValue.group_" + group.getGroup().getName() + ".title1";
	}
	
	// group header
	public static String getHeaderGroupName(final GroupBuilder<?> group)
	{
		return "groupHeaderTitleAndValue.group_" + group.getGroup().getName() + "1";
	}
	
	// crosstab group header
	public static String getCrosstabGroupHeaderName(final AbstractCrosstabGroupBuilder<?, ?, ?> group)
	{
		return "group_" + group.build().getName() + ".header1";
	}
	
	// crosstab group total header
	public static String getCrosstabGroupTotalHeaderName(final AbstractCrosstabGroupBuilder<?, ?, ?> group)
	{
		return "group_" + group.build().getName() + ".totalheader1";
	}
	
	// crosstab group title header
	public static String getCrosstabGroupTitleHeaderName(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure)
	{
		return "group_" + group.build().getName() + ".titleheader." + measure.build().getName() + "1";
	}
	
	// crosstab group title total header
	public static String getCrosstabGroupTitleTotalHeaderName(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure)
	{
		return "group_" + group.build().getName() + ".titletotalheader." + measure.build().getName() + "1";
	}
	
	// crosstab cell
	public static String getCrosstabCellName(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup)
	{
		String name = "cell_measure[" + measure.build().getName() + "]";
		if(rowGroup != null)
		{
			name += "_rowgroup[" + rowGroup.build().getName() + "]";
		}
		if(columnGroup != null)
		{
			name += "_columngroup[" + columnGroup.build().getName() + "]";
		}
		return name + "1";
	}
}
