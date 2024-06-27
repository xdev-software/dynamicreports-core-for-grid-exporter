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
package software.xdev.dynamicreports.jasper.transformation;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;


public class GroupTransform
{
	private final JasperTransformAccessor accessor;
	
	public GroupTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	public void transform()
	{
		for(final DRIDesignGroup group : this.accessor.getReport().getGroups())
		{
			this.addGroup(group);
		}
	}
	
	private void addGroup(final DRIDesignGroup group)
	{
		try
		{
			final JRDesignGroup jrGroup = this.group(group);
			this.accessor.getDesign().addGroup(jrGroup);
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for group \"" + group.getName() + "\"", e);
		}
	}
	
	private JRDesignGroup group(final DRIDesignGroup group)
	{
		final JRDesignGroup jrGroup = new JRDesignGroup();
		jrGroup.setName(group.getName());
		jrGroup.setReprintHeaderOnEachPage(group.isReprintHeaderOnEachPage());
		jrGroup.setStartNewColumn(group.isStartInNewColumn());
		jrGroup.setStartNewPage(group.isStartInNewPage());
		jrGroup.setResetPageNumber(group.isResetPageNumber());
		if(group.getMinHeightToStartNewPage() != null)
		{
			jrGroup.setMinHeightToStartNewPage(group.getMinHeightToStartNewPage());
		}
		jrGroup.setFooterPosition(ConstantTransform.groupFooterPosition(group.getFooterPosition()));
		jrGroup.setKeepTogether(group.isKeepTogether());
		return jrGroup;
	}
	
	public void transformExpressions()
	{
		for(final DRIDesignGroup group : this.accessor.getReport().getGroups())
		{
			this.getGroup(group)
				.setExpression(this.accessor.getExpressionTransform().getExpression(group.getGroupExpression()));
		}
	}
	
	public JRDesignGroup getGroup(final DRIDesignGroup group)
	{
		final JRDesignGroup jrGroup = (JRDesignGroup)this.accessor.getDesign().getGroupsMap().get(group.getName());
		if(jrGroup == null)
		{
			throw new JasperDesignException("Group " + group.getName() + " is not registered");
		}
		return jrGroup;
	}
}
