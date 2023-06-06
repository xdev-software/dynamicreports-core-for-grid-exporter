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
package software.xdev.dynamicreports.report.defaults;

import software.xdev.dynamicreports.report.base.datatype.DRDataType;
import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.defaults.xml.XmlDataType;
import software.xdev.dynamicreports.report.defaults.xml.XmlDynamicReports;
import software.xdev.dynamicreports.report.defaults.xml.XmlFont;
import software.xdev.dynamicreports.report.exception.DRReportException;

/**
 * <p>DefaultBinder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DefaultBinder {

    /**
     * <p>bind.</p>
     *
     * @param xmlDynamicReports a {@link software.xdev.dynamicreports.report.defaults.xml.XmlDynamicReports} object.
     * @return a {@link software.xdev.dynamicreports.report.defaults.Default} object.
     */
    public static Default bind(XmlDynamicReports xmlDynamicReports) {
        Default defaults = new Default();
        if (xmlDynamicReports != null) {
            // font
            bind(defaults.getFont(), xmlDynamicReports.getFont());
            // data type
            bind(defaults.getBigDecimalType(), xmlDynamicReports.getBigDecimalType());
            bind(defaults.getBigIntegerType(), xmlDynamicReports.getBigIntegerType());
            bind(defaults.getByteType(), xmlDynamicReports.getByteType());
            bind(defaults.getDoubleType(), xmlDynamicReports.getDoubleType());
            bind(defaults.getFloatType(), xmlDynamicReports.getFloatType());
            bind(defaults.getIntegerType(), xmlDynamicReports.getIntegerType());
            bind(defaults.getLongType(), xmlDynamicReports.getLongType());
            bind(defaults.getShortType(), xmlDynamicReports.getShortType());
            bind(defaults.getDateType(), xmlDynamicReports.getDateType());
            bind(defaults.getDateYearToMonthType(), xmlDynamicReports.getDateYearToMonthType());
            bind(defaults.getDateYearToHourType(), xmlDynamicReports.getDateYearToHourType());
            bind(defaults.getDateYearToMinuteType(), xmlDynamicReports.getDateYearToMinuteType());
            bind(defaults.getDateYearToSecondType(), xmlDynamicReports.getDateYearToSecondType());
            bind(defaults.getDateYearToFractionType(), xmlDynamicReports.getDateYearToFractionType());
            bind(defaults.getDateYearType(), xmlDynamicReports.getDateYearType());
            bind(defaults.getDateMonthType(), xmlDynamicReports.getDateMonthType());
            bind(defaults.getDateDayType(), xmlDynamicReports.getDateDayType());
            bind(defaults.getTimeHourToMinuteType(), xmlDynamicReports.getTimeHourToMinuteType());
            bind(defaults.getTimeHourToSecondType(), xmlDynamicReports.getTimeHourToSecondType());
            bind(defaults.getTimeHourToFractionType(), xmlDynamicReports.getTimeHourToFractionType());
            bind(defaults.getPercentageType(), xmlDynamicReports.getPercentageType());
            bind(defaults.getBooleanType(), xmlDynamicReports.getBooleanType());
            bind(defaults.getCharacterType(), xmlDynamicReports.getCharacterType());
            bind(defaults.getStringType(), xmlDynamicReports.getStringType());

            if (xmlDynamicReports.isLoadSystemFonts() != null) {
                defaults.setLoadSystemFonts(xmlDynamicReports.isLoadSystemFonts());
            }
        }
        return defaults;
    }

    private static void bind(DRDataType<?, ?> dataType, XmlDataType xmlDataType) {
        if (xmlDataType == null) {
            return;
        }

        if (xmlDataType.getPattern() != null) {
            dataType.setPattern(xmlDataType.getPattern());
        }
        if (xmlDataType.getHorizontalAlignment() != null) {
            HorizontalTextAlignment alignment = HorizontalTextAlignment.valueOf(xmlDataType.getHorizontalAlignment().name());
            if (alignment == null) {
                throw new DRReportException("Horizontal text alignment " + xmlDataType.getHorizontalAlignment().name() + " not supported");
            }
            dataType.setHorizontalTextAlignment(alignment);
        }
    }

    @SuppressWarnings("deprecation")
    private static void bind(DRFont font, XmlFont xmlFont) {
        if (xmlFont == null) {
            return;
        }

        if (xmlFont.getFontName() != null) {
            font.setFontName(xmlFont.getFontName());
        }
        if (xmlFont.getFontSize() != null) {
            font.setFontSize(xmlFont.getFontSize());
        }
        if (xmlFont.getPdfFontName() != null) {
            font.setPdfFontName(xmlFont.getPdfFontName());
        }
        if (xmlFont.getPdfEncoding() != null) {
            font.setPdfEncoding(xmlFont.getPdfEncoding());
        }
        if (xmlFont.isPdfEmbedded() != null) {
            font.setPdfEmbedded(xmlFont.isPdfEmbedded());
        }
    }
}
