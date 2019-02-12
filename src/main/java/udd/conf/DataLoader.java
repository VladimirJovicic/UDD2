package udd.conf;

import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import udd.dto.ArticleDTO;
import udd.elastic.repo.ArticleElasticSearchRepository;

@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Autowired
	private ArticleElasticSearchRepository articleElasticSearchRepository;
	
	public void prepareIndexes() {
		
		ArticleDTO article1 = new ArticleDTO(Long.valueOf(1),"apstraktni opis",
											"pera","keyword1 keyword2","casopis","neki pdf tekst",
											"biologija","pronalazenje biologije",
											new GeoPoint(45.254282, 19.819469));
		article1.setPdfText("neki pdf tekts");
		article1.setMagazine("magazine1111");
		
		ArticleDTO article2 = new ArticleDTO(Long.valueOf(2),"apstraktni opis2",
				"mika","keyword1 keyword2","casopis","neki pdf tekst",
				"biologija","pronalazenje biologije",
				new GeoPoint(45, 19));
		article2.setPdfText("neki pdf tekts za uporedjivanje nekih samo tako random stvari i ove reci svakako pisem tako random samo ");
		article2.setMagazine("magazine2222");
		elasticsearchTemplate.getClient().prepareIndex("article", "article", "1")
		   .setSource(article1, XContentType.JSON)
		   .get();
		
		elasticsearchTemplate.getClient().prepareIndex("article", "article", "2")
		   .setSource(article2, XContentType.JSON)
		   .get();
	
		
		
		articleElasticSearchRepository.save(article1);
		articleElasticSearchRepository.save(article2);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		prepareIndexes();
		
	}

}
