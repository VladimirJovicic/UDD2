package udd.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import udd.elastic.service.ElasticSearchService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value="/search")

public class SearchController {
	
	@Autowired
	private ElasticSearchService elasticSearchService;
	
	@GetMapping
	@RequestMapping(value="{searchField}/{searchValue}")
	public ResponseEntity<?> searchByOneField(@PathVariable("searchField") String searchField, @PathVariable("searchValue") String searchValue) {
		return new ResponseEntity<>(elasticSearchService.searchByOneField(searchField, searchValue), HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping(value = "multipleFields")
	public ResponseEntity<?> searchByMultipleFields(@RequestBody Map<String, String> json) {
		return new ResponseEntity<>(elasticSearchService.searchByMultipleFields(json), HttpStatus.OK);
	}
	
	@PostMapping
	@RequestMapping(value = "multipleOptionalFields")
	public ResponseEntity<?> searchByMultipleOptionalFields(@RequestBody Map<String, Object> json) {
		return new ResponseEntity<>(elasticSearchService.searchByMultipleOptionalFields(json), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping(value = "moreLikeThis/{id}")
	public ResponseEntity<?> searchByMoreLikeThis(@PathVariable Long id) {
		return new ResponseEntity<>(elasticSearchService.searchByMoreLikeThis(id), HttpStatus.OK);
	}

	@GetMapping
	@RequestMapping(value = "geoPoint/{longitude}/{latitude}")
	public ResponseEntity<?> searchByGeoPoint(@PathVariable("longitude") Long longitude, @PathVariable("latitude") Long latitude) {
		return new ResponseEntity<>(elasticSearchService.searchByGeoPoint(longitude, latitude), HttpStatus.OK);
	}

}
