package udd.service;

import java.util.List;
import java.util.Optional;

import udd.model.Article;

public interface ArticleService {
	Optional<Article> findOne(Long id);
	List<Article> findAll();
}
