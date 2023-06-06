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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cnd;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.Serializable;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Style tests.
 * 
 * @author Ricardo Mariaca
 */
public class Style6Test extends AbstractJasperStyleTest implements Serializable {
  private static final long serialVersionUID = 1L;

  private TextColumnBuilder<String> column1;
  private TextColumnBuilder<String> column2;
  private TextColumnBuilder<String> column3;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    StyleBuilder style = stl.style();
    style.setBackgroundColor(Color.WHITE);
    ConditionalStyleBuilder conditionalStyle =
        stl.conditionalStyle(cnd.equal(field("field3", type.stringType()), "2"));
    conditionalStyle.setForegroundColor(Color.BLUE);
    style.conditionalStyles(conditionalStyle);

    rb.highlightDetailEvenRows().columns(
        column1 = col.column("Column1", "field1", type.stringType())
            .setStyle(stl.style().setBackgroundColor(Color.BLUE)),
        column2 = col.column("Column2", "field2", type.stringType()),
        column3 = col.column("Column3", "field3", type.stringType()).setStyle(style));
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    final Color color1 = new Color(60, 60, 251);
    final Color color2 = new Color(240, 240, 240);
    final Color color3 = new Color(251, 251, 251);

    // column1
    columnDetailStyleTest(column1, 0, null, color1, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column1, 1, null, Color.BLUE, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column1, 2, null, color1, TEST_FONT_NAME, 10f, null, null);

    // column2
    columnDetailStyleTest(column2, 0, Color.BLACK, color2, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column2, 1, Color.BLACK, null, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column2, 2, Color.BLACK, color2, TEST_FONT_NAME, 10f, null, null);

    // column3
    columnDetailStyleTest(column3, 0, null, color3, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column3, 1, Color.BLUE, Color.WHITE, TEST_FONT_NAME, 10f, null, null);
    columnDetailStyleTest(column3, 2, null, color3, TEST_FONT_NAME, 10f, null, null);
  }

  @Override
  protected JRDataSource createDataSource() {
    DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("1", "1", "1");
    dataSource.add("1", "1", "2");
    dataSource.add("1", "1", "1");
    return dataSource;
  }
}
