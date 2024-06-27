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
package software.xdev.dynamicreports.jasper.base.tableofcontents;

import java.io.Serializable;


public class JasperTocHeading implements Serializable
{

	private Integer level;
	private String text;
	private String reference;
	private Integer pageIndex;
	private Object customValue;
	
	public Integer getLevel()
	{
		return this.level;
	}
	
	public void setLevel(final Integer level)
	{
		this.level = level;
	}
	
	public String getText()
	{
		return this.text;
	}
	
	public void setText(final String text)
	{
		this.text = text;
	}
	
	public String getReference()
	{
		return this.reference;
	}
	
	public void setReference(final String reference)
	{
		this.reference = reference;
	}
	
	public Integer getPageIndex()
	{
		return this.pageIndex;
	}
	
	public void setPageIndex(final Integer pageIndex)
	{
		this.pageIndex = pageIndex;
	}
	
	public Object getCustomValue()
	{
		return this.customValue;
	}
	
	public void setCustomValue(final Object customValue)
	{
		this.customValue = customValue;
	}
}
