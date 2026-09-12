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
package software.xdev.dynamicreports.design.transformation;

import java.util.List;

import software.xdev.dynamicreports.design.base.DRDesignField;
import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.DRISort;
import software.xdev.dynamicreports.report.definition.DRIVariable;
import software.xdev.dynamicreports.report.exception.DRException;


public class MainDatasetExpressionTransform extends AbstractExpressionTransform
{
	
	public MainDatasetExpressionTransform(final DesignTransformAccessor accessor)
	{
		super(accessor);
	}
	
	@Override
	public void transform() throws DRException
	{
		final DRIReport report = this.accessor.getReport();
		final List<DRIField<?>> templateDesignFields = report.getTemplateDesign().getFields();
		if(templateDesignFields != null)
		{
			for(final DRIField<?> field : templateDesignFields)
			{
				final DRDesignField designField = (DRDesignField)this.transformExpression(field);
				designField.setExternal(true);
			}
		}
		super.transform();
	}
	
	@Override
	protected ResetType getVariableResetType(final DRIVariable<?> variable)
	{
		return ConstantTransform.variableResetType(variable.getResetType(), variable.getResetGroup(), this.accessor);
	}
	
	@Override
	protected DRDesignGroup getVariableResetGroup(final DRIVariable<?> variable) throws DRException
	{
		return this.accessor.getGroupTransform()
			.getGroup(ConstantTransform.variableResetGroup(
				variable.getName(),
				variable.getResetType(),
				variable.getResetGroup(),
				this.accessor));
	}
	
	@Override
	protected List<? extends DRIField<?>> transformFields()
	{
		return this.accessor.getReport().getFields();
	}
	
	@Override
	protected List<? extends DRIVariable<?>> transformVariables()
	{
		return this.accessor.getReport().getVariables();
	}
	
	@Override
	protected List<? extends DRISort> transformSorts()
	{
		return this.accessor.getReport().getSorts();
	}
	
	@Override
	protected DRIDesignDataset getDataset()
	{
		return null;
	}
}
