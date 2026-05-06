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
package software.xdev.dynamicreports.report.component;

import java.util.List;

import net.sf.jasperreports.extensions.ExtensionsEnvironment;


public final class CustomComponents
{
	private CustomComponents()
	{
	}
	
	@SuppressWarnings("rawtypes")
	public static CustomComponentTransform<?, ?> getComponentTransform(final Object component)
	{
		final List<CustomComponentTransform> transforms =
			ExtensionsEnvironment.getExtensionsRegistry().getExtensions(CustomComponentTransform.class);
		for(final CustomComponentTransform transform : transforms)
		{
			if(transform.isTransform(component))
			{
				return transform;
			}
		}
		return null;
	}
}
