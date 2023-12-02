package ua.training.quotes.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@XmlType(name = "Lang")
@XmlEnum
@RequiredArgsConstructor
@Getter
public enum Lang {

    @XmlEnumValue("en")
    EN("en"),
    
    @XmlEnumValue("ru")
    RU("ru");

    private final String code;

    public static Lang fromCode(String code) {
        switch (code) {
            case "en":
                return EN;
            case "ru":
                return RU;
            default:
                throw new IllegalArgumentException();
        }
    }
}
