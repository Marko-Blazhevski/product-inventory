export enum Category {
  ELECTRONICS = 'ELECTRONICS',
  CLOTHING = 'CLOTHING',
  HOME_APPLIANCES = 'HOME_APPLIANCES',
  BOOKS = 'BOOKS',
  FOOD = 'FOOD',
  TOYS = 'TOYS',
  OTHER = 'OTHER',
}

export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  quantityInStock: number;
  category: Category;
  imageUrl?: string;
}
