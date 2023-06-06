
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Font complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="Font">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="fontName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="fontSize" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       <attribute name="pdfFontName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="pdfEncoding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="pdfEmbedded" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Font")
public class XmlFont {

    @XmlAttribute(name = "fontName")
    protected String fontName;
    @XmlAttribute(name = "fontSize")
    protected Integer fontSize;
    @XmlAttribute(name = "pdfFontName")
    protected String pdfFontName;
    @XmlAttribute(name = "pdfEncoding")
    protected String pdfEncoding;
    @XmlAttribute(name = "pdfEmbedded")
    protected Boolean pdfEmbedded;

    /**
     * Gets the value of the fontName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFontName() {
        return fontName;
    }

    /**
     * Sets the value of the fontName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFontName(String value) {
        this.fontName = value;
    }

    /**
     * Gets the value of the fontSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * Sets the value of the fontSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFontSize(Integer value) {
        this.fontSize = value;
    }

    /**
     * Gets the value of the pdfFontName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfFontName() {
        return pdfFontName;
    }

    /**
     * Sets the value of the pdfFontName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfFontName(String value) {
        this.pdfFontName = value;
    }

    /**
     * Gets the value of the pdfEncoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdfEncoding() {
        return pdfEncoding;
    }

    /**
     * Sets the value of the pdfEncoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdfEncoding(String value) {
        this.pdfEncoding = value;
    }

    /**
     * Gets the value of the pdfEmbedded property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPdfEmbedded() {
        return pdfEmbedded;
    }

    /**
     * Sets the value of the pdfEmbedded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPdfEmbedded(Boolean value) {
        this.pdfEmbedded = value;
    }

}
