package quoteutils.orm;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import quoteutils.Lang;

@Converter(autoApply=true)
public class LangConverter implements AttributeConverter<Lang, String>{

	@Override
	public String convertToDatabaseColumn(Lang attribute) {
		return attribute.value();
	}

	@Override
	public Lang convertToEntityAttribute(String dbData) {
		return Lang.fromValue(dbData);
	}

}
