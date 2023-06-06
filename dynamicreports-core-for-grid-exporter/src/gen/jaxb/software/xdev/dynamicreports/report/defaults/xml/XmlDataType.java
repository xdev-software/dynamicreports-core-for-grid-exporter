
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the pattern property.
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
     * Sets the value of the pattern property.
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
     * Gets the value of the horizontalAlignment property.
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
     * Sets the value of the horizontalAlignment property.
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
