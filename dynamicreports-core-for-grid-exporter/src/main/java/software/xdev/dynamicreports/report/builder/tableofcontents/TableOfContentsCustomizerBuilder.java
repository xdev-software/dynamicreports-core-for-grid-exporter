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
package software.xdev.dynamicreports.report.builder.tableofcontents;

import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;


public class TableOfContentsCustomizerBuilder
	extends AbstractBuilder<TableOfContentsCustomizerBuilder, TableOfContentsCustomizer>
{

	public TableOfContentsCustomizerBuilder()
	{
		super(new TableOfContentsCustomizer());
	}
	
	public TableOfContentsCustomizer getTableOfContents()
	{
		return this.build();
	}
	
	public TableOfContentsCustomizerBuilder setTitleStyle(final ReportStyleBuilder titleStyle)
	{
		this.getObject().setTitleStyle(titleStyle);
		return this;
	}
	
	public TableOfContentsCustomizerBuilder setHeadingStyle(final ReportStyleBuilder headingStyle)
	{
		this.getObject().setHeadingStyle(headingStyle);
		return this;
	}
	
	public TableOfContentsCustomizerBuilder setHeadingStyle(final int level, final ReportStyleBuilder headingStyle)
	{
		this.getObject().setHeadingStyle(level, headingStyle);
		return this;
	}
	
	public TableOfContentsCustomizerBuilder setTextFixedWidth(final Integer textFixedWidth)
	{
		this.getObject().setTextFixedWidth(textFixedWidth);
		return this;
	}
	
	public TableOfContentsCustomizerBuilder setDotsFixedWidth(final Integer dotsFixedWidth)
	{
		this.getObject().setDotsFixedWidth(dotsFixedWidth);
		return this;
	}
	
	public TableOfContentsCustomizerBuilder setPageIndexFixedWidth(final Integer pageIndexFixedWidth)
	{
		this.getObject().setPageIndexFixedWidth(pageIndexFixedWidth);
		return this;
	}
	
	public TableOfContentsCustomizerBuilder setPosition(final TableOfContentsPosition position)
	{
		this.getObject().setPosition(position);
		return this;
	}
}
