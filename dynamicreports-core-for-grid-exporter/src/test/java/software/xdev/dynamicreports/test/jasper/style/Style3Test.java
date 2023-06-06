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

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.Serializable;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Style tests.
 * 
 * @author Ricardo Mariaca
 */
public class Style3Test extends AbstractJasperStyleTest implements Serializable {
  private static final long serialVersionUID = 1L;

  private TextColumnBuilder<String> column1;
  private TextColumnBuilder<String> column2;
  private TextColumnBuilder<String> column3;
  private TextColumnBuilder<String> column4;
  private ColumnGroupBuilder group1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    StyleBuilder titleStyle = stl.style().setForegroundColor(Color.RED);
    StyleBuilder columnStyle = stl.style().setForegroundColor(Color.BLUE);

    rb.setColumnTitleStyle(titleStyle).setColumnStyle(columnStyle).columns(
        column1 = col.column("Column1", "field1", type.stringType()).setStyle(stl.style().italic())
            .setTitleStyle(stl.style().bold()),
        column2 = col.column("Column2", "field2", type.stringType()),
        column3 = col.column("Column3", "field3", type.stringType()).setStyle(stl.style().bold()),
        column4 = col.column("Column4", "field4", type.stringType())
            .setStyle(stl.style(columnStyle).bold()).setTitleStyle(stl.style().italic()))
        .groupBy(group1 = grp.group(column1).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE));
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    // column2
    columnTitleStyleTest(column2, 0, Color.RED, null, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column2, 0, Color.BLUE, null, TEST_FONT_NAME, 10f, null, null);

    // column3
    columnTitleStyleTest(column3, 0, Color.RED, null, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column3, 0, null, null, TEST_FONT_NAME, 10f, true, null);

    // column4
    columnTitleStyleTest(column4, 0, null, null, TEST_FONT_NAME, 10f, null, true);
    columnDetailStyleTest(column4, 0, Color.BLUE, null, TEST_FONT_NAME, 10f, true, null);

    // group1
    groupHeaderTitleStyleTest(group1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
    groupHeaderStyleTest(group1, 0, null, null, TEST_FONT_NAME, 10f, null, true);
  }

  @Override
  protected JRDataSource createDataSource() {
    DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("1", "1", "1");
    return dataSource;
  }
}
