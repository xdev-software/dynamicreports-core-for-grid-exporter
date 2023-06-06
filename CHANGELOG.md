# 1.0.0

Initial release of the fork

## Differences to [dynamicreports/dynamicreports-core](https://github.com/dynamicreports/dynamicreports/tree/f7d73961462f3f13cbc27c91df90d4cc3ccc669e/dynamicreports-core)
* Changed from ``javax`` to ``jakarta``
  * Added ``jakarta.xml.bind-api``
  * Replaced ``maven-jaxb2-plugin`` with ``jaxb40-maven-plugin``
* Dependency shedding
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
* Update dependencies to lastest versions
* Fixed compile errors
