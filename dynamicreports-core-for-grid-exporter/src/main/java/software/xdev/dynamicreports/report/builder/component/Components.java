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

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.renderers.Renderable;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.VariableBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.BreakType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public final class Components
{
	private Components()
	{
	}
	
	// horizontal list
	
	public static HorizontalListBuilder horizontalList()
	{
		return new HorizontalListBuilder();
	}
	
	public static HorizontalListBuilder horizontalList(final ComponentBuilder<?, ?>... components)
	{
		return new HorizontalListBuilder().add(components);
	}
	
	public static HorizontalListBuilder horizontalList(final HorizontalListCellBuilder... cells)
	{
		return new HorizontalListBuilder().add(cells);
	}
	
	public static HorizontalListCellBuilder hListCell(final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(component, "component must not be null");
		return new HorizontalListCellBuilder(component);
	}
	
	// horizontal flow list
	
	public static HorizontalListBuilder horizontalFlowList()
	{
		return new HorizontalFlowListBuilder();
	}
	
	public static HorizontalListBuilder horizontalFlowList(final ComponentBuilder<?, ?>... components)
	{
		return new HorizontalFlowListBuilder().add(components);
	}
	
	public static HorizontalListBuilder horizontalFlowList(final HorizontalListCellBuilder... cells)
	{
		return new HorizontalFlowListBuilder().add(cells);
	}
	
	// vertical list
	
	public static VerticalListBuilder verticalList()
	{
		return new VerticalListBuilder();
	}
	
	public static VerticalListBuilder verticalList(final ComponentBuilder<?, ?>... components)
	{
		return new VerticalListBuilder().add(components);
	}
	
	public static VerticalListBuilder verticalList(final VerticalListCellBuilder... cells)
	{
		return new VerticalListBuilder().add(cells);
	}
	
	public static VerticalListCellBuilder vListCell(final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(component, "component must not be null");
		return new VerticalListCellBuilder(component);
	}
	
	// xy list
	
	public static XyListBuilder xyList()
	{
		return new XyListBuilder();
	}
	
	public static XyListBuilder xyList(final XyListCellBuilder... cells)
	{
		return new XyListBuilder().add(cells);
	}
	
	public static XyListCellBuilder xyListCell(
		final Integer x, final Integer y,
		final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(component, "component must not be null");
		return new XyListCellBuilder(x, y, component);
	}
	
	public static XyListCellBuilder xyListCell(
		final Integer x,
		final Integer y,
		final Integer width,
		final Integer height,
		final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(component, "component must not be null");
		return new XyListCellBuilder(x, y, width, height, component);
	}
	
	// multi page list
	
	public static MultiPageListBuilder multiPageList()
	{
		return new MultiPageListBuilder();
	}
	
	public static MultiPageListBuilder multiPageList(final ComponentBuilder<?, ?>... components)
	{
		return new MultiPageListBuilder().add(components);
	}
	
	public static CurrentDateBuilder currentDate()
	{
		return new CurrentDateBuilder();
	}
	
	public static PageNumberBuilder pageNumber()
	{
		return new PageNumberBuilder();
	}
	
	public static PageXofYBuilder pageXofY()
	{
		return new PageXofYBuilder();
	}
	
	public static PageXslashYBuilder pageXslashY()
	{
		return new PageXslashYBuilder();
	}
	
	public static TotalPagesBuilder totalPages()
	{
		return new TotalPagesBuilder();
	}
	
	// text
	
	public static TextFieldBuilder<String> text(final String text)
	{
		return new TextFieldBuilder<String>().setText(text);
	}
	
	public static TextFieldBuilder<Date> text(final Date date)
	{
		return new TextFieldBuilder<Date>().setText(date);
	}
	
	public static <T extends Number> TextFieldBuilder<T> text(final T number)
	{
		return new TextFieldBuilder<T>().setText(number);
	}
	
	public static <T> TextFieldBuilder<T> text(final FieldBuilder<T> field)
	{
		return new TextFieldBuilder<T>().setText(field);
	}
	
	public static <T> TextFieldBuilder<T> text(final VariableBuilder<T> variable)
	{
		return new TextFieldBuilder<T>().setText(variable);
	}
	
	public static <T> TextFieldBuilder<T> text(final DRIExpression<T> textExpression)
	{
		return new TextFieldBuilder<T>().setText(textExpression);
	}
	
	// filler
	
	public static FillerBuilder filler()
	{
		return new FillerBuilder();
	}
	
	public static FillerBuilder horizontalGap(final int width)
	{
		return new FillerBuilder().setFixedWidth(width);
	}
	
	public static FillerBuilder verticalGap(final int height)
	{
		return new FillerBuilder().setFixedHeight(height);
	}
	
	public static FillerBuilder gap(final int width, final int height)
	{
		return new FillerBuilder().setFixedDimension(width, height);
	}
	
	// image
	
	public static ImageBuilder image(final DRIExpression<?> imageExpression)
	{
		return new ImageBuilder().setImage(imageExpression);
	}
	
	public static ImageBuilder image(final String imagePath)
	{
		return new ImageBuilder().setImage(imagePath);
	}
	
	public static ImageBuilder image(final Image image)
	{
		return new ImageBuilder().setImage(image);
	}
	
	public static ImageBuilder image(final InputStream imageInputStream)
	{
		return new ImageBuilder().setImage(imageInputStream);
	}
	
	public static ImageBuilder image(final URL imageUrl)
	{
		return new ImageBuilder().setImage(imageUrl);
	}
	
	public static ImageBuilder image(final Renderable image)
	{
		return new ImageBuilder().setImage(Expressions.value(image, Renderable.class));
	}
	
	@Deprecated
	public static ImageBuilder image(final net.sf.jasperreports.engine.Renderable image)
	{
		return new ImageBuilder().setImage(Expressions.value(image, net.sf.jasperreports.engine.Renderable.class));
	}
	
	// subreport
	
	public static SubreportBuilder subreport(final JasperReportBuilder reportBuilder)
	{
		final SubreportBuilder subreport = new SubreportBuilder();
		subreport.setReport(reportBuilder);
		if(reportBuilder.getConnection() != null)
		{
			subreport.setConnection(reportBuilder.getConnection());
		}
		if(reportBuilder.getDataSource() != null)
		{
			subreport.setDataSource(reportBuilder.getDataSource());
		}
		return subreport;
	}
	
	public static SubreportBuilder subreport(final JasperReport jasperReport)
	{
		return new SubreportBuilder().setReport(jasperReport);
	}
	
	public static SubreportBuilder subreport(final DRIExpression<?> reportExpression)
	{
		return new SubreportBuilder().setReport(reportExpression);
	}
	
	// line
	
	public static LineBuilder line()
	{
		return new LineBuilder();
	}
	
	// break
	
	public static BreakBuilder pageBreak()
	{
		return new BreakBuilder().setType(BreakType.PAGE);
	}
	
	public static BreakBuilder columnBreak()
	{
		return new BreakBuilder().setType(BreakType.COLUMN);
	}
	
	// generic element
	
	public static GenericElementBuilder genericElement(final String namespace, final String name)
	{
		return new GenericElementBuilder(namespace, name);
	}
	
	// boolean
	
	public static BooleanFieldBuilder booleanField(final Boolean value)
	{
		return new BooleanFieldBuilder().setValue(value);
	}
	
	public static BooleanFieldBuilder booleanField(final FieldBuilder<Boolean> field)
	{
		return new BooleanFieldBuilder().setValue(field);
	}
	
	public static BooleanFieldBuilder booleanField(final DRIExpression<Boolean> valueExpression)
	{
		return new BooleanFieldBuilder().setValue(valueExpression);
	}
	
	// ellipse
	
	public static EllipseBuilder ellipse()
	{
		return new EllipseBuilder();
	}
	
	// rectangle
	
	public static RectangleBuilder rectangle()
	{
		return new RectangleBuilder();
	}
	
	public static RectangleBuilder roundRectangle()
	{
		return new RectangleBuilder().setRadius(10);
	}
	
	public static RectangleBuilder roundRectangle(final int radius)
	{
		return new RectangleBuilder().setRadius(radius);
	}
	
	// map
	
	public static MapBuilder map()
	{
		return new MapBuilder();
	}
	
	public static MapBuilder map(final Float latitude, final Float longitude, final Integer zoom)
	{
		final MapBuilder mapBuilder = new MapBuilder();
		mapBuilder.setLatitude(latitude);
		mapBuilder.setLongitude(longitude);
		mapBuilder.setZoom(zoom);
		return mapBuilder;
	}
	
	// alignment
	
	public static HorizontalListBuilder centerHorizontal(final ComponentBuilder<?, ?> component)
	{
		final HorizontalListBuilder list = horizontalList();
		list.add(filler().setWidth(1));
		list.add(component);
		list.add(filler().setWidth(1));
		return list;
	}
	
	public static VerticalListBuilder centerVertical(final ComponentBuilder<?, ?> component)
	{
		final VerticalListBuilder list = verticalList();
		list.add(filler().setHeight(1));
		list.add(component);
		list.add(filler().setHeight(1));
		return list;
	}
}
