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

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.renderers.SimpleDataRenderer;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.component.ImageBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.test.jasper.AbstractJasperTest;


class Image2Test extends AbstractJasperTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final ImageBuilder image = cmp.image(Image2Test.class.getResourceAsStream("dynamicreports.png"))
			.setHorizontalImageAlignment(HorizontalImageAlignment.CENTER);
		rb.pageHeader(image).detail(cmp.filler().setFixedHeight(20));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		
		try
		{
			JRPrintImage jrImage = (JRPrintImage)this.getElementAt("pageHeader.image1", 0);
			final byte[] imageData1 =
				((SimpleDataRenderer)jrImage.getRenderer()).getData(DefaultJasperReportsContext.getInstance());
			jrImage = (JRPrintImage)this.getElementAt("pageHeader.image1", 1);
			final byte[] imageData2 =
				((SimpleDataRenderer)jrImage.getRenderer()).getData(DefaultJasperReportsContext.getInstance());
			Assertions.assertTrue(Arrays.equals(imageData1, imageData2));
			Assertions.assertEquals(HorizontalImageAlignEnum.CENTER, jrImage.getHorizontalImageAlign());
		}
		catch(final JRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	@Override
	protected boolean serializableTest()
	{
		return false;
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(50);
	}
}
