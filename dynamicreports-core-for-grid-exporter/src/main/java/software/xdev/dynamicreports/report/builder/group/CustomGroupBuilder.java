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

import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class CustomGroupBuilder extends GroupBuilder<CustomGroupBuilder>
{

	protected CustomGroupBuilder(final FieldBuilder<?> field)
	{
		Validate.notNull(field, "field must not be null");
		this.setValueExpression(field.build());
	}
	
	protected CustomGroupBuilder(final String name, final FieldBuilder<?> field)
	{
		super(name);
		Validate.notNull(field, "field must not be null");
		this.setValueExpression(field.build());
	}
	
	protected CustomGroupBuilder(final DRIExpression<?> valueExpression)
	{
		this.setValueExpression(valueExpression);
	}
	
	protected CustomGroupBuilder(final String name, final DRIExpression<?> valueExpression)
	{
		super(name);
		this.setValueExpression(valueExpression);
	}
	
	public CustomGroupBuilder setTitle(final DRIExpression<?> titleExpression)
	{
		this.getObject().setTitleExpression(titleExpression);
		this.getObject().setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE);
		return this;
	}
	
	public CustomGroupBuilder setTitle(final String title)
	{
		this.getObject().setTitleExpression(Expressions.text(title));
		this.getObject().setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE);
		return this;
	}
}
