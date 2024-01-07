package ua.training.quotes.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Unmarshaller;
import org.glassfish.jaxb.runtime.v2.JAXBContextFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.training.quotes.Application;
import ua.training.quotes.jaxb.XmlQuote;
import ua.training.quotes.jaxb.XmlQuotes;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class XmlQuoteParserService {

    private Unmarshaller unmarshaller;

    public XmlQuoteParserService() {
        try {
            JAXBContextFactory factory = new JAXBContextFactory();
            JAXBContext context = factory.createContext("ua.training.quotes.jaxb", Application.class.getClassLoader(), Collections.emptyMap());
            this.unmarshaller = context.createUnmarshaller();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Quote> parse(MultipartFile file) {
        try {
            JAXBElement<XmlQuotes> xmlQuotes = (JAXBElement<XmlQuotes>) unmarshaller.unmarshal(file.getInputStream());
            return xmlQuotes.getValue().getXmlQuote().stream().map(this::convert).collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Quote convert(XmlQuote quote) {
        Quote q = new Quote();
        q.setPerson(quote.getPerson());
        q.setText(quote.getText());
        q.setLang(Lang.valueOf(quote.getLang().value()));
        return q;
    }
}
