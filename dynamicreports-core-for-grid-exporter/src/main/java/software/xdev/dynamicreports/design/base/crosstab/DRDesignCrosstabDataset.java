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
package software.xdev.dynamicreports.design.base.crosstab;

import software.xdev.dynamicreports.design.base.DRDesignDataset;
import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabDataset;


public class DRDesignCrosstabDataset implements DRIDesignCrosstabDataset
{

	private DRDesignDataset subDataset;
	private Boolean dataPreSorted;
	private ResetType resetType;
	private DRDesignGroup resetGroup;
	
	@Override
	public DRDesignDataset getSubDataset()
	{
		return this.subDataset;
	}
	
	public void setSubDataset(final DRDesignDataset subDataset)
	{
		this.subDataset = subDataset;
	}
	
	@Override
	public Boolean getDataPreSorted()
	{
		return this.dataPreSorted;
	}
	
	public void setDataPreSorted(final Boolean dataPreSorted)
	{
		this.dataPreSorted = dataPreSorted;
	}
	
	@Override
	public ResetType getResetType()
	{
		return this.resetType;
	}
	
	public void setResetType(final ResetType resetType)
	{
		this.resetType = resetType;
	}
	
	@Override
	public DRDesignGroup getResetGroup()
	{
		return this.resetGroup;
	}
	
	public void setResetGroup(final DRDesignGroup resetGroup)
	{
		this.resetGroup = resetGroup;
	}
}
