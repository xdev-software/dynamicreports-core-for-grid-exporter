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
package software.xdev.dynamicreports.test.bugs;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.report.builder.component.TextFieldBuilder;


/**
 * @author Ricardo Mariaca
 * <p>
 * The height in setDimension(Integer width, Integer height) of the DimensionComponentBuilder class is set with the
 * width.
 */
class Bug_2997586_Test
{
	
	@Test
	void test()
	{
		final Integer width = 150;
		final Integer height = 200;
		
		TextFieldBuilder<String> textField = cmp.text("").setDimension(width, height);
		Assertions.assertEquals(width, textField.getComponent().getWidth());
		Assertions.assertEquals(height, textField.getComponent().getHeight());
		
		textField = cmp.text("").setFixedDimension(width, height);
		Assertions.assertEquals(width, textField.getComponent().getWidth());
		Assertions.assertEquals(height, textField.getComponent().getHeight());
		
		textField = cmp.text("").setMinDimension(width, height);
		Assertions.assertEquals(width, textField.getComponent().getWidth());
		Assertions.assertEquals(height, textField.getComponent().getHeight());
	}
}
