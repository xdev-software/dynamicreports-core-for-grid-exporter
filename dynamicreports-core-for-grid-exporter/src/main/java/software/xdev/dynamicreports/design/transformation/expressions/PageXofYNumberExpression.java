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
package software.xdev.dynamicreports.design.transformation.expressions;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class PageXofYNumberExpression extends AbstractComplexExpression<String>
{

	private final int index;
	
	public PageXofYNumberExpression(final DRIExpression<String> pageNumberFormatExpression, final int index)
	{
		this.addExpression(pageNumberFormatExpression);
		this.index = index;
	}
	
	@Override
	public String evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		String pattern = (String)values.get(0);
		Validate.isTrue(
			StringUtils.contains(pattern, "{0}"),
			"Wrong format pattern \"" + pattern + "\", missing argument {0}");
		Validate.isTrue(
			StringUtils.contains(pattern, "{1}"),
			"Wrong format pattern \"" + pattern + "\", missing argument {1}");
		Validate.isTrue(
			pattern.indexOf("{0}") < pattern.indexOf("{1}"),
			"Wrong format pattern \"" + pattern + "\", argument {0} must be before {1}");
		final int index1 = pattern.indexOf("{0}");
		if(this.index == 0)
		{
			pattern = pattern.substring(0, index1 + 3);
		}
		else
		{
			pattern = pattern.substring(index1 + 3);
		}
		final MessageFormat format = new MessageFormat(pattern, reportParameters.getLocale());
		final String result = format.format(new Object[]{
			reportParameters.getPageNumber(),
			reportParameters.getPageNumber()});
		return result;
	}
}
