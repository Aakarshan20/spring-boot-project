package com.it.bootlauch;

import com.it.bootlauch.service.ArticleRestJDBCServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ArticleRestControllerTest2 {
	@Resource
	private MockMvc mockMvc;

	@Resource
	ArticleRestJDBCServiceImpl articleRestJDBCServiceImpl;

	//已使用AutoConfigureMockMvc取代
//	@Before
//	public void setUp(){
//		mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
//	}
	
	@Test
	public void saveArticle() throws Exception{
		String article = "{\n" + 
			"\"id\":1,\n" + 
			"\"auther\":\"abc\",\n" +
			"\"title\":\"title abc\",\n" + 
			"\"content\":\"content abc\",\n" + 
			//"\"createTime\":\"2020-03-15 13:44:20\",\n" +
			"\"reader\":[{\"name\":\"reader1\", \"age\":15}, {\"name\":\"reader2\", \"age\":20}]}\n" ;
			
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/rest/article")
				.contentType("application/json").content(article))
					.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.auther").value("abc"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(15))
				.andDo(print())
				.andReturn();

			log.info(result.getResponse().getContentAsString());
	}
}