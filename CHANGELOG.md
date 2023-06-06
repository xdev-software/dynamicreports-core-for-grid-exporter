# 1.0.0

Initial release of the fork

## Differences to [dynamicreports/dynamicreports-core](https://github.com/dynamicreports/dynamicreports/tree/f7d73961462f3f13cbc27c91df90d4cc3ccc669e/dynamicreports-core)
* Renamed ``net.sf.dynamicreports`` to ``software.xdev.dynamicreports`` to prevent conflicts with existing installations
* Dependency shedding
  * Removed default-XML subsystem; can be supplied manually to the builder or using Java Service Loading
    * ``jakarta.xml.bind:jakarta.xml.bind-api``
    * ``org.glassfish.jaxb:jaxb-runtime``
  * Removed barcode subsystem
    * Barbecue
    * ``net.sf.barcode4j:barcode4j``
    * ``com.google.zxing:core``
  * Removed charts subsystem
  * Removed boolean to image subsystem
    * ``org.apache.xmlgraphics:batik-bridge``
  * Dropped support for ``XLS`` as the required dependency was never shipped
    * ``XLSX`` still works as expected
  * Removed ``org.apache.commons:commons-text`` as it was only used on a single unused line of code
* Removed XML subsystem as XML exports were never producing valid XML
* Removed half implemented Image subsystem as it is unused
* Update dependencies to lastest versions
* Fixed compile errors
