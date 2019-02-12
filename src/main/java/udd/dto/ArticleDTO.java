package udd.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "article", type = "article")
public class ArticleDTO {
	@Id
	private Long id;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String title;

	@Field(type = FieldType.Keyword)
	@JsonProperty
	private String author;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String keywords;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String pdfText;

	@Field(type = FieldType.Keyword)
	@JsonProperty
	private String magazine;

	@Field(type = FieldType.Text)
	@JsonProperty
	private String abstractDescription;

	@Field(type = FieldType.Keyword)
	@JsonProperty
	private String scientificArea;
	
	@GeoPointField
	@JsonProperty
	private GeoPoint geo_point;
	
	public ArticleDTO(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPdfText() {
		return pdfText;
	}

	public void setPdfText(String pdfText) {
		this.pdfText = pdfText;
	}

	public String getMagazine() {
		return magazine;
	}

	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}

	public String getAbstractDescription() {
		return abstractDescription;
	}

	public void setAbstractDescription(String abstractDescription) {
		this.abstractDescription = abstractDescription;
	}

	public String getScientificArea() {
		return scientificArea;
	}

	public void setScientificArea(String scientificArea) {
		this.scientificArea = scientificArea;
	}

	public GeoPoint getGeo_point() {
		return geo_point;
	}

	public void setGeo_point(GeoPoint geo_point) {
		this.geo_point = geo_point;
	}

	public ArticleDTO(Long i, String title, String author, String keywords, String pdfText, String magazine,
			String abstractDescription, String scientificArea, GeoPoint geo_point) {
		super();
		this.id = i;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.pdfText = pdfText;
		this.magazine = magazine;
		this.abstractDescription = abstractDescription;
		this.scientificArea = scientificArea;
		this.geo_point = geo_point;
	}
	
	
	
	
}
