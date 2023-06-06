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
import java.util.Date;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;

/**
 * Style tests.
 * 
 * @author Ricardo Mariaca
 */
public class Style4Test extends AbstractJasperStyleTest implements Serializable {
  private static final long serialVersionUID = 1L;

  private TextColumnBuilder<Date> column1;
  private ColumnGroupBuilder group1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    StyleBuilder groupStyle =
        stl.style().bold().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

    column1 = col.column("field1", type.dateYearType());

    group1 = grp.group(column1).setHideColumn(false).groupByDataType().setStyle(groupStyle);

    rb.columns(column1).groupBy(group1);
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    // column1
    columnDetailStyleTest(column1, 0, Color.BLACK, null, TEST_FONT_NAME, 10f, null, null);
    columnDetailAlignmentTest(column1, 0, HorizontalTextAlignEnum.RIGHT);

    // group1
    groupHeaderStyleTest(group1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
    groupHeaderAlignmentTest(group1, 0, HorizontalTextAlignEnum.LEFT);
  }

  @Override
  protected JRDataSource createDataSource() {
    DRDataSource dataSource = new DRDataSource("field1");
    dataSource.add(new Date());
    return dataSource;
  }
}
