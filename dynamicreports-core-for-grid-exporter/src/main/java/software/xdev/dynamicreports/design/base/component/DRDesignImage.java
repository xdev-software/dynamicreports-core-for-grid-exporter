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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.definition.component.DRIDesignImage;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;


public class DRDesignImage extends DRDesignHyperlinkComponent implements DRIDesignImage
{

	private ImageScale imageScale;
	private DRIDesignExpression imageExpression;
	private Boolean usingCache;
	private Boolean lazy;
	private HorizontalImageAlignment horizontalImageAlignment;
	
	public DRDesignImage()
	{
		super("image");
	}
	
	@Override
	public DRIDesignExpression getImageExpression()
	{
		return this.imageExpression;
	}
	
	public void setImageExpression(final DRIDesignExpression imageExpression)
	{
		this.imageExpression = imageExpression;
	}
	
	@Override
	public ImageScale getImageScale()
	{
		return this.imageScale;
	}
	
	public void setImageScale(final ImageScale imageScale)
	{
		this.imageScale = imageScale;
	}
	
	@Override
	public Boolean getUsingCache()
	{
		return this.usingCache;
	}
	
	public void setUsingCache(final Boolean usingCache)
	{
		this.usingCache = usingCache;
	}
	
	@Override
	public Boolean getLazy()
	{
		return this.lazy;
	}
	
	public void setLazy(final Boolean lazy)
	{
		this.lazy = lazy;
	}
	
	@Override
	public HorizontalImageAlignment getHorizontalImageAlignment()
	{
		return this.horizontalImageAlignment;
	}
	
	public void setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.horizontalImageAlignment = horizontalImageAlignment;
	}
}
