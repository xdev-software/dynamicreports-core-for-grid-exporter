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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.jasper.base.JasperTemplateStyleLoader;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.exception.DRException;


public class TemplateStylesBuilder extends AbstractBuilder<TemplateStylesBuilder, List<StyleBuilder>>
{

	protected TemplateStylesBuilder()
	{
		super(new ArrayList<>());
	}
	
	public TemplateStylesBuilder loadStyles(final InputStream inputStream)
	{
		return this.addStyles(JasperTemplateStyleLoader.loadStyles(inputStream));
	}
	
	public TemplateStylesBuilder loadStyles(final File file)
	{
		return this.addStyles(JasperTemplateStyleLoader.loadStyles(file));
	}
	
	public TemplateStylesBuilder loadStyles(final String fileName) throws DRException
	{
		return this.addStyles(JasperTemplateStyleLoader.loadStyles(fileName));
	}
	
	public TemplateStylesBuilder loadStyles(final URL url)
	{
		return this.addStyles(JasperTemplateStyleLoader.loadStyles(url));
	}
	
	private TemplateStylesBuilder addStyles(final DRStyle[] styles)
	{
		for(final DRStyle style : styles)
		{
			this.getObject().add(new StyleBuilder(style));
		}
		return this;
	}
	
	public TemplateStylesBuilder styles(final StyleBuilder... styles)
	{
		return this.addStyle(styles);
	}
	
	public TemplateStylesBuilder addStyle(final StyleBuilder... styles)
	{
		Validate.notNull(styles, "styles must not be null");
		Validate.noNullElements(styles, "styles must not contains null style");
		for(final StyleBuilder templateStyle : styles)
		{
			this.getObject().add(templateStyle);
		}
		return this;
	}
	
	public StyleBuilder getStyle(final String name)
	{
		Validate.notNull(name, "name must not be null");
		for(final StyleBuilder style : this.getStyles())
		{
			if(name.equals(style.getStyle().getName()))
			{
				return style;
			}
		}
		return null;
	}
	
	public List<StyleBuilder> getStyles()
	{
		return this.build();
	}
}
