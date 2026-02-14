import { Routes } from '@angular/router';
import {ProductListComponent} from './components/product-list/product-list.component';
import {ProductDetailsComponent} from './components/product-details/product-details.component';
import {ProductFormComponent} from './components/product-form/product-form.component';

export const routes: Routes = [
  { path: '', component: ProductListComponent },
  { path: 'products/new', component: ProductFormComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'products/edit/:id', component: ProductFormComponent }
];
