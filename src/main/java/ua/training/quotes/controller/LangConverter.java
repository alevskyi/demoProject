package ua.training.quotes.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.training.quotes.model.Lang;

@Component
public class LangConverter implements Converter<String, Lang> {
    @Override
    public Lang convert(String source) {
        return Lang.fromCode(source);
    }
}
