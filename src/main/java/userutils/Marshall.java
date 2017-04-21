package userutils;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.core.io.ClassPathResource;

class Marshall {
	
	protected static void write(){
		try {
			JAXBContext context = JAXBContext.newInstance(Users.class);
			Marshaller m = context.createMarshaller();
			File file = new ClassPathResource("users.xml").getFile();
			
			Users users = new Users();
			users.user = UserData.users;
			m.marshal(users, file);
		} 
		catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
}
