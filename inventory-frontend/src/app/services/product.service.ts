import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {Product} from '../models/product.model';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/products';

  getProducts(page: number, size: number, name?: string, category?: string): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    if (name) params = params.set('name', name);
    if (category) params = params.set('category', category);

    return this.http.get<any>(this.apiUrl, { params }).pipe(
      map(response => response.data)
    );
  }

  createProduct(formData: FormData): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, formData);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  updateProduct(id: number, product: FormData): Observable<Product> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, product).pipe(
      map(response => response.data)
    );
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
