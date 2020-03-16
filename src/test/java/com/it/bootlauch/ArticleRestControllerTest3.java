package com.it.bootlauch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.bootlauch.controller.ArticleRestController;
import com.it.bootlauch.model.Article;
import com.it.bootlauch.service.ArticleRestJDBCServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@WebMvcTest
@WebMvcTest(ArticleRestController.class)
public class ArticleRestControllerTest3 {
	@Resource
	private MockMvc mockMvc;

	@MockBean
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
		ObjectMapper objectMapper = new ObjectMapper();
		Article articleObj = objectMapper.readValue(article, Article.class);
//		articleRestService.saveArticle(articleObj);

		//打樁(偽造的返回值 因為service是假的 只有controller是真的)
		//when(articleRestJDBCServiceImpl.saveArticle(articleObj)).thenReturn(articleObj);


			
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