# 1.1.1
* ⚠️ GroupId changed from ``com.xdev-software`` to ``software.xdev``
* Updated dependencies

# 1.1.0
* Updated to Jasperreports ``6.20.6``
  * API changes: Html - ``borderCollapse`` is now an enum

# 1.0.3
* Re-Added ``ecj`` dependency #12

# 1.0.2
* Removed unused Jasperreports dependencies

# 1.0.1
* Removed unused resources

# 1.0.0

Initial release of the fork

## Differences to [dynamicreports/dynamicreports-core](https://github.com/dynamicreports/dynamicreports/tree/f7d73961462f3f13cbc27c91df90d4cc3ccc669e/dynamicreports-core)
* Renamed ``net.sf.dynamicreports`` to ``software.xdev.dynamicreports`` to prevent conflicts with existing installations
* Dependency shedding
  * Removed [``Defaults``](./src/main/java/software/xdev/dynamicreports/report/defaults/Defaults.java)-XML subsystem; can be supplied manually to the builder or using [Java Service Loading](https://docs.oracle.com/javase/8/docs/api/java/util/ServiceLoader.html)
    * ``jakarta.xml.bind:jakarta.xml.bind-api``
    * ``org.glassfish.jaxb:jaxb-runtime``
  * Removed barcode subsystem
    * ``net.sf.barcode4j:barcode4j``
    * ``com.google.zxing:core``
    * ``net.sourceforge.barbecue:barbecue``
  * Removed charts subsystem
  * Removed boolean to image subsystem
    * ``org.apache.xmlgraphics:batik-bridge``
  * Dropped support for ``XLS`` as the required dependency was never shipped
    * ``XLSX`` still works as expected
  * Removed ``org.apache.commons:commons-text`` as it was only used on a single unused line of code
* Removed XML subsystem as XML exports were never producing valid XML
* Removed half-way implemented Image subsystem as it is unused
* Updated dependencies to lastest versions
* Fixed compile errors
