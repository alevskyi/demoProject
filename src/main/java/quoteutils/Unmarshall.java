package quoteutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

class Unmarshall {
		
	public static HashSet<Quote> read(){
		Quotes quotes = null;
	try {
		JAXBContext context = JAXBContext.newInstance(Quotes.class);
		Unmarshaller u = context.createUnmarshaller();
		Resource resource = new ClassPathResource("quotes.xml");
		InputStream is = resource.getInputStream();

		quotes = (Quotes) u.unmarshal(is);
		is.close();
	}
	catch (JAXBException | IOException e) {
		
	}
	return quotes.quote;
	}
}
