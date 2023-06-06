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
package net.sf.dynamicreports.jasper.transformation;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.Second;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;

import com.lowagie.text.pdf.PdfWriter;

import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.jasper.constant.PdfPermission;
import net.sf.dynamicreports.jasper.constant.PdfVersion;
import net.sf.dynamicreports.jasper.constant.SizeUnit;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.report.constant.BreakType;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.ComponentPositionType;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.GroupFooterPosition;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.HyperLinkTarget;
import net.sf.dynamicreports.report.constant.HyperLinkType;
import net.sf.dynamicreports.report.constant.ImageAnchorType;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.LineDirection;
import net.sf.dynamicreports.report.constant.LineSpacing;
import net.sf.dynamicreports.report.constant.LineStyle;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PdfPrintScaling;
import net.sf.dynamicreports.report.constant.PdfaConformance;
import net.sf.dynamicreports.report.constant.RectangleAnchor;
import net.sf.dynamicreports.report.constant.Rotation;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.StretchType;
import net.sf.dynamicreports.report.constant.TabStopAlignment;
import net.sf.dynamicreports.report.constant.TextAdjust;
import net.sf.dynamicreports.report.constant.TimePeriod;
import net.sf.dynamicreports.report.constant.VerticalImageAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.constant.WhenResourceMissingType;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.analytics.dataset.BucketOrder;
import net.sf.jasperreports.engine.export.type.ImageAnchorTypeEnum;
import net.sf.jasperreports.engine.type.BreakTypeEnum;
import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.DatasetResetTypeEnum;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports.engine.type.FooterPositionEnum;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports.engine.type.LineDirectionEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.PrintOrderEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;
import net.sf.jasperreports.engine.type.TextAdjustEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum;
import net.sf.jasperreports.export.type.HtmlSizeUnitEnum;
import net.sf.jasperreports.export.type.PdfPrintScalingEnum;
import net.sf.jasperreports.export.type.PdfVersionEnum;
import net.sf.jasperreports.export.type.PdfaConformanceEnum;


/**
 * <p>ConstantTransform class.</p>
 *
 * @author Ricardo Mariaca
 */
public class ConstantTransform
{
	
	/**
	 * <p>lineStyle.</p>
	 *
	 * @param lineStyle a {@link net.sf.dynamicreports.report.constant.LineStyle} object.
	 * @return a {@link net.sf.jasperreports.engine.type.LineStyleEnum} object.
	 */
	protected static LineStyleEnum lineStyle(final LineStyle lineStyle)
	{
		if(lineStyle == null)
		{
			return null;
		}
		
		switch(lineStyle)
		{
			case SOLID:
				return LineStyleEnum.SOLID;
			case DASHED:
				return LineStyleEnum.DASHED;
			case DOTTED:
				return LineStyleEnum.DOTTED;
			case DOUBLE:
				return LineStyleEnum.DOUBLE;
			default:
				throw new JasperDesignException("Line style " + lineStyle.name() + " not supported");
		}
	}
	
	/**
	 * <p>imageScale.</p>
	 *
	 * @param imageScale a {@link net.sf.dynamicreports.report.constant.ImageScale} object.
	 * @return a {@link net.sf.jasperreports.engine.type.ScaleImageEnum} object.
	 */
	@SuppressWarnings("deprecation")
	protected static ScaleImageEnum imageScale(final ImageScale imageScale)
	{
		if(imageScale == null)
		{
			return null;
		}
		
		switch(imageScale)
		{
			case CLIP:
			case NO_RESIZE:
				return ScaleImageEnum.CLIP;
			case FILL_FRAME:
			case FILL:
				return ScaleImageEnum.FILL_FRAME;
			case RETAIN_SHAPE:
			case FILL_PROPORTIONALLY:
				return ScaleImageEnum.RETAIN_SHAPE;
			case REAL_HEIGHT:
				return ScaleImageEnum.REAL_HEIGHT;
			case REAL_SIZE:
				return ScaleImageEnum.REAL_SIZE;
			default:
				throw new JasperDesignException("Image scale " + imageScale.name() + " not supported");
		}
	}
	
	/**
	 * <p>imageAnchorType.</p>
	 *
	 * @param imageAnchorType a {@link net.sf.dynamicreports.report.constant.ImageAnchorType} object.
	 * @return a {@link net.sf.jasperreports.engine.export.type.ImageAnchorTypeEnum} object.
	 */
	protected static ImageAnchorTypeEnum imageAnchorType(final ImageAnchorType imageAnchorType)
	{
		if(imageAnchorType == null)
		{
			return null;
		}
		
		switch(imageAnchorType)
		{
			case MOVE_SIZE:
				return ImageAnchorTypeEnum.MOVE_SIZE;
			case MOVE_NO_SIZE:
				return ImageAnchorTypeEnum.MOVE_NO_SIZE;
			case NO_MOVE_NO_SIZE:
				return ImageAnchorTypeEnum.NO_MOVE_NO_SIZE;
			default:
				throw new JasperDesignException("Image anchor type " + imageAnchorType.name() + " not supported");
		}
	}
	
	/**
	 * <p>horizontalTextAlignment.</p>
	 *
	 * @param horizontalTextAlignment a {@link net.sf.dynamicreports.report.constant.HorizontalTextAlignment} object.
	 * @return a {@link net.sf.jasperreports.engine.type.HorizontalTextAlignEnum} object.
	 */
	protected static HorizontalTextAlignEnum horizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		if(horizontalTextAlignment == null)
		{
			return null;
		}
		
		switch(horizontalTextAlignment)
		{
			case LEFT:
				return HorizontalTextAlignEnum.LEFT;
			case CENTER:
				return HorizontalTextAlignEnum.CENTER;
			case RIGHT:
				return HorizontalTextAlignEnum.RIGHT;
			case JUSTIFIED:
				return HorizontalTextAlignEnum.JUSTIFIED;
			default:
				throw new JasperDesignException(
					"Horizontal text alignment " + horizontalTextAlignment.name() + " not supported");
		}
	}
	
	/**
	 * <p>verticalTextAlignment.</p>
	 *
	 * @param verticalTextAlignment a {@link net.sf.dynamicreports.report.constant.VerticalTextAlignment} object.
	 * @return a {@link net.sf.jasperreports.engine.type.VerticalTextAlignEnum} object.
	 */
	protected static VerticalTextAlignEnum verticalTextAlignment(final VerticalTextAlignment verticalTextAlignment)
	{
		if(verticalTextAlignment == null)
		{
			return null;
		}
		
		switch(verticalTextAlignment)
		{
			case TOP:
				return VerticalTextAlignEnum.TOP;
			case MIDDLE:
				return VerticalTextAlignEnum.MIDDLE;
			case BOTTOM:
				return VerticalTextAlignEnum.BOTTOM;
			case JUSTIFIED:
				return VerticalTextAlignEnum.JUSTIFIED;
			default:
				throw new JasperDesignException(
					"Vertical text alignment " + verticalTextAlignment.name() + " not supported");
		}
	}
	
	/**
	 * <p>horizontalImageAlignment.</p>
	 *
	 * @param horizontalImageAlignment a {@link net.sf.dynamicreports.report.constant.HorizontalImageAlignment} object.
	 * @return a {@link net.sf.jasperreports.engine.type.HorizontalImageAlignEnum} object.
	 */
	protected static HorizontalImageAlignEnum horizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		if(horizontalImageAlignment == null)
		{
			return null;
		}
		
		switch(horizontalImageAlignment)
		{
			case LEFT:
				return HorizontalImageAlignEnum.LEFT;
			case CENTER:
				return HorizontalImageAlignEnum.CENTER;
			case RIGHT:
				return HorizontalImageAlignEnum.RIGHT;
			default:
				throw new JasperDesignException(
					"Horizontal image alignment " + horizontalImageAlignment.name() + " not supported");
		}
	}
	
	/**
	 * <p>verticalImageAlignment.</p>
	 *
	 * @param verticalImageAlignment a {@link net.sf.dynamicreports.report.constant.VerticalImageAlignment} object.
	 * @return a {@link net.sf.jasperreports.engine.type.VerticalImageAlignEnum} object.
	 */
	protected static VerticalImageAlignEnum verticalImageAlignment(final VerticalImageAlignment verticalImageAlignment)
	{
		if(verticalImageAlignment == null)
		{
			return null;
		}
		
		switch(verticalImageAlignment)
		{
			case TOP:
				return VerticalImageAlignEnum.TOP;
			case MIDDLE:
				return VerticalImageAlignEnum.MIDDLE;
			case BOTTOM:
				return VerticalImageAlignEnum.BOTTOM;
			default:
				throw new JasperDesignException(
					"Vertical image alignment " + verticalImageAlignment.name() + " not supported");
		}
	}
	
	/**
	 * <p>rotation.</p>
	 *
	 * @param rotation a {@link net.sf.dynamicreports.report.constant.Rotation} object.
	 * @return a {@link net.sf.jasperreports.engine.type.RotationEnum} object.
	 */
	protected static RotationEnum rotation(final Rotation rotation)
	{
		if(rotation == null)
		{
			return null;
		}
		
		switch(rotation)
		{
			case NONE:
				return RotationEnum.NONE;
			case LEFT:
				return RotationEnum.LEFT;
			case RIGHT:
				return RotationEnum.RIGHT;
			case UPSIDE_DOWN:
				return RotationEnum.UPSIDE_DOWN;
			default:
				throw new JasperDesignException("Rotation " + rotation.name() + " not supported");
		}
	}
	
	/**
	 * <p>whenNoDataType.</p>
	 *
	 * @param whenNoDataType a {@link net.sf.dynamicreports.report.constant.WhenNoDataType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.WhenNoDataTypeEnum} object.
	 */
	protected static WhenNoDataTypeEnum whenNoDataType(final WhenNoDataType whenNoDataType)
	{
		switch(whenNoDataType)
		{
			case NO_PAGES:
				return WhenNoDataTypeEnum.NO_PAGES;
			case BLANK_PAGE:
				return WhenNoDataTypeEnum.BLANK_PAGE;
			case ALL_SECTIONS_NO_DETAIL:
				return WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL;
			case NO_DATA_SECTION:
				return WhenNoDataTypeEnum.NO_DATA_SECTION;
			default:
				throw new JasperDesignException("When no data type " + whenNoDataType.name() + " not supported");
		}
	}
	
	/**
	 * <p>whenNoDataType.</p>
	 *
	 * @param whenNoDataType a {@link net.sf.jasperreports.engine.type.WhenNoDataTypeEnum} object.
	 * @return a {@link net.sf.dynamicreports.report.constant.WhenNoDataType} object.
	 */
	public static WhenNoDataType whenNoDataType(final WhenNoDataTypeEnum whenNoDataType)
	{
		if(whenNoDataType == null)
		{
			return null;
		}
		switch(whenNoDataType)
		{
			case NO_PAGES:
				return WhenNoDataType.NO_PAGES;
			case BLANK_PAGE:
				return WhenNoDataType.BLANK_PAGE;
			case ALL_SECTIONS_NO_DETAIL:
				return WhenNoDataType.ALL_SECTIONS_NO_DETAIL;
			case NO_DATA_SECTION:
				return WhenNoDataType.NO_DATA_SECTION;
			default:
				throw new JasperDesignException("When no data type " + whenNoDataType.name() + " not supported");
		}
	}
	
	/**
	 * <p>whenResourceMissingType.</p>
	 *
	 * @param whenResourceMissingType a {@link net.sf.dynamicreports.report.constant.WhenResourceMissingType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum} object.
	 */
	protected static WhenResourceMissingTypeEnum whenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		switch(whenResourceMissingType)
		{
			case NULL:
				return WhenResourceMissingTypeEnum.NULL;
			case EMPTY:
				return WhenResourceMissingTypeEnum.EMPTY;
			case KEY:
				return WhenResourceMissingTypeEnum.KEY;
			case ERROR:
				return WhenResourceMissingTypeEnum.ERROR;
			default:
				throw new JasperDesignException(
					"When resource missing type " + whenResourceMissingType.name() + " not supported");
		}
	}
	
	/**
	 * <p>whenResourceMissingType.</p>
	 *
	 * @param whenResourceMissingType a {@link net.sf.jasperreports.engine.type.WhenResourceMissingTypeEnum} object.
	 * @return a {@link net.sf.dynamicreports.report.constant.WhenResourceMissingType} object.
	 */
	public static WhenResourceMissingType whenResourceMissingType(final WhenResourceMissingTypeEnum whenResourceMissingType)
	{
		switch(whenResourceMissingType)
		{
			case NULL:
				return WhenResourceMissingType.NULL;
			case EMPTY:
				return WhenResourceMissingType.EMPTY;
			case KEY:
				return WhenResourceMissingType.KEY;
			case ERROR:
				return WhenResourceMissingType.ERROR;
			default:
				throw new JasperDesignException(
					"When resource missing type " + whenResourceMissingType.name() + " not supported");
		}
	}
	
	/**
	 * <p>pageOrientation.</p>
	 *
	 * @param orientation a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
	 * @return a {@link net.sf.jasperreports.engine.type.OrientationEnum} object.
	 */
	protected static OrientationEnum pageOrientation(final PageOrientation orientation)
	{
		switch(orientation)
		{
			case PORTRAIT:
				return OrientationEnum.PORTRAIT;
			case LANDSCAPE:
				return OrientationEnum.LANDSCAPE;
			default:
				throw new JasperDesignException("Page orientation " + orientation.name() + " not supported");
		}
	}
	
	/**
	 * <p>pageOrientation.</p>
	 *
	 * @param orientation a {@link net.sf.jasperreports.engine.type.OrientationEnum} object.
	 * @return a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
	 */
	public static PageOrientation pageOrientation(final OrientationEnum orientation)
	{
		switch(orientation)
		{
			case PORTRAIT:
				return PageOrientation.PORTRAIT;
			case LANDSCAPE:
				return PageOrientation.LANDSCAPE;
			default:
				throw new JasperDesignException("Page orientation " + orientation.name() + " not supported");
		}
	}
	
	/**
	 * <p>variableResetType.</p>
	 *
	 * @param resetType a {@link net.sf.dynamicreports.design.constant.ResetType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.ResetTypeEnum} object.
	 */
	public static ResetTypeEnum variableResetType(final ResetType resetType)
	{
		if(resetType == null)
		{
			return ResetTypeEnum.NONE;
		}
		
		switch(resetType)
		{
			case NONE:
				return ResetTypeEnum.NONE;
			case REPORT:
				return ResetTypeEnum.REPORT;
			case PAGE:
				return ResetTypeEnum.PAGE;
			case COLUMN:
				return ResetTypeEnum.COLUMN;
			case GROUP:
				return ResetTypeEnum.GROUP;
			default:
				throw new JasperDesignException("Variable reset type " + resetType.name() + " not supported");
		}
	}
	
	public static DatasetResetTypeEnum variableDatabaseResetType(final ResetType resetType)
	{
		return switch(variableResetType(resetType))
		{
			case REPORT -> DatasetResetTypeEnum.REPORT;
			case PAGE -> DatasetResetTypeEnum.PAGE;
			case COLUMN -> DatasetResetTypeEnum.COLUMN;
			case GROUP -> DatasetResetTypeEnum.GROUP;
			case NONE -> DatasetResetTypeEnum.NONE;
			default -> throw new IllegalStateException("ResetType " + resetType + " can't be processed");
		};
	}
	
	/**
	 * <p>evaluationTime.</p>
	 *
	 * @param evaluationTime a {@link net.sf.dynamicreports.design.constant.EvaluationTime} object.
	 * @return a {@link net.sf.jasperreports.engine.type.EvaluationTimeEnum} object.
	 */
	public static EvaluationTimeEnum evaluationTime(final EvaluationTime evaluationTime)
	{
		if(evaluationTime == null)
		{
			return EvaluationTimeEnum.NOW;
		}
		
		switch(evaluationTime)
		{
			case NOW:
				return EvaluationTimeEnum.NOW;
			case REPORT:
				return EvaluationTimeEnum.REPORT;
			case PAGE:
				return EvaluationTimeEnum.PAGE;
			case COLUMN:
				return EvaluationTimeEnum.COLUMN;
			case GROUP:
				return EvaluationTimeEnum.GROUP;
			case BAND:
				return EvaluationTimeEnum.BAND;
			case AUTO:
				return EvaluationTimeEnum.AUTO;
			default:
				throw new JasperDesignException("Evaluation time " + evaluationTime.name() + " not supported");
		}
	}
	
	/**
	 * <p>splitType.</p>
	 *
	 * @param splitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.SplitTypeEnum} object.
	 */
	protected static SplitTypeEnum splitType(final SplitType splitType)
	{
		if(splitType == null)
		{
			return null;
		}
		switch(splitType)
		{
			case IMMEDIATE:
				return SplitTypeEnum.IMMEDIATE;
			case PREVENT:
				return SplitTypeEnum.PREVENT;
			case STRETCH:
				return SplitTypeEnum.STRETCH;
			default:
				throw new JasperDesignException("Split type " + splitType.name() + " not supported");
		}
	}
	
	/**
	 * <p>timePeriodType.</p>
	 *
	 * @param timePeriodType a {@link net.sf.dynamicreports.report.constant.TimePeriod} object.
	 * @return a {@link java.lang.Class} object.
	 */
	protected static Class<?> timePeriodType(final TimePeriod timePeriodType)
	{
		switch(timePeriodType)
		{
			case YEAR:
				return Year.class;
			case QUARTER:
				return Quarter.class;
			case MONTH:
				return Month.class;
			case WEEK:
				return Week.class;
			case DAY:
				return Day.class;
			case HOUR:
				return Hour.class;
			case MINUTE:
				return Minute.class;
			case SECOND:
				return Second.class;
			case MILLISECOND:
				return Millisecond.class;
			default:
				throw new JasperDesignException("Time period type " + timePeriodType.name() + " not supported");
		}
	}
	
	/**
	 * <p>calculation.</p>
	 *
	 * @param calculation a {@link net.sf.dynamicreports.report.constant.Calculation} object.
	 * @return a {@link net.sf.jasperreports.engine.type.CalculationEnum} object.
	 */
	protected static CalculationEnum calculation(final Calculation calculation)
	{
		switch(calculation)
		{
			case NOTHING:
				return CalculationEnum.NOTHING;
			case COUNT:
				return CalculationEnum.COUNT;
			case SUM:
				return CalculationEnum.SUM;
			case AVERAGE:
				return CalculationEnum.AVERAGE;
			case LOWEST:
				return CalculationEnum.LOWEST;
			case HIGHEST:
				return CalculationEnum.HIGHEST;
			case STANDARD_DEVIATION:
				return CalculationEnum.STANDARD_DEVIATION;
			case VARIANCE:
				return CalculationEnum.VARIANCE;
			case FIRST:
				return CalculationEnum.FIRST;
			case DISTINCT_COUNT:
				return CalculationEnum.DISTINCT_COUNT;
			default:
				throw new JasperDesignException("Calculation " + calculation.name() + " not supported");
		}
	}
	
	/**
	 * <p>sizeUnit.</p>
	 *
	 * @param sizeUnit a {@link net.sf.dynamicreports.jasper.constant.SizeUnit} object.
	 * @return a {@link net.sf.jasperreports.export.type.HtmlSizeUnitEnum} object.
	 */
	protected static HtmlSizeUnitEnum sizeUnit(final SizeUnit sizeUnit)
	{
		switch(sizeUnit)
		{
			case PIXEL:
				return HtmlSizeUnitEnum.PIXEL;
			case POINT:
				return HtmlSizeUnitEnum.POINT;
			default:
				throw new JasperDesignException("SizeUnit " + sizeUnit.name() + " not supported");
		}
	}
	
	/**
	 * <p>pdfVersion.</p>
	 *
	 * @param pdfVersion a {@link net.sf.dynamicreports.jasper.constant.PdfVersion} object.
	 * @return a {@link net.sf.jasperreports.export.type.PdfVersionEnum} object.
	 */
	protected static PdfVersionEnum pdfVersion(final PdfVersion pdfVersion)
	{
		switch(pdfVersion)
		{
			case VERION_1_2:
				return PdfVersionEnum.VERSION_1_2;
			case VERION_1_3:
				return PdfVersionEnum.VERSION_1_3;
			case VERION_1_4:
				return PdfVersionEnum.VERSION_1_4;
			case VERION_1_5:
				return PdfVersionEnum.VERSION_1_5;
			case VERION_1_6:
				return PdfVersionEnum.VERSION_1_6;
			case VERION_1_7:
				return PdfVersionEnum.VERSION_1_7;
			default:
				throw new JasperDesignException("PdfVersion " + pdfVersion.name() + " not supported");
		}
	}
	
	/**
	 * <p>pdfPermission.</p>
	 *
	 * @param permissions a {@link java.util.List} object.
	 * @return a {@link java.lang.Integer} object.
	 */
	protected static Integer pdfPermission(final List<PdfPermission> permissions)
	{
		final int permission = 0;
		for(final PdfPermission pdfPermission : permissions)
		{
			switch(pdfPermission)
			{
				case PRINTING:
					return permission | PdfWriter.ALLOW_PRINTING;
				case MODIFY_CONTENTS:
					return permission | PdfWriter.ALLOW_MODIFY_CONTENTS;
				case COPY:
					return permission | PdfWriter.ALLOW_COPY;
				case MODIFY_ANNOTATIONS:
					return permission | PdfWriter.ALLOW_MODIFY_ANNOTATIONS;
				case FILL_IN:
					return permission | PdfWriter.ALLOW_FILL_IN;
				case SCREEN_READERS:
					return permission | PdfWriter.ALLOW_SCREENREADERS;
				case ASSEMBLY:
					return permission | PdfWriter.ALLOW_ASSEMBLY;
				case DEGRADED_PRINTING:
					return permission | PdfWriter.ALLOW_DEGRADED_PRINTING;
				default:
					throw new JasperDesignException("PdfPermission " + pdfPermission.name() + " not supported");
			}
		}
		return permission;
	}
	
	/**
	 * <p>pdfPrintScaling.</p>
	 *
	 * @param pdfPrintScaling a {@link net.sf.dynamicreports.report.constant.PdfPrintScaling} object.
	 * @return a {@link net.sf.jasperreports.export.type.PdfPrintScalingEnum} object.
	 */
	protected static PdfPrintScalingEnum pdfPrintScaling(final PdfPrintScaling pdfPrintScaling)
	{
		switch(pdfPrintScaling)
		{
			case NONE:
				return PdfPrintScalingEnum.NONE;
			case DEFAULT:
				return PdfPrintScalingEnum.DEFAULT;
			default:
				throw new JasperDesignException("Pdf print scaling " + pdfPrintScaling.name() + " not supported");
		}
	}
	
	/**
	 * <p>pdfaConformance.</p>
	 *
	 * @param pdfaConformance a {@link net.sf.dynamicreports.report.constant.PdfaConformance} object.
	 * @return a {@link net.sf.jasperreports.export.type.PdfaConformanceEnum} object.
	 */
	protected static PdfaConformanceEnum pdfaConformance(final PdfaConformance pdfaConformance)
	{
		switch(pdfaConformance)
		{
			case NONE:
				return PdfaConformanceEnum.NONE;
			case PDFA_1A:
				return PdfaConformanceEnum.PDFA_1A;
			case PDFA_1B:
				return PdfaConformanceEnum.PDFA_1B;
			default:
				throw new JasperDesignException("Pdfa conformance " + pdfaConformance.name() + " not supported");
		}
	}
	
	/**
	 * <p>lineDirection.</p>
	 *
	 * @param lineDirection a {@link net.sf.dynamicreports.report.constant.LineDirection} object.
	 * @return a {@link net.sf.jasperreports.engine.type.LineDirectionEnum} object.
	 */
	public static LineDirectionEnum lineDirection(final LineDirection lineDirection)
	{
		if(lineDirection == null)
		{
			return null;
		}
		
		switch(lineDirection)
		{
			case TOP_DOWN:
				return LineDirectionEnum.TOP_DOWN;
			case BOTTOM_UP:
				return LineDirectionEnum.BOTTOM_UP;
			default:
				throw new JasperDesignException("LineDirection " + lineDirection.name() + " not supported");
		}
	}
	
	/**
	 * <p>markup.</p>
	 *
	 * @param markup a {@link net.sf.dynamicreports.report.constant.Markup} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String markup(final Markup markup)
	{
		if(markup == null)
		{
			return null;
		}
		
		switch(markup)
		{
			case NONE:
				return "none";
			case STYLED:
				return "styled";
			case RTF:
				return "rtf";
			case HTML:
				return "html";
			default:
				throw new JasperDesignException("Markup " + markup.name() + " not supported");
		}
	}
	
	/**
	 * <p>lineSpacing.</p>
	 *
	 * @param lineSpacing a {@link net.sf.dynamicreports.report.constant.LineSpacing} object.
	 * @return a {@link net.sf.jasperreports.engine.type.LineSpacingEnum} object.
	 */
	public static LineSpacingEnum lineSpacing(final LineSpacing lineSpacing)
	{
		if(lineSpacing == null)
		{
			return null;
		}
		
		switch(lineSpacing)
		{
			case SINGLE:
				return LineSpacingEnum.SINGLE;
			case ONE_AND_HALF:
				return LineSpacingEnum.ONE_AND_HALF;
			case DOUBLE:
				return LineSpacingEnum.DOUBLE;
			case AT_LEAST:
				return LineSpacingEnum.AT_LEAST;
			case FIXED:
				return LineSpacingEnum.FIXED;
			case PROPORTIONAL:
				return LineSpacingEnum.PROPORTIONAL;
			default:
				throw new JasperDesignException("LineSpacing " + lineSpacing.name() + " not supported");
		}
	}
	
	/**
	 * <p>breakType.</p>
	 *
	 * @param breakType a {@link net.sf.dynamicreports.report.constant.BreakType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.BreakTypeEnum} object.
	 */
	public static BreakTypeEnum breakType(final BreakType breakType)
	{
		if(breakType == null)
		{
			return null;
		}
		
		switch(breakType)
		{
			case PAGE:
				return BreakTypeEnum.PAGE;
			case COLUMN:
				return BreakTypeEnum.COLUMN;
			default:
				throw new JasperDesignException("BreakType " + breakType.name() + " not supported");
		}
	}
	
	/**
	 * <p>runDirection.</p>
	 *
	 * @param runDirection a {@link net.sf.dynamicreports.report.constant.RunDirection} object.
	 * @return a {@link net.sf.jasperreports.engine.type.RunDirectionEnum} object.
	 */
	public static RunDirectionEnum runDirection(final RunDirection runDirection)
	{
		if(runDirection == null)
		{
			return null;
		}
		
		switch(runDirection)
		{
			case LEFT_TO_RIGHT:
				return RunDirectionEnum.LTR;
			case RIGHT_TO_LEFT:
				return RunDirectionEnum.RTL;
			default:
				throw new JasperDesignException("RunDirection " + runDirection.name() + " not supported");
		}
	}
	
	/**
	 * <p>crosstabTotalPosition.</p>
	 *
	 * @param totalPosition a {@link net.sf.dynamicreports.report.constant.CrosstabTotalPosition} object.
	 * @return a {@link net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum} object.
	 */
	public static CrosstabTotalPositionEnum crosstabTotalPosition(final CrosstabTotalPosition totalPosition)
	{
		if(totalPosition == null)
		{
			return CrosstabTotalPositionEnum.NONE;
		}
		
		switch(totalPosition)
		{
			case START:
				return CrosstabTotalPositionEnum.START;
			case END:
				return CrosstabTotalPositionEnum.END;
			default:
				throw new JasperDesignException("CrosstabTotalPosition " + totalPosition.name() + " not supported");
		}
	}
	
	/**
	 * <p>crosstabPercentageType.</p>
	 *
	 * @param percentageType a {@link net.sf.dynamicreports.report.constant.CrosstabPercentageType} object.
	 * @return a {@link net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum} object.
	 */
	public static CrosstabPercentageEnum crosstabPercentageType(final CrosstabPercentageType percentageType)
	{
		if(percentageType == null)
		{
			return null;
		}
		
		switch(percentageType)
		{
			case NONE:
				return CrosstabPercentageEnum.NONE;
			case GRAND_TOTAL:
				return CrosstabPercentageEnum.GRAND_TOTAL;
			default:
				throw new JasperDesignException("CrosstabPercentageType " + percentageType.name() + " not supported");
		}
	}
	
	/**
	 * <p>orderType.</p>
	 *
	 * @param orderType a {@link net.sf.dynamicreports.report.constant.OrderType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.SortOrderEnum} object.
	 */
	public static SortOrderEnum orderType(final OrderType orderType)
	{
		if(orderType == null)
		{
			return null;
		}
		
		switch(orderType)
		{
			case ASCENDING:
				return SortOrderEnum.ASCENDING;
			case DESCENDING:
				return SortOrderEnum.DESCENDING;
			default:
				throw new JasperDesignException("OrderType " + orderType.name() + " not supported");
		}
	}
	
	/**
	 * <p>bucketOrderType.</p>
	 *
	 * @param orderType a {@link net.sf.dynamicreports.report.constant.OrderType} object.
	 * @return a {@link net.sf.jasperreports.engine.analytics.dataset.BucketOrder} object.
	 */
	public static BucketOrder bucketOrderType(final OrderType orderType)
	{
		if(orderType == null)
		{
			return null;
		}
		
		switch(orderType)
		{
			case ASCENDING:
				return BucketOrder.ASCENDING;
			case DESCENDING:
				return BucketOrder.DESCENDING;
			default:
				throw new JasperDesignException("OrderType " + orderType.name() + " not supported");
		}
	}
	
	/**
	 * <p>componentPositionType.</p>
	 *
	 * @param componentPositionType a {@link net.sf.dynamicreports.report.constant.ComponentPositionType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.PositionTypeEnum} object.
	 */
	public static PositionTypeEnum componentPositionType(final ComponentPositionType componentPositionType)
	{
		switch(componentPositionType)
		{
			case FLOAT:
				return PositionTypeEnum.FLOAT;
			case FIX_RELATIVE_TO_TOP:
				return PositionTypeEnum.FIX_RELATIVE_TO_TOP;
			case FIX_RELATIVE_TO_BOTTOM:
				return PositionTypeEnum.FIX_RELATIVE_TO_BOTTOM;
			default:
				throw new JasperDesignException(
					"ComponentPositionType " + componentPositionType.name() + " not supported");
		}
	}
	
	/**
	 * <p>stretchType.</p>
	 *
	 * @param stretchType a {@link net.sf.dynamicreports.report.constant.StretchType} object.
	 * @return a {@link net.sf.jasperreports.engine.type.StretchTypeEnum} object.
	 */
	@SuppressWarnings("deprecation")
	public static StretchTypeEnum stretchType(final StretchType stretchType)
	{
		if(stretchType == null)
		{
			return StretchTypeEnum.NO_STRETCH;
		}
		
		switch(stretchType)
		{
			case NO_STRETCH:
				return StretchTypeEnum.NO_STRETCH;
			case RELATIVE_TO_BAND_HEIGHT:
				return StretchTypeEnum.CONTAINER_HEIGHT;
			case RELATIVE_TO_TALLEST_OBJECT:
				return StretchTypeEnum.ELEMENT_GROUP_HEIGHT;
			case ELEMENT_GROUP_HEIGHT:
				return StretchTypeEnum.ELEMENT_GROUP_HEIGHT;
			case ELEMENT_GROUP_BOTTOM:
				return StretchTypeEnum.ELEMENT_GROUP_BOTTOM;
			case CONTAINER_HEIGHT:
				return StretchTypeEnum.CONTAINER_HEIGHT;
			case CONTAINER_BOTTOM:
				return StretchTypeEnum.CONTAINER_BOTTOM;
			default:
				throw new JasperDesignException("StretchType " + stretchType.name() + " not supported");
		}
	}
	
	/**
	 * <p>hyperLinkType.</p>
	 *
	 * @param hyperLinkType a {@link java.lang.String} object.
	 * @return a {@link net.sf.jasperreports.engine.type.HyperlinkTypeEnum} object.
	 */
	public static HyperlinkTypeEnum hyperLinkType(final String hyperLinkType)
	{
		if(hyperLinkType == null || !EnumUtils.isValidEnum(HyperLinkType.class, hyperLinkType))
		{
			return null;
		}
		
		final HyperLinkType type = HyperLinkType.valueOf(hyperLinkType);
		switch(type)
		{
			case NONE:
				return HyperlinkTypeEnum.NONE;
			case REFERENCE:
				return HyperlinkTypeEnum.REFERENCE;
			case LOCAL_ANCHOR:
				return HyperlinkTypeEnum.LOCAL_ANCHOR;
			case LOCAL_PAGE:
				return HyperlinkTypeEnum.LOCAL_PAGE;
			case REMOTE_ANCHOR:
				return HyperlinkTypeEnum.REMOTE_ANCHOR;
			case REMOTE_PAGE:
				return HyperlinkTypeEnum.REMOTE_PAGE;
			default:
				throw new JasperDesignException("HyperLinkType " + type.name() + " not supported");
		}
	}
	
	/**
	 * <p>hyperLinkTarget.</p>
	 *
	 * @param hyperLinkTarget a {@link java.lang.String} object.
	 * @return a {@link net.sf.jasperreports.engine.type.HyperlinkTargetEnum} object.
	 */
	public static HyperlinkTargetEnum hyperLinkTarget(final String hyperLinkTarget)
	{
		if(hyperLinkTarget == null || !EnumUtils.isValidEnum(HyperLinkTarget.class, hyperLinkTarget))
		{
			return null;
		}
		
		final HyperLinkTarget target = HyperLinkTarget.valueOf(hyperLinkTarget);
		switch(target)
		{
			case NONE:
				return HyperlinkTargetEnum.NONE;
			case SELF:
				return HyperlinkTargetEnum.SELF;
			case BLANK:
				return HyperlinkTargetEnum.BLANK;
			case PARENT:
				return HyperlinkTargetEnum.PARENT;
			case TOP:
				return HyperlinkTargetEnum.TOP;
			default:
				throw new JasperDesignException("HyperLinkTarget " + target.name() + " not supported");
		}
	}
	
	/**
	 * <p>groupFooterPosition.</p>
	 *
	 * @param footerPosition a {@link net.sf.dynamicreports.report.constant.GroupFooterPosition} object.
	 * @return a {@link net.sf.jasperreports.engine.type.FooterPositionEnum} object.
	 */
	public static FooterPositionEnum groupFooterPosition(final GroupFooterPosition footerPosition)
	{
		switch(footerPosition)
		{
			case NORMAL:
				return FooterPositionEnum.NORMAL;
			case COLLATE_AT_BOTTOM:
				return FooterPositionEnum.COLLATE_AT_BOTTOM;
			case FORCE_AT_BOTTOM:
				return FooterPositionEnum.FORCE_AT_BOTTOM;
			case STACK_AT_BOTTOM:
				return FooterPositionEnum.STACK_AT_BOTTOM;
			default:
				throw new JasperDesignException("GroupFooterPosition " + footerPosition.name() + " not supported");
		}
	}
	
	/**
	 * <p>tabStopAlignment.</p>
	 *
	 * @param alignment a {@link net.sf.dynamicreports.report.constant.TabStopAlignment} object.
	 * @return a {@link net.sf.jasperreports.engine.type.TabStopAlignEnum} object.
	 */
	public static TabStopAlignEnum tabStopAlignment(final TabStopAlignment alignment)
	{
		switch(alignment)
		{
			case LEFT:
				return TabStopAlignEnum.LEFT;
			case CENTER:
				return TabStopAlignEnum.CENTER;
			case RIGHT:
				return TabStopAlignEnum.RIGHT;
			default:
				throw new JasperDesignException("TabStopAlignment " + alignment.name() + " not supported");
		}
	}
	
	/**
	 * <p>printOrder.</p>
	 *
	 * @param printOrder a {@link net.sf.dynamicreports.report.constant.Orientation} object.
	 * @return a {@link net.sf.jasperreports.engine.type.PrintOrderEnum} object.
	 */
	protected static PrintOrderEnum printOrder(final Orientation printOrder)
	{
		switch(printOrder)
		{
			case HORIZONTAL:
				return PrintOrderEnum.HORIZONTAL;
			case VERTICAL:
				return PrintOrderEnum.VERTICAL;
			default:
				throw new JasperDesignException("PrintOrder " + printOrder.name() + " not supported");
		}
	}
	
	/**
	 * <p>rectangleAnchor.</p>
	 *
	 * @param rectangleAnchor a {@link net.sf.dynamicreports.report.constant.RectangleAnchor} object.
	 * @return a {@link org.jfree.ui.RectangleAnchor} object.
	 */
	public static org.jfree.ui.RectangleAnchor rectangleAnchor(final RectangleAnchor rectangleAnchor)
	{
		switch(rectangleAnchor)
		{
			case CENTER:
				return org.jfree.ui.RectangleAnchor.CENTER;
			case TOP:
				return org.jfree.ui.RectangleAnchor.TOP;
			case TOP_LEFT:
				return org.jfree.ui.RectangleAnchor.TOP_LEFT;
			case TOP_RIGHT:
				return org.jfree.ui.RectangleAnchor.TOP_RIGHT;
			case BOTTOM:
				return org.jfree.ui.RectangleAnchor.BOTTOM;
			case BOTTOM_LEFT:
				return org.jfree.ui.RectangleAnchor.BOTTOM_LEFT;
			case BOTTOM_RIGHT:
				return org.jfree.ui.RectangleAnchor.BOTTOM_RIGHT;
			case LEFT:
				return org.jfree.ui.RectangleAnchor.LEFT;
			case RIGHT:
				return org.jfree.ui.RectangleAnchor.RIGHT;
			default:
				throw new JasperDesignException("RectangleAnchor " + rectangleAnchor.name() + " not supported");
		}
	}
	
	/**
	 * <p>textAdjust.</p>
	 *
	 * @param textAdjust a {@link net.sf.dynamicreports.report.constant.TextAdjust} object.
	 * @return a {@link net.sf.jasperreports.engine.type.TextAdjustEnum} object.
	 */
	public static TextAdjustEnum textAdjust(final TextAdjust textAdjust)
	{
		switch(textAdjust)
		{
			case CUT_TEXT:
				return TextAdjustEnum.CUT_TEXT;
			case SCALE_FONT:
				return TextAdjustEnum.SCALE_FONT;
			case STRETCH_HEIGHT:
				return TextAdjustEnum.STRETCH_HEIGHT;
			default:
				throw new JasperDesignException("TextAdjust " + textAdjust.name() + " not supported");
		}
	}
}
