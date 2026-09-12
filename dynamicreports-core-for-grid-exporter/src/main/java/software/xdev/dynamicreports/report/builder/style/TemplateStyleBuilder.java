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

import software.xdev.dynamicreports.report.base.style.DRTemplateStyle;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;


public class TemplateStyleBuilder extends AbstractBuilder<TemplateStyleBuilder, DRTemplateStyle>
	implements ReportStyleBuilder
{

	protected TemplateStyleBuilder(final String name)
	{
		super(new DRTemplateStyle(name));
	}
	
	@Override
	public DRTemplateStyle getStyle()
	{
		return this.build();
	}
}
