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
package software.xdev.dynamicreports.jasper.transformation;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import software.xdev.dynamicreports.design.definition.DRIDesignBand;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.design.definition.DRIDesignTemplateDesign;
import software.xdev.dynamicreports.report.constant.ListType;


public class BandTransform
{
	private final JasperTransformAccessor accessor;
	
	public BandTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	public void transform()
	{
		final DRIDesignReport report = this.accessor.getReport();
		final JasperDesign design = this.accessor.getDesign();
		final DRIDesignTemplateDesign templateDesign = report.getTemplateDesign();
		
		if(templateDesign.getTitleComponentsCount() == 0 && report.getTitleBand() != null)
		{
			design.setTitle(this.band(report.getTitleBand()));
		}
		if(templateDesign.getPageHeaderComponentsCount() == 0 && report.getPageHeaderBand() != null)
		{
			design.setPageHeader(this.band(report.getPageHeaderBand()));
		}
		if(templateDesign.getPageFooterComponentsCount() == 0 && report.getPageFooterBand() != null)
		{
			design.setPageFooter(this.band(report.getPageFooterBand()));
		}
		if(templateDesign.getColumnHeaderComponentsCount() == 0 && report.getColumnHeaderBand() != null)
		{
			design.setColumnHeader(this.band(report.getColumnHeaderBand()));
		}
		if(templateDesign.getColumnFooterComponentsCount() == 0 && report.getColumnFooterBand() != null)
		{
			design.setColumnFooter(this.band(report.getColumnFooterBand()));
		}
		for(final DRIDesignGroup group : report.getGroups())
		{
			if(group.getHeaderBands() != null)
			{
				JRDesignBand jrTitleAndValueBand = null;
				for(final DRIDesignBand band : group.getHeaderBands())
				{
					final JRDesignBand jrBand = this.band(band);
					if(band.getName() != null && band.getName().equals("groupHeaderTitleAndValue"))
					{
						jrTitleAndValueBand = jrBand;
					}
					if(jrBand != null)
					{
						if(band.getName() != null && band.getName().equals("subtotalGroupHeader")
							&& jrTitleAndValueBand != null && group.isHeaderWithSubtotal())
						{
							if(jrTitleAndValueBand.getHeight() < jrBand.getHeight())
							{
								jrTitleAndValueBand.setHeight(jrBand.getHeight());
							}
							for(final JRElement jrElement : jrBand.getElements())
							{
								jrTitleAndValueBand.addElement(jrElement);
							}
							continue;
						}
						((JRDesignSection)this.accessor.getGroupTransform()
							.getGroup(group)
							.getGroupHeaderSection()).addBand(
							jrBand);
					}
				}
			}
			if(group.getFooterBands() != null)
			{
				for(final DRIDesignBand band : group.getFooterBands())
				{
					final JRDesignBand jrBand = this.band(band);
					if(jrBand != null)
					{
						((JRDesignSection)this.accessor.getGroupTransform()
							.getGroup(group)
							.getGroupFooterSection()).addBand(
							jrBand);
					}
				}
			}
		}
		for(final DRIDesignBand band : report.getDetailBands())
		{
			final JRDesignBand jrBand = this.band(band);
			if(jrBand != null)
			{
				((JRDesignSection)design.getDetailSection()).addBand(jrBand);
			}
		}
		if(templateDesign.getLastPageFooterComponentsCount() == 0 && report.getLastPageFooterBand() != null)
		{
			design.setLastPageFooter(this.band(report.getLastPageFooterBand()));
		}
		if(templateDesign.getSummaryComponentsCount() == 0 && report.getSummaryBand() != null)
		{
			design.setSummary(this.band(report.getSummaryBand()));
		}
		if(templateDesign.getNoDataComponentsCount() == 0 && report.getNoDataBand() != null)
		{
			design.setNoData(this.band(report.getNoDataBand()));
		}
		if(templateDesign.getBackgroundComponentsCount() == 0 && report.getBackgroundBand() != null)
		{
			design.setBackground(this.band(report.getBackgroundBand()));
		}
	}
	
	// band
	private JRDesignBand band(final DRIDesignBand band)
	{
		if(band == null)
		{
			return null;
		}
		final JRDesignBand jrBand = new JRDesignBand();
		if(band.getBandComponent() != null)
		{
			if(band.getPrintWhenExpression() != null)
			{
				jrBand.setPrintWhenExpression(this.accessor.getExpressionTransform()
					.getExpression(band.getPrintWhenExpression()));
			}
			else
			{
				jrBand.setPrintWhenExpression(this.accessor.getExpressionTransform()
					.getExpression(band.getBandComponent().getPrintWhenExpression()));
			}
			jrBand.setSplitType(ConstantTransform.splitType(band.getSplitType()));
			final JRDesignElement[] jrElements =
				this.accessor.getComponentTransform().component(band.getBandComponent(), ListType.VERTICAL);
			for(final JRDesignElement jrElement : jrElements)
			{
				jrBand.addElement(jrElement);
			}
		}
		jrBand.setHeight(band.getHeight());
		return jrBand;
	}
}
