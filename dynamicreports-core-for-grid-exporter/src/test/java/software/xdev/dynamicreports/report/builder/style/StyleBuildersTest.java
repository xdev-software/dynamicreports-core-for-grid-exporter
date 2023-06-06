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

package software.xdev.dynamicreports.report.builder.style;

import static software.xdev.dynamicreports.report.constant.FontName.ARIAL;
import static software.xdev.dynamicreports.report.constant.FontName.COURIER_NEW;
import static software.xdev.dynamicreports.report.constant.FontName.TIMES_NEW_ROMAN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for {@link StyleBuilders}.
 */
public class StyleBuildersTest {

  private StyleBuilders cut = new StyleBuilders();

  @Test
  public void shouldCreateFontBuilderWithArial() {
    FontBuilder fontBuilder = cut.fontArial();
    Assertions.assertEquals(ARIAL, fontBuilder.getFont().getFontName());
    Assertions.assertFalse(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithArialBold() {
    FontBuilder fontBuilder = cut.fontArialBold();
    Assertions.assertEquals(ARIAL, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithArialBoldItalic() {
    FontBuilder fontBuilder = cut.fontArialBold().italic();
    Assertions.assertEquals(ARIAL, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertTrue(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithTimesNewRoman() {
    FontBuilder fontBuilder = cut.fontTimesNewRoman();
    Assertions.assertEquals(TIMES_NEW_ROMAN, fontBuilder.getFont().getFontName());
    Assertions.assertFalse(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithTimesNewRomanBold() {
    FontBuilder fontBuilder = cut.fontTimesNewRomanBold();
    Assertions.assertEquals(TIMES_NEW_ROMAN, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithTimesNewRomanBoldItalic() {
    FontBuilder fontBuilder = cut.fontTimesNewRomanBold().italic();
    Assertions.assertEquals(TIMES_NEW_ROMAN, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertTrue(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithCourierNew() {
    FontBuilder fontBuilder = cut.fontCourierNew();
    Assertions.assertEquals(COURIER_NEW, fontBuilder.getFont().getFontName());
    Assertions.assertFalse(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithCourierNewBold() {
    FontBuilder fontBuilder = cut.fontCourierNewBold();
    Assertions.assertEquals(COURIER_NEW, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertFalse(fontBuilder.getFont().getItalic());
  }

  @Test
  public void shouldCreateFontBuilderWithCourierNewBoldItalic() {
    FontBuilder fontBuilder = cut.fontCourierNewBold().italic();
    Assertions.assertEquals(COURIER_NEW, fontBuilder.getFont().getFontName());
    Assertions.assertTrue(fontBuilder.getFont().getBold());
    Assertions.assertTrue(fontBuilder.getFont().getItalic());
  }

}
