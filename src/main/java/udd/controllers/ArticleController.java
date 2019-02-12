package udd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import udd.model.Article;
import udd.service.ArticleService;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	private ResponseEntity<List<Article>> getArticleById(@PathVariable String id) {
		List<Article> articles = articleService.findAll();
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}


}
