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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.engine.JRGenericElementType;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignBreak;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignEllipse;
import net.sf.jasperreports.engine.design.JRDesignFrame;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.OnErrorTypeEnum;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.definition.DRIDesignHyperLink;
import software.xdev.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import software.xdev.dynamicreports.design.definition.component.DRIDesignBreak;
import software.xdev.dynamicreports.design.definition.component.DRIDesignComponent;
import software.xdev.dynamicreports.design.definition.component.DRIDesignEllipse;
import software.xdev.dynamicreports.design.definition.component.DRIDesignFiller;
import software.xdev.dynamicreports.design.definition.component.DRIDesignGenericElement;
import software.xdev.dynamicreports.design.definition.component.DRIDesignImage;
import software.xdev.dynamicreports.design.definition.component.DRIDesignLine;
import software.xdev.dynamicreports.design.definition.component.DRIDesignList;
import software.xdev.dynamicreports.design.definition.component.DRIDesignMap;
import software.xdev.dynamicreports.design.definition.component.DRIDesignRectangle;
import software.xdev.dynamicreports.design.definition.component.DRIDesignSubreport;
import software.xdev.dynamicreports.design.definition.component.DRIDesignTextField;
import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.jasper.transformation.expression.JasperSubreportParametersExpression;
import software.xdev.dynamicreports.jasper.transformation.expression.SubreportExpression;
import software.xdev.dynamicreports.jasper.transformation.expression.SubreportParametersExpression;
import software.xdev.dynamicreports.jasper.transformation.expression.SubreportWidthExpression;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.component.CustomComponentTransform;
import software.xdev.dynamicreports.report.component.CustomComponents;
import software.xdev.dynamicreports.report.component.DRIDesignCustomComponent;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.StretchType;


public class ComponentTransform
{
	private final JasperTransformAccessor accessor;
	
	public ComponentTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	protected JRDesignElement[] component(final DRIDesignComponent component, final ListType listType)
	{
		final JRDesignElement[] jrElements;
		if(component instanceof DRIDesignList)
		{
			jrElements = this.list((DRIDesignList)component);
		}
		else if(component instanceof DRIDesignTextField)
		{
			final JRDesignElement jrElement = this.textField((DRIDesignTextField)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignFiller)
		{
			final JRDesignElement jrElement = this.filler((DRIDesignFiller)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignImage)
		{
			final JRDesignElement jrElement = this.image((DRIDesignImage)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignSubreport)
		{
			final JRDesignElement jrElement = this.subreport((DRIDesignSubreport)component, component.getWidth());
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignLine)
		{
			final JRDesignElement jrElement = this.line((DRIDesignLine)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignEllipse)
		{
			final JRDesignElement jrElement = this.ellipse((DRIDesignEllipse)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignRectangle)
		{
			final JRDesignElement jrElement = this.rectangle((DRIDesignRectangle)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignBreak)
		{
			final JRDesignElement jrElement = this.breakComponent((DRIDesignBreak)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignGenericElement)
		{
			final JRDesignElement jrElement = this.genericElement((DRIDesignGenericElement)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignCrosstab)
		{
			final JRDesignElement jrElement =
				this.accessor.getCrosstabTransform().transform((DRIDesignCrosstab)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignMap)
		{
			final JRDesignElement jrElement = this.map((DRIDesignMap)component);
			jrElements = this.component(jrElement, component, listType);
		}
		else if(component instanceof DRIDesignCustomComponent)
		{
			final JRDesignElement jrElement = this.customComponent(component);
			jrElements = this.component(jrElement, component, listType);
		}
		else
		{
			throw new JasperDesignException("Component " + component.getClass().getName() + " not supported");
		}
		
		return jrElements;
	}
	
	private JRDesignElement[] component(
		final JRDesignElement jrElement,
		final DRIDesignComponent component,
		final ListType listType)
	{
		StretchType stretchType = component.getStretchType();
		if(stretchType == null)
		{
			if(component instanceof DRIDesignSubreport || component instanceof DRIDesignLine
				|| component instanceof DRIDesignBreak || component instanceof DRIDesignCrosstab
				|| component instanceof DRIDesignList)
			{
				stretchType = StretchType.NO_STRETCH;
			}
			else
			{
				stretchType = this.detectStretchType(listType);
			}
		}
		
		ComponentPositionType positionType = component.getPositionType();
		if(positionType == null)
		{
			positionType = ComponentPositionType.FLOAT;
		}
		
		jrElement.setPositionType(ConstantTransform.componentPositionType(positionType));
		jrElement.setStretchType(ConstantTransform.stretchType(stretchType));
		jrElement.setPrintInFirstWholeBand(component.isPrintInFirstWholeBand());
		jrElement.setPrintWhenDetailOverflows(component.isPrintWhenDetailOverflows());
		if(component.getPrintWhenGroupChanges() != null)
		{
			jrElement.setPrintWhenGroupChanges(this.accessor.getGroupTransform()
				.getGroup(component.getPrintWhenGroupChanges()));
		}
		jrElement.setKey(component.getUniqueName());
		jrElement.setX(component.getX());
		jrElement.setY(component.getY());
		jrElement.setWidth(component.getWidth());
		jrElement.setHeight(component.getHeight());
		
		if(component.getStyle() != null)
		{
			jrElement.setStyle(this.accessor.getStyleTransform().getStyle(component.getStyle()));
		}
		jrElement.setPrintWhenExpression(this.accessor.getExpressionTransform()
			.getExpression(component.getPrintWhenExpression()));
		jrElement.setRemoveLineWhenBlank(component.isRemoveLineWhenBlank());
		
		for(final DRIDesignPropertyExpression propertyExpression : component.getPropertyExpressions())
		{
			jrElement.addPropertyExpression(this.accessor.getExpressionTransform()
				.getPropertyExpression(propertyExpression));
		}
		
		final DRIDesignTableOfContentsHeading tocHeading = component.getTableOfContentsHeading();
		if(tocHeading != null)
		{
			final DRDesignTextField referenceField = (DRDesignTextField)tocHeading.getReferenceField();
			referenceField.setX(component.getX());
			referenceField.setY(component.getY());
			final JRDesignElement jrReferenceElement = this.textField(referenceField);
			this.component(jrReferenceElement, referenceField, listType);
			return new JRDesignElement[]{jrReferenceElement, jrElement};
		}
		else
		{
			return new JRDesignElement[]{jrElement};
		}
	}
	
	private StretchType detectStretchType(final ListType listType)
	{
		if(listType == null || listType.equals(ListType.VERTICAL))
		{
			return StretchType.NO_STRETCH;
		}
		
		return StretchType.ELEMENT_GROUP_HEIGHT;
	}
	
	// list
	private JRDesignElement[] list(final DRIDesignList list)
	{
		switch(list.getComponentGroupType())
		{
			case FRAME:
				final JRDesignFrame frame = new JRDesignFrame();
				final JRDesignElement[] jrElems = this.component(frame, list, list.getType());
				
				final DRDesignComponent background = (DRDesignComponent)list.getBackgroundComponent();
				if(background != null)
				{
					final JRDesignElement jrBackground = this.component(background, list.getType())[0];
					frame.addElement(jrBackground);
				}
				
				for(final DRIDesignComponent element : list.getComponents())
				{
					final JRDesignElement[] jrElements = this.component(element, list.getType());
					for(final JRDesignElement jrElement : jrElements)
					{
						frame.addElement(jrElement);
					}
				}
				return jrElems;
			case NONE:
				final List<JRDesignElement> jrElementList = new ArrayList<>();
				for(final DRIDesignComponent element : list.getComponents())
				{
					final JRDesignElement[] jrElements = this.component(element, list.getType());
					for(final JRDesignElement jrElement : jrElements)
					{
						jrElementList.add(jrElement);
					}
				}
				return jrElementList.toArray(new JRDesignElement[jrElementList.size()]);
			default:
				throw new JasperDesignException(
					"ComponentGroupType " + list.getComponentGroupType().getClass().getName() + " not supported");
		}
	}
	
	// textField
	private JRDesignElement textField(final DRIDesignTextField textField)
	{
		final JRDesignTextField jrTextField = new JRDesignTextField();
		
		jrTextField.setAnchorNameExpression(this.accessor.getExpressionTransform()
			.getExpression(textField.getAnchorNameExpression()));
		if(textField.getBookmarkLevel() != null)
		{
			jrTextField.setBookmarkLevel(textField.getBookmarkLevel());
		}
		
		final DRIDesignHyperLink hyperLink = textField.getHyperLink();
		if(hyperLink != null)
		{
			jrTextField.setHyperlinkAnchorExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getAnchorExpression()));
			jrTextField.setHyperlinkPageExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getPageExpression()));
			jrTextField.setHyperlinkReferenceExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getReferenceExpression()));
			jrTextField.setHyperlinkTooltipExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getTooltipExpression()));
			if(hyperLink.getType() != null)
			{
				final HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
				if(hyperLinkType != null)
				{
					jrTextField.setHyperlinkType(hyperLinkType);
				}
				else
				{
					jrTextField.setLinkType(hyperLink.getType());
				}
			}
			if(hyperLink.getTarget() != null)
			{
				final HyperlinkTargetEnum hyperLinkTarget = ConstantTransform.hyperLinkTarget(hyperLink.getTarget());
				if(hyperLinkTarget != null)
				{
					jrTextField.setHyperlinkTarget(hyperLinkTarget);
				}
				else
				{
					jrTextField.setLinkTarget(hyperLink.getTarget());
				}
			}
		}
		
		final EvaluationTime evaluationTime = textField.getEvaluationTime();
		jrTextField.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if(evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP)
			&& textField.getEvaluationGroup() != null)
		{
			jrTextField.setEvaluationGroup(this.accessor.getGroupTransform().getGroup(textField.getEvaluationGroup()));
		}
		
		if(textField.getTextAdjust() != null)
		{
			jrTextField.setTextAdjust(ConstantTransform.textAdjust(textField.getTextAdjust()));
		}
		
		final String pattern = textField.getPattern();
		if(!StringUtils.isBlank(pattern))
		{
			jrTextField.setPattern(pattern);
		}
		jrTextField.setPatternExpression(this.accessor.getExpressionTransform()
			.getExpression(textField.getPatternExpression()));
		jrTextField.setHorizontalTextAlign(
			ConstantTransform.horizontalTextAlignment(textField.getHorizontalTextAlignment()));
		jrTextField.setExpression(
			this.accessor.getExpressionTransform().getExpression(textField.getValueExpression()));
		jrTextField.setPrintRepeatedValues(textField.isPrintRepeatedValues());
		jrTextField.setMarkup(ConstantTransform.markup(textField.getMarkup()));
		jrTextField.setBlankWhenNull(true);
		
		return jrTextField;
	}
	
	// filler
	private JRDesignElement filler(final DRIDesignFiller filler)
	{
		final JRDesignStaticText jrDesignStaticText = new JRDesignStaticText();
		return jrDesignStaticText;
	}
	
	// image
	private JRDesignElement image(final DRIDesignImage image)
	{
		final JRDesignImage jrImage = new JRDesignImage(new JRDesignStyle().getDefaultStyleProvider());
		
		jrImage.setAnchorNameExpression(this.accessor.getExpressionTransform()
			.getExpression(image.getAnchorNameExpression()));
		if(image.getBookmarkLevel() != null)
		{
			jrImage.setBookmarkLevel(image.getBookmarkLevel());
		}
		
		final DRIDesignHyperLink hyperLink = image.getHyperLink();
		if(hyperLink != null)
		{
			jrImage.setHyperlinkAnchorExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getAnchorExpression()));
			jrImage.setHyperlinkPageExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getPageExpression()));
			jrImage.setHyperlinkReferenceExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getReferenceExpression()));
			jrImage.setHyperlinkTooltipExpression(this.accessor.getExpressionTransform()
				.getExpression(hyperLink.getTooltipExpression()));
			if(hyperLink.getType() != null)
			{
				final HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
				if(hyperLinkType != null)
				{
					jrImage.setHyperlinkType(hyperLinkType);
				}
				else
				{
					jrImage.setLinkType(hyperLink.getType());
				}
			}
			if(hyperLink.getTarget() != null)
			{
				final HyperlinkTargetEnum hyperLinkTarget = ConstantTransform.hyperLinkTarget(hyperLink.getTarget());
				if(hyperLinkTarget != null)
				{
					jrImage.setHyperlinkTarget(hyperLinkTarget);
				}
				else
				{
					jrImage.setLinkTarget(hyperLink.getTarget());
				}
			}
		}
		
		jrImage.setOnErrorType(OnErrorTypeEnum.BLANK);
		jrImage.setScaleImage(ConstantTransform.imageScale(image.getImageScale()));
		jrImage.setHorizontalImageAlign(
			ConstantTransform.horizontalImageAlignment(image.getHorizontalImageAlignment()));
		jrImage.setExpression(this.accessor.getExpressionTransform().getExpression(image.getImageExpression()));
		if(image.getUsingCache() != null)
		{
			jrImage.setUsingCache(image.getUsingCache());
		}
		if(image.getLazy() != null)
		{
			jrImage.setLazy(image.getLazy());
		}
		
		return jrImage;
	}
	
	// subreport
	private JRDesignElement subreport(final DRIDesignSubreport subreport, final Integer width)
	{
		final JRDesignSubreport jrSubreport = new JRDesignSubreport(new JRDesignStyle().getDefaultStyleProvider());
		jrSubreport.setConnectionExpression(this.accessor.getExpressionTransform()
			.getExpression(subreport.getConnectionExpression()));
		jrSubreport.setDataSourceExpression(this.accessor.getExpressionTransform()
			.getExpression(subreport.getDataSourceExpression()));
		jrSubreport.setRunToBottom(subreport.getRunToBottom());
		
		if(ReportBuilder.class.isAssignableFrom(subreport.getReportExpression().getValueClass()))
		{
			final SubreportWidthExpression pageWidthExpression = new SubreportWidthExpression(width);
			this.accessor.getExpressionTransform().addSimpleExpression(pageWidthExpression);
			final SubreportExpression subreportExpression =
				new SubreportExpression(pageWidthExpression, subreport.getReportExpression(), width);
			this.accessor.getExpressionTransform().addComplexExpression(subreportExpression);
			jrSubreport.setExpression(this.accessor.getExpressionTransform().getExpression(subreportExpression));
			
			final SubreportParametersExpression parametersExpression =
				new SubreportParametersExpression(subreportExpression, subreport.getParametersExpression());
			this.accessor.getExpressionTransform().addComplexExpression(parametersExpression);
			jrSubreport.setParametersMapExpression(this.accessor.getExpressionTransform()
				.getExpression(parametersExpression));
		}
		else
		{
			jrSubreport.setExpression(this.accessor.getExpressionTransform()
				.getExpression(subreport.getReportExpression()));
			
			final JasperSubreportParametersExpression parametersExpression =
				new JasperSubreportParametersExpression(subreport.getParametersExpression());
			this.accessor.getExpressionTransform().addComplexExpression(parametersExpression);
			jrSubreport.setParametersMapExpression(this.accessor.getExpressionTransform()
				.getExpression(parametersExpression));
		}
		
		return jrSubreport;
	}
	
	// line
	private JRDesignElement line(final DRIDesignLine line)
	{
		final JRDesignLine jrDesignLine = new JRDesignLine();
		jrDesignLine.setDirection(ConstantTransform.lineDirection(line.getDirection()));
		this.accessor.getStyleTransform().pen(jrDesignLine.getLinePen(), line.getPen());
		return jrDesignLine;
	}
	
	// ellipse
	private JRDesignElement ellipse(final DRIDesignEllipse ellipse)
	{
		final JRDesignEllipse jrDesignEllipse = new JRDesignEllipse(new JRDesignStyle().getDefaultStyleProvider());
		this.accessor.getStyleTransform().pen(jrDesignEllipse.getLinePen(), ellipse.getPen());
		return jrDesignEllipse;
	}
	
	// rectangle
	private JRDesignElement rectangle(final DRIDesignRectangle rectangle)
	{
		final JRDesignRectangle jrDesignRectangle = new JRDesignRectangle();
		jrDesignRectangle.setRadius(rectangle.getRadius());
		this.accessor.getStyleTransform().pen(jrDesignRectangle.getLinePen(), rectangle.getPen());
		return jrDesignRectangle;
	}
	
	// break
	private JRDesignElement breakComponent(final DRIDesignBreak breakComponent)
	{
		final JRDesignBreak jrDesignBreak = new JRDesignBreak();
		jrDesignBreak.setType(ConstantTransform.breakType(breakComponent.getType()));
		return jrDesignBreak;
	}
	
	// generic element
	private JRDesignElement genericElement(final DRIDesignGenericElement genericElement)
	{
		final JRDesignGenericElement jrDesignGenericElement =
			new JRDesignGenericElement(new JRDesignStyle().getDefaultStyleProvider());
		final JRGenericElementType genericType = new JRGenericElementType(
			genericElement.getGenericElementNamespace(),
			genericElement.getGenericElementName());
		jrDesignGenericElement.setGenericType(genericType);
		final EvaluationTime evaluationTime = genericElement.getEvaluationTime();
		jrDesignGenericElement.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if(evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP)
			&& genericElement.getEvaluationGroup() != null)
		{
			jrDesignGenericElement.setEvaluationGroupName(this.accessor.getGroupTransform()
				.getGroup(genericElement.getEvaluationGroup())
				.getName());
		}
		for(final DRIDesignParameterExpression parameterExpression : genericElement.getParameterExpressions())
		{
			jrDesignGenericElement.addParameter(this.accessor.getExpressionTransform()
				.getGenericElementParameterExpression(parameterExpression));
		}
		return jrDesignGenericElement;
	}
	
	// map
	private JRDesignElement map(final DRIDesignMap map)
	{
		final StandardMapComponent jrMap = new StandardMapComponent();
		final EvaluationTime evaluationTime = map.getEvaluationTime();
		jrMap.setEvaluationTime(ConstantTransform.evaluationTime(evaluationTime));
		if(evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP) && map.getEvaluationGroup() != null)
		{
			jrMap.setEvaluationGroup(this.accessor.getGroupTransform().getGroup(map.getEvaluationGroup()).getName());
		}
		jrMap.setLatitudeExpression(this.accessor.getExpressionTransform().getExpression(map.getLatitudeExpression()));
		jrMap.setLongitudeExpression(this.accessor.getExpressionTransform()
			.getExpression(map.getLongitudeExpression()));
		jrMap.setZoomExpression(this.accessor.getExpressionTransform().getExpression(map.getZoomExpression()));
		
		final JRDesignComponentElement jrComponent = new JRDesignComponentElement();
		jrComponent.setComponent(jrMap);
		jrComponent.setComponentKey(new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "jr", "map"));
		
		return jrComponent;
	}
	
	// custom component
	private JRDesignElement customComponent(final DRIDesignComponent component)
	{
		@SuppressWarnings("rawtypes")
		final CustomComponentTransform componentTransfom = CustomComponents.getComponentTransform(component);
		if(componentTransfom == null)
		{
			throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
		}
		@SuppressWarnings("unchecked")
		final JRDesignComponentElement jrComponent = (JRDesignComponentElement)componentTransfom.jasperComponent(
			this.accessor, component);
		return jrComponent;
	}
}
