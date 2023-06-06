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

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.DRDesignHyperLink;
import software.xdev.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import software.xdev.dynamicreports.design.base.DRDesignVariable;
import software.xdev.dynamicreports.design.base.component.DRDesignBreak;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignEllipse;
import software.xdev.dynamicreports.design.base.component.DRDesignFiller;
import software.xdev.dynamicreports.design.base.component.DRDesignGenericElement;
import software.xdev.dynamicreports.design.base.component.DRDesignHyperlinkComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignImage;
import software.xdev.dynamicreports.design.base.component.DRDesignLine;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignMap;
import software.xdev.dynamicreports.design.base.component.DRDesignRectangle;
import software.xdev.dynamicreports.design.base.component.DRDesignSubreport;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.design.transformation.expressions.BooleanTextValueFormatter;
import software.xdev.dynamicreports.design.transformation.expressions.CurrentDateExpression;
import software.xdev.dynamicreports.design.transformation.expressions.MultiPageListDataSourceExpression;
import software.xdev.dynamicreports.design.transformation.expressions.MultiPageListSubreportExpression;
import software.xdev.dynamicreports.design.transformation.expressions.PageNumberExpression;
import software.xdev.dynamicreports.design.transformation.expressions.PageXofYNumberExpression;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.DRGroup;
import software.xdev.dynamicreports.report.base.DRHyperLink;
import software.xdev.dynamicreports.report.base.component.DRHyperLinkComponent;
import software.xdev.dynamicreports.report.base.component.DRList;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.base.style.DRPen;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.component.Components;
import software.xdev.dynamicreports.report.builder.datatype.DataTypes;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.component.CustomComponentTransform;
import software.xdev.dynamicreports.report.component.CustomComponents;
import software.xdev.dynamicreports.report.component.DRICustomComponent;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.component.DRIBooleanField;
import software.xdev.dynamicreports.report.definition.component.DRIBreak;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.component.DRICurrentDate;
import software.xdev.dynamicreports.report.definition.component.DRIDimensionComponent;
import software.xdev.dynamicreports.report.definition.component.DRIEllipse;
import software.xdev.dynamicreports.report.definition.component.DRIFiller;
import software.xdev.dynamicreports.report.definition.component.DRIFormatField;
import software.xdev.dynamicreports.report.definition.component.DRIGenericElement;
import software.xdev.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import software.xdev.dynamicreports.report.definition.component.DRIImage;
import software.xdev.dynamicreports.report.definition.component.DRILine;
import software.xdev.dynamicreports.report.definition.component.DRIList;
import software.xdev.dynamicreports.report.definition.component.DRIListCell;
import software.xdev.dynamicreports.report.definition.component.DRIMap;
import software.xdev.dynamicreports.report.definition.component.DRIMultiPageList;
import software.xdev.dynamicreports.report.definition.component.DRIPageNumber;
import software.xdev.dynamicreports.report.definition.component.DRIPageXofY;
import software.xdev.dynamicreports.report.definition.component.DRIRectangle;
import software.xdev.dynamicreports.report.definition.component.DRISubreport;
import software.xdev.dynamicreports.report.definition.component.DRITextField;
import software.xdev.dynamicreports.report.definition.component.DRITotalPages;
import software.xdev.dynamicreports.report.definition.component.DRIXyList;
import software.xdev.dynamicreports.report.definition.component.DRIXyListCell;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIParameterExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.exception.DRException;

/**
 * <p>ComponentTransform class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public class ComponentTransform {
    private final DesignTransformAccessor accessor;

    /**
     * <p>Constructor for ComponentTransform.</p>
     *
     * @param accessor a {@link software.xdev.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public ComponentTransform(final DesignTransformAccessor accessor) {
        this.accessor = accessor;
    }

    // component

    /**
     * <p>component.</p>
     *
     * @param component        a {@link software.xdev.dynamicreports.report.definition.component.DRIComponent} object.
     * @param defaultStyleType a {@link software.xdev.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignComponent component(final DRIComponent component, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        if (component instanceof DRITextField<?>) {
            return this.textField((DRITextField<?>) component, defaultStyleType);
        }
        if (component instanceof DRIList) {
            return this.list((DRIList) component, defaultStyleType, resetType, resetGroup);
        }
        if (component instanceof DRIXyList) {
            return this.xyList((DRIXyList) component, defaultStyleType, resetType, resetGroup);
        }
        if (component instanceof DRIMultiPageList) {
            return this.multiPageList((DRIMultiPageList) component);
        }
        if (component instanceof DRIFiller) {
            return this.filler((DRIFiller) component);
        }
        if (component instanceof DRIImage) {
            return this.image((DRIImage) component);
        }
        if (component instanceof DRISubreport) {
            return this.subreport((DRISubreport) component);
        }
        if (component instanceof DRIPageXofY) {
            return this.pageXofY((DRIPageXofY) component, defaultStyleType);
        }
        if (component instanceof DRITotalPages) {
            return this.totalPages((DRITotalPages) component, defaultStyleType);
        }
        if (component instanceof DRIPageNumber) {
            return this.pageNumber((DRIPageNumber) component, defaultStyleType);
        }
        if (component instanceof DRICurrentDate) {
            return this.currentDate((DRICurrentDate) component, defaultStyleType);
        }
        if (component instanceof DRILine) {
            return this.line((DRILine) component);
        }
        if (component instanceof DRIEllipse) {
            return this.ellipse((DRIEllipse) component);
        }
        if (component instanceof DRIRectangle) {
            return this.rectangle((DRIRectangle) component);
        }
        if (component instanceof DRIBooleanField) {
            return this.booleanField((DRIBooleanField) component, defaultStyleType, resetType, resetGroup);
        }
        if (component instanceof DRIBreak) {
            return this.breakComponent((DRIBreak) component);
        }
        if (component instanceof DRIGenericElement) {
            return this.genericElement((DRIGenericElement) component, resetType, resetGroup);
        }
        if (component instanceof DRICrosstab) {
            return this.crosstab((DRICrosstab) component, resetType, resetGroup);
        }
        if (component instanceof DRIMap) {
            return this.map((DRIMap) component, resetType, resetGroup);
        }
        if (component instanceof DRICustomComponent) {
            return this.customComponent((DRICustomComponent) component, resetType, resetGroup);
        }
        throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
    }

    private void component(final DRDesignComponent designComponent, final DRIComponent component, final DRIReportStyle style, final boolean textStyle, final DefaultStyleType defaultStyleType) throws DRException {
        designComponent.setStyle(this.accessor.getStyleTransform().transformStyle(style, textStyle, defaultStyleType));
        designComponent.setPrintWhenExpression(this.accessor.getExpressionTransform().transformExpression(component.getPrintWhenExpression()));
        designComponent.setRemoveLineWhenBlank(this.accessor.getTemplateTransform().getRemoveLineWhenBlank(component));
        designComponent.setPositionType(this.accessor.getTemplateTransform().getPositionType(component));
        designComponent.setStretchType(this.accessor.getTemplateTransform().getStretchType(component));
        designComponent.setPrintInFirstWholeBand(this.accessor.getTemplateTransform().getPrintInFirstWholeBand(component));
        designComponent.setPrintWhenDetailOverflows(this.accessor.getTemplateTransform().getPrintWhenDetailOverflows(component));
        designComponent.setPrintWhenGroupChanges(this.accessor.getTemplateTransform().getPrintWhenGroupChanges(component));
        for (final DRIPropertyExpression propertyExpression : component.getPropertyExpressions()) {
            designComponent.getPropertyExpressions().add(this.accessor.getExpressionTransform().transformPropertyExpression(propertyExpression));
        }
        final DRDesignTableOfContentsHeading designTocHeading =
            this.accessor.getTableOfContentsTransform().componentHeading(component);
        if (designTocHeading != null) {
            designComponent.setTableOfContentsHeading(designTocHeading);
            final DRIDesignExpression anchorNameExpression = designTocHeading.getReferenceField().getAnchorNameExpression();
            final Integer bookmarkLevel = designTocHeading.getReferenceField().getBookmarkLevel();
            final DRDesignHyperLink designHyperLink = designTocHeading.getReferenceField().getHyperLink();
            if (designComponent instanceof DRDesignHyperlinkComponent) {
                ((DRDesignHyperlinkComponent) designComponent).setAnchorNameExpression(anchorNameExpression);
                ((DRDesignHyperlinkComponent) designComponent).setBookmarkLevel(bookmarkLevel);
                ((DRDesignHyperlinkComponent) designComponent).setHyperLink(designHyperLink);
            }
        }
    }

    private void hyperlink(final DRDesignHyperlinkComponent designHyperlinkComponent, final DRIHyperLinkComponent hyperlinkComponent, final DRIReportStyle style, final boolean textStyle, final DefaultStyleType defaultStyleType)
        throws DRException {
        this.component(designHyperlinkComponent, hyperlinkComponent, style, textStyle, defaultStyleType);

        if (hyperlinkComponent.getAnchorNameExpression() != null) {
            designHyperlinkComponent.setAnchorNameExpression(this.accessor.getExpressionTransform().transformExpression(hyperlinkComponent.getAnchorNameExpression()));
        }
        if (hyperlinkComponent.getBookmarkLevel() != null) {
            designHyperlinkComponent.setBookmarkLevel(hyperlinkComponent.getBookmarkLevel());
        }

        final DRIHyperLink hyperLink = hyperlinkComponent.getHyperLink();
        final DRDesignHyperLink designHyperLink = this.accessor.getReportTransform().hyperlink(hyperLink);
        if (designHyperLink != null) {
            designHyperlinkComponent.setHyperLink(designHyperLink);
        }
    }

    // list

    /**
     * <p>list.</p>
     *
     * @param list             a {@link software.xdev.dynamicreports.report.definition.component.DRIList} object.
     * @param defaultStyleType a {@link software.xdev.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignList} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignList list(final DRIList list, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignList designList = new DRDesignList();
        this.component(designList, list, list.getStyle(), false, DefaultStyleType.NONE);
        designList.setType(list.getType());
        designList.setGap(this.accessor.getTemplateTransform().getListGap(list));
        designList.setWidth(this.accessor.getTemplateTransform().getListWidth(list));
        designList.setHeight(this.accessor.getTemplateTransform().getListHeight(list));
        designList.setCalculateComponents(designList.getWidth() == null && designList.getHeight() == null);
        for (final DRIListCell innerComponent : list.getListCells()) {
            final DRIComponent component = innerComponent.getComponent();
            HorizontalCellComponentAlignment horizontalAlignment = innerComponent.getHorizontalAlignment();
            VerticalCellComponentAlignment verticalAlignment = innerComponent.getVerticalAlignment();
            if (component instanceof DRIDimensionComponent) {
                final DRIDimensionComponent dimComponent = (DRIDimensionComponent) component;
                if (horizontalAlignment == null) {
                    horizontalAlignment = ConstantTransform.toHorizontalCellComponentAlignment(dimComponent.getWidthType());
                }
                if (verticalAlignment == null) {
                    verticalAlignment = ConstantTransform.toVerticalCellComponentAlignment(dimComponent.getHeightType());
                }
            }
            designList.addComponent(horizontalAlignment, verticalAlignment,
                this.component(component, defaultStyleType, resetType, resetGroup));
        }
        designList.setBackgroundComponent(this.listBackgroundComponent(list.getBackgroundComponent(), defaultStyleType, resetType, resetGroup));

        return designList;
    }

    /**
     * <p>xyList.</p>
     *
     * @param xyList           a {@link software.xdev.dynamicreports.report.definition.component.DRIXyList} object.
     * @param defaultStyleType a {@link software.xdev.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignList} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignList xyList(final DRIXyList xyList, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignList designList = new DRDesignList(null);
        this.component(designList, xyList, xyList.getStyle(), false, DefaultStyleType.NONE);
        designList.setWidth(this.accessor.getTemplateTransform().getXyListWidth(xyList));
        designList.setHeight(this.accessor.getTemplateTransform().getXyListHeight(xyList));
        designList.setCalculateComponents(designList.getWidth() == null && designList.getHeight() == null);
        designList.setRemovable(true);
        for (final DRIXyListCell innerComponent : xyList.getXyListCells()) {
            final DRDesignComponent designComponent =
                this.component(innerComponent.getComponent(), defaultStyleType, resetType, resetGroup);
            designComponent.setX(innerComponent.getX());
            designComponent.setY(innerComponent.getY());
            designList.addComponent(HorizontalCellComponentAlignment.LEFT, VerticalCellComponentAlignment.TOP, designComponent);
        }

        return designList;
    }

    /**
     * <p>listBackgroundComponent.</p>
     *
     * @param backgroundComponent a {@link software.xdev.dynamicreports.report.definition.component.DRIComponent} object.
     * @param defaultStyleType    a {@link software.xdev.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType           a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup          a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignComponent listBackgroundComponent(final DRIComponent backgroundComponent, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        if (backgroundComponent != null) {
            if (backgroundComponent instanceof DRIRectangle || backgroundComponent instanceof DRIImage || backgroundComponent instanceof DRITextField) {
                return this.component(backgroundComponent, defaultStyleType, resetType, resetGroup);
            } else {
                throw new DRDesignReportException("List background component not supported. Only rectangle, image and textfield are supported");
            }
        }
        return null;
    }

    // multi page list
    private DRDesignSubreport multiPageList(final DRIMultiPageList multiPageList) throws DRException {
        final DRDesignSubreport designSubreport = new DRDesignSubreport();
        this.component(designSubreport, multiPageList, multiPageList.getStyle(), false, DefaultStyleType.NONE);
        designSubreport.setWidth(this.accessor.getTemplateTransform().getMultiPageListWidth(multiPageList));
        designSubreport.setHeight(this.accessor.getTemplateTransform().getMultiPageListHeight(multiPageList));
        final JasperReportBuilder multiPageReport = DynamicReports.report();
        final MultiPageListSubreportExpression subreportExpression =
            new MultiPageListSubreportExpression(
                this.accessor.getLocale(),
                this.accessor.getResourceBundle(),
                this.accessor.getResourceBundleName(),
                this.accessor.getWhenResourceMissingType(),
                                                 multiPageList.getComponents(),
                this.accessor.getTemplateTransform().getTemplateStyles());
        multiPageReport.detail(Components.subreport(subreportExpression));
        multiPageReport.setDetailSplitType(multiPageList.getSplitType());
        final DRIDesignExpression reportExpression =
            this.accessor.getExpressionTransform().transformExpression(Expressions.value(multiPageReport));
        final DRIDesignExpression dataSourceExpression = this.accessor.getExpressionTransform().transformExpression(new MultiPageListDataSourceExpression(multiPageList.getComponents().size()));
        designSubreport.setReportExpression(reportExpression);
        designSubreport.setDataSourceExpression(dataSourceExpression);
        return designSubreport;
    }

    // text field

    /**
     * <p>textField.</p>
     *
     * @param textField        a {@link software.xdev.dynamicreports.report.definition.component.DRITextField} object.
     * @param defaultStyleType a {@link software.xdev.dynamicreports.design.constant.DefaultStyleType} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignTextField} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignTextField textField(final DRITextField<?> textField, final DefaultStyleType defaultStyleType) throws DRException {
        final DRDesignTextField designTextField = new DRDesignTextField();
        this.hyperlink(designTextField, textField, textField.getStyle(), true, defaultStyleType);
        final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
        designTextField.setPrintRepeatedValues(templateTransform.isTextFieldPrintRepeatedValues(textField));
        designTextField.setStretchWithOverflow(templateTransform.getTextFieldStretchWithOverflow(textField));
        designTextField.setTextAdjust(templateTransform.getTextFieldTextAdjust(textField));
        final DRDesignStyle style = designTextField.getStyle();
        designTextField.setWidth(templateTransform.getTextFieldWidth(textField, style));
        designTextField.setHeight(templateTransform.getTextFieldHeight(textField, style));
        designTextField.setPattern(templateTransform.getTextFieldPattern(textField, style));
        designTextField.setPatternExpression(this.accessor.getExpressionTransform().transformExpression(textField.getPatternExpression()));
        designTextField.setHorizontalTextAlignment(templateTransform.getTextFieldHorizontalTextAlignment(textField, style));
        designTextField.setValueExpression(this.accessor.getExpressionTransform().transformExpression(textField.getValueExpression(), templateTransform.getTextFieldValueFormatter(textField), null));
        designTextField.setMarkup(textField.getMarkup());
        if (textField.getEvaluationTime() != null) {
            designTextField.setEvaluationTime(ConstantTransform.textFieldEvaluationTime(textField.getEvaluationTime(), textField.getEvaluationGroup(),
                this.accessor));
            designTextField.setEvaluationGroup(
                this.accessor.getGroupTransform().getGroup(ConstantTransform.textFieldEvaluationGroup(textField.getEvaluationTime(), textField.getEvaluationGroup(),
                    this.accessor)));
        } else {
            if (textField.getEvaluationGroup() != null) {
                throw new DRException("Evaluation group for textField is required only for evaluation time BEFORE_GROUP or GROUP");
            }
            final EvaluationTime evaluationTime = this.detectEvaluationTime(designTextField.getValueExpression());
            designTextField.setEvaluationTime(evaluationTime);
            designTextField.setEvaluationGroup(this.detectEvaluationGroup(evaluationTime, designTextField.getValueExpression()));
        }
        return designTextField;
    }

    // filler

    /**
     * <p>filler.</p>
     *
     * @param filler a {@link software.xdev.dynamicreports.report.definition.component.DRIFiller} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignFiller} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignFiller filler(final DRIFiller filler) throws DRException {
        final DRDesignFiller designFiller = new DRDesignFiller();
        this.component(designFiller, filler, filler.getStyle(), false, DefaultStyleType.NONE);
        designFiller.setWidth(this.accessor.getTemplateTransform().getFillerWidth(filler));
        designFiller.setHeight(this.accessor.getTemplateTransform().getFillerHeight(filler));
        return designFiller;
    }

    // image
    private DRDesignImage image(final DRIImage image) throws DRException {
        return this.image(image, null, DefaultStyleType.IMAGE);
    }

    private DRDesignImage image(final DRIImage image, final Integer imageHeight, final DefaultStyleType defaultStyleType) throws DRException {
        final DRDesignImage designImage = new DRDesignImage();
        this.hyperlink(designImage, image, image.getStyle(), false, defaultStyleType);
        designImage.setImageScale(image.getImageScale());
        designImage.setImageExpression(this.accessor.getExpressionTransform().transformExpression(image.getImageExpression()));
        designImage.setUsingCache(image.getUsingCache());
        designImage.setLazy(image.getLazy());
        designImage.setHorizontalImageAlignment(image.getHorizontalImageAlignment());
        designImage.setWidth(this.accessor.getTemplateTransform().getImageWidth(image));
        final DRDesignStyle style = designImage.getStyle();
        designImage.setHeight(this.accessor.getTemplateTransform().getImageHeight(image, imageHeight, style));
        return designImage;
    }

    
    // subreport
    private DRDesignSubreport subreport(final DRISubreport subreport) throws DRException {
        final DRDesignSubreport designSubreport = new DRDesignSubreport();
        this.component(designSubreport, subreport, subreport.getStyle(), false, DefaultStyleType.NONE);
        designSubreport.setWidth(this.accessor.getTemplateTransform().getSubreportWidth(subreport));
        designSubreport.setHeight(this.accessor.getTemplateTransform().getSubreportHeight(subreport));
        designSubreport.setReportExpression(this.accessor.getExpressionTransform().transformExpression(subreport.getReportExpression()));
        designSubreport.setParametersExpression(this.accessor.getExpressionTransform().transformExpression(subreport.getParametersExpression()));
        designSubreport.setConnectionExpression(this.accessor.getExpressionTransform().transformExpression(subreport.getConnectionExpression()));
        designSubreport.setDataSourceExpression(this.accessor.getExpressionTransform().transformExpression(subreport.getDataSourceExpression()));
        designSubreport.setRunToBottom(subreport.getRunToBottom());
        return designSubreport;
    }

    // page x of y
    private DRDesignList pageXofY(final DRIPageXofY pageXofY, final DefaultStyleType defaultStyleType) throws DRException {
        final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
        DRIReportStyle pageXofYStyle = pageXofY.getStyle();
        if (pageXofYStyle == null) {
            pageXofYStyle = this.accessor.getTemplateTransform().getTextStyle();
        }
        final DRDesignStyle style =
            this.accessor.getStyleTransform().transformStyle(pageXofYStyle, true, defaultStyleType);
        final Integer height = templateTransform.getPageXofYHeight(pageXofY, style);
        final HorizontalTextAlignment horizontalTextAlignment = templateTransform.getPageXofYHorizontalTextAlignment(pageXofY, style);

        final DRStyle newStylePageX = new DRStyle();
        newStylePageX.setParentStyle(pageXofYStyle);
        newStylePageX.getPadding().setRight(0);
        final DRPen pen = new DRPen();
        pen.setLineWidth(0f);
        newStylePageX.getBorder().setRightPen(pen);
        final DRStyle newStylePageY = new DRStyle();
        newStylePageY.setParentStyle(pageXofYStyle);
        newStylePageY.getPadding().setLeft(0);
        newStylePageY.getBorder().setLeftPen(pen);

        final DRTextField<String> pageXField = new DRTextField<>();
        pageXField.setAnchorNameExpression(pageXofY.getAnchorNameExpression());
        pageXField.setBookmarkLevel(pageXofY.getBookmarkLevel());
        pageXField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
        pageXField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
        pageXField.setStyle(newStylePageX);
        pageXField.setHeight(height);
        pageXField.setHeightType(pageXofY.getHeightType());
        pageXField.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
        pageXField.setValueExpression(new PageXofYNumberExpression(pageXofY.getFormatExpression(), 0));

        final DRTextField<String> pageYField = new DRTextField<>();
        pageYField.setAnchorNameExpression(pageXofY.getAnchorNameExpression());
        pageYField.setBookmarkLevel(pageXofY.getBookmarkLevel());
        pageYField.setHyperLink((DRHyperLink) pageXofY.getHyperLink());
        pageYField.setPrintWhenExpression(pageXofY.getPrintWhenExpression());
        pageYField.setStyle(newStylePageY);
        pageYField.setHeight(height);
        pageYField.setHeightType(pageXofY.getHeightType());
        pageYField.setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
        pageYField.setValueExpression(new PageXofYNumberExpression(pageXofY.getFormatExpression(), 1));
        final DRIGroup pageYEvaluationGroup = this.accessor.getGroupTransform().getFirstResetPageNumberGroup();
        if (pageYEvaluationGroup == null) {
            pageYField.setEvaluationTime(Evaluation.REPORT);
        } else {
            pageYField.setEvaluationTime(Evaluation.GROUP);
            pageYField.setEvaluationGroup((DRGroup) pageYEvaluationGroup);
        }

        final int pageXofYWidth = templateTransform.getPageXofYWidth(pageXofY);
        switch (horizontalTextAlignment) {
            case LEFT:
                int pageXWidth = StyleResolver.getFontWidth(style, 4);
                int pageYWidth = pageXofYWidth - pageXWidth;
                if (pageYWidth <= 0) {
                    pageYWidth = 10;
                }
                pageXField.setWidth(pageXWidth);
                pageXField.setWidthType(ComponentDimensionType.FIXED);
                pageYField.setWidth(pageYWidth);
                pageYField.setWidthType(pageXofY.getWidthType());
                break;
            case RIGHT:
                pageYWidth = StyleResolver.getFontWidth(style, 6);
                pageXWidth = pageXofYWidth - pageYWidth;
                if (pageXWidth <= 0) {
                    pageXWidth = 10;
                }
                pageXField.setWidth(pageXWidth);
                pageXField.setWidthType(pageXofY.getWidthType());
                pageYField.setWidth(pageYWidth);
                pageYField.setWidthType(ComponentDimensionType.FIXED);
                break;
            default:
                pageXField.setWidth(pageXofYWidth / 2);
                pageXField.setWidthType(pageXofY.getWidthType());
                pageYField.setWidth(pageXofYWidth / 2);
                pageYField.setWidthType(pageXofY.getWidthType());
                break;
        }

        if (pageXofY.getPageXWidth() != null) {
            pageXField.setWidth(pageXofY.getPageXWidth());
        }
        if (pageXofY.getPageXWidthType() != null) {
            pageXField.setWidthType(pageXofY.getPageXWidthType());
        }
        if (pageXofY.getPageYWidth() != null) {
            pageYField.setWidth(pageXofY.getPageYWidth());
        }
        if (pageXofY.getPageYWidthType() != null) {
            pageYField.setWidthType(pageXofY.getPageYWidthType());
        }

        final DRList listPageXofY = new DRList();
        listPageXofY.addComponent(pageXField);
        listPageXofY.addComponent(pageYField);
        return this.list(listPageXofY, DefaultStyleType.TEXT, null, null);
    }

    // total pages
    private DRDesignTextField totalPages(final DRITotalPages totalPages, final DefaultStyleType defaultStyleType) throws DRException {
        final PageNumberExpression expression = new PageNumberExpression(totalPages.getFormatExpression());
        final DRTextField<String> totalPagesField = this.formatField(totalPages, expression);
        final DRIGroup pageEvaluationGroup = this.accessor.getGroupTransform().getFirstResetPageNumberGroup();
        if (pageEvaluationGroup == null) {
            totalPagesField.setEvaluationTime(Evaluation.REPORT);
        } else {
            totalPagesField.setEvaluationTime(Evaluation.GROUP);
            totalPagesField.setEvaluationGroup((DRGroup) pageEvaluationGroup);
        }

        return this.textField(totalPagesField, defaultStyleType);
    }

    // page number
    private DRDesignTextField pageNumber(final DRIPageNumber pageNumber, final DefaultStyleType defaultStyleType) throws DRException {
        final PageNumberExpression expression = new PageNumberExpression(pageNumber.getFormatExpression());
        return this.textField(this.formatField(pageNumber, expression), defaultStyleType);
    }

    // current date
    private DRDesignTextField currentDate(final DRICurrentDate currentDate, final DefaultStyleType defaultStyleType) throws DRException {
        final CurrentDateExpression expression = new CurrentDateExpression(currentDate.getFormatExpression(), currentDate.getPattern());
        return this.textField(this.formatField(currentDate, expression), defaultStyleType);
    }

    // format field
    private DRTextField<String> formatField(final DRIFormatField formatField, final DRIExpression<String> expression) throws DRException {
        final DRTextField<String> formatFieldTextField = new DRTextField<>();
        formatFieldTextField.setAnchorNameExpression(formatField.getAnchorNameExpression());
        formatFieldTextField.setBookmarkLevel(formatField.getBookmarkLevel());
        formatFieldTextField.setHyperLink((DRHyperLink) formatField.getHyperLink());
        formatFieldTextField.setPrintWhenExpression(formatField.getPrintWhenExpression());
        formatFieldTextField.setStyle(formatField.getStyle());
        formatFieldTextField.setWidth(formatField.getWidth());
        formatFieldTextField.setWidthType(formatField.getWidthType());
        formatFieldTextField.setHeight(formatField.getHeight());
        formatFieldTextField.setHeightType(formatField.getHeightType());
        formatFieldTextField.setHorizontalTextAlignment(formatField.getHorizontalTextAlignment());
        formatFieldTextField.setValueExpression(expression);
        return formatFieldTextField;
    }

    // line

    /**
     * <p>line.</p>
     *
     * @param line a {@link software.xdev.dynamicreports.report.definition.component.DRILine} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignLine} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignLine line(final DRILine line) throws DRException {
        final DRDesignLine designLine = new DRDesignLine();
        this.component(designLine, line, line.getStyle(), false, DefaultStyleType.NONE);
        designLine.setDirection(line.getDirection());
        designLine.setPen(this.accessor.getStyleTransform().pen(line.getPen()));
        designLine.setWidth(this.accessor.getTemplateTransform().getLineWidth(line));
        designLine.setHeight(this.accessor.getTemplateTransform().getLineHeight(line));
        return designLine;
    }

    // ellipse

    /**
     * <p>ellipse.</p>
     *
     * @param ellipse a {@link software.xdev.dynamicreports.report.definition.component.DRIEllipse} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignEllipse} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignEllipse ellipse(final DRIEllipse ellipse) throws DRException {
        final DRDesignEllipse designEllipse = new DRDesignEllipse();
        this.component(designEllipse, ellipse, ellipse.getStyle(), false, DefaultStyleType.NONE);
        designEllipse.setPen(this.accessor.getStyleTransform().pen(ellipse.getPen()));
        designEllipse.setWidth(this.accessor.getTemplateTransform().getEllipseWidth(ellipse));
        designEllipse.setHeight(this.accessor.getTemplateTransform().getEllipseHeight(ellipse));
        return designEllipse;
    }

    // rectangle

    /**
     * <p>rectangle.</p>
     *
     * @param rectangle a {@link software.xdev.dynamicreports.report.definition.component.DRIRectangle} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignRectangle} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignRectangle rectangle(final DRIRectangle rectangle) throws DRException {
        final DRDesignRectangle designRectangle = new DRDesignRectangle();
        this.component(designRectangle, rectangle, rectangle.getStyle(), false, DefaultStyleType.NONE);
        designRectangle.setRadius(this.accessor.getTemplateTransform().getRectangleRadius(rectangle));
        designRectangle.setPen(this.accessor.getStyleTransform().pen(rectangle.getPen()));
        designRectangle.setWidth(this.accessor.getTemplateTransform().getRectangleWidth(rectangle));
        designRectangle.setHeight(this.accessor.getTemplateTransform().getRectangleHeight(rectangle));
        return designRectangle;
    }

    // boolean

    /**
     * <p>booleanField.</p>
     *
     * @param booleanField     a {@link software.xdev.dynamicreports.report.definition.component.DRIBooleanField} object.
     * @param defaultStyleType a {@link software.xdev.dynamicreports.design.constant.DefaultStyleType} object.
     * @param resetType        a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup       a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignComponent booleanField(final DRIBooleanField booleanField, final DefaultStyleType defaultStyleType, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final BooleanComponentType componentType =
            this.accessor.getTemplateTransform().getBooleanComponentType(booleanField);
        final boolean emptyWhenNullValue =
            this.accessor.getTemplateTransform().getBooleanEmptyWhenNullValue(booleanField);

        DRHyperLinkComponent component = null;

        switch (componentType) {
            case TEXT_TRUE_FALSE:
            case TEXT_YES_NO:
                final String keyTrue;
                final String keyFalse;
                if (componentType.equals(BooleanComponentType.TEXT_TRUE_FALSE)) {
                    keyTrue = "true";
                    keyFalse = "false";
                } else {
                    keyTrue = "yes";
                    keyFalse = "no";
                }
                final DRTextField<Boolean> textField = new DRTextField<>();
                textField.setValueExpression(booleanField.getValueExpression());
                textField.setDataType(DataTypes.booleanType());
                textField.setHorizontalTextAlignment(booleanField.getHorizontalTextAlignment());
                textField.setValueFormatter(new BooleanTextValueFormatter(keyTrue, keyFalse, emptyWhenNullValue));
                component = textField;
                break;
            default:
                throw new DRDesignReportException("Boolean component type " + componentType.name() + " not supported");
        }

        component.setWidth(booleanField.getWidth());
        component.setWidthType(booleanField.getWidthType());
        component.setHeight(booleanField.getHeight());
        component.setHeightType(booleanField.getHeightType());
        component.setAnchorNameExpression(booleanField.getAnchorNameExpression());
        component.setBookmarkLevel(booleanField.getBookmarkLevel());
        component.setHyperLink((DRHyperLink) booleanField.getHyperLink());
        component.setStyle(booleanField.getStyle());
        component.setPrintWhenExpression(booleanField.getPrintWhenExpression());
        component.setPropertyExpressions(booleanField.getPropertyExpressions());

        final DRDesignComponent designComponent;
        if (component instanceof DRIImage) {
            final int imageHeight = this.accessor.getTemplateTransform().getBooleanImageHeight(booleanField);
            designComponent = this.image((DRIImage) component, imageHeight, defaultStyleType);
            final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
            ((DRDesignImage) designComponent).setHorizontalImageAlignment(templateTransform.getBooleanHorizontalImageAlignment(booleanField, designComponent.getStyle()));
        } else {
            designComponent = this.component(component, defaultStyleType, resetType, resetGroup);
        }
        return designComponent;
    }

    // break

    /**
     * <p>breakComponent.</p>
     *
     * @param breakComponent a {@link software.xdev.dynamicreports.report.definition.component.DRIBreak} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignBreak} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignBreak breakComponent(final DRIBreak breakComponent) throws DRException {
        final DRDesignBreak designBreak = new DRDesignBreak();
        this.component(designBreak, breakComponent, null, false, DefaultStyleType.NONE);
        designBreak.setType(breakComponent.getType());
        designBreak.setWidth(this.accessor.getTemplateTransform().getBreakWidth(breakComponent));
        designBreak.setHeight(this.accessor.getTemplateTransform().getBreakHeight(breakComponent));
        return designBreak;
    }

    // generic element

    /**
     * <p>genericElement.</p>
     *
     * @param genericElement a {@link software.xdev.dynamicreports.report.definition.component.DRIGenericElement} object.
     * @param resetType      a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup     a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a {@link software.xdev.dynamicreports.design.base.component.DRDesignGenericElement} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignGenericElement genericElement(final DRIGenericElement genericElement, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignGenericElement designGenericElement = new DRDesignGenericElement();
        this.component(designGenericElement, genericElement, genericElement.getStyle(), false, DefaultStyleType.NONE);
        designGenericElement.setGenericElementNamespace(genericElement.getGenericElementNamespace());
        designGenericElement.setGenericElementName(genericElement.getGenericElementName());
        designGenericElement.setEvaluationTime(this.evaluationTimeFromResetType(resetType));
        designGenericElement.setEvaluationGroup(resetGroup);
        designGenericElement.setWidth(this.accessor.getTemplateTransform().getGenericElementWidth(genericElement));
        designGenericElement.setHeight(this.accessor.getTemplateTransform().getGenericElementHeight(genericElement));
        for (final DRIParameterExpression parameterExpression : genericElement.getParameterExpressions()) {
            designGenericElement.getParameterExpressions().add(this.accessor.getExpressionTransform().transformParameterExpression(parameterExpression));
        }
        return designGenericElement;
    }

    // crosstab
    private DRDesignCrosstab crosstab(final DRICrosstab crosstab, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignCrosstab designCrosstab =
            this.accessor.getCrosstabTransform().transform(crosstab, resetType, resetGroup);
        this.component(designCrosstab, crosstab, crosstab.getStyle(), false, DefaultStyleType.NONE);
        return designCrosstab;
    }

    // map
    private DRDesignMap map(final DRIMap map, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        final DRDesignMap designMap = new DRDesignMap();
        this.component(designMap, map, map.getStyle(), false, DefaultStyleType.NONE);
        designMap.setLatitudeExpression(this.accessor.getExpressionTransform().transformExpression(map.getLatitudeExpression()));
        designMap.setLongitudeExpression(this.accessor.getExpressionTransform().transformExpression(map.getLongitudeExpression()));
        designMap.setZoomExpression(this.accessor.getExpressionTransform().transformExpression(map.getZoomExpression()));
        designMap.setWidth(this.accessor.getTemplateTransform().getMapWidth(map));
        designMap.setHeight(this.accessor.getTemplateTransform().getMapHeight(map));
        designMap.setEvaluationTime(this.evaluationTimeFromResetType(resetType));
        designMap.setEvaluationGroup(resetGroup);
        return designMap;
    }

    // custom component
    private DRDesignComponent customComponent(final DRICustomComponent component, final ResetType resetType, final DRDesignGroup resetGroup) throws DRException {
        @SuppressWarnings("rawtypes") final CustomComponentTransform componentTransfom = CustomComponents.getComponentTransform(component);
        if (componentTransfom == null) {
            throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
        }
        @SuppressWarnings("unchecked") final DRDesignComponent designComponent = (DRDesignComponent) componentTransfom.designComponent(
            this.accessor, component, resetType, resetGroup);
        this.component(designComponent, component, component.getStyle(), false, DefaultStyleType.NONE);
        final DRIDimensionComponent dimensionComponent = component;
        if (designComponent.getWidth() == null) {
            designComponent.setWidth(this.accessor.getTemplateTransform().getCustomComponentWidth(dimensionComponent));
        }
        if (designComponent.getHeight() == null) {
            designComponent.setHeight(this.accessor.getTemplateTransform().getCustomComponentHeight(dimensionComponent));
        }
        return designComponent;
    }

    /**
     * <p>detectEvaluationTime.</p>
     *
     * @param expression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     * @return a {@link software.xdev.dynamicreports.design.constant.EvaluationTime} object.
     */
    protected EvaluationTime detectEvaluationTime(final DRIDesignExpression expression) {
        if (expression == null) {
            return null;
        }

        if (expression instanceof DRIDesignField || expression instanceof DRIDesignSystemExpression || expression instanceof DRIDesignSimpleExpression ||
            expression instanceof DRIDesignJasperExpression) {
            return EvaluationTime.NOW;
        }
        if (expression instanceof DRIDesignVariable) {
            return this.evaluationTimeFromResetType(((DRIDesignVariable) expression).getResetType());
        }
        if (expression instanceof DRIDesignComplexExpression) {
            return this.detectComplexExpressionEvaluationTime((DRIDesignComplexExpression) expression);
        }
        throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
    }

    /**
     * <p>evaluationTimeFromResetType.</p>
     *
     * @param resetType a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @return a {@link software.xdev.dynamicreports.design.constant.EvaluationTime} object.
     */
    public EvaluationTime evaluationTimeFromResetType(final ResetType resetType) {
        if (resetType == null) {
            return null;
        }

        switch (resetType) {
            case NONE:
                return EvaluationTime.NOW;
            case REPORT:
                return EvaluationTime.REPORT;
            case PAGE:
                return EvaluationTime.PAGE;
            case COLUMN:
                return EvaluationTime.COLUMN;
            case GROUP:
                return EvaluationTime.GROUP;
            default:
                throw new DRDesignReportException("Reset type " + resetType.name() + " not supported");
        }
    }

    private EvaluationTime detectComplexExpressionEvaluationTime(final DRIDesignComplexExpression complexExpression) {
        EvaluationTime evaluationTime = null;
        for (final DRIDesignExpression expression : complexExpression.getExpressions()) {
            final EvaluationTime evalTime = this.detectEvaluationTime(expression);
            if (evaluationTime == null) {
                evaluationTime = evalTime;
            } else if (evaluationTime != evalTime || evaluationTime.equals(EvaluationTime.GROUP) && evalTime.equals(EvaluationTime.GROUP)) {
                return EvaluationTime.AUTO;
            }
        }
        return evaluationTime;
    }

    private DRDesignGroup detectEvaluationGroup(final EvaluationTime evaluationTime, final DRIDesignExpression expression) {
        if (expression != null && evaluationTime != null && evaluationTime.equals(EvaluationTime.GROUP)) {
            final DRDesignGroup evaluationGroup = this.detectEvaluationGroup(expression);
            if (evaluationGroup == null) {
                throw new DRDesignReportException("Can not detect evaluation group");
            }
            return evaluationGroup;
        }
        return null;
    }

    private DRDesignGroup detectEvaluationGroup(final DRIDesignExpression expression) {
        if (expression instanceof DRIDesignField || expression instanceof DRIDesignSimpleExpression) {
            return null;
        }
        if (expression instanceof DRDesignVariable) {
            return ((DRDesignVariable) expression).getResetGroup();
        }
        if (expression instanceof DRIDesignComplexExpression) {
            return this.detectComplexExpressionEvaluationGroup((DRIDesignComplexExpression) expression);
        }
        throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
    }

    private DRDesignGroup detectComplexExpressionEvaluationGroup(final DRIDesignComplexExpression complexExpression) {
        DRDesignGroup evaluationGroup = null;
        for (final DRIDesignExpression expression : complexExpression.getExpressions()) {
            final DRDesignGroup group = this.detectEvaluationGroup(expression);
            if (evaluationGroup == null) {
                evaluationGroup = group;
            } else if (evaluationGroup != group) {
                throw new DRDesignReportException("Can not detect evaluation group");
            }
        }
        return evaluationGroup;
    }

}
