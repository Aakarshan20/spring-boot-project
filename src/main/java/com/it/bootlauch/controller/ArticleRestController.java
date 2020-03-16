package com.it.bootlauch.controller;

import com.it.bootlauch.model.AjaxResponse;
import com.it.bootlauch.model.Article;
import com.it.bootlauch.service.ArticleRestJDBCServiceImpl;
import com.it.bootlauch.service.ArticleRestService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest")
public class ArticleRestController{
	@Resource(name="articleRestJDBCServiceImpl")
	//@Resource
	ArticleRestService articleRestService;



	@ApiOperation(value="添加文章", notes="添加新的文章", tags="Article", httpMethod="POST")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name="title", value="文章標題", required=true, dataType = "String"),
//			@ApiImplicitParam(name="content", value="文章內容", required=true, dataType = "String"),
//			@ApiImplicitParam(name="author", value="作者", required=true, dataType = "String"),
//			@ApiImplicitParam(name="reader", value="讀者", required=false, dataType = "List<Reader>"),
//	})
	@ApiResponses({
			@ApiResponse(code=200, message="成功", response = AjaxResponse.class),
			@ApiResponse(code=400, message="用戶輸入錯誤", response = AjaxResponse.class),
			@ApiResponse(code=500, message="系統內部錯誤", response = AjaxResponse.class)
	})
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
		//log.info("saveArticle: {}", article);
		//log.info("articleRestService return:" + articleRestJDBCServiceImpl.saveArticle(article));
		articleRestService.saveArticle(article);
		//articleRestService.saveArticle(article);
		return AjaxResponse.success(article);
	}
	
	//@RequestMapping(value="/article/{id}", method=DELETE, produces="application/json")
	@DeleteMapping(value="/article/{id}", produces="application/json")
	public AjaxResponse deleteArticle(@PathVariable Long id){
		log.info("deleteArticle: {}", id);
		articleRestService.deleteArticle(id);
		return AjaxResponse.success(id);
	}
	
	//@RequestMapping(value="/article/{id}", method=PUT, produces="application/json")
	@PutMapping(value="/article/{id}")
	public AjaxResponse updateArticle(@PathVariable Long id, @RequestBody
	Article article) {

		article.setId(id);
		log.info("updateArticle: {}", article);
		articleRestService.updateArticle(article);
		return AjaxResponse.success(article);
	}
	
//	@RequestMapping(value="/article/{id}", method=GET, produces="application/json")
	@GetMapping(value="/article/{id}", produces="application/json")
	public AjaxResponse getArticle(@PathVariable Long id){
		
		//Article article = Article.builder().id(1L).author("test author").content("test content").createTime(new Date()).title("title 1").build();
		Article article = articleRestService.getArticle(id);
		
		 //log.info("getArticle: {}", article);
		 return AjaxResponse.success(article);
	}

	@GetMapping(value="/article", produces="application/json")
	public AjaxResponse getAll(){

		//Article article = Article.builder().id(1L).author("test author").content("test content").createTime(new Date()).title("title 1").build();
		List<Article> articles = articleRestService.getAll();

		//log.info("getArticle: {}", article);
		return AjaxResponse.success(articles);
	}

	
	
}