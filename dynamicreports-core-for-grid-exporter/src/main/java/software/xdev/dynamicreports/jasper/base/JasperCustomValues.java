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
package software.xdev.dynamicreports.jasper.base;

import static java.lang.Boolean.TRUE;
import static software.xdev.dynamicreports.jasper.base.JasperScriptletManager.USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import software.xdev.dynamicreports.jasper.constant.ValueType;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.DRICustomValues;


/**
 * <p>
 * JasperCustomValues class.
 * </p>
 *
 * @author edwin.njeru
 */
public class JasperCustomValues implements DRICustomValues
{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private Map<String, ValueType> valueTypes;
	private Map<String, DRIDesignSimpleExpression> simpleExpressions;
	private Map<String, DRIDesignComplexExpression> complexExpressions;
	private Map<String, Object> systemValues;
	private Integer startPageNumber;
	private Map<String, JasperTocHeading> tocHeadings;
	private Integer subreportWidth;
	private transient JasperScriptletManager scriptletManager;
	
	/**
	 * <p>
	 * Constructor for JasperCustomValues.
	 * </p>
	 *
	 * @param properties a {@link java.util.Properties} object.
	 */
	public JasperCustomValues(final Properties properties)
	{
		this.init(properties);
	}
	
	private void init(final Properties properties)
	{
		this.valueTypes = new HashMap<>();
		this.simpleExpressions = new HashMap<>();
		this.complexExpressions = new HashMap<>();
		this.systemValues = new HashMap<>();
		this.scriptletManager = this.createScriptletManager(properties);
	}
	
	private JasperScriptletManager createScriptletManager(final Properties properties)
	{
		final String useThreadSafeScriptletManagerPropertyValue =
			properties.getProperty(USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY);
		if(TRUE.toString().equalsIgnoreCase(useThreadSafeScriptletManagerPropertyValue))
		{
			return new ThreadSafeJasperScriptletManager();
		}
		return new DefaultJasperScriptletManager();
	}
	
	/**
	 * <p>
	 * addSimpleExpression.
	 * </p>
	 *
	 * @param simpleExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression}
	 *                         object.
	 */
	public void addSimpleExpression(final DRIDesignSimpleExpression simpleExpression)
	{
		this.simpleExpressions.put(simpleExpression.getName(), simpleExpression);
		this.addValueType(simpleExpression.getName(), ValueType.SIMPLE_EXPRESSION);
	}
	
	/**
	 * <p>
	 * addComplexExpression.
	 * </p>
	 *
	 * @param complexExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression}
	 *                          object.
	 */
	public void addComplexExpression(final DRIDesignComplexExpression complexExpression)
	{
		this.complexExpressions.put(complexExpression.getName(), complexExpression);
		this.addValueType(complexExpression.getName(), ValueType.COMPLEX_EXPRESSION);
	}
	
	/**
	 * <p>
	 * addValueType.
	 * </p>
	 *
	 * @param name      a {@link java.lang.String} object.
	 * @param valueType a {@link software.xdev.dynamicreports.jasper.constant.ValueType} object.
	 */
	public void addValueType(final String name, final ValueType valueType)
	{
		this.valueTypes.put(name, valueType);
	}
	
	/**
	 * <p>
	 * getValueType.
	 * </p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.jasper.constant.ValueType} object.
	 */
	protected ValueType getValueType(final String name)
	{
		return this.valueTypes.get(name);
	}
	
	/**
	 * <p>
	 * getSimpleExpression.
	 * </p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression} object.
	 */
	protected DRIDesignSimpleExpression getSimpleExpression(final String name)
	{
		return this.simpleExpressions.get(name);
	}
	
	/**
	 * <p>
	 * getComplexExpression.
	 * </p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression} object.
	 */
	protected DRIDesignComplexExpression getComplexExpression(final String name)
	{
		return this.complexExpressions.get(name);
	}
	
	/**
	 * <p>
	 * isEmpty.
	 * </p>
	 *
	 * @return a boolean.
	 */
	public boolean isEmpty()
	{
		if(!this.simpleExpressions.isEmpty())
		{
			return false;
		}
		if(!this.complexExpressions.isEmpty())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * <p>
	 * getValue.
	 * </p>
	 *
	 * @param valueName a {@link java.lang.String} object.
	 * @return a {@link java.lang.Object} object.
	 */
	public Object getValue(final String valueName)
	{
		return this.scriptletManager.getJasperScriptlet().getValue(valueName);
	}
	
	/**
	 * <p>
	 * getValue.
	 * </p>
	 *
	 * @param name   a {@link java.lang.String} object.
	 * @param values an array of {@link java.lang.Object} objects.
	 * @return a {@link java.lang.Object} object.
	 */
	public Object getValue(final String name, final Object[] values)
	{
		return this.scriptletManager.getJasperScriptlet().getValue(name, values);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSystemValue(final String name, final Object value)
	{
		this.systemValues.put(name, value);
	}
	
	/**
	 * <p>
	 * getSystemValue.
	 * </p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link java.lang.Object} object.
	 */
	protected Object getSystemValue(final String name)
	{
		return this.systemValues.get(name);
	}
	
	/**
	 * <p>
	 * Getter for the field <code>jasperScriptlet</code>.
	 * </p>
	 *
	 * @return a {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} object.
	 */
	protected JasperScriptlet getJasperScriptlet()
	{
		return this.scriptletManager.getJasperScriptlet();
	}
	
	/**
	 * <p>
	 * Setter for the field <code>jasperScriptlet</code>.
	 * </p>
	 *
	 * @param jasperScriptlet a {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} object.
	 */
	protected void setJasperScriptlet(final JasperScriptlet jasperScriptlet)
	{
		this.scriptletManager.setJasperScriptlet(jasperScriptlet);
	}
	
	/**
	 * <p>
	 * Getter for the field <code>startPageNumber</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getStartPageNumber()
	{
		return this.startPageNumber;
	}
	
	/**
	 * <p>
	 * Setter for the field <code>startPageNumber</code>.
	 * </p>
	 *
	 * @param startPageNumber a {@link java.lang.Integer} object.
	 */
	public void setStartPageNumber(final Integer startPageNumber)
	{
		this.startPageNumber = startPageNumber;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTocHeading(final int level, final String id, final String text, final Object customValue)
	{
		final JasperTocHeading heading = new JasperTocHeading();
		heading.setLevel(level);
		heading.setText(text);
		heading.setReference(id);
		heading.setCustomValue(customValue);
		this.tocHeadings.put(id, heading);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, JasperTocHeading> getTocHeadings()
	{
		return this.tocHeadings;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTocHeadings(final Map<String, JasperTocHeading> tocHeadings)
	{
		this.tocHeadings = tocHeadings;
	}
	
	/**
	 * <p>
	 * Getter for the field <code>subreportWidth</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getSubreportWidth()
	{
		return this.subreportWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSubreportWidth(final Integer subreportWidth)
	{
		this.subreportWidth = subreportWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		final StringBuilder result = new StringBuilder();
		for(final String name : this.valueTypes.keySet())
		{
			result.append(this.valueTypes.get(name).name() + ":" + name);
			result.append(", ");
		}
		return "{" + StringUtils.removeEnd(result.toString(), ", ") + "}";
	}
	
	/**
	 * Getter for testing purposes.
	 *
	 * @return the {@link JasperScriptletManager}
	 */
	JasperScriptletManager getScriptletManager()
	{
		return this.scriptletManager;
	}
}
