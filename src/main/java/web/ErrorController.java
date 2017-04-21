package web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@RequestMapping("/errors")
public class ErrorController {
		//Spring security 403 redirects here.
		//should throw exception
		@RequestMapping(value="/403", method=RequestMethod.GET)
		public void forbidden(){
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
}
