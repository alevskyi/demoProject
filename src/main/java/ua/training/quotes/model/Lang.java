package ua.training.quotes.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


@XmlType(name = "Lang")
@XmlEnum
public enum Lang {

    @XmlEnumValue("English")
    ENGLISH("English", "en"),
    
    @XmlEnumValue("Russian")
    RUSSIAN("Russian", "ru");
    
    
    private final String value;
    private final String valueShourtcut; 
    
    Lang(String v, String vs) {
        value = v;
        valueShourtcut = vs;
    }

    public String value() {
        return value;
    }

    public static Lang fromValue(String v) {
        for (Lang c: Lang.values()) 
        {
            if (c.value.equals(v) || c.valueShourtcut.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
