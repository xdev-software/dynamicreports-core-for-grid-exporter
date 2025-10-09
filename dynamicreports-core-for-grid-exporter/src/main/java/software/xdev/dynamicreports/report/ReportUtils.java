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
package software.xdev.dynamicreports.report;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import software.xdev.dynamicreports.report.constant.Calculation;


public final class ReportUtils
{
	private ReportUtils()
	{
	}
	
	private static final Lock LOCK = new ReentrantLock();
	private static int counter;
	
	public static String generateUniqueName(final String name)
	{
		try
		{
			LOCK.lock();
			return generateName(name);
		}
		finally
		{
			LOCK.unlock();
		}
	}
	
	public static Class<?> getVariableValueClass(final Calculation calculation, final Class<?> valueClass)
	{
		if(calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT))
		{
			return Long.class;
		}
		if(calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION)
			|| calculation.equals(Calculation.VARIANCE))
		{
			return Number.class;
		}
		return valueClass;
	}
	
	public static Class<?> getGenericClass(final Object object, final int index)
	{
		final ParameterizedType genericSuperclass = getParameterizedType(object.getClass());
		if(genericSuperclass == null)
		{
			return String.class;
		}
		final Class<?> rawType = getRawType(genericSuperclass.getActualTypeArguments()[index]);
		if(rawType == null)
		{
			return String.class;
		}
		return rawType;
	}
	
	static void setCounter(final int counter)
	{
		ReportUtils.counter = counter;
	}
	
	private static ParameterizedType getParameterizedType(final Class<?> classs)
	{
		if(classs == null)
		{
			return null;
		}
		if(classs.getGenericSuperclass() instanceof ParameterizedType)
		{
			return (ParameterizedType)classs.getGenericSuperclass();
		}
		return getParameterizedType((Class<?>)classs.getGenericSuperclass());
	}
	
	private static Class<?> getRawType(final Object typeArgument)
	{
		if(typeArgument instanceof ParameterizedType)
		{
			return getRawType(((ParameterizedType)typeArgument).getRawType());
		}
		else
		{
			if(typeArgument instanceof Class<?>)
			{
				return (Class<?>)typeArgument;
			}
			else
			{
				return null;
			}
		}
	}
	
	private static String generateName(final String name)
	{
		if(counter == Integer.MAX_VALUE)
		{
			counter = 0;
		}
		return name + "_" + counter++ + "_";
	}
}
