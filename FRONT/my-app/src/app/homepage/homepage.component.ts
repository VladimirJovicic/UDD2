import { Component, OnInit } from '@angular/core';
import { SharedService } from '../service/shared.service';
@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  private articles: any = [];
  private field: string = "magazine";
  private value: string = "";

  private boolMap = {};
  private valueMap = {};
  private json: any = {};

  private boolMap2 = {};
  private valueMap2 = {};
  private json2: any = {};

  private optional = [];
  private phrases = [];

  private moreLikeThisArticles = [];
  private geoPointArticles = [];
  constructor(private service: SharedService) { }

  ngOnInit() {
    this.boolMap["magazine"] = false;
    this.boolMap["title"] = false;
    this.boolMap["author"] = false;
    this.boolMap["keywords"] = false;
    this.boolMap["pdfText"] = false;
    this.boolMap["scientificArea"] = false;

    this.valueMap["magazine"] = "";
    this.valueMap["title"] = "";
    this.valueMap["author"] = "";
    this.valueMap["keywords"] = "";
    this.valueMap["pdfText"] = "";
    this.valueMap["scientificArea"] = "";

    this.boolMap2["magazine"] = false;
    this.boolMap2["title"] = false;
    this.boolMap2["author"] = false;
    this.boolMap2["keywords"] = false;
    this.boolMap2["pdfText"] = false;
    this.boolMap2["scientificArea"] = false;

    this.valueMap2["magazine"] = "";
    this.valueMap2["title"] = "";
    this.valueMap2["author"] = "";
    this.valueMap2["keywords"] = "";
    this.valueMap2["pdfText"] = "";
    this.valueMap2["scientificArea"] = "";

  }

  setField(filed: string) {
    this.field = filed;
  }

  searchByOptionalField() {
    console.log(this.field, this.value);
    this.service.searchByOptionalField(this.field.trim(), this.value.trim()).subscribe(
      (data: any) => {
        this.articles = data;
      }, (err: any) => {
        console.log(err);
      }
    )
  }

  searchByMultipleFields() {
    Object.keys(this.boolMap).map(field => {
      if (this.boolMap[field]) {
        this.json[field] = this.valueMap[field]
      }
    })
    this.service.searchByMultipleFields(this.json).subscribe(
      (data: any) => {
        this.articles = data;
      }, (err: any) => {
        console.log(err)
      }
    )

    this.boolMap = {};
    this.valueMap = {};
    this.json = {};

    // console.log(this.json)
  }

  addRemoveOptional(type: string) {
    if (this.optional.indexOf(type) === -1) {
      this.optional.push(type)
    } else {
      this.optional.splice(this.optional.indexOf(type), 1)
    }
  }

  addRemovePhrases(type: string) {
    if (this.phrases.indexOf(type) === -1) {
      this.phrases.push(type)
    } else {
      this.phrases.splice(this.phrases.indexOf(type), 1)
    }
  }


  multipleOptionalFields() {
    Object.keys(this.boolMap2).map(field => {
      if (this.boolMap2[field]) {
        this.json2[field] = this.valueMap2[field]
      }
    })

    if (this.phrases.length > 0) {
      this.json2["phrase"] = this.phrases;
    } else {
      this.json2["phrase"] = null;
    }

    if (this.optional.length > 0) {
      this.json2["optional"] = this.optional;
    } else {
      this.json2["optional"] = null;
    }



    this.service.searchByMultipleOptionalFields(this.json2).subscribe(
      (data: any) => {
        this.articles = data;

        this.boolMap2 = {};
        this.valueMap2 = {};
        this.json2 = {};
        this.phrases = [];
        this.optional = [];
        for (let i = 0; i < document.getElementsByTagName("input").length; i++) {
          if (document.getElementsByTagName("input")[i].type === "checkbox") {
            document.getElementsByTagName("input")[i].checked = false;
          }
        }
      }, (err: any) => {
        console.log(err)
      }
    )

    this.boolMap2 = {};
    this.valueMap2 = {};
    this.json2 = {};
    this.phrases = [];
    this.optional = [];
    for (let i = 0; i < document.getElementsByTagName("input").length; i++) {
      if (document.getElementsByTagName("input")[i].type === "checkbox") {
        document.getElementsByTagName("input")[i].checked = false;
      }
    }
  }


  moreLikeThis(id: any) {
    this.service.searchMoreLikeThis(id).subscribe(
      (data: any) => {
        this.moreLikeThisArticles = data;
      }
    )
  }

  geoPointSearch(longitude: Number, latitude: Number) {
    this.service.searchGeoPoint(longitude, latitude).subscribe(
      (data: any) => {
        this.geoPointArticles = data;
      }
    )
  }

  closeMoreLikeThis() {
    this.moreLikeThisArticles = [];
  }

  closeGeoPointArticles() {
    this.geoPointArticles = [];
  }

  closeArticles() {
    this.articles = [];
    this.moreLikeThisArticles = [];
    this.geoPointArticles = [];
  }

  addToCart() {
    alert("Add to cart...")
  }

  download(title: string) {
    this.service.downloadPdf(title);
  }

}
