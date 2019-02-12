package udd.conf;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import udd.dto.ArticleDTO;


@EnableElasticsearchRepositories(basePackages = "udd.repository")
@Configuration
public class ElasticsearchConf {
	
	@Bean
    public Client client() throws Exception {
		Settings esSettings = Settings.builder()
				.put("cluster.name", "jovas_cLuStEr")
				.build();
				
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(esSettings)
		
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
    	ElasticsearchOperations est = new ElasticsearchTemplate(client());
    	est.createIndex(ArticleDTO.class);
        return est;
    }
}
