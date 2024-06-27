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
package software.xdev.dynamicreports.report.builder.group;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.column.DRValueColumn;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;


public class ColumnGroupBuilder extends GroupBuilder<ColumnGroupBuilder>
{

	private final DRValueColumn<?> column;
	
	protected ColumnGroupBuilder(final ValueColumnBuilder<?, ?> column)
	{
		Validate.notNull(column, "column must not be null");
		this.column = column.build();
		this.init();
	}
	
	protected ColumnGroupBuilder(final String name, final ValueColumnBuilder<?, ?> column)
	{
		super(name);
		Validate.notNull(column, "column must not be null");
		this.column = column.build();
		this.init();
	}
	
	@SuppressWarnings("unchecked")
	private void init()
	{
		@SuppressWarnings("rawtypes")
		final DRIDataType dataType = this.column.getComponent().getDataType();
		this.getObject().getValueField().setDataType(dataType);
		this.getObject().getValueField().setStyle(this.column.getComponent().getStyle());
		this.getObject().getValueField().setPattern(this.column.getComponent().getPattern());
		this.getObject().getValueField().setPatternExpression(this.column.getComponent().getPatternExpression());
		this.getObject()
			.getValueField()
			.setHorizontalTextAlignment(this.column.getComponent().getHorizontalTextAlignment());
		this.getObject().setTitleExpression(this.column.getTitleExpression());
		this.getObject().setTitleStyle(this.column.getTitleStyle());
		this.getObject().setTitleWidth(this.column.getComponent().getWidth());
	}
	
	public ColumnGroupBuilder setHideColumn(final Boolean hideColumn)
	{
		this.getObject().setHideColumn(hideColumn);
		return this;
	}
	
	@Override
	protected void configure()
	{
		this.setValueExpression(this.column);
		super.configure();
	}
}
