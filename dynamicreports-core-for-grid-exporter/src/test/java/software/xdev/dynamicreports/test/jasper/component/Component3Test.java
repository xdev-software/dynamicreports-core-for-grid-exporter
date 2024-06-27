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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.component.TextFieldBuilder;
import software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperTest;


public class Component3Test extends AbstractJasperTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final CustomGroupBuilder group = grp.group("group1", exp.text(""));
		
		final TextFieldBuilder<String> textField = cmp.text("")
			.setPositionType(ComponentPositionType.FIX_RELATIVE_TO_TOP)
			.setStretchType(StretchType.NO_STRETCH)
			.setPrintInFirstWholeBand(true)
			.setPrintWhenDetailOverflows(true)
			.setPrintWhenGroupChanges(group);
		rb.title(textField)
			.columns(col.column("column1", type.stringType()).setPrintWhenDetailOverflows(true))
			.groupBy(group);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		JRElement textField = this.getJasperReport().getTitle().getElementByKey("title.textField1");
		Assertions.assertEquals(PositionTypeEnum.FIX_RELATIVE_TO_TOP, textField.getPositionTypeValue());
		Assertions.assertEquals(StretchTypeEnum.NO_STRETCH, textField.getStretchTypeValue());
		Assertions.assertTrue(textField.isPrintInFirstWholeBand());
		Assertions.assertTrue(textField.isPrintWhenDetailOverflows());
		Assertions.assertEquals("group1", textField.getPrintWhenGroupChanges().getName());
		
		textField = this.getJasperReport().getDetailSection().getBands()[0].getElementByKey("detail.column_column11");
		Assertions.assertTrue(textField.isPrintWhenDetailOverflows());
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(1);
	}
}
