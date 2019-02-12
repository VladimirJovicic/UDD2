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

  searchByMultipleOptionalFields() {
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



}
