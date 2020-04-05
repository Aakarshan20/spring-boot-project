package com.it.bootlauch.controller;

import com.it.bootlauch.model.AjaxResponse;
import com.it.bootlauch.model.ArticleVO;
import com.it.bootlauch.response.BaseResponse;
import com.it.bootlauch.service.ArticleJPARestService;
import com.it.bootlauch.service.ArticleRestJDBCServiceImpl;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest")
@BaseResponse
public class ArticleRestController{
	@Resource(name="articleJPARestService")
	//@Resource
	ArticleJPARestService articleRestService;



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
	public ArticleVO saveArticle(@RequestBody ArticleVO articleVO){
//	public AjaxResponse saveArticle(@RequestParam String id,
//									@RequestParam String author,
//									@RequestParam String title,
//									@RequestParam String content,
//									@RequestParam String createTime){
//		log.info("saveArticle: {}", author);
//		return AjaxResponse.success(author);
		//log.info("saveArticle: {}", article);
		//log.info("articleRestService return:" + articleRestJDBCServiceImpl.saveArticle(article));
		articleRestService.saveArticle(articleVO);
		//articleRestService.saveArticle(article);
		//return AjaxResponse.success(article);
		return articleVO;
	}
	
	//@RequestMapping(value="/article/{id}", method=DELETE, produces="application/json")
	@DeleteMapping(value="/article/{id}", produces="application/json")
	public ArticleVO deleteArticle(@PathVariable Long id){
		log.info("deleteArticle: {}", id);
		ArticleVO articleVO = articleRestService.getArticle(id);

		articleRestService.deleteArticle(id);
		return articleVO;
	}
	
	//@RequestMapping(value="/article/{id}", method=PUT, produces="application/json")
	@PutMapping(value="/article/{id}")
	public ArticleVO updateArticle(@PathVariable Long id, @RequestBody
			ArticleVO articleVO) {

		articleVO.setId(id);
		log.info("updateArticle: {}", articleVO);
		articleRestService.updateArticle(articleVO);
		//return AjaxResponse.success(article);
		return articleVO;
	}
	
//	@RequestMapping(value="/article/{id}", method=GET, produces="application/json")
	@GetMapping(value="/article/{id}", produces="application/json")
	public ArticleVO getArticle(@PathVariable Long id){
		
		//Article article = Article.builder().id(1L).author("test author").content("test content").createTime(new Date()).title("title 1").build();
		ArticleVO articleVO = articleRestService.getArticle(id);
		System.out.println(articleVO);
		
		 //log.info("getArticle: {}", article);
		// return AjaxResponse.success(articleVO);
		return articleVO;
	}

	@GetMapping(value="/article", produces="application/json")
	public List<ArticleVO> getAll(){

		//Article article = Article.builder().id(1L).author("test author").content("test content").createTime(new Date()).title("title 1").build();
		List<ArticleVO> articleVOS = articleRestService.getAll();
		//log.info("getArticle: {}", article);
		//return AjaxResponse.success(articles);
		return articleVOS;
	}

	
	
}