package com.it.bootlauch.controller;

import com.it.bootlauch.model.AjaxResponse;
import com.it.bootlauch.model.Article;
import com.it.bootlauch.model.Reader;
import com.it.bootlauch.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("/rest")
public class ArticleRestController{
	@Resource
	ArticleRestService articleRestService;




	//@RequestMapping(value="/article", method= POST, produces="application/json")
	@PostMapping(value="/article")
	public AjaxResponse saveArticle(@RequestBody Article article){
//	public AjaxResponse saveArticle(@RequestParam String id,
//									@RequestParam String author,
//									@RequestParam String title,
//									@RequestParam String content,
//									@RequestParam String createTime){
//		log.info("saveArticle: {}", author);
//		return AjaxResponse.success(author);
		log.info("saveArticle: {}", article);
		articleRestService.saveArticle(article);
		return AjaxResponse.success(article);
	}
	
	//@RequestMapping(value="/article/{id}", method=DELETE, produces="application/json")
	@DeleteMapping(value="/article/{id}", produces="application/json")
	public AjaxResponse deleteArticle(@PathVariable Long id){
		log.info("deleteArticle: {}", id);
		return AjaxResponse.success(id);
	}
	
	//@RequestMapping(value="/article/{id}", method=PUT, produces="application/json")
	@PutMapping(value="/article/{id}")
	public AjaxResponse updateArticle(@PathVariable Long id, @RequestBody
	Article article) {

		article.setId(id);
		log.info("updateArticle: {}", article);
		return AjaxResponse.success(article);
	}
	
//	@RequestMapping(value="/article/{id}", method=GET, produces="application/json")
	@GetMapping(value="/article/{id}", produces="application/json")
	public AjaxResponse getArticle(@PathVariable Long id){
		
		Article article = Article.builder().id(1L).author("test author").content("test content").createTime(new Date()).title("title 1").build();
		
		 log.info("getArticle: {}", article);
		 return AjaxResponse.success(article);
	}
	
	
}