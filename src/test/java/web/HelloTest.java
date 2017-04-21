package web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import web.AppRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AppRunner.class)
@AutoConfigureMockMvc

public class HelloTest {
	
	@Autowired
	private MockMvc mvc;
			
	@Test
	public void rootTest() throws Exception{
		mvc.perform(get("/")).andExpect(view().name("hello"));
	}
}