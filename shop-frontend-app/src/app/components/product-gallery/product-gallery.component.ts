import {Component, Input, OnInit} from '@angular/core';
import {AddLineItemDto} from "../../model/add-line-item-dto";
import {Product} from "../../model/product";
import {CartService} from "../../services/cart.service";
import {ProductService} from "../../services/product.service";

@Component({
  selector: 'app-product-gallery',
  templateUrl: './product-gallery.component.html',
  styleUrls: ['./product-gallery.component.scss']
})
export class ProductGalleryComponent implements OnInit {
  @Input() products: Product[] = [];
  isError: boolean = false;
  constructor(private cartService: CartService,private productService: ProductService) {
   }

  ngOnInit(): void {
  }

  addToCart(id: number) {
    this.cartService.addToCart(new AddLineItemDto(id, 1))
      .subscribe();
  }
}


// products: Product[] = [];
// isError: boolean = false;
// constructor(private cartService: CartService,private productService: ProductService) {
// }
//
// ngOnInit(): void { this.retrieveProducts();
// }
// private retrieveProducts() {
//   this.productService.findAll()
//     .then(res => {
//       this.products = res.content;
//     })
//     .catch(err => {
//       console.error(err);
//       this.isError = true;
//     })
// }
// addToCart(id: number) {
//   this.cartService.addToCart(new AddLineItemDto(id, 1))
//     .subscribe();
// }
// }
