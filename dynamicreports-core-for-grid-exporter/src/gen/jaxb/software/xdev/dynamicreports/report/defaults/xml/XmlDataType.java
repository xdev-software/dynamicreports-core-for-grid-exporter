
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DataType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>{@code
 * <complexType name="DataType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="horizontalAlignment" type="{}HorizontalAlignment" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataType")
public class XmlDataType {

    @XmlAttribute(name = "pattern")
    protected String pattern;
    @XmlAttribute(name = "horizontalAlignment")
    protected XmlHorizontalAlignment horizontalAlignment;

    /**
     * Ruft den Wert der pattern-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Legt den Wert der pattern-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPattern(String value) {
        this.pattern = value;
    }

    /**
     * Ruft den Wert der horizontalAlignment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlHorizontalAlignment }
     *     
     */
    public XmlHorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    /**
     * Legt den Wert der horizontalAlignment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlHorizontalAlignment }
     *     
     */
    public void setHorizontalAlignment(XmlHorizontalAlignment value) {
        this.horizontalAlignment = value;
    }

}
