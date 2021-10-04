import { Injectable } from '@angular/core';
import {Product} from "../model/product";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Page} from "../model/page";
import {Observable} from "rxjs";
import {ProductFilterDto} from "../model/product-filter";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private products: Product[] = [];
  constructor(private http: HttpClient) { }
  public findAll(productFilter?: ProductFilterDto, page? : number) : Observable<Page> {
    let params = new HttpParams();
    if (productFilter?.namePattern != null) {
      params = params.set('namePattern', productFilter?.namePattern);
    }
    params = params.set("page", page != null ? page : 1);
    params = params.set("size", 6);
    return this.http.get<Page>('/api/v1/product/all', { params: params });
  }
}
