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
package software.xdev.dynamicreports.jasper.constant;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRTextElement;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterTagHelper;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractMetadataExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRTextMeasurerUtil;
import net.sf.jasperreports.engine.xml.PrintSaxParserFactory;
import net.sf.jasperreports.export.CommonExportConfiguration;
import net.sf.jasperreports.export.CsvExporterConfiguration;
import net.sf.jasperreports.export.CsvMetadataReportConfiguration;
import net.sf.jasperreports.export.DocxReportConfiguration;
import net.sf.jasperreports.export.Graphics2DReportConfiguration;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.HtmlReportConfiguration;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.ReportExportConfiguration;
import net.sf.jasperreports.export.TextReportConfiguration;
import net.sf.jasperreports.export.WriterExporterOutput;
import net.sf.jasperreports.export.XlsExporterConfiguration;
import net.sf.jasperreports.export.XlsMetadataReportConfiguration;
import net.sf.jasperreports.export.XlsReportConfiguration;


public final class JasperProperty
{
	private JasperProperty()
	{
	}
	
	// export
	public static final String EXPORT_CHARACTER_ENCODING = WriterExporterOutput.PROPERTY_CHARACTER_ENCODING;
	public static final String EXPORT_CONFIGURATION_OVERRIDE_REPORT_HINTS =
		CommonExportConfiguration.PROPERTY_EXPORT_CONFIGURATION_OVERRIDE_REPORT_HINTS;
	public static final String EXPORT_IGNORE_PAGE_MARGINS = ReportExportConfiguration.PROPERTY_IGNORE_PAGE_MARGINS;
	public static final String EXPORT_GRAPHICD2D_MINIMIZE_PRINTER_JOB_SIZE =
		Graphics2DReportConfiguration.MINIMIZE_PRINTER_JOB_SIZE;
	public static final String EXPORT_DEFAULT_FILTER_FACTORY = JRAbstractExporter.PROPERTY_DEFAULT_FILTER_FACTORY;
	
	// text
	public static final String PRINT_KEEP_FULL_TEXT = JRTextElement.PROPERTY_PRINT_KEEP_FULL_TEXT;
	public static final String TEXT_TRUNCATE_AT_CHAR = JRTextElement.PROPERTY_TRUNCATE_AT_CHAR;
	public static final String TEXT_TRUNCATE_SUFFIX = JRTextElement.PROPERTY_TRUNCATE_SUFFIX;
	public static final String TEXT_SAVE_LINE_BREAKS = JRTextElement.PROPERTY_SAVE_LINE_BREAKS;
	public static final String TEXT_MEASURER_FACTORY = JRTextMeasurerUtil.PROPERTY_TEXT_MEASURER_FACTORY;
	public static final String EXPORT_TEXT_CHARACTER_WIDTH = TextReportConfiguration.PROPERTY_CHARACTER_WIDTH;
	public static final String EXPORT_TEXT_CHARACTER_HEIGHT = TextReportConfiguration.PROPERTY_CHARACTER_HEIGHT;
	public static final String EXPORT_TEXT_PAGE_WIDTH = TextReportConfiguration.PROPERTY_PAGE_WIDTH;
	public static final String EXPORT_TEXT_PAGE_HEIGHT = TextReportConfiguration.PROPERTY_PAGE_HEIGHT;
	
	// pdf
	public static final String EXPORT_PDF_TAG_TABLE = JRPdfExporterTagHelper.PROPERTY_TAG_TABLE;
	public static final String EXPORT_PDF_TAG_TR = JRPdfExporterTagHelper.PROPERTY_TAG_TR;
	public static final String EXPORT_PDF_TAG_TH = JRPdfExporterTagHelper.PROPERTY_TAG_TH;
	public static final String EXPORT_PDF_TAG_TD = JRPdfExporterTagHelper.PROPERTY_TAG_TD;
	public static final String EXPORT_PDF_TAG_H1 = JRPdfExporterTagHelper.PROPERTY_TAG_H1;
	public static final String EXPORT_PDF_TAG_H2 = JRPdfExporterTagHelper.PROPERTY_TAG_H2;
	public static final String EXPORT_PDF_TAG_H3 = JRPdfExporterTagHelper.PROPERTY_TAG_H3;
	public static final String EXPORT_PDF_TAG_COLSPAN = JRPdfExporterTagHelper.PROPERTY_TAG_COLSPAN;
	public static final String EXPORT_PDF_TAG_ROWSPAN = JRPdfExporterTagHelper.PROPERTY_TAG_ROWSPAN;
	public static final String EXPORT_PDF_CREATE_BATCH_MODE_BOOKMARKS =
		PdfExporterConfiguration.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS;
	public static final String EXPORT_PDF_COMPRESSED = PdfExporterConfiguration.PROPERTY_COMPRESSED;
	public static final String EXPORT_PDF_ENCRYPTED = PdfExporterConfiguration.PROPERTY_ENCRYPTED;
	public static final String EXPORT_PDF_128_BIT_KEY = PdfExporterConfiguration.PROPERTY_128_BIT_KEY;
	public static final String EXPORT_PDF_USER_PASSWORD = PdfExporterConfiguration.PROPERTY_USER_PASSWORD;
	public static final String EXPORT_PDF_OWNER_PASSWORD = PdfExporterConfiguration.PROPERTY_OWNER_PASSWORD;
	public static final String EXPORT_PDF_VERSION = PdfExporterConfiguration.PROPERTY_PDF_VERSION;
	public static final String EXPORT_PDF_FORCE_SVG_SHAPES = PdfReportConfiguration.PROPERTY_FORCE_SVG_SHAPES;
	public static final String EXPORT_PDF_JAVASCRIPT = PdfExporterConfiguration.PROPERTY_PDF_JAVASCRIPT;
	public static final String EXPORT_PDF_PRINT_SCALING = PdfExporterConfiguration.PROPERTY_PRINT_SCALING;
	public static final String EXPORT_PDF_TAGGED = PdfExporterConfiguration.PROPERTY_TAGGED;
	public static final String EXPORT_PDF_TAG_LANGUAGE = PdfExporterConfiguration.PROPERTY_TAG_LANGUAGE;
	public static final String EXPORT_PDF_COLLAPSE_MISSING_BOOKMARK_LEVELS =
		PdfReportConfiguration.PROPERTY_COLLAPSE_MISSING_BOOKMARK_LEVELS;
	public static final String EXPORT_PDF_PDFA_CONFORMANCE = PdfExporterConfiguration.PROPERTY_PDFA_CONFORMANCE;
	public static final String EXPORT_PDF_PDFA_ICC_PROFILE_PATH =
		PdfExporterConfiguration.PROPERTY_PDFA_ICC_PROFILE_PATH;
	
	// html
	public static final String EXPORT_HTML_CLASS = HtmlExporter.PROPERTY_HTML_CLASS;
	public static final String EXPORT_HTML_ID = HtmlExporter.PROPERTY_HTML_ID;
	public static final String EXPORT_HTML_ACCESSIBLE = HtmlReportConfiguration.PROPERTY_ACCESSIBLE;
	public static final String EXPORT_HTML_REMOVE_EMPTY_SPACE_BETWEEN_ROWS =
		HtmlReportConfiguration.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS;
	public static final String EXPORT_HTML_WHITE_PAGE_BACKGROUND =
		HtmlReportConfiguration.PROPERTY_WHITE_PAGE_BACKGROUND;
	public static final String EXPORT_HTML_WRAP_BREAK_WORD = HtmlReportConfiguration.PROPERTY_WRAP_BREAK_WORD;
	public static final String EXPORT_HTML_SIZE_UNIT = HtmlReportConfiguration.PROPERTY_SIZE_UNIT;
	public static final String EXPORT_HTML_FLUSH_OUTPUT = HtmlExporterConfiguration.PROPERTY_FLUSH_OUTPUT;
	
	// xls
	public static final String EXPORT_XLS_ONE_PAGE_PER_SHEET = XlsReportConfiguration.PROPERTY_ONE_PAGE_PER_SHEET;
	public static final String EXPORT_XLS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS =
		XlsReportConfiguration.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_ROWS;
	public static final String EXPORT_XLS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS =
		XlsReportConfiguration.PROPERTY_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS;
	public static final String EXPORT_XLS_WHITE_PAGE_BACKGROUND =
		XlsReportConfiguration.PROPERTY_WHITE_PAGE_BACKGROUND;
	public static final String EXPORT_XLS_DETECT_CELL_TYPE = XlsReportConfiguration.PROPERTY_DETECT_CELL_TYPE;
	public static final String EXPORT_XLS_SHEET_NAMES_PREFIX = XlsReportConfiguration.PROPERTY_SHEET_NAMES_PREFIX;
	public static final String EXPORT_XLS_FONT_SIZE_FIX_ENABLED =
		XlsReportConfiguration.PROPERTY_FONT_SIZE_FIX_ENABLED;
	public static final String EXPORT_XLS_IMAGE_BORDER_FIX_ENABLED =
		XlsReportConfiguration.PROPERTY_IMAGE_BORDER_FIX_ENABLED;
	public static final String EXPORT_XLS_MAXIMUM_ROWS_PER_SHEET =
		XlsReportConfiguration.PROPERTY_MAXIMUM_ROWS_PER_SHEET;
	public static final String EXPORT_XLS_IGNORE_GRAPHICS = XlsReportConfiguration.PROPERTY_IGNORE_GRAPHICS;
	public static final String EXPORT_XLS_COLLAPSE_ROW_SPAN = XlsReportConfiguration.PROPERTY_COLLAPSE_ROW_SPAN;
	public static final String EXPORT_XLS_IGNORE_CELL_BORDER = XlsReportConfiguration.PROPERTY_IGNORE_CELL_BORDER;
	public static final String EXPORT_XLS_CREATE_CUSTOM_PALETTE =
		XlsExporterConfiguration.PROPERTY_CREATE_CUSTOM_PALETTE;
	public static final String EXPORT_XLS_IGNORE_CELL_BACKGROUND =
		XlsReportConfiguration.PROPERTY_IGNORE_CELL_BACKGROUND;
	public static final String EXPORT_XLS_PASSWORD = XlsReportConfiguration.PROPERTY_PASSWORD;
	public static final String EXPORT_XLS_CELL_FORMULA = JRXlsAbstractExporter.PROPERTY_CELL_FORMULA;
	public static final String EXPORT_XLS_CELL_PATTERN = JRXlsAbstractExporter.PROPERTY_CELL_PATTERN;
	public static final String EXPORT_XLS_WRAP_TEXT = XlsReportConfiguration.PROPERTY_WRAP_TEXT;
	public static final String EXPORT_XLS_FIT_WIDTH = XlsReportConfiguration.PROPERTY_FIT_WIDTH;
	public static final String EXPORT_XLS_FIT_HEIGHT = XlsReportConfiguration.PROPERTY_FIT_HEIGHT;
	public static final String EXPORT_XLS_CELL_LOCKED = XlsReportConfiguration.PROPERTY_CELL_LOCKED;
	public static final String EXPORT_XLS_CELL_HIDDEN = XlsReportConfiguration.PROPERTY_CELL_HIDDEN;
	public static final String EXPORT_XLS_SHEET_HEADER_LEFT = XlsReportConfiguration.PROPERTY_SHEET_HEADER_LEFT;
	public static final String EXPORT_XLS_SHEET_HEADER_CENTER = XlsReportConfiguration.PROPERTY_SHEET_HEADER_CENTER;
	public static final String EXPORT_XLS_SHEET_HEADER_RIGHT = XlsReportConfiguration.PROPERTY_SHEET_HEADER_RIGHT;
	public static final String EXPORT_XLS_SHEET_FOOTER_LEFT = XlsReportConfiguration.PROPERTY_SHEET_FOOTER_LEFT;
	public static final String EXPORT_XLS_SHEET_FOOTER_CENTER = XlsReportConfiguration.PROPERTY_SHEET_FOOTER_CENTER;
	public static final String EXPORT_XLS_SHEET_FOOTER_RIGHT = XlsReportConfiguration.PROPERTY_SHEET_FOOTER_RIGHT;
	public static final String EXPORT_XLS_SHEET_DIRECTION = XlsReportConfiguration.PROPERTY_SHEET_DIRECTION;
	public static final String EXPORT_XLS_FREEZE_ROW = XlsReportConfiguration.PROPERTY_FREEZE_ROW;
	public static final String EXPORT_XLS_FREEZE_COLUMN = XlsReportConfiguration.PROPERTY_FREEZE_COLUMN;
	public static final String EXPORT_XLS_FREEZE_ROW_EDGE = JRXlsAbstractExporter.PROPERTY_FREEZE_ROW_EDGE;
	public static final String EXPORT_XLS_FREEZE_COLUMN_EDGE = JRXlsAbstractExporter.PROPERTY_FREEZE_COLUMN_EDGE;
	public static final String EXPORT_XLS_BREAK_BEFORE_ROW = JRXlsAbstractExporter.PROPERTY_BREAK_BEFORE_ROW;
	public static final String EXPORT_XLS_BREAK_AFTER_ROW = JRXlsAbstractExporter.PROPERTY_BREAK_AFTER_ROW;
	
	// xls metadata
	public static final String EXPORT_XLSMETADATA_COLUMN_NAMES_PREFIX =
		XlsMetadataReportConfiguration.PROPERTY_COLUMN_NAMES_PREFIX;
	public static final String EXPORT_XLSMETADATA_WRITE_HEADER = XlsMetadataReportConfiguration.PROPERTY_WRITE_HEADER;
	public static final String EXPORT_XLSMETADATA_COLUMN_NAME = JRXlsAbstractMetadataExporter.PROPERTY_COLUMN_NAME;
	public static final String EXPORT_XLSMETADATA_REPEAT_VALUE = JRXlsAbstractMetadataExporter.PROPERTY_REPEAT_VALUE;
	public static final String EXPORT_XLSMETADATA_DATA = JRXlsAbstractMetadataExporter.PROPERTY_DATA;
	
	// xml
	public static final String EXPORT_XML_VALIDATION = PrintSaxParserFactory.EXPORT_XML_VALIDATION;
	
	// csv
	public static final String EXPORT_CSV_FIELD_DELIMITER = CsvExporterConfiguration.PROPERTY_FIELD_DELIMITER;
	public static final String EXPORT_CSV_RECORD_DELIMITER = CsvExporterConfiguration.PROPERTY_RECORD_DELIMITER;
	
	// csv metadata
	public static final String EXPORT_CSVMETADATA_COLUMN_NAMES_PREFIX =
		CsvMetadataReportConfiguration.PROPERTY_COLUMN_NAMES_PREFIX;
	public static final String EXPORT_CSVMETADATA_WRITE_HEADER = CsvMetadataReportConfiguration.PROPERTY_WRITE_HEADER;
	public static final String EXPORT_CSVMETADATA_COLUMN_NAME = JRCsvMetadataExporter.PROPERTY_COLUMN_NAME;
	public static final String EXPORT_CSVMETADATA_REPEAT_VALUE = JRCsvMetadataExporter.PROPERTY_REPEAT_VALUE;
	public static final String EXPORT_CSVMETADATA_DATA = JRCsvMetadataExporter.PROPERTY_DATA;
	
	// docx
	public static final String EXPORT_DOCX_HIDDEN_TEXT = JRDocxExporter.PROPERTY_HIDDEN_TEXT;
	public static final String EXPORT_DOCX_FRAMES_AS_NESTED_TABLES =
		DocxReportConfiguration.PROPERTY_FRAMES_AS_NESTED_TABLES;
	public static final String EXPORT_DOCX_FLEXIBLE_ROW_HEIGHT = DocxReportConfiguration.PROPERTY_FLEXIBLE_ROW_HEIGHT;
}
