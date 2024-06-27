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

import java.awt.Color;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.style.DRBaseStyle;
import software.xdev.dynamicreports.report.base.style.DRTabStop;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.LineSpacing;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.Rotation;
import software.xdev.dynamicreports.report.constant.TabStopAlignment;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;


@SuppressWarnings({"unchecked"})
public abstract class BaseStyleBuilder<T extends BaseStyleBuilder<T, U>, U extends DRBaseStyle>
	extends AbstractBuilder<T, U>
{
	protected BaseStyleBuilder(final U baseStyle)
	{
		super(baseStyle);
	}
	
	public T setBackgroundColor(final Color backgroundColor)
	{
		this.getObject().setBackgroundColor(backgroundColor);
		return (T)this;
	}
	
	public T setBorder(final BorderBuilder border)
	{
		if(border != null)
		{
			this.getObject().setBorder(border.build());
		}
		else
		{
			this.getObject().setBorder(null);
		}
		return (T)this;
	}
	
	public T setBorder(final PenBuilder pen)
	{
		return this.setBorder(pen != null ? Styles.border(pen) : null);
	}
	
	public T setTopBorder(final PenBuilder topPen)
	{
		if(topPen != null)
		{
			this.getObject().getBorder().setTopPen(topPen.build());
		}
		else
		{
			this.getObject().getBorder().setTopPen(null);
		}
		return (T)this;
	}
	
	public T setLeftBorder(final PenBuilder leftPen)
	{
		if(leftPen != null)
		{
			this.getObject().getBorder().setLeftPen(leftPen.build());
		}
		else
		{
			this.getObject().getBorder().setLeftPen(null);
		}
		return (T)this;
	}
	
	public T setBottomBorder(final PenBuilder bottomPen)
	{
		if(bottomPen != null)
		{
			this.getObject().getBorder().setBottomPen(bottomPen.build());
		}
		else
		{
			this.getObject().getBorder().setBottomPen(null);
		}
		return (T)this;
	}
	
	public T setRightBorder(final PenBuilder rightPen)
	{
		if(rightPen != null)
		{
			this.getObject().getBorder().setRightPen(rightPen.build());
		}
		else
		{
			this.getObject().getBorder().setRightPen(null);
		}
		return (T)this;
	}
	
	public T setFont(final FontBuilder font)
	{
		if(font != null)
		{
			this.getObject().setFont(font.build());
		}
		else
		{
			this.getObject().setFont(null);
		}
		return (T)this;
	}
	
	public T bold()
	{
		return this.setBold(true);
	}
	
	public T setBold(final Boolean bold)
	{
		this.getObject().getFont().setBold(bold);
		return (T)this;
	}
	
	public T setFontName(final String fontName)
	{
		this.getObject().getFont().setFontName(fontName);
		return (T)this;
	}
	
	public T setFontSize(final Integer fontSize)
	{
		this.getObject().getFont().setFontSize(fontSize);
		return (T)this;
	}
	
	public T italic()
	{
		return this.setItalic(true);
	}
	
	public T setItalic(final Boolean italic)
	{
		this.getObject().getFont().setItalic(italic);
		return (T)this;
	}
	
	public T boldItalic()
	{
		this.setBold(true);
		return this.setItalic(true);
	}
	
	public T strikeThrough()
	{
		return this.setStrikeThrough(true);
	}
	
	public T setStrikeThrough(final Boolean strikeThrough)
	{
		this.getObject().getFont().setStrikeThrough(strikeThrough);
		return (T)this;
	}
	
	public T underline()
	{
		return this.setUnderline(true);
	}
	
	public T setUnderline(final Boolean underline)
	{
		this.getObject().getFont().setUnderline(underline);
		return (T)this;
	}
	
	public T setForegroundColor(final Color foregroudColor)
	{
		this.getObject().setForegroundColor(foregroudColor);
		return (T)this;
	}
	
	public T setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getObject().setHorizontalTextAlignment(horizontalTextAlignment);
		return (T)this;
	}
	
	public T setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.getObject().setHorizontalImageAlignment(horizontalImageAlignment);
		return (T)this;
	}
	
	public T setImageScale(final ImageScale imageScale)
	{
		this.getObject().setImageScale(imageScale);
		return (T)this;
	}
	
	public T setPadding(final PaddingBuilder padding)
	{
		if(padding != null)
		{
			this.getObject().setPadding(padding.build());
		}
		else
		{
			this.getObject().setPadding(null);
		}
		return (T)this;
	}
	
	public T setPadding(final Integer padding)
	{
		return this.setPadding(Styles.padding(padding));
	}
	
	public T setTopPadding(final Integer top)
	{
		this.getObject().getPadding().setTop(top);
		return (T)this;
	}
	
	public T setLeftPadding(final Integer left)
	{
		this.getObject().getPadding().setLeft(left);
		return (T)this;
	}
	
	public T setBottomPadding(final Integer bottom)
	{
		this.getObject().getPadding().setBottom(bottom);
		return (T)this;
	}
	
	public T setRightPadding(final Integer right)
	{
		this.getObject().getPadding().setRight(right);
		return (T)this;
	}
	
	public T setPattern(final String pattern)
	{
		this.getObject().setPattern(pattern);
		return (T)this;
	}
	
	public T setRadius(final Integer radius)
	{
		this.getObject().setRadius(radius);
		return (T)this;
	}
	
	public T setRotation(final Rotation rotation)
	{
		this.getObject().setRotation(rotation);
		return (T)this;
	}
	
	public T setTextAlignment(
		final HorizontalTextAlignment horizontalTextAlignment,
		final VerticalTextAlignment verticalTextAlignment)
	{
		this.getObject().setHorizontalTextAlignment(horizontalTextAlignment);
		this.getObject().setVerticalTextAlignment(verticalTextAlignment);
		return (T)this;
	}
	
	public T setImageAlignment(
		final HorizontalImageAlignment horizontalImageAlignment,
		final VerticalImageAlignment verticalImageAlignment)
	{
		this.getObject().setHorizontalImageAlignment(horizontalImageAlignment);
		this.getObject().setVerticalImageAlignment(verticalImageAlignment);
		return (T)this;
	}
	
	public T setVerticalTextAlignment(final VerticalTextAlignment verticalTextAlignment)
	{
		this.getObject().setVerticalTextAlignment(verticalTextAlignment);
		return (T)this;
	}
	
	public T setVerticalImageAlignment(final VerticalImageAlignment verticalImageAlignment)
	{
		this.getObject().setVerticalImageAlignment(verticalImageAlignment);
		return (T)this;
	}
	
	public T setMarkup(final Markup markup)
	{
		this.getObject().setMarkup(markup);
		return (T)this;
	}
	
	public T setLineSpacing(final LineSpacing lineSpacing)
	{
		this.getObject().getParagraph().setLineSpacing(lineSpacing);
		return (T)this;
	}
	
	public T setLineSpacingSize(final Float lineSpacingSize)
	{
		this.getObject().getParagraph().setLineSpacingSize(lineSpacingSize);
		return (T)this;
	}
	
	public T setFirstLineIndent(final Integer firstLineIndent)
	{
		this.getObject().getParagraph().setFirstLineIndent(firstLineIndent);
		return (T)this;
	}
	
	public T setLeftIndent(final Integer leftIndent)
	{
		this.getObject().getParagraph().setLeftIndent(leftIndent);
		return (T)this;
	}
	
	public T setRightIndent(final Integer rightIndent)
	{
		this.getObject().getParagraph().setRightIndent(rightIndent);
		return (T)this;
	}
	
	public T setSpacingBefore(final Integer spacingBefore)
	{
		this.getObject().getParagraph().setSpacingBefore(spacingBefore);
		return (T)this;
	}
	
	public T setSpacingAfter(final Integer spacingAfter)
	{
		this.getObject().getParagraph().setSpacingAfter(spacingAfter);
		return (T)this;
	}
	
	public T setTabStopWidth(final Integer tabStopWidth)
	{
		this.getObject().getParagraph().setTabStopWidth(tabStopWidth);
		return (T)this;
	}
	
	public T addTabStop(final int position, final TabStopAlignment alignment)
	{
		Validate.notNull(alignment, "alignment must not be null");
		final DRTabStop tabStop = new DRTabStop();
		tabStop.setPosition(position);
		tabStop.setAlignment(alignment);
		this.getObject().getParagraph().getTabStops().add(tabStop);
		return (T)this;
	}
	
	public T setLinePen(final PenBuilder linePen)
	{
		if(linePen != null)
		{
			this.getObject().setLinePen(linePen.build());
		}
		else
		{
			this.getObject().setLinePen(null);
		}
		return (T)this;
	}
	
	public U getStyle()
	{
		return this.build();
	}
}
