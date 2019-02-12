package udd.elastic.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import udd.dto.ArticleDTO;


@Repository
public interface ArticleElasticSearchRepository extends ElasticsearchRepository<ArticleDTO, Long>{
	
}

