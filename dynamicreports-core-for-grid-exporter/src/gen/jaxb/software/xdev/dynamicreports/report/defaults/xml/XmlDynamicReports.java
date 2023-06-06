
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für DynamicReports complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>{@code
 * <complexType name="DynamicReports">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="font" type="{}Font" minOccurs="0"/>
 *         <element name="bigDecimalType" type="{}DataType" minOccurs="0"/>
 *         <element name="bigIntegerType" type="{}DataType" minOccurs="0"/>
 *         <element name="byteType" type="{}DataType" minOccurs="0"/>
 *         <element name="doubleType" type="{}DataType" minOccurs="0"/>
 *         <element name="floatType" type="{}DataType" minOccurs="0"/>
 *         <element name="integerType" type="{}DataType" minOccurs="0"/>
 *         <element name="longType" type="{}DataType" minOccurs="0"/>
 *         <element name="shortType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateYearToMonthType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateYearToHourType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateYearToMinuteType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateYearToSecondType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateYearToFractionType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateYearType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateMonthType" type="{}DataType" minOccurs="0"/>
 *         <element name="dateDayType" type="{}DataType" minOccurs="0"/>
 *         <element name="timeHourToMinuteType" type="{}DataType" minOccurs="0"/>
 *         <element name="timeHourToSecondType" type="{}DataType" minOccurs="0"/>
 *         <element name="timeHourToFractionType" type="{}DataType" minOccurs="0"/>
 *         <element name="percentageType" type="{}DataType" minOccurs="0"/>
 *         <element name="booleanType" type="{}DataType" minOccurs="0"/>
 *         <element name="characterType" type="{}DataType" minOccurs="0"/>
 *         <element name="stringType" type="{}DataType" minOccurs="0"/>
 *         <element name="loadSystemFonts" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DynamicReports", propOrder = {
    "font",
    "bigDecimalType",
    "bigIntegerType",
    "byteType",
    "doubleType",
    "floatType",
    "integerType",
    "longType",
    "shortType",
    "dateType",
    "dateYearToMonthType",
    "dateYearToHourType",
    "dateYearToMinuteType",
    "dateYearToSecondType",
    "dateYearToFractionType",
    "dateYearType",
    "dateMonthType",
    "dateDayType",
    "timeHourToMinuteType",
    "timeHourToSecondType",
    "timeHourToFractionType",
    "percentageType",
    "booleanType",
    "characterType",
    "stringType",
    "loadSystemFonts"
})
public class XmlDynamicReports {

    protected XmlFont font;
    protected XmlDataType bigDecimalType;
    protected XmlDataType bigIntegerType;
    protected XmlDataType byteType;
    protected XmlDataType doubleType;
    protected XmlDataType floatType;
    protected XmlDataType integerType;
    protected XmlDataType longType;
    protected XmlDataType shortType;
    protected XmlDataType dateType;
    protected XmlDataType dateYearToMonthType;
    protected XmlDataType dateYearToHourType;
    protected XmlDataType dateYearToMinuteType;
    protected XmlDataType dateYearToSecondType;
    protected XmlDataType dateYearToFractionType;
    protected XmlDataType dateYearType;
    protected XmlDataType dateMonthType;
    protected XmlDataType dateDayType;
    protected XmlDataType timeHourToMinuteType;
    protected XmlDataType timeHourToSecondType;
    protected XmlDataType timeHourToFractionType;
    protected XmlDataType percentageType;
    protected XmlDataType booleanType;
    protected XmlDataType characterType;
    protected XmlDataType stringType;
    protected Boolean loadSystemFonts;

    /**
     * Ruft den Wert der font-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlFont }
     *     
     */
    public XmlFont getFont() {
        return font;
    }

    /**
     * Legt den Wert der font-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlFont }
     *     
     */
    public void setFont(XmlFont value) {
        this.font = value;
    }

    /**
     * Ruft den Wert der bigDecimalType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getBigDecimalType() {
        return bigDecimalType;
    }

    /**
     * Legt den Wert der bigDecimalType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setBigDecimalType(XmlDataType value) {
        this.bigDecimalType = value;
    }

    /**
     * Ruft den Wert der bigIntegerType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getBigIntegerType() {
        return bigIntegerType;
    }

    /**
     * Legt den Wert der bigIntegerType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setBigIntegerType(XmlDataType value) {
        this.bigIntegerType = value;
    }

    /**
     * Ruft den Wert der byteType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getByteType() {
        return byteType;
    }

    /**
     * Legt den Wert der byteType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setByteType(XmlDataType value) {
        this.byteType = value;
    }

    /**
     * Ruft den Wert der doubleType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDoubleType() {
        return doubleType;
    }

    /**
     * Legt den Wert der doubleType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDoubleType(XmlDataType value) {
        this.doubleType = value;
    }

    /**
     * Ruft den Wert der floatType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getFloatType() {
        return floatType;
    }

    /**
     * Legt den Wert der floatType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setFloatType(XmlDataType value) {
        this.floatType = value;
    }

    /**
     * Ruft den Wert der integerType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getIntegerType() {
        return integerType;
    }

    /**
     * Legt den Wert der integerType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setIntegerType(XmlDataType value) {
        this.integerType = value;
    }

    /**
     * Ruft den Wert der longType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getLongType() {
        return longType;
    }

    /**
     * Legt den Wert der longType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setLongType(XmlDataType value) {
        this.longType = value;
    }

    /**
     * Ruft den Wert der shortType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getShortType() {
        return shortType;
    }

    /**
     * Legt den Wert der shortType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setShortType(XmlDataType value) {
        this.shortType = value;
    }

    /**
     * Ruft den Wert der dateType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateType() {
        return dateType;
    }

    /**
     * Legt den Wert der dateType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateType(XmlDataType value) {
        this.dateType = value;
    }

    /**
     * Ruft den Wert der dateYearToMonthType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateYearToMonthType() {
        return dateYearToMonthType;
    }

    /**
     * Legt den Wert der dateYearToMonthType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateYearToMonthType(XmlDataType value) {
        this.dateYearToMonthType = value;
    }

    /**
     * Ruft den Wert der dateYearToHourType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateYearToHourType() {
        return dateYearToHourType;
    }

    /**
     * Legt den Wert der dateYearToHourType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateYearToHourType(XmlDataType value) {
        this.dateYearToHourType = value;
    }

    /**
     * Ruft den Wert der dateYearToMinuteType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateYearToMinuteType() {
        return dateYearToMinuteType;
    }

    /**
     * Legt den Wert der dateYearToMinuteType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateYearToMinuteType(XmlDataType value) {
        this.dateYearToMinuteType = value;
    }

    /**
     * Ruft den Wert der dateYearToSecondType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateYearToSecondType() {
        return dateYearToSecondType;
    }

    /**
     * Legt den Wert der dateYearToSecondType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateYearToSecondType(XmlDataType value) {
        this.dateYearToSecondType = value;
    }

    /**
     * Ruft den Wert der dateYearToFractionType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateYearToFractionType() {
        return dateYearToFractionType;
    }

    /**
     * Legt den Wert der dateYearToFractionType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateYearToFractionType(XmlDataType value) {
        this.dateYearToFractionType = value;
    }

    /**
     * Ruft den Wert der dateYearType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateYearType() {
        return dateYearType;
    }

    /**
     * Legt den Wert der dateYearType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateYearType(XmlDataType value) {
        this.dateYearType = value;
    }

    /**
     * Ruft den Wert der dateMonthType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateMonthType() {
        return dateMonthType;
    }

    /**
     * Legt den Wert der dateMonthType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateMonthType(XmlDataType value) {
        this.dateMonthType = value;
    }

    /**
     * Ruft den Wert der dateDayType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getDateDayType() {
        return dateDayType;
    }

    /**
     * Legt den Wert der dateDayType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setDateDayType(XmlDataType value) {
        this.dateDayType = value;
    }

    /**
     * Ruft den Wert der timeHourToMinuteType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getTimeHourToMinuteType() {
        return timeHourToMinuteType;
    }

    /**
     * Legt den Wert der timeHourToMinuteType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setTimeHourToMinuteType(XmlDataType value) {
        this.timeHourToMinuteType = value;
    }

    /**
     * Ruft den Wert der timeHourToSecondType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getTimeHourToSecondType() {
        return timeHourToSecondType;
    }

    /**
     * Legt den Wert der timeHourToSecondType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setTimeHourToSecondType(XmlDataType value) {
        this.timeHourToSecondType = value;
    }

    /**
     * Ruft den Wert der timeHourToFractionType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getTimeHourToFractionType() {
        return timeHourToFractionType;
    }

    /**
     * Legt den Wert der timeHourToFractionType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setTimeHourToFractionType(XmlDataType value) {
        this.timeHourToFractionType = value;
    }

    /**
     * Ruft den Wert der percentageType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getPercentageType() {
        return percentageType;
    }

    /**
     * Legt den Wert der percentageType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setPercentageType(XmlDataType value) {
        this.percentageType = value;
    }

    /**
     * Ruft den Wert der booleanType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getBooleanType() {
        return booleanType;
    }

    /**
     * Legt den Wert der booleanType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setBooleanType(XmlDataType value) {
        this.booleanType = value;
    }

    /**
     * Ruft den Wert der characterType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getCharacterType() {
        return characterType;
    }

    /**
     * Legt den Wert der characterType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setCharacterType(XmlDataType value) {
        this.characterType = value;
    }

    /**
     * Ruft den Wert der stringType-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XmlDataType }
     *     
     */
    public XmlDataType getStringType() {
        return stringType;
    }

    /**
     * Legt den Wert der stringType-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDataType }
     *     
     */
    public void setStringType(XmlDataType value) {
        this.stringType = value;
    }

    /**
     * Ruft den Wert der loadSystemFonts-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoadSystemFonts() {
        return loadSystemFonts;
    }

    /**
     * Legt den Wert der loadSystemFonts-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoadSystemFonts(Boolean value) {
        this.loadSystemFonts = value;
    }

}
