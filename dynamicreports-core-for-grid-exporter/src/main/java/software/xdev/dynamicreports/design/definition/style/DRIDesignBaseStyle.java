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
package software.xdev.dynamicreports.design.definition.style;

import java.awt.Color;
import java.io.Serializable;

import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.Rotation;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;


public interface DRIDesignBaseStyle extends Serializable
{
	
	public Color getForegroundColor();
	
	public Color getBackgroundColor();
	
	public Integer getRadius();
	
	public ImageScale getImageScale();
	
	public HorizontalTextAlignment getHorizontalTextAlignment();
	
	public VerticalTextAlignment getVerticalTextAlignment();
	
	public HorizontalImageAlignment getHorizontalImageAlignment();
	
	public VerticalImageAlignment getVerticalImageAlignment();
	
	public DRIDesignBorder getBorder();
	
	public DRIDesignPadding getPadding();
	
	public DRIDesignFont getFont();
	
	public Rotation getRotation();
	
	public String getPattern();
	
	public Markup getMarkup();
	
	public DRIDesignParagraph getParagraph();
	
	public DRIDesignPen getLinePen();
}
