package udd.elastic.service;

import java.util.List;
import java.util.Map;

import udd.dto.ArticleDTO;

public interface ElasticSearchService {
	List<ArticleDTO> searchByOneField(String field, String value);
	List<ArticleDTO> searchByMultipleFields(Map<String, String> json);
	List<ArticleDTO> searchByMultipleOptionalFields(Map<String, Object> json);
	List<ArticleDTO> searchByMoreLikeThis(Long id);
	List<ArticleDTO> searchByGeoPoint(Long longitude, Long latitude);
}
