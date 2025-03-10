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
package software.xdev.dynamicreports.report.builder.crosstab;

import java.sql.Connection;

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.report.base.crosstab.DRCrosstab;
import software.xdev.dynamicreports.report.builder.DatasetBuilder;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.builder.component.DimensionComponentBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder;
import software.xdev.dynamicreports.report.constant.RunDirection;


public class CrosstabBuilder extends DimensionComponentBuilder<CrosstabBuilder, DRCrosstab>
{

	protected CrosstabBuilder()
	{
		super(new DRCrosstab());
	}
	
	public CrosstabBuilder setRepeatColumnHeaders(final Boolean repeatColumnHeaders)
	{
		this.getObject().setRepeatColumnHeaders(repeatColumnHeaders);
		return this;
	}
	
	public CrosstabBuilder setRepeatRowHeaders(final Boolean repeatRowHeaders)
	{
		this.getObject().setRepeatRowHeaders(repeatRowHeaders);
		return this;
	}
	
	public CrosstabBuilder setColumnBreakOffset(final Integer columnBreakOffset)
	{
		this.getObject().setColumnBreakOffset(columnBreakOffset);
		return this;
	}
	
	public CrosstabBuilder setIgnoreWidth(final Boolean ignoreWidth)
	{
		this.getObject().setIgnoreWidth(ignoreWidth);
		return this;
	}
	
	public CrosstabBuilder setRunDirection(final RunDirection runDirection)
	{
		this.getObject().setRunDirection(runDirection);
		return this;
	}
	
	public CrosstabBuilder setCellWidth(final Integer cellWidth)
	{
		this.getObject().setCellWidth(cellWidth);
		return this;
	}
	
	public CrosstabBuilder setCellHeight(final Integer cellHeight)
	{
		this.getObject().setCellHeight(cellHeight);
		return this;
	}
	
	public CrosstabBuilder highlightOddRows()
	{
		return this.setHighlightOddRows(true);
	}
	
	public CrosstabBuilder setHighlightOddRows(final Boolean highlightOddRows)
	{
		this.getObject().setHighlightOddRows(highlightOddRows);
		return this;
	}
	
	public CrosstabBuilder setOddRowStyle(final SimpleStyleBuilder oddRowStyle)
	{
		if(oddRowStyle != null)
		{
			this.getObject().setOddRowStyle(oddRowStyle.build());
		}
		else
		{
			this.getObject().setOddRowStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder setGroupStyle(final ReportStyleBuilder groupStyle)
	{
		if(groupStyle != null)
		{
			this.getObject().setGroupStyle(groupStyle.build());
		}
		else
		{
			this.getObject().setGroupStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder setGroupTotalStyle(final ReportStyleBuilder groupTotalStyle)
	{
		if(groupTotalStyle != null)
		{
			this.getObject().setGroupTotalStyle(groupTotalStyle.build());
		}
		else
		{
			this.getObject().setGroupTotalStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder setGrandTotalStyle(final ReportStyleBuilder grandTotalStyle)
	{
		if(grandTotalStyle != null)
		{
			this.getObject().setGrandTotalStyle(grandTotalStyle.build());
		}
		else
		{
			this.getObject().setGrandTotalStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder setCellStyle(final ReportStyleBuilder cellStyle)
	{
		if(cellStyle != null)
		{
			this.getObject().setCellStyle(cellStyle.build());
		}
		else
		{
			this.getObject().setCellStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder setMeasureTitleStyle(final ReportStyleBuilder measureTitleStyle)
	{
		if(measureTitleStyle != null)
		{
			this.getObject().setMeasureTitleStyle(measureTitleStyle.build());
		}
		else
		{
			this.getObject().setMeasureTitleStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder highlightEvenRows()
	{
		return this.setHighlightEvenRows(true);
	}
	
	public CrosstabBuilder setHighlightEvenRows(final Boolean highlightEvenRows)
	{
		this.getObject().setHighlightEvenRows(highlightEvenRows);
		return this;
	}
	
	public CrosstabBuilder setEvenRowStyle(final SimpleStyleBuilder evenRowStyle)
	{
		if(evenRowStyle != null)
		{
			this.getObject().setEvenRowStyle(evenRowStyle.build());
		}
		else
		{
			this.getObject().setEvenRowStyle(null);
		}
		return this;
	}
	
	public CrosstabBuilder whenNoDataCell(final ComponentBuilder<?, ?>... components)
	{
		return this.addWhenNoDataCell(components);
	}
	
	public CrosstabBuilder addWhenNoDataCell(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getWhenNoDataCell().addComponent(component.build());
		}
		return this;
	}
	
	public CrosstabBuilder headerCell(final ComponentBuilder<?, ?>... components)
	{
		return this.addHeaderCell(components);
	}
	
	public CrosstabBuilder addHeaderCell(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getHeaderCell().addComponent(component.build());
		}
		return this;
	}
	
	public CrosstabBuilder columnGroups(final CrosstabColumnGroupBuilder<?>... columnGroups)
	{
		return this.addColumnGroup(columnGroups);
	}
	
	public CrosstabBuilder addColumnGroup(final CrosstabColumnGroupBuilder<?>... columnGroups)
	{
		Validate.notNull(columnGroups, "columnGroups must not be null");
		Validate.noNullElements(columnGroups, "columnGroups must not contains null columnGroup");
		for(final CrosstabColumnGroupBuilder<?> columnGroup : columnGroups)
		{
			this.getObject().addColumnGroup(columnGroup.build());
		}
		return this;
	}
	
	public CrosstabBuilder rowGroups(final CrosstabRowGroupBuilder<?>... rowGroups)
	{
		return this.addRowGroup(rowGroups);
	}
	
	public CrosstabBuilder addRowGroup(final CrosstabRowGroupBuilder<?>... rowGroups)
	{
		Validate.notNull(rowGroups, "rowGroups must not be null");
		Validate.noNullElements(rowGroups, "rowGroups must not contains null rowGroup");
		for(final CrosstabRowGroupBuilder<?> rowGroup : rowGroups)
		{
			this.getObject().addRowGroup(rowGroup.build());
		}
		return this;
	}
	
	public CrosstabBuilder variables(final CrosstabVariableBuilder<?>... variables)
	{
		return this.addVariable(variables);
	}
	
	public CrosstabBuilder addVariable(final CrosstabVariableBuilder<?>... variables)
	{
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null measure");
		for(final CrosstabVariableBuilder<?> variable : variables)
		{
			this.getObject().addVariable(variable.build());
		}
		return this;
	}
	
	public CrosstabBuilder measures(final CrosstabMeasureBuilder<?>... measures)
	{
		return this.addMeasure(measures);
	}
	
	public CrosstabBuilder addMeasure(final CrosstabMeasureBuilder<?>... measures)
	{
		Validate.notNull(measures, "measures must not be null");
		Validate.noNullElements(measures, "measures must not contains null measure");
		for(final CrosstabMeasureBuilder<?> measure : measures)
		{
			this.getObject().addMeasure(measure.build());
		}
		return this;
	}
	
	// subdataset
	
	public CrosstabBuilder setSubDataset(final DatasetBuilder subDataset)
	{
		Validate.notNull(subDataset, "subDataset must not be null");
		this.getObject().getDataset().setSubDataset(subDataset.build());
		return this;
	}
	
	public CrosstabBuilder setDataSource(final JRDataSource dataSource)
	{
		final DatasetBuilder dataset = DynamicReports.dataset();
		dataset.setDataSource(dataSource);
		return this.setSubDataset(dataset);
	}
	
	public CrosstabBuilder setDataSource(final String sql, final Connection connection)
	{
		final DatasetBuilder dataset = DynamicReports.dataset();
		dataset.setDataSource(sql, connection);
		return this.setSubDataset(dataset);
	}
	
	public CrosstabBuilder setDataPreSorted(final Boolean dataPreSorted)
	{
		this.getObject().getDataset().setDataPreSorted(dataPreSorted);
		return this;
	}
}
