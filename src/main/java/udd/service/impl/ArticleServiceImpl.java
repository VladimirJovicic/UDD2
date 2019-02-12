package udd.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import udd.model.Article;
import udd.repository.ArticleRepository;
import udd.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public Optional<Article> findOne(Long id) {
		return articleRepository.findById(id);
	}

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

}
