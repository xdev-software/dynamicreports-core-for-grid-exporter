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
package software.xdev.dynamicreports.test.jasper.report;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.hyperLink;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.constant.HyperLinkTarget;
import software.xdev.dynamicreports.report.constant.HyperLinkType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class HyperLinkTest extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final HyperLinkBuilder hyperLink1 = hyperLink().setReference("reference")
			.setTooltip("tooltip")
			.setAnchor("anchor")
			.setPage(1)
			.setType(HyperLinkType.LOCAL_ANCHOR)
			.setTarget(HyperLinkTarget.TOP);
		final HyperLinkBuilder hyperLink2 = hyperLink().setType("customType").setTarget("customTarget");
		
		rb.title(
			cmp.text("title 1").setHyperLink(hyperLink1).setAnchorName("anchorName").setBookmarkLevel(1),
			cmp.text("title 2").setHyperLink(hyperLink2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		JRPrintText textField = (JRPrintText)this.getElementAt("title.textField1", 0);
		Assertions.assertEquals("reference", textField.getHyperlinkReference());
		Assertions.assertEquals("tooltip", textField.getHyperlinkTooltip());
		Assertions.assertEquals("anchor", textField.getHyperlinkAnchor());
		Assertions.assertEquals("anchorName", textField.getAnchorName());
		Assertions.assertEquals(1, textField.getBookmarkLevel());
		Assertions.assertEquals(Integer.valueOf(1), textField.getHyperlinkPage());
		Assertions.assertEquals(HyperlinkTypeEnum.LOCAL_ANCHOR, textField.getHyperlinkTypeValue());
		Assertions.assertEquals(HyperlinkTypeEnum.LOCAL_ANCHOR.getName(), textField.getLinkType());
		Assertions.assertEquals(HyperlinkTargetEnum.TOP, textField.getHyperlinkTargetValue());
		Assertions.assertEquals(HyperlinkTargetEnum.TOP.getName(), textField.getLinkTarget());
		
		textField = (JRPrintText)this.getElementAt("title.textField2", 0);
		Assertions.assertEquals(HyperlinkTypeEnum.CUSTOM, textField.getHyperlinkTypeValue());
		Assertions.assertEquals("customType", textField.getLinkType());
		Assertions.assertEquals(HyperlinkTargetEnum.CUSTOM, textField.getHyperlinkTargetValue());
		Assertions.assertEquals("customTarget", textField.getLinkTarget());
	}
}
