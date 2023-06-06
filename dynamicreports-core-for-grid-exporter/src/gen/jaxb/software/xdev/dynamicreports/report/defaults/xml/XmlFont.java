
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für Font complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
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
     * Ruft den Wert der fontName-Eigenschaft ab.
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
     * Legt den Wert der fontName-Eigenschaft fest.
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
     * Ruft den Wert der fontSize-Eigenschaft ab.
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
     * Legt den Wert der fontSize-Eigenschaft fest.
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
     * Ruft den Wert der pdfFontName-Eigenschaft ab.
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
     * Legt den Wert der pdfFontName-Eigenschaft fest.
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
     * Ruft den Wert der pdfEncoding-Eigenschaft ab.
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
     * Legt den Wert der pdfEncoding-Eigenschaft fest.
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
     * Ruft den Wert der pdfEmbedded-Eigenschaft ab.
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
     * Legt den Wert der pdfEmbedded-Eigenschaft fest.
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
