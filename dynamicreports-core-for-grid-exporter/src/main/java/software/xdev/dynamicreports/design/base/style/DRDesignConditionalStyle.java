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
package software.xdev.dynamicreports.design.base.style;

import java.util.Objects;

import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.style.DRIDesignConditionalStyle;


public class DRDesignConditionalStyle extends DRDesignBaseStyle implements DRIDesignConditionalStyle
{

	private DRIDesignExpression conditionExpression;
	private DRIDesignDataset dataset;
	
	@Override
	public DRIDesignExpression getConditionExpression()
	{
		return this.conditionExpression;
	}
	
	public void setConditionExpression(final DRIDesignExpression conditionExpression)
	{
		this.conditionExpression = conditionExpression;
	}
	
	@Override
	public DRIDesignDataset getDataset()
	{
		return this.dataset;
	}
	
	public void setDataset(final DRIDesignDataset dataset)
	{
		this.dataset = dataset;
	}
	
	@Override
	public boolean equals(final Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || this.getClass() != o.getClass())
		{
			return false;
		}
		if(!super.equals(o))
		{
			return false;
		}
		final DRDesignConditionalStyle that = (DRDesignConditionalStyle)o;
		return Objects.equals(this.getConditionExpression(), that.getConditionExpression())
			&& Objects.equals(this.getDataset(), that.getDataset());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), this.getConditionExpression(), this.getDataset());
	}
}
