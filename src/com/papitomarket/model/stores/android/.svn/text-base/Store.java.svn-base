/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.model.stores.android;

import com.papitomarket.model.products.android.Category;
import com.papitomarket.model.products.android.Product;
import java.util.ArrayList;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class Store {
	
	
    private String id;
	private String companyname;
    private String companylogo;
    private ArrayList<Product> products;
    private ArrayList<Category> categories;
    private String webpage;
    private String email;
    private String address;
    private String lat;
    private String lng;
    
    public Store(){
        products = new ArrayList<Product>();
        categories = new ArrayList<Category>();
    }
    
    public Store(String storeJSON){
   
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanylogo() {
		return companylogo;
	}

	public void setCompanylogo(String companylogo) {
		this.companylogo = companylogo;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

    
    public void addCategory(Category category){
       
        categories.add(category);
        
    }
    
    public void addProduct(Product p){
        products.add(p);
    }
   
}
