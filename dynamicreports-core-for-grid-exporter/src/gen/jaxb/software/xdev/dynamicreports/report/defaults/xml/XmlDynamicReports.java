
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DynamicReports complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the font property.
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
     * Sets the value of the font property.
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
     * Gets the value of the bigDecimalType property.
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
     * Sets the value of the bigDecimalType property.
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
     * Gets the value of the bigIntegerType property.
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
     * Sets the value of the bigIntegerType property.
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
     * Gets the value of the byteType property.
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
     * Sets the value of the byteType property.
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
     * Gets the value of the doubleType property.
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
     * Sets the value of the doubleType property.
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
     * Gets the value of the floatType property.
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
     * Sets the value of the floatType property.
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
     * Gets the value of the integerType property.
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
     * Sets the value of the integerType property.
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
     * Gets the value of the longType property.
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
     * Sets the value of the longType property.
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
     * Gets the value of the shortType property.
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
     * Sets the value of the shortType property.
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
     * Gets the value of the dateType property.
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
     * Sets the value of the dateType property.
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
     * Gets the value of the dateYearToMonthType property.
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
     * Sets the value of the dateYearToMonthType property.
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
     * Gets the value of the dateYearToHourType property.
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
     * Sets the value of the dateYearToHourType property.
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
     * Gets the value of the dateYearToMinuteType property.
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
     * Sets the value of the dateYearToMinuteType property.
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
     * Gets the value of the dateYearToSecondType property.
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
     * Sets the value of the dateYearToSecondType property.
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
     * Gets the value of the dateYearToFractionType property.
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
     * Sets the value of the dateYearToFractionType property.
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
     * Gets the value of the dateYearType property.
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
     * Sets the value of the dateYearType property.
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
     * Gets the value of the dateMonthType property.
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
     * Sets the value of the dateMonthType property.
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
     * Gets the value of the dateDayType property.
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
     * Sets the value of the dateDayType property.
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
     * Gets the value of the timeHourToMinuteType property.
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
     * Sets the value of the timeHourToMinuteType property.
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
     * Gets the value of the timeHourToSecondType property.
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
     * Sets the value of the timeHourToSecondType property.
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
     * Gets the value of the timeHourToFractionType property.
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
     * Sets the value of the timeHourToFractionType property.
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
     * Gets the value of the percentageType property.
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
     * Sets the value of the percentageType property.
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
     * Gets the value of the booleanType property.
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
     * Sets the value of the booleanType property.
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
     * Gets the value of the characterType property.
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
     * Sets the value of the characterType property.
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
     * Gets the value of the stringType property.
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
     * Sets the value of the stringType property.
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
     * Gets the value of the loadSystemFonts property.
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
     * Sets the value of the loadSystemFonts property.
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
