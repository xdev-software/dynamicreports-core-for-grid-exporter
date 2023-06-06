
package software.xdev.dynamicreports.report.defaults.xml;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the software.xdev.dynamicreports.report.defaults.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DynamicReports_QNAME = new QName("", "DynamicReports");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: software.xdev.dynamicreports.report.defaults.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XmlDynamicReports }
     * 
     * @return
     *     the new instance of {@link XmlDynamicReports }
     */
    public XmlDynamicReports createXmlDynamicReports() {
        return new XmlDynamicReports();
    }

    /**
     * Create an instance of {@link XmlFont }
     * 
     * @return
     *     the new instance of {@link XmlFont }
     */
    public XmlFont createXmlFont() {
        return new XmlFont();
    }

    /**
     * Create an instance of {@link XmlDataType }
     * 
     * @return
     *     the new instance of {@link XmlDataType }
     */
    public XmlDataType createXmlDataType() {
        return new XmlDataType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XmlDynamicReports }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XmlDynamicReports }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "DynamicReports")
    public JAXBElement<XmlDynamicReports> createDynamicReports(XmlDynamicReports value) {
        return new JAXBElement<>(_DynamicReports_QNAME, XmlDynamicReports.class, null, value);
    }

}
