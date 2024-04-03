import { Stock } from "./stock";

export interface ApiResponse {
    content: Stock[]; // Assuming 'content' contains an array of Stock objects
  totalPages: number;
  totalElements: number;
}
