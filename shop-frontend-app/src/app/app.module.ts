import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { FooterComponent } from './components/footer/footer.component';
import {

  ProductGalleryPageComponent
} from './pages/product-gallery-page/product-gallery-page.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { CartPageComponent } from './pages/cart-page/cart-page.component';
import { ProductFilterComponent } from './components/product-filter/product-filter.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import {ProductGalleryComponent} from "./components/product-gallery/product-gallery.component";
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { OrderPageComponent } from './pages/order-page/order-page.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import {UnauthorizedInterceptor} from "./helpers/unauthorized-interceptor";

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    FooterComponent,
    ProductGalleryComponent,
    ProductGalleryPageComponent,
    CartPageComponent,
    ProductFilterComponent,
    CartItemComponent,
    PaginationComponent,
    LoginPageComponent,
    OrderPageComponent,
    RegisterPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({cookieName: 'XSRF-TOKEN'})
  ],
  providers: [   { provide: HTTP_INTERCEPTORS, useClass: UnauthorizedInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
