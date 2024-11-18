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
package software.xdev.dynamicreports.design.definition;

import java.io.Serializable;
import java.util.Collection;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;


public interface DRIDesignDataset extends Serializable
{
	public String getName();
	
	public Collection<DRIDesignField> getFields();
	
	public Collection<DRIDesignVariable> getVariables();
	
	public Collection<DRIDesignSystemExpression> getSystemExpressions();
	
	public Collection<DRIDesignJasperExpression> getJasperExpressions();
	
	public Collection<DRIDesignSimpleExpression> getSimpleExpressions();
	
	public Collection<DRIDesignComplexExpression> getComplexExpressions();
	
	public Collection<DRIDesignSort> getSorts();
	
	public DRIDesignQuery getQuery();
	
	public DRIDesignExpression getConnectionExpression();
	
	public DRIDesignExpression getDataSourceExpression();
	
	public DRIDesignExpression getFilterExpression();
}
