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
package software.xdev.dynamicreports.report.base.style;

import java.awt.Color;

import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.Rotation;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;
import software.xdev.dynamicreports.report.definition.style.DRIBaseStyle;


public abstract class DRBaseStyle implements DRIBaseStyle
{

	private Color foregroundColor;
	private Color backgroundColor;
	private Integer radius;
	private ImageScale imageScale;
	private HorizontalImageAlignment horizontalImageAlignment;
	private VerticalImageAlignment verticalImageAlignment;
	private HorizontalTextAlignment horizontalTextAlignment;
	private VerticalTextAlignment verticalTextAlignment;
	private DRBorder border;
	private DRPadding padding;
	private DRFont font;
	private Rotation rotation;
	private String pattern;
	private Markup markup;
	private DRParagraph paragraph;
	private DRPen linePen;
	
	public DRBaseStyle()
	{
		this.init();
	}
	
	protected void init()
	{
		this.font = new DRFont();
		this.border = new DRBorder();
		this.padding = new DRPadding();
		this.paragraph = new DRParagraph();
		this.linePen = new DRPen();
	}
	
	@Override
	public Color getForegroundColor()
	{
		return this.foregroundColor;
	}
	
	public void setForegroundColor(final Color foregroundColor)
	{
		this.foregroundColor = foregroundColor;
	}
	
	@Override
	public Color getBackgroundColor()
	{
		return this.backgroundColor;
	}
	
	public void setBackgroundColor(final Color backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	
	@Override
	public Integer getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(final Integer radius)
	{
		this.radius = radius;
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
	public HorizontalImageAlignment getHorizontalImageAlignment()
	{
		return this.horizontalImageAlignment;
	}
	
	public void setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.horizontalImageAlignment = horizontalImageAlignment;
	}
	
	@Override
	public VerticalImageAlignment getVerticalImageAlignment()
	{
		return this.verticalImageAlignment;
	}
	
	public void setVerticalImageAlignment(final VerticalImageAlignment verticalImageAlignment)
	{
		this.verticalImageAlignment = verticalImageAlignment;
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
	public VerticalTextAlignment getVerticalTextAlignment()
	{
		return this.verticalTextAlignment;
	}
	
	public void setVerticalTextAlignment(final VerticalTextAlignment verticalTextAlignment)
	{
		this.verticalTextAlignment = verticalTextAlignment;
	}
	
	@Override
	public DRBorder getBorder()
	{
		return this.border;
	}
	
	public void setBorder(final DRBorder border)
	{
		this.border = border;
	}
	
	@Override
	public DRPadding getPadding()
	{
		return this.padding;
	}
	
	public void setPadding(final DRPadding padding)
	{
		this.padding = padding;
	}
	
	@Override
	public DRFont getFont()
	{
		return this.font;
	}
	
	public void setFont(final DRFont font)
	{
		this.font = font;
	}
	
	@Override
	public Rotation getRotation()
	{
		return this.rotation;
	}
	
	public void setRotation(final Rotation rotation)
	{
		this.rotation = rotation;
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
	public Markup getMarkup()
	{
		return this.markup;
	}
	
	public void setMarkup(final Markup markup)
	{
		this.markup = markup;
	}
	
	@Override
	public DRParagraph getParagraph()
	{
		return this.paragraph;
	}
	
	public void setParagraph(final DRParagraph paragraph)
	{
		this.paragraph = paragraph;
	}
	
	@Override
	public DRPen getLinePen()
	{
		return this.linePen;
	}
	
	public void setLinePen(final DRPen linePen)
	{
		this.linePen = linePen;
	}
}
