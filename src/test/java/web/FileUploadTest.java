//package web;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import quoteutils.templateutils.InvalidFileContentException;
//import web.quote.QuoteResource;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//
//@ActiveProfiles("test")
//
//@SpringBootTest(classes=AppRunner.class)
//@AutoConfigureMockMvc
//
//public class FileUploadTest {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@Autowired
//	private QuoteResource resource;
//
//	@WithMockUser("testUser")
//	@Test
//	public void uploadTest() throws Exception {
//
//		MockMultipartFile validQuotes = new MockMultipartFile("file",
//				"Quotes.xml",
//				"multipart/form-data",
//				new ClassPathResource("quotes_template.xml").getInputStream());
//
//		mvc.perform(fileUpload("/quotes/xml")
//				.file(validQuotes)
//				.with(csrf()))
//				.andExpect(redirectedUrl("/users/testUser"));
//
//		verify(resource, times(1)).addQuotes(any(), eq("testUser"));
//	}
//
//
//	@WithMockUser("testUser")
//	@Test
//	public void uploadFailTest() throws Exception {
//
//		MockMultipartFile invalidQuotes = new MockMultipartFile("file",
//				"Quotes.xml",
//				"multipart/form-data",
//				new ClassPathResource("quotes_template_invalid.xml").getInputStream());
//
//		Exception e = mvc.perform(fileUpload("/quotes/xml")
//				.file(invalidQuotes)
//				.with(csrf())).andReturn().getResolvedException();
//
//		assertThat(e).isInstanceOf(InvalidFileContentException.class);
//	}
//
//}

