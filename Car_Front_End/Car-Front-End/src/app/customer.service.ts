import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from './customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseURL = "http://localhost:8800/c1/customer";
  private uUrl = "http://localhost:8800/c1/updatecustomer";

  constructor(private httpClient: HttpClient) { }
  
  getCustomersList(): Observable<Customer[]>{
    return this.httpClient.get<Customer[]>(`${"http://localhost:8800/c1/customers"}`);
  }
  createCustomer(customer: Customer): Observable<any>{
    return this.httpClient.post(`${this.baseURL}`, customer);
  }

  
  getCustomerById(userId: string): Observable<Customer>{
    return this.httpClient.get<Customer>(`${this.baseURL}/${userId}`);
  }

  updateCustomer(userId: string, customer: Customer): Observable<any>{
    return this.httpClient.put(`${this.uUrl}/${userId}`, customer);
  }
 
  deleteCustomer(userId: string): Observable<any>{
    return this.httpClient.delete(`${this.baseURL}/${userId}`);
  }

}
