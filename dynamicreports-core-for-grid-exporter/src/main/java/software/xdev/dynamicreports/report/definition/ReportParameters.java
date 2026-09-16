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
package software.xdev.dynamicreports.report.definition;

import java.sql.Connection;
import java.util.Locale;


public interface ReportParameters
{
	public static final String CROSSTAB_ROW_COUNTER = "CROSSTAB_ROW_NUMBER";
	
	public <T> T getValue(String name);
	
	public <T> T getValue(DRIValue<T> value);
	
	public <T> T getFieldValue(String name);
	
	public <T> T getVariableValue(String name);
	
	public <T> T getParameterValue(String name);
	
	public Integer getPageNumber();
	
	public Integer getColumnNumber();
	
	public Integer getReportRowNumber();
	
	public Integer getPageRowNumber();
	
	public Integer getColumnRowNumber();
	
	public Integer getCrosstabRowNumber();
	
	public Integer getGroupCount(String groupName);
	
	public Connection getConnection();
	
	public Locale getLocale();
	
	public DRIScriptlet getScriptlet(String name);
	
	public String getMessage(String key);
	
	public String getMessage(String key, Object[] arguments);
	
	public ReportParameters getMasterParameters();
	
	public Integer getSubreportWidth();
}
