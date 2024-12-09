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
package software.xdev.dynamicreports.report.builder;

import software.xdev.dynamicreports.report.base.DRMargin;


public class MarginBuilder extends AbstractBuilder<MarginBuilder, DRMargin>
{

	protected MarginBuilder()
	{
		super(new DRMargin());
	}
	
	protected MarginBuilder(final int margin)
	{
		super(new DRMargin(margin));
	}
	
	public MarginBuilder setTop(final int top)
	{
		this.getObject().setTop(top);
		return this;
	}
	
	public MarginBuilder setLeft(final int left)
	{
		this.getObject().setLeft(left);
		return this;
	}
	
	public MarginBuilder setBottom(final int bottom)
	{
		this.getObject().setBottom(bottom);
		return this;
	}
	
	public MarginBuilder setRight(final int right)
	{
		this.getObject().setRight(right);
		return this;
	}
	
	public DRMargin getMargin()
	{
		return this.build();
	}
}
