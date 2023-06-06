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
package software.xdev.dynamicreports.test.jasper.style;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.template;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.ReportTemplateBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.LineStyleEnum;

/**
 * Template style tests.
 * 
 * @author Ricardo Mariaca
 */
public class TemplateStyle4Test extends AbstractJasperStyleTest implements Serializable {
  private static final long serialVersionUID = 1L;

  private TextColumnBuilder<Integer> column1;
  private TextColumnBuilder<String> column2;
  private ColumnGroupBuilder group1;
  private AggregationSubtotalBuilder<Integer> subtotal1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    StyleBuilder textFieldStyle1 =
        stl.style(stl.templateStyle("boldStyle")).setName("textFieldStyle1").setFontSize(15);
    StyleBuilder textFieldStyle2 = stl.style(stl.templateStyle("boldStyle")).setFontSize(10);

    ReportTemplateBuilder template = template().addTemplateStyle(textFieldStyle1).templateStyles(
        stl.loadStyles(TemplateStyle4Test.class.getResource("StyleTemplate2.jrtx")));

    rb.setTemplate(template).setTextStyle(stl.templateStyle("textStyle"))
        .setColumnTitleStyle(stl.templateStyle("titleStyle"))
        .setSubtotalStyle(stl.templateStyle("subtotalStyle"))
        .columns(column1 = col.column("Column1", "field1", type.integerType()),
            column2 = col.column("Column2", "field2", type.stringType())
                .setStyle(stl.templateStyle("boldStyle")))
        .title(cmp.text("text").setStyle(stl.templateStyle("textFieldStyle1")),
            cmp.text("text").setStyle(textFieldStyle2))
        .groupBy(group1 = grp.group(column2)).subtotalsAtSummary(subtotal1 =
            sbt.sum(column1).setLabel("total").setLabelStyle(stl.templateStyle("boldStyle")));
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    // column1
    columnTitleStyleTest(column1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
    columnTitlePaddingTest(column1, 0, 2, 2, 2, 2);

    columnDetailStyleTest(column1, 0, null, null, TEST_FONT_NAME, 10f, null, null);
    columnDetailPaddingTest(column1, 0, 2, 2, 2, 2);

    // column2
    groupHeaderStyleTest(group1, 0, null, null, TEST_FONT_NAME, 10f, true, null);

    // subtotal
    subtotalLabelStyleTest(subtotal1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
    subtotalLabelBorderTest(subtotal1, 0, null, LineStyleEnum.SOLID, 0, null, LineStyleEnum.SOLID,
        0, null, LineStyleEnum.SOLID, 0, null, LineStyleEnum.SOLID, 0);

    // title
    styleTest("title.textField1", 0, null, null, TEST_FONT_NAME, 15f, true, null);
    styleTest("title.textField2", 0, null, null, TEST_FONT_NAME, 10f, true, null);
  }

  @Override
  protected JRDataSource createDataSource() {
    DRDataSource dataSource = new DRDataSource("field1", "field2");
    dataSource.add(1, "1");
    return dataSource;
  }
}
