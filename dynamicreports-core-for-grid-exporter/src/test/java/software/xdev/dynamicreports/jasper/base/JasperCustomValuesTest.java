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
package software.xdev.dynamicreports.jasper.base;

import static software.xdev.dynamicreports.jasper.base.JasperScriptletManager.USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY;

import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link JasperCustomValues}.
 */
public class JasperCustomValuesTest {

  private JasperScriptlet scriptlet = new JasperScriptlet();
  
  @Test
  public void shouldUseDefaultScriptletManager() {
    JasperCustomValues cut = createClassUnderTest(false);
    Assertions.assertTrue(cut.getScriptletManager() instanceof DefaultJasperScriptletManager);
  }
  
  @Test
  public void shouldUseThreadSafeScriptleManagerIfPropertySet() {
    JasperCustomValues cut = createClassUnderTest(true);
    Assertions.assertTrue(cut.getScriptletManager() instanceof ThreadSafeJasperScriptletManager);
  }

  @Test
  public void shouldSetScriptletWithDefaultManager() {
    JasperCustomValues cut = createClassUnderTest(false);
    cut.setJasperScriptlet(scriptlet);
    Assertions.assertEquals(scriptlet, cut.getJasperScriptlet());
  }
  
  @Test
  public void shouldSetScriptletWithThreadSafeManager() {
    JasperCustomValues cut = createClassUnderTest(true);
    cut.setJasperScriptlet(scriptlet);
    Assertions.assertEquals(scriptlet, cut.getJasperScriptlet());    
  }
  
  private JasperCustomValues createClassUnderTest(boolean useThreadSafeManager) {
    Properties properties = new Properties();
    if (useThreadSafeManager) {
      properties.setProperty(USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY, "true");
    }
    return new JasperCustomValues(properties);
  }
}
