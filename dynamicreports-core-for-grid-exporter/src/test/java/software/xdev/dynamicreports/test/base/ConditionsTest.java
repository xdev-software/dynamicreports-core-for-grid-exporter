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
package software.xdev.dynamicreports.test.base;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cnd;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;

import java.sql.Connection;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression;


class ConditionsTest
{
	
	@Test
	void test()
	{
		// equal
		final FieldBuilder<Integer> value = field("name", Integer.class);
		// conditionTrue("equal", cnd.equal(value, 5, 10, 20));
		// conditionFalse("equal", cnd.equal(value, 5, 20));
		
		// unequal
		// conditionFalse("unequal", cnd.unEqual(value, 5, 10, 20));
		// conditionTrue("unequal", cnd.unEqual(value, 5, 20));
		
		// smaller
		this.conditionFalse("smaller", cnd.smaller(value, 5));
		this.conditionFalse("smaller", cnd.smaller(value, 10));
		this.conditionTrue("smaller", cnd.smaller(value, 15));
		
		// smallerOrEquals
		this.conditionFalse("smallerOrEquals", cnd.smallerOrEquals(value, 5));
		this.conditionTrue("smallerOrEquals", cnd.smallerOrEquals(value, 10));
		this.conditionTrue("smallerOrEquals", cnd.smallerOrEquals(value, 15));
		
		// greater
		this.conditionTrue("greater", cnd.greater(value, 5));
		this.conditionFalse("greater", cnd.greater(value, 10));
		this.conditionFalse("greater", cnd.greater(value, 15));
		
		// greaterOrEquals
		this.conditionTrue("greaterOrEquals", cnd.greaterOrEquals(value, 5));
		this.conditionTrue("greaterOrEquals", cnd.greaterOrEquals(value, 10));
		this.conditionFalse("greaterOrEquals", cnd.greaterOrEquals(value, 15));
		
		// between
		this.conditionTrue("between", cnd.between(value, 5, 15));
		this.conditionTrue("between", cnd.between(value, 5, 10));
		this.conditionTrue("between", cnd.between(value, 10, 20));
		this.conditionFalse("between", cnd.between(value, 5, 9));
		this.conditionFalse("between", cnd.between(value, 11, 20));
		
		// notBetween
		this.conditionFalse("notBetween", cnd.notBetween(value, 5, 15));
		this.conditionFalse("notBetween", cnd.notBetween(value, 5, 10));
		this.conditionFalse("notBetween", cnd.notBetween(value, 10, 20));
		this.conditionTrue("notBetween", cnd.notBetween(value, 5, 9));
		this.conditionTrue("notBetween", cnd.notBetween(value, 11, 20));
		
		// equal object
		final FieldBuilder<Object> value2 = field("name", Object.class);
		this.conditionTrue("equal", cnd.equal(value2, Type.A, Type.C, Type.F), Type.C);
		this.conditionFalse("equal", cnd.equal(value2, Type.B, Type.C), Type.E);
		
		// unequal object
		this.conditionFalse("unequal", cnd.unEqual(value2, Type.A, Type.C, Type.F), Type.C);
		this.conditionTrue("unequal", cnd.unEqual(value2, Type.B, Type.C), Type.E);
	}
	
	private void conditionTrue(final String name, final DRISimpleExpression<Boolean> condition)
	{
		Assertions.assertTrue(condition.evaluate(new TestReportParameters(10)), name + " condition");
	}
	
	private void conditionFalse(final String name, final DRISimpleExpression<Boolean> condition)
	{
		Assertions.assertFalse(condition.evaluate(new TestReportParameters(10)), name + " condition");
	}
	
	private void conditionTrue(
		final String name,
		final DRISimpleExpression<Boolean> condition,
		final Object actualValue)
	{
		Assertions.assertTrue(condition.evaluate(new TestReportParameters(actualValue)), name + " condition");
	}
	
	private void conditionFalse(
		final String name,
		final DRISimpleExpression<Boolean> condition,
		final Object actualValue)
	{
		Assertions.assertFalse(condition.evaluate(new TestReportParameters(actualValue)), name + " condition");
	}
	
	private enum Type
	{
		A, B, C, D, E, F
	}
	
	
	private static class TestReportParameters implements ReportParameters
	{
		private final Object value;
		
		public TestReportParameters(final Object value)
		{
			this.value = value;
		}
		
		@Override
		public Integer getColumnNumber()
		{
			return null;
		}
		
		@Override
		public Integer getColumnRowNumber()
		{
			return null;
		}
		
		@Override
		public Connection getConnection()
		{
			return null;
		}
		
		@Override
		public Integer getGroupCount(final String groupName)
		{
			return null;
		}
		
		@Override
		public Locale getLocale()
		{
			return null;
		}
		
		@Override
		public String getMessage(final String key)
		{
			return null;
		}
		
		@Override
		public String getMessage(final String key, final Object[] arguments)
		{
			return null;
		}
		
		@Override
		public Integer getPageNumber()
		{
			return null;
		}
		
		@Override
		public Integer getPageRowNumber()
		{
			return null;
		}
		
		@Override
		public Integer getReportRowNumber()
		{
			return null;
		}
		
		@Override
		public Integer getCrosstabRowNumber()
		{
			return null;
		}
		
		@Override
		public DRIScriptlet getScriptlet(final String name)
		{
			return null;
		}
		
		@Override
		public <T> T getValue(final String name)
		{
			return null;
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public <T> T getValue(final DRIValue<T> value)
		{
			return (T)this.value;
		}
		
		@Override
		public ReportParameters getMasterParameters()
		{
			return null;
		}
		
		@Override
		public Integer getSubreportWidth()
		{
			return null;
		}
		
		@Override
		public <T> T getFieldValue(final String name)
		{
			return null;
		}
		
		@Override
		public <T> T getVariableValue(final String name)
		{
			return null;
		}
		
		@Override
		public <T> T getParameterValue(final String name)
		{
			return null;
		}
	}
}
