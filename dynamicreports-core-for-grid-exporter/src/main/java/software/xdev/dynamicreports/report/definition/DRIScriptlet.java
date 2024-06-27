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

public interface DRIScriptlet
{
	
	public String getName();
	
	public void beforeReportInit(ReportParameters reportParameters);
	
	public void afterReportInit(ReportParameters reportParameters);
	
	public void beforePageInit(ReportParameters reportParameters);
	
	public void afterPageInit(ReportParameters reportParameters);
	
	public void beforeColumnInit(ReportParameters reportParameters);
	
	public void afterColumnInit(ReportParameters reportParameters);
	
	public void beforeGroupInit(String groupName, ReportParameters reportParameters);
	
	public void afterGroupInit(String groupName, ReportParameters reportParameters);
	
	public void beforeDetailEval(ReportParameters reportParameters);
	
	public void afterDetailEval(ReportParameters reportParameters);
}
