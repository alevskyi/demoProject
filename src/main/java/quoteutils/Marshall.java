package quoteutils;

import java.io.File;
import java.io.IOException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import org.springframework.core.io.ClassPathResource;


class Marshall {
	
	public static void write(){
		try {
			JAXBContext context = JAXBContext.newInstance(Quotes.class);
			Marshaller m = context.createMarshaller();
			File file = new ClassPathResource("quotes.xml").getFile();
			
			Quotes quotes = new Quotes();
			quotes.quote = XmlQuoteResource.quotes;
			m.marshal(quotes, file);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
}
