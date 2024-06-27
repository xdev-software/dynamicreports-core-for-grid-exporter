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
package software.xdev.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.component.DRDesignFiller;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignPage;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.DRIBand;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.exception.DRException;


public class BandTransform
{
	private final DesignTransformAccessor accessor;
	
	private DRDesignBand titleBand;
	private DRDesignBand pageHeaderBand;
	private DRDesignBand pageFooterBand;
	private DRDesignBand columnHeaderBand;
	private DRDesignBand columnHeaderForGroupBand;
	private DRDesignBand columnFooterBand;
	private final List<DRDesignBand> detailBands;
	private DRDesignBand detailBand;
	private DRDesignBand lastPageFooterBand;
	private DRDesignBand summaryBand;
	private DRDesignBand noDataBand;
	private DRDesignBand backgroundBand;
	
	public BandTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.detailBands = new ArrayList<>();
	}
	
	public void transform() throws DRException
	{
		final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
		
		final DRIReport report = this.accessor.getReport();
		
		DRIBand band = report.getTitleBand();
		this.titleBand = this.band(
			"title",
			band,
			templateTransform.getTitleSplitType(band),
			templateTransform.getTitleStyle(band),
			templateTransform.getTitleBackgroundComponent(band),
			ResetType.REPORT,
			null);
		
		band = report.getPageHeaderBand();
		this.pageHeaderBand = this.band(
			"pageHeader",
			band,
			templateTransform.getPageHeaderSplitType(band),
			templateTransform.getPageHeaderStyle(band),
			templateTransform.getPageHeaderBackgroundComponent(band),
			ResetType.PAGE,
			null);
		
		band = report.getPageFooterBand();
		this.pageFooterBand = this.band(
			"pageFooter",
			band,
			templateTransform.getPageFooterSplitType(band),
			templateTransform.getPageFooterStyle(band),
			templateTransform.getPageFooterBackgroundComponent(band),
			ResetType.PAGE,
			null);
		
		band = report.getColumnHeaderBand();
		this.columnHeaderBand =
			this.band(
				"columnHeader",
				band,
				templateTransform.getColumnHeaderSplitType(band),
				templateTransform.getColumnHeaderStyle(band),
				templateTransform.getColumnHeaderBackgroundComponent(band),
				ResetType.COLUMN,
				null);
		
		for(final DRIGroup group : report.getGroups())
		{
			if(templateTransform.isGroupShowColumnHeaderAndFooter(group))
			{
				band = report.getColumnHeaderBand();
				this.columnHeaderForGroupBand = this.band(
					"columnHeaderForGroup",
					band,
					templateTransform.getColumnHeaderSplitType(band),
					templateTransform.getColumnHeaderStyle(band),
					templateTransform.getColumnHeaderBackgroundComponent(band),
					ResetType.COLUMN,
					null);
				break;
			}
		}
		
		band = report.getColumnFooterBand();
		this.columnFooterBand =
			this.band(
				"columnFooter",
				band,
				templateTransform.getColumnFooterSplitType(band),
				templateTransform.getColumnFooterStyle(band),
				templateTransform.getColumnFooterBackgroundComponent(band),
				ResetType.COLUMN,
				null);
		
		band = report.getDetailHeaderBand();
		this.detailBands.add(
			this.band(
				"detailHeader",
				band,
				templateTransform.getDetailHeaderSplitType(band),
				templateTransform.getDetailHeaderStyle(band),
				templateTransform.getDetailHeaderBackgroundComponent(band),
				ResetType.REPORT,
				null));
		
		band = report.getDetailBand();
		this.detailBand =
			this.band(
				"detail",
				band,
				templateTransform.getDetailSplitType(band),
				templateTransform.getDetailStyle(band),
				templateTransform.getDetailBackgroundComponent(band),
				ResetType.REPORT,
				null);
		this.detailBands.add(this.detailBand);
		
		band = report.getDetailFooterBand();
		this.detailBands.add(
			this.band(
				"detailFooter",
				band,
				templateTransform.getDetailFooterSplitType(band),
				templateTransform.getDetailFooterStyle(band),
				templateTransform.getDetailFooterBackgroundComponent(band),
				ResetType.REPORT,
				null));
		
		band = report.getLastPageFooterBand();
		this.lastPageFooterBand = this.band(
			"lastPageFooter",
			band,
			templateTransform.getLastPageFooterSplitType(band),
			templateTransform.getLastPageFooterStyle(band),
			templateTransform.getLastPageFooterBackgroundComponent(band),
			ResetType.PAGE,
			null);
		
		band = report.getSummaryBand();
		this.summaryBand =
			this.band(
				"summary",
				band,
				templateTransform.getSummarySplitType(band),
				templateTransform.getSummaryStyle(band),
				templateTransform.getSummaryBackgroundComponent(band),
				ResetType.NONE,
				null);
		
		band = report.getNoDataBand();
		this.noDataBand =
			this.band(
				"noData",
				band,
				templateTransform.getNoDataSplitType(band),
				templateTransform.getNoDataStyle(band),
				templateTransform.getNoDataBackgroundComponent(band),
				ResetType.NONE,
				null);
		
		band = report.getBackgroundBand();
		this.backgroundBand = this.band(
			"background",
			band,
			templateTransform.getBackgroundSplitType(band),
			templateTransform.getBackgroundStyle(band),
			templateTransform.getBackgroundBackgroundComponent(band),
			ResetType.NONE,
			null);
	}
	
	public void prepareBands() throws DRException
	{
		final BandComponentsTransform bandComponents = new BandComponentsTransform(this.accessor);
		final DRITemplateDesign<?> templateDesign = this.accessor.getReport().getTemplateDesign();
		final int maxWidth = this.accessor.getPageTransform().getMaxBandWidth();
		final int maxColumnWidth = this.accessor.getPageTransform().getPage().getColumnWidth();
		
		this.titleBand = bandComponents.prepareBand(this.titleBand, maxWidth,
			templateDesign.getTitleComponentsCount());
		this.pageHeaderBand =
			bandComponents.prepareBand(this.pageHeaderBand, maxWidth, templateDesign.getPageHeaderComponentsCount());
		this.pageFooterBand =
			bandComponents.prepareBand(this.pageFooterBand, maxWidth, templateDesign.getPageFooterComponentsCount());
		this.columnHeaderBand = bandComponents.prepareBand(
			this.columnHeaderBand,
			maxColumnWidth,
			templateDesign.getColumnHeaderComponentsCount());
		this.columnFooterBand = bandComponents.prepareBand(
			this.columnFooterBand,
			maxColumnWidth,
			templateDesign.getColumnFooterComponentsCount());
		final List<DRDesignBand> removeDetailBands = new ArrayList<>();
		for(final DRDesignBand detailBand : this.detailBands)
		{
			if(bandComponents.prepareBand(detailBand, maxColumnWidth, 0) == null)
			{
				removeDetailBands.add(detailBand);
			}
		}
		this.detailBands.removeAll(removeDetailBands);
		this.lastPageFooterBand =
			bandComponents.prepareBand(
				this.lastPageFooterBand, maxWidth,
				templateDesign.getLastPageFooterComponentsCount());
		this.summaryBand =
			bandComponents.prepareBand(this.summaryBand, maxWidth, templateDesign.getSummaryComponentsCount());
		this.noDataBand =
			bandComponents.prepareBand(this.noDataBand, maxWidth, templateDesign.getNoDataComponentsCount());
		
		if(this.backgroundBand.getList() != null && this.backgroundBand.getList().isEmpty() && (
			this.backgroundBand.getList().getStyle() != null
				|| this.backgroundBand.getList().getBackgroundComponent() != null))
		{
			final DRDesignFiller component = new DRDesignFiller();
			component.setWidth(1);
			component.setHeight(1);
			this.backgroundBand.getList().addComponent(component);
		}
		this.backgroundBand =
			bandComponents.prepareBand(this.backgroundBand, maxWidth, templateDesign.getBackgroundComponentsCount());
		if(this.backgroundBand != null && this.backgroundBand.getBandComponent() != null
			&& this.backgroundBand.getList() != null
			&& (this.backgroundBand.getList().getStyle() != null
				|| this.backgroundBand.getList().getBackgroundComponent() != null))
		{
			final DRIDesignPage page = this.accessor.getPage();
			final int height = page.getHeight() - page.getMargin().getTop() - page.getMargin().getBottom();
			this.backgroundBand.getList().setHeight(height);
			if(this.backgroundBand.getList().getBackgroundComponent() != null)
			{
				this.backgroundBand.getList().getBackgroundComponent().setHeight(height);
			}
			this.backgroundBand.setHeight(height);
		}
		
		for(final DRDesignGroup group : this.accessor.getGroupTransform().getGroups())
		{
			List<DRDesignBand> bands = new ArrayList<>();
			for(final DRDesignBand band : group.getHeaderBands())
			{
				final DRDesignBand newBand = bandComponents.prepareBand(band, maxColumnWidth, 0);
				if(newBand != null)
				{
					bands.add(newBand);
				}
			}
			group.setHeaderBands(bands);
			bands = new ArrayList<>();
			for(final DRDesignBand band : group.getFooterBands())
			{
				final DRDesignBand newBand = bandComponents.prepareBand(band, maxColumnWidth, 0);
				if(newBand != null)
				{
					bands.add(newBand);
				}
			}
			group.setFooterBands(bands);
		}
	}
	
	// band
	
	protected DRDesignBand band(
		final String bandName,
		final DRIBand band,
		final SplitType splitType,
		final DRIReportStyle defaultStyle,
		final DRIComponent defaultBackgroundComponent,
		final ResetType resetType,
		final DRDesignGroup resetGroup)
		throws DRException
	{
		final DRDesignBand designBand = new DRDesignBand(bandName);
		designBand.setSplitType(splitType);
		designBand.setList(this.accessor.getComponentTransform()
			.list(band.getList(), DefaultStyleType.TEXT, resetType, resetGroup));
		designBand.setPrintWhenExpression(this.accessor.getExpressionTransform()
			.transformExpression(band.getPrintWhenExpression()));
		
		if(designBand.getList().getStyle() == null && defaultStyle != null)
		{
			designBand.getList()
				.setStyle(this.accessor.getStyleTransform().transformStyle(defaultStyle, false,
					DefaultStyleType.NONE));
		}
		if(designBand.getList().getBackgroundComponent() == null && defaultBackgroundComponent != null)
		{
			designBand.getList()
				.setBackgroundComponent(this.accessor.getComponentTransform()
					.listBackgroundComponent(defaultBackgroundComponent, DefaultStyleType.TEXT, resetType,
						resetGroup));
		}
		
		return designBand;
	}
	
	protected DRDesignBand band(
		final String bandName,
		final DRIBand band,
		final SplitType splitType,
		final DRIReportStyle defaultStyle,
		final DRIComponent defaultBackgroundComponent) throws DRException
	{
		final DRDesignBand designBand = new DRDesignBand(bandName);
		designBand.setSplitType(splitType);
		final DRDesignList list = new DRDesignList();
		list.setType(band.getList().getType());
		list.setGap(this.accessor.getTemplateTransform().getListGap(band.getList()));
		list.setStretchType(this.accessor.getTemplateTransform().getStretchType(band.getList()));
		list.setPrintWhenExpression(this.accessor.getExpressionTransform()
			.transformExpression(band.getList().getPrintWhenExpression()));
		list.setStyle(this.accessor.getStyleTransform()
			.transformStyle(band.getList().getStyle(), false, DefaultStyleType.NONE));
		designBand.setList(list);
		
		if(list.getStyle() == null && defaultStyle != null)
		{
			list.setStyle(this.accessor.getStyleTransform().transformStyle(defaultStyle, false,
				DefaultStyleType.NONE));
		}
		if(designBand.getList().getBackgroundComponent() == null && defaultBackgroundComponent != null)
		{
			designBand.getList()
				.setBackgroundComponent(this.accessor.getComponentTransform()
					.listBackgroundComponent(defaultBackgroundComponent, DefaultStyleType.TEXT, ResetType.NONE, null));
		}
		
		return designBand;
	}
	
	public DRDesignBand getTitleBand()
	{
		return this.titleBand;
	}
	
	public DRDesignBand getPageHeaderBand()
	{
		return this.pageHeaderBand;
	}
	
	public DRDesignBand getPageFooterBand()
	{
		return this.pageFooterBand;
	}
	
	public DRDesignBand getColumnHeaderBand()
	{
		return this.columnHeaderBand;
	}
	
	public DRDesignBand getColumnHeaderForGroupBand()
	{
		return this.columnHeaderForGroupBand;
	}
	
	public DRDesignBand getColumnFooterBand()
	{
		return this.columnFooterBand;
	}
	
	public List<DRDesignBand> getDetailBands()
	{
		return this.detailBands;
	}
	
	public DRDesignBand getDetailBand()
	{
		return this.detailBand;
	}
	
	public DRDesignBand getLastPageFooterBand()
	{
		return this.lastPageFooterBand;
	}
	
	public DRDesignBand getSummaryBand()
	{
		return this.summaryBand;
	}
	
	public DRDesignBand getNoDataBand()
	{
		return this.noDataBand;
	}
	
	public DRDesignBand getBackgroundBand()
	{
		return this.backgroundBand;
	}
}
