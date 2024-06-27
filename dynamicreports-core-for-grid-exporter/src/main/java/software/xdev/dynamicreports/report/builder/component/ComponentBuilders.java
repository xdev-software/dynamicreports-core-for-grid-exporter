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
package software.xdev.dynamicreports.report.builder.component;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.renderers.Renderable;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.VariableBuilder;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class ComponentBuilders
{
	
	// horizontal list
	
	public HorizontalListBuilder horizontalList()
	{
		return Components.horizontalList();
	}
	
	public HorizontalListBuilder horizontalList(final ComponentBuilder<?, ?>... components)
	{
		return Components.horizontalList(components);
	}
	
	public HorizontalListBuilder horizontalList(final HorizontalListCellBuilder... cells)
	{
		return Components.horizontalList(cells);
	}
	
	public HorizontalListCellBuilder hListCell(final ComponentBuilder<?, ?> component)
	{
		return Components.hListCell(component);
	}
	
	// horizontal flow list
	
	public HorizontalListBuilder horizontalFlowList()
	{
		return Components.horizontalFlowList();
	}
	
	public HorizontalListBuilder horizontalFlowList(final ComponentBuilder<?, ?>... components)
	{
		return Components.horizontalFlowList(components);
	}
	
	public HorizontalListBuilder horizontalFlowList(final HorizontalListCellBuilder... cells)
	{
		return Components.horizontalFlowList(cells);
	}
	
	// vertical list
	
	public VerticalListBuilder verticalList()
	{
		return Components.verticalList();
	}
	
	public VerticalListBuilder verticalList(final ComponentBuilder<?, ?>... components)
	{
		return Components.verticalList(components);
	}
	
	public VerticalListBuilder verticalList(final VerticalListCellBuilder... cells)
	{
		return Components.verticalList(cells);
	}
	
	public VerticalListCellBuilder vListCell(final ComponentBuilder<?, ?> component)
	{
		return Components.vListCell(component);
	}
	
	// xy list
	
	public XyListBuilder xyList()
	{
		return Components.xyList();
	}
	
	public XyListBuilder xyList(final XyListCellBuilder... cells)
	{
		return Components.xyList(cells);
	}
	
	public XyListCellBuilder xyListCell(final Integer x, final Integer y, final ComponentBuilder<?, ?> component)
	{
		return Components.xyListCell(x, y, component);
	}
	
	public XyListCellBuilder xyListCell(
		final Integer x,
		final Integer y,
		final Integer width,
		final Integer height,
		final ComponentBuilder<?, ?> component)
	{
		return Components.xyListCell(x, y, width, height, component);
	}
	
	// multi page list
	
	public MultiPageListBuilder multiPageList()
	{
		return Components.multiPageList();
	}
	
	public MultiPageListBuilder multiPageList(final ComponentBuilder<?, ?>... components)
	{
		return Components.multiPageList(components);
	}
	
	public CurrentDateBuilder currentDate()
	{
		return Components.currentDate();
	}
	
	public PageNumberBuilder pageNumber()
	{
		return Components.pageNumber();
	}
	
	public PageXofYBuilder pageXofY()
	{
		return Components.pageXofY();
	}
	
	public PageXslashYBuilder pageXslashY()
	{
		return Components.pageXslashY();
	}
	
	public TotalPagesBuilder totalPages()
	{
		return Components.totalPages();
	}
	
	// text
	
	public TextFieldBuilder<String> text(final String text)
	{
		return Components.text(text);
	}
	
	public TextFieldBuilder<Date> text(final Date date)
	{
		return Components.text(date);
	}
	
	public <T extends Number> TextFieldBuilder<T> text(final T number)
	{
		return Components.text(number);
	}
	
	public <T> TextFieldBuilder<T> text(final FieldBuilder<T> field)
	{
		return Components.text(field);
	}
	
	public <T> TextFieldBuilder<T> text(final VariableBuilder<T> variable)
	{
		return Components.text(variable);
	}
	
	public <T> TextFieldBuilder<T> text(final DRIExpression<T> textExpression)
	{
		return Components.text(textExpression);
	}
	
	// filler
	
	public FillerBuilder filler()
	{
		return Components.filler();
	}
	
	public FillerBuilder horizontalGap(final int width)
	{
		return Components.horizontalGap(width);
	}
	
	public FillerBuilder verticalGap(final int height)
	{
		return Components.verticalGap(height);
	}
	
	public FillerBuilder gap(final int width, final int height)
	{
		return Components.gap(width, height);
	}
	
	// image
	
	public ImageBuilder image(final DRIExpression<?> imageExpression)
	{
		return Components.image(imageExpression);
	}
	
	public ImageBuilder image(final String imagePath)
	{
		return Components.image(imagePath);
	}
	
	public ImageBuilder image(final Image image)
	{
		return Components.image(image);
	}
	
	public ImageBuilder image(final InputStream imageInputStream)
	{
		return Components.image(imageInputStream);
	}
	
	public ImageBuilder image(final URL imageUrl)
	{
		return Components.image(imageUrl);
	}
	
	public ImageBuilder image(final Renderable image)
	{
		return Components.image(image);
	}
	
	@Deprecated
	public ImageBuilder image(final net.sf.jasperreports.engine.Renderable image)
	{
		return Components.image(image);
	}
	
	// subreport
	
	public SubreportBuilder subreport(final JasperReportBuilder reportBuilder)
	{
		return Components.subreport(reportBuilder);
	}
	
	public SubreportBuilder subreport(final JasperReport jasperReport)
	{
		return Components.subreport(jasperReport);
	}
	
	public SubreportBuilder subreport(final DRIExpression<?> reportExpression)
	{
		return Components.subreport(reportExpression);
	}
	
	// line
	
	public LineBuilder line()
	{
		return Components.line();
	}
	
	// break
	
	public BreakBuilder pageBreak()
	{
		return Components.pageBreak();
	}
	
	public BreakBuilder columnBreak()
	{
		return Components.columnBreak();
	}
	
	// generic element
	
	public GenericElementBuilder genericElement(final String namespace, final String name)
	{
		return Components.genericElement(namespace, name);
	}
	
	// boolean
	
	public BooleanFieldBuilder booleanField(final Boolean value)
	{
		return Components.booleanField(value);
	}
	
	public BooleanFieldBuilder booleanField(final FieldBuilder<Boolean> field)
	{
		return Components.booleanField(field);
	}
	
	public BooleanFieldBuilder booleanField(final DRIExpression<Boolean> valueExpression)
	{
		return Components.booleanField(valueExpression);
	}
	
	// ellipse
	
	public EllipseBuilder ellipse()
	{
		return Components.ellipse();
	}
	
	// rectangle
	
	public RectangleBuilder rectangle()
	{
		return Components.rectangle();
	}
	
	public RectangleBuilder roundRectangle()
	{
		return Components.roundRectangle();
	}
	
	public RectangleBuilder roundRectangle(final int radius)
	{
		return Components.roundRectangle(radius);
	}
	
	// map
	
	public MapBuilder map()
	{
		return Components.map();
	}
	
	public MapBuilder map(final Float latitude, final Float longitude, final Integer zoom)
	{
		return Components.map(latitude, longitude, zoom);
	}
	
	// alignment
	
	public HorizontalListBuilder centerHorizontal(final ComponentBuilder<?, ?> component)
	{
		return Components.centerHorizontal(component);
	}
	
	public VerticalListBuilder centerVertical(final ComponentBuilder<?, ?> component)
	{
		return Components.centerVertical(component);
	}
}
