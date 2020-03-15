package com.it.bootlauch;

import com.it.bootlauch.controller.ArticleRestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
public class ArticleRestControllerTest{
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
	}
	
	@Test
	public void saveArticle() throws Exception{
		String article = "{\n" + 
			"\"id\":1,\n" + 
			"\"author\":\"authro abc\",\n" + 
			"\"title\":\"title abc\",\n" + 
			"\"content\":\"content abc\",\n" + 
			"\"createTime\":\"2020-03-15 13:44:20\",\n" + 
			"\"reader\":[{\"name\":\"reader1\", \"age\":15}], {\"name\":\"reader2\", \"age\":20},\n" ;
			
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/article")
				.contentType("application/json").content(article))
					.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("abc"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(18))
				.andDo(print())
				.andReturn();
			
			log.info(result.getResponse().getContentAsString());
	}
}