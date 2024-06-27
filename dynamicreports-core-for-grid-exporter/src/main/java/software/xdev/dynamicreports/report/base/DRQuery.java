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
package software.xdev.dynamicreports.report.base;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.DRIQuery;


public class DRQuery implements DRIQuery
{

	private final String text;
	private final String language;
	
	public DRQuery(final String text, final String language)
	{
		Validate.notNull(text, "text must not be null");
		Validate.notNull(language, "language must not be null");
		this.text = text;
		this.language = language;
	}
	
	@Override
	public String getText()
	{
		return this.text;
	}
	
	@Override
	public String getLanguage()
	{
		return this.language;
	}
}
