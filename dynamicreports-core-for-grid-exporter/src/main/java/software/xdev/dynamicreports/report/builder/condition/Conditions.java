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
package software.xdev.dynamicreports.report.builder.condition;

import software.xdev.dynamicreports.report.definition.DRIValue;


@SuppressWarnings("java:S1221")
public final class Conditions
{
	private Conditions()
	{
	}
	
	@SafeVarargs
	public static <T> EqualExpression equal(final DRIValue<T> value, final T... values)
	{
		return new EqualExpression(value, values);
	}
	
	public static <T extends Number> EqualValueExpression<T> equal(final DRIValue<T> value, final Number... number)
	{
		return new EqualValueExpression<>(value, number);
	}
	
	@SafeVarargs
	public static <T> UnEqualExpression unEqual(final DRIValue<T> value, final T... values)
	{
		return new UnEqualExpression(value, values);
	}
	
	public static <T extends Number> UnEqualValueExpression<T> unEqual(final DRIValue<T> value, final Number... number)
	{
		return new UnEqualValueExpression<>(value, number);
	}
	
	public static <T extends Number> SmallerValueExpression<T> smaller(final DRIValue<T> value, final Number number)
	{
		return new SmallerValueExpression<>(value, number);
	}
	
	public static <T extends Number> SmallerOrEqualsValueExpression<T> smallerOrEquals(
		final DRIValue<T> value,
		final Number number)
	{
		return new SmallerOrEqualsValueExpression<>(value, number);
	}
	
	public static <T extends Number> GreaterValueExpression<T> greater(final DRIValue<T> value, final Number number)
	{
		return new GreaterValueExpression<>(value, number);
	}
	
	public static <T extends Number> GreaterOrEqualsValueExpression<T> greaterOrEquals(
		final DRIValue<T> value,
		final Number number)
	{
		return new GreaterOrEqualsValueExpression<>(value, number);
	}
	
	public static <T extends Number> BetweenValueExpression<T> between(
		final DRIValue<T> value,
		final Number min,
		final Number max)
	{
		return new BetweenValueExpression<>(value, min, max);
	}
	
	public static <T extends Number> NotBetweenValueExpression<T> notBetween(
		final DRIValue<T> value,
		final Number min,
		final Number max)
	{
		return new NotBetweenValueExpression<>(value, min, max);
	}
}
