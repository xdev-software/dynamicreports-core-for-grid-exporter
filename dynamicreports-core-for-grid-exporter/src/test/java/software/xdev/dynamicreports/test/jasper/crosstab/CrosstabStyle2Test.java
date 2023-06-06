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
package software.xdev.dynamicreports.test.jasper.crosstab;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabStyleTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Crosstab style tests.
 * 
 * @author Ricardo Mariaca
 */
public class CrosstabStyle2Test extends AbstractJasperCrosstabStyleTest implements Serializable {
  private static final long serialVersionUID = 1L;

  private CrosstabRowGroupBuilder<String> rowGroup;
  private CrosstabColumnGroupBuilder<String> columnGroup;
  private CrosstabMeasureBuilder<Integer> measure1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    FieldBuilder<String> field1 = field("field1", String.class);
    FieldBuilder<String> field2 = field("field2", String.class);

    final StyleBuilder cellStyle =
        stl.style().conditionalStyles(stl.conditionalStyle(new ConditionExpression(10, 15, 14, 36))
            .setBackgroundColor(Color.ORANGE));

    rowGroup = ctab.rowGroup(field1);
    columnGroup = ctab.columnGroup(field2);

    measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
    measure1.setStyle(cellStyle);

    CrosstabBuilder crosstab =
        ctab.crosstab().rowGroups(rowGroup).columnGroups(columnGroup).measures(measure1);

    rb.summary(crosstab);
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    setCrosstabBand("summary");

    crosstabCellStyleTest(measure1, null, null, 0, null, null, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 1, null, null, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 2, null, null, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 3, null, Color.ORANGE, TEST_FONT_NAME, 10f, null,
        null);

    crosstabCellStyleTest(measure1, rowGroup, null, 0, null, Color.ORANGE, TEST_FONT_NAME, 10f,
        null, null);
    crosstabCellStyleTest(measure1, rowGroup, null, 1, null, null, TEST_FONT_NAME, 10f, null, null);

    crosstabCellStyleTest(measure1, null, columnGroup, 0, null, Color.ORANGE, TEST_FONT_NAME, 10f,
        null, null);
    crosstabCellStyleTest(measure1, null, columnGroup, 1, null, null, TEST_FONT_NAME, 10f, null,
        null);

    crosstabCellStyleTest(measure1, rowGroup, columnGroup, 0, null, Color.ORANGE, TEST_FONT_NAME,
        10f, null, null);

  }

  @Override
  protected JRDataSource createDataSource() {
    DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("a", "c", 1);
    dataSource.add("a", "c", 2);
    dataSource.add("a", "d", 3);
    dataSource.add("a", "d", 4);
    dataSource.add("b", "c", 5);
    dataSource.add("b", "c", 6);
    dataSource.add("b", "d", 7);
    dataSource.add("b", "d", 8);
    return dataSource;
  }

  private class ConditionExpression extends AbstractSimpleExpression<Boolean> {
    private static final long serialVersionUID = 1L;

    private List<Integer> values;

    private ConditionExpression(Integer... values) {
      this.values = Arrays.asList(values);
    }

    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
      Integer value = reportParameters.getValue(measure1);
      return values.contains(value);
    }
  }
}
