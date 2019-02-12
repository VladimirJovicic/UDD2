package udd.conf;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
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
		
		long id = 1;
		
		try {
			File folder = new File("src/main/java/udd/elastic");
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
			    if (file.isFile() && file.getName().endsWith(".pdf")){
			    	PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
					parser.parse();
					String text = getText(parser);  


					// metadata extraction
					PDDocument pdf = parser.getPDDocument();
					PDDocumentInformation info = pdf.getDocumentInformation();
					
					System.out.println("--------------------");
					System.out.println(info.getAuthor());
					System.out.println(info.getTitle());
					System.out.println(info.getKeywords());
					System.out.println(info.getSubject());
					//System.out.println(text);
					System.out.println("--------------------");
					
					ArticleDTO dto = new ArticleDTO();
					dto.setId(id);
					dto.setAuthor(info.getAuthor());
					dto.setTitle(info.getTitle());
					dto.setKeywords(info.getKeywords());
					dto.setMagazine(info.getSubject());
					dto.setPdfText(text);
					dto.setAbstractDescription("Abstract description " + dto.getId());
					dto.setScientificArea("Area " + dto.getId());
					if(id % 2 == 0){
						dto.setOpen_access(false);
						dto.setGeo_point(new GeoPoint(50 , 50));
					}else {
						dto.setOpen_access(true);
						dto.setGeo_point(new GeoPoint(50 + id , 50 - id));
					}
					
					elasticsearchTemplate.getClient().prepareIndex("article", "article", dto.getId().toString())
					   .setSource(dto, XContentType.JSON).get();
					articleElasticSearchRepository.save(dto);
					
			    	id++;
			    	pdf.close();
			    }
			}
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		prepareIndexes();
		
	}
	
	public static String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
	
	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser( new RandomAccessFile(file, "r"));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}

}
