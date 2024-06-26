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

import java.util.ResourceBundle;

import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class BooleanTextValueFormatter extends AbstractValueFormatter<String, Boolean>
{

	private final String keyTrue;
	private final String keyFalse;
	private final boolean emptyWhenNullValue;
	
	public BooleanTextValueFormatter(final String keyTrue, final String keyFalse, final boolean emptyWhenNullValue)
	{
		this.keyTrue = keyTrue;
		this.keyFalse = keyFalse;
		this.emptyWhenNullValue = emptyWhenNullValue;
	}
	
	@Override
	public String format(final Boolean value, final ReportParameters reportParameters)
	{
		if(this.emptyWhenNullValue && value == null)
		{
			return "";
		}
		final String key;
		if(value != null && value)
		{
			key = this.keyTrue;
		}
		else
		{
			key = this.keyFalse;
		}
		return ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_NAME, reportParameters.getLocale()).getString(key);
	}
}
