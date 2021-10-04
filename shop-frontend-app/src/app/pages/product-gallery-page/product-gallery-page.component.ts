import { Component, OnInit } from '@angular/core';
import {Product} from "../../model/product";
import {ProductService} from "../../services/product.service";
import {Page} from "../../model/page";
import {ProductFilterDto} from "../../model/product-filter";
export const PRODUCT_GALLERY_URL = 'product';
@Component({
  selector: 'app-product-gallery-page',
  templateUrl: './product-gallery-page.component.html',
  styleUrls: ['./product-gallery-page.component.scss']
})
export class ProductGalleryPageComponent implements OnInit {
  products: Product[] = [];
  page?: Page;

  productFilter?: ProductFilterDto;

  pageNumber: number = 1;
  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {   this.productService.findAll()
    .subscribe(
      res => {
        console.log("Loading products");
        this.page = res;
        this.products = res.content;
      },
      err => {
        console.log(`Can't load products ${err}`);
      });
  }
  filterApplied($event: ProductFilterDto) {
    console.log($event);
    this.productFilter = $event;
    this.productService.findAll($event, this.pageNumber)
      .subscribe(
        res => {
          this.page = res;
          this.products = res.content;
          this.pageNumber = 1;
        },
        err => {
          console.log(`Can't load products ${err}`);
        });
  }

  goToPage($event: number) {
    this.productService.findAll(this.productFilter, $event)
      .subscribe(
        res => {
          this.page = res;
          this.products = res.content;
          this.pageNumber = res.number + 1;
        },
        err => {
          console.log(`Can't load products ${err}`);
        });
  }
}
  // addToCart(id: number) {
  //   this.cartService.addToCart(new AddLineItemDto(id, 1))
  //     .subscribe();
  // }

