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
package software.xdev.dynamicreports.report.base.datatype;

import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


public class DRDataType<U, T extends U> extends AbstractDataType<U, T>
{

	private String pattern;
	private HorizontalTextAlignment horizontalTextAlignment;
	private DRIValueFormatter<?, ? extends U> valueFormatter;
	
	public DRDataType()
	{
	}
	
	public DRDataType(final String pattern)
	{
		this.pattern = pattern;
	}
	
	public DRDataType(final String pattern, final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.pattern = pattern;
		this.horizontalTextAlignment = horizontalTextAlignment;
	}
	
	@Override
	public String getPattern()
	{
		return this.pattern;
	}
	
	public void setPattern(final String pattern)
	{
		this.pattern = pattern;
	}
	
	@Override
	public HorizontalTextAlignment getHorizontalTextAlignment()
	{
		return this.horizontalTextAlignment;
	}
	
	public void setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.horizontalTextAlignment = horizontalTextAlignment;
	}
	
	@Override
	public DRIValueFormatter<?, ? extends U> getValueFormatter()
	{
		return this.valueFormatter;
	}
	
	public void setValueFormatter(final DRIValueFormatter<?, ? extends U> valueFormatter)
	{
		this.valueFormatter = valueFormatter;
	}
	
	public String toString(final T value)
	{
		return String.valueOf(value);
	}
}
