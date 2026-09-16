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
package software.xdev.dynamicreports.report.builder.component;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;

import software.xdev.dynamicreports.report.base.component.DRImage;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings("deprecation")
public class ImageBuilder extends HyperLinkComponentBuilder<ImageBuilder, DRImage>
{

	protected ImageBuilder()
	{
		super(new DRImage());
	}
	
	public ImageBuilder setImage(final DRIExpression<?> imageExpression)
	{
		this.getObject().setImageExpression(imageExpression);
		return this;
	}
	
	public ImageBuilder setImage(final String imagePath)
	{
		this.getObject().setImageExpression(Expressions.text(imagePath));
		this.getObject().setUsingCache(true);
		return this;
	}
	
	public ImageBuilder setImage(final Image image)
	{
		this.getObject().setImageExpression(Expressions.image(image));
		this.getObject().setUsingCache(true);
		return this;
	}
	
	public ImageBuilder setImage(final InputStream imageInputStream)
	{
		this.getObject().setImageExpression(Expressions.inputStream(imageInputStream));
		this.getObject().setUsingCache(true);
		return this;
	}
	
	public ImageBuilder setImage(final URL imageUrl)
	{
		this.getObject().setImageExpression(Expressions.url(imageUrl));
		this.getObject().setUsingCache(true);
		return this;
	}
	
	public ImageBuilder setImageScale(final ImageScale imageScale)
	{
		this.getObject().setImageScale(imageScale);
		return this;
	}
	
	public ImageBuilder setUsingCache(final Boolean usingCache)
	{
		this.getObject().setUsingCache(usingCache);
		return this;
	}
	
	public ImageBuilder setLazy(final Boolean lazy)
	{
		this.getObject().setLazy(lazy);
		return this;
	}
	
	public ImageBuilder setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.getObject().setHorizontalImageAlignment(horizontalImageAlignment);
		return this;
	}
}
