package web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ua.training.quotes.Application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ActiveProfiles("test")
@SpringBootTest(classes= Application.class)
@AutoConfigureMockMvc

public class HelloTest {
	
	@Autowired
	private MockMvc mvc;
			
	@Test
	public void rootTest() throws Exception{
		mvc.perform(get("/")).andExpect(view().name("hello"));
	}
}