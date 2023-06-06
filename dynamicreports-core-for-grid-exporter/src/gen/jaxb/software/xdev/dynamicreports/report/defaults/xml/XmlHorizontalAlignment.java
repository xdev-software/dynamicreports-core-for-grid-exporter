
package software.xdev.dynamicreports.report.defaults.xml;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HorizontalAlignment.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="HorizontalAlignment">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="LEFT"/>
 *     <enumeration value="CENTER"/>
 *     <enumeration value="RIGHT"/>
 *     <enumeration value="JUSTIFIED"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "HorizontalAlignment")
@XmlEnum
public enum XmlHorizontalAlignment {

    LEFT,
    CENTER,
    RIGHT,
    JUSTIFIED;

    public String value() {
        return name();
    }

    public static XmlHorizontalAlignment fromValue(String v) {
        return valueOf(v);
    }

}
