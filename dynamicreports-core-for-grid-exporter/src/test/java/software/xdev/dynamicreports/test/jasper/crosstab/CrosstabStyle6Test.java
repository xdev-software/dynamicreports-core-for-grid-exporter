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

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.ConditionalStyleBuilder;
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
public class CrosstabStyle6Test extends AbstractJasperCrosstabStyleTest implements Serializable {
  private static final long serialVersionUID = 1L;

  private CrosstabRowGroupBuilder<String> rowGroup;
  private CrosstabColumnGroupBuilder<String> columnGroup;
  private CrosstabMeasureBuilder<Integer> measure1;

  @Override
  protected void configureReport(JasperReportBuilder rb) {
    FieldBuilder<String> field1 = field("field1", String.class);
    FieldBuilder<String> field2 = field("field2", String.class);

    rowGroup = ctab.rowGroup(field1).setShowTotal(false);
    columnGroup = ctab.columnGroup(field2).setShowTotal(false);

    measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);

    ConditionalStyleBuilder condition = stl.conditionalStyle(new ConditionExpression())
        .setBackgroundColor(new Color(200, 200, 200));

    StyleBuilder style = stl.style().conditionalStyles(condition);

    measure1.setStyle(style);

    CrosstabBuilder crosstab = ctab.crosstab().highlightEvenRows().rowGroups(rowGroup)
        .columnGroups(columnGroup).measures(measure1).setDataSource(createDataSource1());

    rb.summary(crosstab);
  }

  @Override
  public void test() {
    super.test();

    numberOfPagesTest(1);

    setCrosstabBand("summary");

    Color color1 = new Color(240, 240, 240);
    Color color2 = new Color(200, 200, 200);

    crosstabCellStyleTest(measure1, null, null, 0, null, color2, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 1, null, color1, TEST_FONT_NAME, 10f, null, null);
    crosstabCellStyleTest(measure1, null, null, 2, null, color2, TEST_FONT_NAME, 10f, null, null);
  }

  protected JRDataSource createDataSource1() {
    DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
    dataSource.add("a", "c", 1);
    dataSource.add("b", "c", 1);
    dataSource.add("c", "c", 1);
    return dataSource;
  }

  private class ConditionExpression extends AbstractSimpleExpression<Boolean> {
    private static final long serialVersionUID = 1L;

    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
      return reportParameters.getCrosstabRowNumber() == 1
          || reportParameters.getCrosstabRowNumber() == 3;
    }

  }
}
