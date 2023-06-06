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
package software.xdev.dynamicreports.report.builder.tableofcontents;

import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;

/**
 * <p>TableOfContentsCustomizerBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TableOfContentsCustomizerBuilder extends AbstractBuilder<TableOfContentsCustomizerBuilder, TableOfContentsCustomizer> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for TableOfContentsCustomizerBuilder.</p>
     */
    public TableOfContentsCustomizerBuilder() {
        super(new TableOfContentsCustomizer());
    }

    /**
     * <p>getTableOfContents.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer} object.
     */
    public TableOfContentsCustomizer getTableOfContents() {
        return build();
    }

    /**
     * <p>setTitleStyle.</p>
     *
     * @param titleStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setTitleStyle(ReportStyleBuilder titleStyle) {
        this.getObject().setTitleStyle(titleStyle);
        return this;
    }

    /**
     * <p>setHeadingStyle.</p>
     *
     * @param headingStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setHeadingStyle(ReportStyleBuilder headingStyle) {
        this.getObject().setHeadingStyle(headingStyle);
        return this;
    }

    /**
     * <p>setHeadingStyle.</p>
     *
     * @param level        a int.
     * @param headingStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setHeadingStyle(int level, ReportStyleBuilder headingStyle) {
        this.getObject().setHeadingStyle(level, headingStyle);
        return this;
    }

    /**
     * <p>setTextFixedWidth.</p>
     *
     * @param textFixedWidth a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setTextFixedWidth(Integer textFixedWidth) {
        this.getObject().setTextFixedWidth(textFixedWidth);
        return this;
    }

    /**
     * <p>setDotsFixedWidth.</p>
     *
     * @param dotsFixedWidth a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setDotsFixedWidth(Integer dotsFixedWidth) {
        this.getObject().setDotsFixedWidth(dotsFixedWidth);
        return this;
    }

    /**
     * <p>setPageIndexFixedWidth.</p>
     *
     * @param pageIndexFixedWidth a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setPageIndexFixedWidth(Integer pageIndexFixedWidth) {
        this.getObject().setPageIndexFixedWidth(pageIndexFixedWidth);
        return this;
    }

    /**
     * <p>setPosition.</p>
     *
     * @param position a {@link software.xdev.dynamicreports.report.constant.TableOfContentsPosition} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder} object.
     */
    public TableOfContentsCustomizerBuilder setPosition(TableOfContentsPosition position) {
        this.getObject().setPosition(position);
        return this;
    }
}
