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

import software.xdev.dynamicreports.design.base.DRDesignMargin;
import software.xdev.dynamicreports.design.base.DRDesignPage;
import software.xdev.dynamicreports.report.definition.DRIMargin;
import software.xdev.dynamicreports.report.exception.DRException;


public class PageTransform
{
	private final TemplateTransform templateTransform;
	private final DRDesignPage page;
	private int maxBandWidth;
	
	public PageTransform(final DesignTransformAccessor accessor)
	{
		this.templateTransform = accessor.getTemplateTransform();
		this.page = new DRDesignPage();
	}
	
	public void transform() throws DRException
	{
		this.page.setHeight(this.templateTransform.getPageHeight());
		this.page.setOrientation(this.templateTransform.getPageOrientation());
		this.page.setMargin(this.margin(this.templateTransform.getPageMargin()));
		this.page.setColumnsPerPage(this.templateTransform.getPageColumnsPerPage());
		this.page.setColumnSpace(this.templateTransform.getPageColumnSpace());
	}
	
	private DRDesignMargin margin(final DRIMargin margin)
	{
		final DRDesignMargin designMargin = new DRDesignMargin();
		designMargin.setTop(margin.getTop());
		designMargin.setLeft(margin.getLeft());
		designMargin.setBottom(margin.getBottom());
		designMargin.setRight(margin.getRight());
		return designMargin;
	}
	
	public DRDesignPage getPage()
	{
		return this.page;
	}
	
	public void transformPageWidth() throws DRException
	{
		final int pageWidth = this.templateTransform.getPageWidth();
		this.maxBandWidth = pageWidth - this.getPage().getMargin().getLeft() - this.getPage().getMargin().getRight();
		this.page.setWidth(pageWidth);
		this.page.setColumnWidth(this.templateTransform.getPageColumnWidth(this.page));
	}
	
	public int getMaxBandWidth()
	{
		return this.maxBandWidth;
	}
}
