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
package software.xdev.dynamicreports.test.jasper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;


/**
 * @author Ricardo Mariaca
 */
public abstract class AbstractJasperTest
{
	private JasperReportBuilder reportBuilder;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	
	@BeforeEach
	public void init()
	{
		try
		{
			this.reportBuilder = DynamicReports.report();
			this.configureReport(this.reportBuilder);
			if(this.serializableTest())
			{
				this.reportBuilder = this.serializableReportTest(this.reportBuilder);
				this.serializableParametersTest(this.reportBuilder.getJasperParameters());
			}
			final JRDataSource dataSource = this.createDataSource();
			if(dataSource != null)
			{
				this.reportBuilder.setDataSource(dataSource);
			}
			this.build();
			if(this.serializableJrPrintTest())
			{
				this.jasperPrint = this.serializableTest(this.jasperPrint);
			}
		}
		catch(final Exception e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	protected void build() throws DRException
	{
		this.jasperReport = this.reportBuilder.toJasperReport();
		this.jasperPrint = this.reportBuilder.toJasperPrint();
	}
	
	@Test
	public void test()
	{
	}
	
	protected boolean serializableTest()
	{
		return true;
	}
	
	protected boolean serializableJrPrintTest()
	{
		return true;
	}
	
	private JasperReportBuilder serializableReportTest(final JasperReportBuilder report)
		throws IOException, ClassNotFoundException
	{
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(report);
		oos.flush();
		oos.close();
		
		final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
		final ObjectInputStream ois = new ObjectInputStream(stream);
		return (JasperReportBuilder)ois.readObject();
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> serializableParametersTest(final Map<String, Object> parameters)
		throws IOException, ClassNotFoundException
	{
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(parameters);
		oos.flush();
		oos.close();
		
		final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
		final ObjectInputStream ois = new ObjectInputStream(stream);
		return (Map<String, Object>)ois.readObject();
	}
	
	private JasperPrint serializableTest(final JasperPrint jasperPrint) throws IOException, JRException
	{
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JRSaver.saveObject(jasperPrint, bos);
		bos.flush();
		bos.close();
		
		final InputStream stream = new ByteArrayInputStream(bos.toByteArray());
		return (JasperPrint)JRLoader.loadObject(stream);
	}
	
	public JasperReportBuilder getReportBuilder()
	{
		return this.reportBuilder;
	}
	
	public JasperReport getJasperReport()
	{
		return this.jasperReport;
	}
	
	public JasperPrint getJasperPrint()
	{
		return this.jasperPrint;
	}
	
	protected void numberOfPagesTest(final int expectedNumberOfPages)
	{
		Assertions.assertEquals(expectedNumberOfPages, this.getNumberOfPages());
	}
	
	private int getNumberOfPages()
	{
		return this.jasperPrint.getPages().size();
	}
	
	protected JRPrintElement getElementAt(final String key, final int index)
	{
		final List<JRPrintElement> elements = this.findElement(key);
		if(elements.size() - 1 < index)
		{
			Assertions.fail("Element " + key + " at index " + index + " not found");
			return null;
		}
		return elements.get(index);
	}
	
	protected List<JRPrintElement> findElement(final String key)
	{
		final List<JRPrintElement> elements = new ArrayList<>();
		for(
			final Iterator<?> iterator = this.jasperPrint.getPages().iterator(); iterator.hasNext(); )
		{
			final JRPrintPage page = (JRPrintPage)iterator.next();
			for(
				final Iterator<?> iterator2 = page.getElements().iterator(); iterator2.hasNext(); )
			{
				final JRPrintElement element = (JRPrintElement)iterator2.next();
				this.findElement(key, elements, element);
			}
		}
		return elements;
	}
	
	protected void findElement(final String key, final List<JRPrintElement> elements, final JRPrintElement element)
	{
		if(key.equals(element.getKey()))
		{
			elements.add(element);
		}
		if(element instanceof JRPrintFrame)
		{
			for(
				final Iterator<?> iterator = ((JRPrintFrame)element).getElements().iterator(); iterator.hasNext(); )
			{
				final JRPrintElement element2 = (JRPrintElement)iterator.next();
				this.findElement(key, elements, element2);
			}
		}
	}
	
	protected void containsElement(final String key, final int pageIndex)
	{
		final List<JRPrintElement> elements = new ArrayList<>();
		final JRPrintPage page = this.getJasperPrint().getPages().get(pageIndex);
		for(
			final Iterator<?> iterator = page.getElements().iterator(); iterator.hasNext(); )
		{
			final JRPrintElement element = (JRPrintElement)iterator.next();
			this.findElement(key, elements, element);
		}
		if(elements.isEmpty())
		{
			Assertions.fail("Element " + key + " at page index " + pageIndex + " not found");
		}
	}
	
	protected Date toDate(final int year, final int month, final int day)
	{
		final Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}
	
	protected JRDataSource createDataSource()
	{
		return null;
	}
	
	protected abstract void configureReport(JasperReportBuilder rb) throws DRException;
}
