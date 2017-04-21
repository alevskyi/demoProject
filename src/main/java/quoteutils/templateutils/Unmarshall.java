package quoteutils.templateutils;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Unmarshall {

	public static HashSet<XmlQuote> read(InputStream is){
		
		XmlQuotes quotes = null;
		try {
			JAXBContext context = JAXBContext.newInstance(XmlQuotes.class);
			Unmarshaller u = context.createUnmarshaller();
			quotes = (XmlQuotes) u.unmarshal(is);
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<XmlQuotes>> errors = validator.validate(quotes);
			
			if(!errors.isEmpty()) throw new InvalidFileContentException("Uploaded file is invalid");
		} 
		catch (JAXBException e) {
			throw new InvalidFileContentException("Uploaded file is invalid");
		}
		
		return quotes.xmlQuote;
	}
}
