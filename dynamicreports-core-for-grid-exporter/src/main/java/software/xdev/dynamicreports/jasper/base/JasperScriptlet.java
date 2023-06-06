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

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.jasper.constant.ValueType;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillField;
import net.sf.jasperreports.engine.fill.JRFillGroup;
import net.sf.jasperreports.engine.fill.JRFillParameter;
import net.sf.jasperreports.engine.fill.JRFillVariable;


/**
 * <p>JasperScriptlet class.</p>
 *
 * @author Ricardo Mariaca
 */
public class JasperScriptlet extends JRDefaultScriptlet
{
	/**
	 * Constant <code>NAME="DYNAMICREPORTS"</code>
	 */
	public static final String NAME = "DYNAMICREPORTS";
	/**
	 * Constant <code>SCRIPTLET_NAME="NAME + JRScriptlet.SCRIPTLET_PARAMETER_"{trunked}</code>
	 */
	public static final String SCRIPTLET_NAME = NAME + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX;
	
	private JasperReportParameters reportParameters;
	
	/**
	 * <p>getValue.</p>
	 *
	 * @param valueName a {@link java.lang.String} object.
	 * @return a {@link java.lang.Object} object.
	 */
	public Object getValue(final String valueName)
	{
		return this.reportParameters.getValue(valueName);
	}
	
	/**
	 * <p>getValue.</p>
	 *
	 * @param name   a {@link java.lang.String} object.
	 * @param values an array of {@link java.lang.Object} objects.
	 * @return a {@link java.lang.Object} object.
	 */
	public Object getValue(final String name, final Object[] values)
	{
		return this.getComplexExpression(name).evaluate(Arrays.asList(values), this.reportParameters);
	}
	
	/**
	 * <p>Getter for the field <code>reportParameters</code>.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.jasper.base.JasperReportParameters} object.
	 */
	public JasperReportParameters getReportParameters()
	{
		return this.reportParameters;
	}
	
	private JasperCustomValues getCustomValues()
	{
		try
		{
			return (JasperCustomValues)this.getParameterValue(JasperCustomValues.NAME, false);
		}
		catch(final JRScriptletException e)
		{
			throw new JasperDesignException("Custom values not found", e);
		}
	}
	
	/**
	 * <p>getValueType.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.jasper.constant.ValueType} object.
	 */
	protected ValueType getValueType(final String name)
	{
		return this.getCustomValues().getValueType(name);
	}
	
	/**
	 * <p>getSimpleExpression.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression} object.
	 */
	protected DRIDesignSimpleExpression getSimpleExpression(final String name)
	{
		return this.getCustomValues().getSimpleExpression(name);
	}
	
	/**
	 * <p>getComplexExpression.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression} object.
	 */
	protected DRIDesignComplexExpression getComplexExpression(final String name)
	{
		return this.getCustomValues().getComplexExpression(name);
	}
	
	/**
	 * <p>getSystemValue.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @return a {@link java.lang.Object} object.
	 */
	protected Object getSystemValue(final String name)
	{
		return this.getCustomValues().getSystemValue(name);
	}
	
	/**
	 * <p>getFields.</p>
	 *
	 * @return a {@link java.util.Collection} object.
	 */
	protected Collection<String> getFields()
	{
		return this.fieldsMap.keySet();
	}
	
	/**
	 * <p>getVariables.</p>
	 *
	 * @return a {@link java.util.Collection} object.
	 */
	protected Collection<String> getVariables()
	{
		return this.variablesMap.keySet();
	}
	
	/**
	 * <p>getParameters.</p>
	 *
	 * @return a {@link java.util.Collection} object.
	 */
	protected Collection<String> getParameters()
	{
		return this.parametersMap.keySet();
	}
	
	/**
	 * <p>getSubreportWidth.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	protected Integer getSubreportWidth()
	{
		return this.getCustomValues().getSubreportWidth();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setData(
		final Map<String, JRFillParameter> parsm,
		final Map<String, JRFillField> fldsm,
		final Map<String, JRFillVariable> varsm,
		final JRFillGroup[] grps)
	{
		super.setData(parsm, fldsm, varsm, grps);
		this.reportParameters = new JasperReportParameters(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterReportInit() throws JRScriptletException
	{
		super.afterReportInit();
		final JasperCustomValues customValues = this.getCustomValues();
		if(customValues != null)
		{
			customValues.setJasperScriptlet(this);
		}
	}
}
