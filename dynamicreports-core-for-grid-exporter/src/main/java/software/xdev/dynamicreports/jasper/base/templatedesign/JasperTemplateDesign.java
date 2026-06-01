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
package software.xdev.dynamicreports.jasper.base.templatedesign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import software.xdev.dynamicreports.jasper.transformation.ConstantTransform;
import software.xdev.dynamicreports.report.base.DRField;
import software.xdev.dynamicreports.report.base.DRMargin;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIMargin;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.exception.DRException;


public class JasperTemplateDesign implements DRITemplateDesign<JasperDesign>
{

	private JasperDesign jasperDesign;
	private List<DRIField<?>> fields;
	private DRMargin margin;
	private transient ByteArrayOutputStream templateDesign;
	
	public JasperTemplateDesign(final JasperDesign jasperDesign) throws DRException
	{
		this.init(jasperDesign);
	}
	
	public JasperTemplateDesign(final File file) throws DRException
	{
		Validate.notNull(file, "file must not be null");
		try
		{
			this.init(JRXmlLoader.load(file));
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
	}
	
	public JasperTemplateDesign(final String fileName) throws DRException
	{
		Validate.notNull(fileName, "fileName must not be null");
		try
		{
			this.init(JRXmlLoader.load(fileName));
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
	}
	
	public JasperTemplateDesign(final InputStream inputStream) throws DRException
	{
		Validate.notNull(inputStream, "inputStream must not be null");
		try
		{
			this.init(JRXmlLoader.load(inputStream));
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
	}
	
	public JasperTemplateDesign(final URL url) throws DRException
	{
		Validate.notNull(url, "url must not be null");
		try
		{
			this.init(JRXmlLoader.load(url.openStream()));
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
		catch(final IOException e)
		{
			throw new DRException(e);
		}
	}
	
	private void init(final JasperDesign jasperDesign) throws DRException
	{
		Validate.notNull(jasperDesign, "jasperDesign must not be null");
		this.jasperDesign = jasperDesign;
		
		this.fields = new ArrayList<>();
		for(final JRField jrField : jasperDesign.getFields())
		{
			@SuppressWarnings({"unchecked", "rawtypes"})
			final DRField<?> field = new DRField(jrField.getName(), jrField.getValueClass());
			this.fields.add(field);
		}
		
		this.margin = new DRMargin();
		this.margin.setTop(jasperDesign.getTopMargin());
		this.margin.setLeft(jasperDesign.getLeftMargin());
		this.margin.setBottom(jasperDesign.getBottomMargin());
		this.margin.setRight(jasperDesign.getRightMargin());
	}
	
	@Override
	public String getReportName()
	{
		return this.jasperDesign.getName();
	}
	
	@Override
	public List<DRIField<?>> getFields()
	{
		return this.fields;
	}
	
	@Override
	public boolean isDefinedParameter(final String name)
	{
		final JRParameter parameter = this.jasperDesign.getParametersMap().get(name);
		return parameter != null;
	}
	
	@Override
	public String getResourceBundleName()
	{
		return this.jasperDesign.getResourceBundle();
	}
	
	@Override
	public Boolean getIgnorePagination()
	{
		return this.jasperDesign.isIgnorePagination();
	}
	
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return ConstantTransform.whenNoDataType(this.jasperDesign.getWhenNoDataType());
	}
	
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return ConstantTransform.whenResourceMissingType(this.jasperDesign.getWhenResourceMissingType());
	}
	
	@Override
	public Boolean getTitleOnANewPage()
	{
		return this.jasperDesign.isTitleNewPage();
	}
	
	@Override
	public Boolean getSummaryOnANewPage()
	{
		return this.jasperDesign.isSummaryNewPage();
	}
	
	@Override
	public Boolean getSummaryWithPageHeaderAndFooter()
	{
		return this.jasperDesign.isSummaryWithPageHeaderAndFooter();
	}
	
	@Override
	public Boolean getFloatColumnFooter()
	{
		return this.jasperDesign.isFloatColumnFooter();
	}
	
	@Override
	public Integer getPageWidth()
	{
		return this.jasperDesign.getPageWidth();
	}
	
	@Override
	public Integer getPageHeight()
	{
		return this.jasperDesign.getPageHeight();
	}
	
	@Override
	public PageOrientation getPageOrientation()
	{
		return ConstantTransform.pageOrientation(this.jasperDesign.getOrientation());
	}
	
	@Override
	public DRIMargin getPageMargin()
	{
		return this.margin;
	}
	
	@Override
	public Integer getPageColumnsPerPage()
	{
		return this.jasperDesign.getColumnCount();
	}
	
	@Override
	public Integer getPageColumnSpace()
	{
		return this.jasperDesign.getColumnSpacing();
	}
	
	@Override
	public Integer getPageColumnWidth()
	{
		return this.jasperDesign.getColumnWidth();
	}
	
	@Override
	public int getTitleComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getTitle());
	}
	
	@Override
	public int getPageHeaderComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getPageHeader());
	}
	
	@Override
	public int getPageFooterComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getPageFooter());
	}
	
	@Override
	public int getColumnHeaderComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getColumnHeader());
	}
	
	@Override
	public int getColumnFooterComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getColumnFooter());
	}
	
	@Override
	public int getLastPageFooterComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getLastPageFooter());
	}
	
	@Override
	public int getSummaryComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getSummary());
	}
	
	@Override
	public int getNoDataComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getNoData());
	}
	
	@Override
	public int getBackgroundComponentsCount()
	{
		return this.getBandComponentsCount(this.jasperDesign.getBackground());
	}
	
	private int getBandComponentsCount(final JRBand band)
	{
		if(band != null && band.getElements() != null)
		{
			return band.getElements().length;
		}
		return 0;
	}
	
	@Override
	public JasperDesign getDesign() throws DRException
	{
		try
		{
			if(this.templateDesign == null)
			{
				this.templateDesign = new ByteArrayOutputStream();
				JRXmlWriter.writeReport(this.jasperDesign, this.templateDesign, "UTF-8");
			}
			
			return JRXmlLoader.load(new ByteArrayInputStream(this.templateDesign.toByteArray()));
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
	}
}
