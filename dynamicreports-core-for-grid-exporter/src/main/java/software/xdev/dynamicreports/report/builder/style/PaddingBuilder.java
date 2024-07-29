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
package software.xdev.dynamicreports.report.builder.style;

import software.xdev.dynamicreports.report.base.style.DRPadding;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;


public class PaddingBuilder extends AbstractBuilder<PaddingBuilder, DRPadding>
{

	protected PaddingBuilder()
	{
		super(new DRPadding());
	}
	
	protected PaddingBuilder(final int padding)
	{
		super(new DRPadding(padding));
	}
	
	public PaddingBuilder setTop(final Integer top)
	{
		this.getObject().setTop(top);
		return this;
	}
	
	public PaddingBuilder setLeft(final Integer left)
	{
		this.getObject().setLeft(left);
		return this;
	}
	
	public PaddingBuilder setBottom(final Integer bottom)
	{
		this.getObject().setBottom(bottom);
		return this;
	}
	
	public PaddingBuilder setRight(final Integer right)
	{
		this.getObject().setRight(right);
		return this;
	}
	
	public DRPadding getPadding()
	{
		return this.build();
	}
}
