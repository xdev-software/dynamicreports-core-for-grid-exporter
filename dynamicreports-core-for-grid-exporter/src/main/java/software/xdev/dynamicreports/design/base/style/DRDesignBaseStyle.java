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
package software.xdev.dynamicreports.design.base.style;

import java.awt.Color;
import java.util.Objects;

import software.xdev.dynamicreports.design.definition.style.DRIDesignBaseStyle;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.Rotation;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;


public abstract class DRDesignBaseStyle implements DRIDesignBaseStyle
{

	private Color foregroundColor;
	private Color backgroundColor;
	private Integer radius;
	private ImageScale imageScale;
	private HorizontalTextAlignment horizontalTextAlignment;
	private VerticalTextAlignment verticalTextAlignment;
	private HorizontalImageAlignment horizontalImageAlignment;
	private VerticalImageAlignment verticalImageAlignment;
	private DRDesignBorder border;
	private DRDesignPadding padding;
	private DRDesignFont font;
	private Rotation rotation;
	private String pattern;
	private Markup markup;
	private DRDesignParagraph paragraph;
	private DRDesignPen linePen;
	
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
	public DRDesignBorder getBorder()
	{
		return this.border;
	}
	
	public void setBorder(final DRDesignBorder border)
	{
		this.border = border;
	}
	
	@Override
	public DRDesignPadding getPadding()
	{
		return this.padding;
	}
	
	public void setPadding(final DRDesignPadding padding)
	{
		this.padding = padding;
	}
	
	@Override
	public DRDesignFont getFont()
	{
		return this.font;
	}
	
	public void setFont(final DRDesignFont font)
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
	public DRDesignParagraph getParagraph()
	{
		return this.paragraph;
	}
	
	public void setParagraph(final DRDesignParagraph paragraph)
	{
		this.paragraph = paragraph;
	}
	
	@Override
	public DRDesignPen getLinePen()
	{
		return this.linePen;
	}
	
	public void setLinePen(final DRDesignPen linePen)
	{
		this.linePen = linePen;
	}
	
	@Override
	public boolean equals(final Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || this.getClass() != o.getClass())
		{
			return false;
		}
		final DRDesignBaseStyle that = (DRDesignBaseStyle)o;
		return Objects.equals(this.getForegroundColor(), that.getForegroundColor())
			&& Objects.equals(this.getBackgroundColor(), that.getBackgroundColor())
			&& Objects.equals(this.getRadius(), that.getRadius())
			&& this.getImageScale() == that.getImageScale()
			&& this.getHorizontalTextAlignment() == that.getHorizontalTextAlignment()
			&& this.getVerticalTextAlignment() == that.getVerticalTextAlignment()
			&& this.getHorizontalImageAlignment() == that.getHorizontalImageAlignment()
			&& this.getVerticalImageAlignment() == that.getVerticalImageAlignment()
			&& Objects.equals(this.getBorder(), that.getBorder())
			&& Objects.equals(this.getPadding(), that.getPadding())
			&& Objects.equals(this.getFont(), that.getFont())
			&& this.getRotation() == that.getRotation()
			&& Objects.equals(this.getPattern(), that.getPattern())
			&& this.getMarkup() == that.getMarkup()
			&& Objects.equals(this.getParagraph(), that.getParagraph())
			&& Objects.equals(this.getLinePen(), that.getLinePen());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(
			this.getForegroundColor(),
			this.getBackgroundColor(),
			this.getRadius(),
			this.getImageScale(),
			this.getHorizontalTextAlignment(),
			this.getVerticalTextAlignment(),
			this.getHorizontalImageAlignment(),
			this.getVerticalImageAlignment(),
			this.getBorder(),
			this.getPadding(),
			this.getFont(),
			this.getRotation(),
			this.getPattern(),
			this.getMarkup(),
			this.getParagraph(),
			this.getLinePen());
	}
}
