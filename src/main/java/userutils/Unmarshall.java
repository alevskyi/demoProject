package userutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

class Unmarshall {

	protected static HashSet<User> read(){
		Users users = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Users.class);
			Unmarshaller u = context.createUnmarshaller();
			Resource resource = new ClassPathResource("users.xml");
			InputStream is = resource.getInputStream();
			
			users = (Users) u.unmarshal(is);
			is.close();
		} 
		catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		return users.user;
	}
	
}
