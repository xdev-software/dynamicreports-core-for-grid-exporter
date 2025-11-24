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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;
import net.sf.jasperreports.engine.fonts.FontExtensionsRegistry;
import net.sf.jasperreports.extensions.DefaultExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistry;
import net.sf.jasperreports.extensions.ExtensionsRegistryFactory;
import software.xdev.dynamicreports.report.defaults.Defaults;


public class JasperSystemFontExtensionsRegistryFactory implements ExtensionsRegistryFactory
{
	public static final String SYSTEM_FONT_FAMILIES_PROPERTY_PREFIX =
		DefaultExtensionsRegistry.PROPERTY_REGISTRY_PREFIX + "system.font.families.";

	public static final String PROPERTY_SYSTEM_FONT_FAMILIES_REGISTRY_FACTORY =
		DefaultExtensionsRegistry.PROPERTY_REGISTRY_FACTORY_PREFIX + "system.font.families";
	
	@Override
	public ExtensionsRegistry createRegistry(final String registryId, final JRPropertiesMap properties)
	{
		final List<PropertySuffix> fontFamiliesProperties =
			JRPropertiesUtil.getProperties(properties, SYSTEM_FONT_FAMILIES_PROPERTY_PREFIX);
		final List<String> fontFamiliesLocations = new ArrayList<>();
		if(Defaults.getDefaults().isLoadSystemFonts())
		{
			for(final Iterator<PropertySuffix> it = fontFamiliesProperties.iterator(); it.hasNext();)
			{
				final PropertySuffix fontFamiliesProp = it.next();
				final String fontFamiliesLocation = fontFamiliesProp.getValue();
				fontFamiliesLocations.add(fontFamiliesLocation);
			}
		}
		
		return new FontExtensionsRegistry(fontFamiliesLocations);
	}
}
