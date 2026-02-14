import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service';
import {Category, Product} from '../../models/product.model';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: 'product-list.component.html',
  styleUrl: 'product-list.component.css'
})
export class ProductListComponent implements OnInit {
  private productService = inject(ProductService);
  private router = inject(Router);

  products = signal<Product[]>([]);
  searchTerm = '';
  currentPage = 0;
  totalElements = 0;
  totalPages = 0;
  selectedCategory = '';
  categories = Object.values(Category);
  productIdToDelete: number | null = null;
  message = signal<{type: string, text: string} | null>(null);

  ngOnInit() { this.load(); }

  load() {
    this.productService.getProducts(this.currentPage, 5, this.searchTerm, this.selectedCategory)
      .subscribe(data => {
        this.products.set(data.content);
        this.totalElements = data.page.totalElements;
        this.totalPages = data.page.totalPages;
      });
  }

  reset() {
    this.searchTerm = '';
    this.currentPage = 0;
    this.selectedCategory = '';
    this.load();
  }

  goToPage(pageIndex: number) {
    this.currentPage = pageIndex;
    this.load();
  }

  prepareToDelete(id: number) {
    this.productIdToDelete = id;
  }

  confirmDelete() {
    if (this.productIdToDelete) {
      this.productService.deleteProduct(this.productIdToDelete).subscribe({
        next: () => {
          this.message.set({ type: 'success', text: 'Product deleted successfully!' });
          this.load();
          setTimeout(() => this.message.set(null), 3000);
        },
        error: () => {
          this.message.set({ type: 'danger', text: 'Error: Could not delete product.' });
        }
      });
    }
  }

  viewDetails(id: number) {
    this.router.navigate(['/products', id]);
  }
}
