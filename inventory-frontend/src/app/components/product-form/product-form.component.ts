import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Category } from '../../models/product.model';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './product-form.component.html'
})
export class ProductFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private productService = inject(ProductService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  isEditMode = signal(false);
  productId: number | null = null;
  selectedFile: File | null = null;
  categories = Object.values(Category);
  message = signal<{type: string, text: string} | null>(null);

  productForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],

    description: [''],

    price: [1, [Validators.required, Validators.min(0.01)]],
    quantityInStock: [0, [Validators.required, Validators.min(0)]],

    category: [Category.ELECTRONICS, Validators.required],

    selectedFile: [null, Validators.required]
  });

  ngOnInit() {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.productId = +idParam;
      this.isEditMode.set(true);
      this.loadProductForEdit(this.productId);
    }
  }

  loadProductForEdit(id: number) {
    this.productService.getProductById(id).subscribe({
      next: (product) => this.productForm.patchValue(product),
      error: () => this.message.set({ type: 'danger', text: 'Could not load product data.' })
    });
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      this.productForm.patchValue({ selectedFile: file });
      this.productForm.get('selectedFile')?.updateValueAndValidity();
    }
  }

  onSubmit() {
    if (this.productForm.invalid) return;

    const formData = new FormData();
    const productData = new Blob([JSON.stringify(this.productForm.value)], { type: 'application/json' });
    formData.append('product', productData);

    if (this.selectedFile) {
      formData.append('file', this.selectedFile);
    }

    const request = this.isEditMode()
      ? this.productService.updateProduct(this.productId!, formData)
      : this.productService.createProduct(formData);

    request.subscribe({
      next: () => {
        this.message.set({ type: 'success', text: `Product ${this.isEditMode() ? 'updated' : 'created'} successfully!` });
        this.router.navigate(['/']);
      },
      error: () => this.message.set({ type: 'danger', text: 'An error occurred while saving.' })
    });
  }
}
