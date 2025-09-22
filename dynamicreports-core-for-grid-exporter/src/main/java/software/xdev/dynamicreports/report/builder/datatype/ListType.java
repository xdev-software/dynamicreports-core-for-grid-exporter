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
package software.xdev.dynamicreports.report.builder.datatype;

import java.util.List;

import software.xdev.dynamicreports.report.base.datatype.AbstractDataType;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.defaults.Defaults;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


@SuppressWarnings("rawtypes")
public class ListType extends AbstractDataType<List, List>
{

	private static final ListFormatter LIST_FORMATTER = new ListFormatter();
	
	@Override
	public DRIValueFormatter<?, ? extends List> getValueFormatter()
	{
		return LIST_FORMATTER;
	}
	
	@Override
	public String getPattern()
	{
		return Defaults.getDefaults().getStringType().getPattern();
	}
	
	@Override
	public HorizontalTextAlignment getHorizontalTextAlignment()
	{
		return Defaults.getDefaults().getStringType().getHorizontalTextAlignment();
	}
	
	static class ListFormatter extends AbstractValueFormatter<String, List>
	{

		@Override
		public String format(final List values, final ReportParameters reportParameters)
		{
			final StringBuilder result = new StringBuilder();
			for(final Object value : values)
			{
				if(!result.isEmpty())
				{
					result.append("\n");
				}
				if(value != null)
				{
					result.append(value.toString());
				}
			}
			return result.toString();
		}
	}
}
