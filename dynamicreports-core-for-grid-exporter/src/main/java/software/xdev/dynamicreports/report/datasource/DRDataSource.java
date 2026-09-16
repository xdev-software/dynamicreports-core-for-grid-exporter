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
package software.xdev.dynamicreports.report.datasource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRRewindableDataSource;


public class DRDataSource implements JRRewindableDataSource, Serializable
{

	private final String[] columns;
	private final List<Map<String, Object>> values;
	private Iterator<Map<String, Object>> iterator;
	private Map<String, Object> currentRecord;
	
	public DRDataSource(final String... columns)
	{
		this.columns = columns;
		this.values = new ArrayList<>();
	}
	
	public void add(final Object... values)
	{
		final Map<String, Object> row = new HashMap<>();
		for(int i = 0; i < values.length; i++)
		{
			row.put(this.columns[i], values[i]);
		}
		this.values.add(row);
	}
	
	@Override
	public Object getFieldValue(final JRField field) throws JRException
	{
		return this.currentRecord.get(field.getName());
	}
	
	@Override
	public boolean next() throws JRException
	{
		if(this.iterator == null)
		{
			this.iterator = this.values.iterator();
		}
		final boolean hasNext = this.iterator.hasNext();
		if(hasNext)
		{
			this.currentRecord = this.iterator.next();
		}
		return hasNext;
	}
	
	@Override
	public void moveFirst() throws JRException
	{
		this.iterator = null;
	}
}
