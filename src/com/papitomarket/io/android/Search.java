/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papitomarket.io.android;

import android.content.Context;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.papitomarket.gps.android.Gps;
import com.papitomarket.util.Util;
import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.papitomarket.model.tags.android.*;
import java.util.ArrayList;
import android.util.*;
import com.papitomarket.model.products.android.Category;
import com.papitomarket.model.products.android.Product;
import com.papitomarket.model.stores.android.Store;
import java.util.Iterator;

import org.json.JSONArray;

/**
 *
 * @author Pablo Tomas Borda Di Berardino
 */
public class Search {
    public String tag;

    private String search_url;
    private Context ctx;
    private static Search s;
    
    private Search(Context ctx){
        search_url = "http://soa1.papitomarket.com:9494/search?";  
        this.ctx = ctx;
    }
    
    public static Search getSearch(Context ctx){
        if (s == null){
            s = new Search(ctx);
        }
        return s;
    }
    
    
    
    public String find(String tag){        
        Gps gps = new Gps(this.ctx);
        gps.update();
        String url = search_url + "superprod=" + tag + "&lat=" + gps.lat.toString() + "&lng=" + gps.lng.toString();
        return Util.get(url);
    }
    
    public Tag[] tags(String tag){
        String url = "http://soa1.papitomarket.com:9494/superproducts?term=" + tag;
        ObjectMapper om = new ObjectMapper();
    	String json = Util.get(url);
        
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        ArrayList<Tag> tags = new ArrayList<Tag>();
        
        
        try
		{
			jp = f.createJsonParser(json);
                        jp.nextToken();
                        while (jp.nextToken() == JsonToken.START_OBJECT) {
                          Tag t = om.readValue(jp, Tag.class);
                          tags.add(t);
                        }
                        return tags.toArray(new Tag[tags.size()]);
        }
        	catch (IOException e)
			{
				Log.e("JSON","Error parsing json");
			}
        return (new Tag[0]);
    }
    
    public String load_product(String company_id){
        String url= "http://soa1.papitomarket.com:9494/company/" + company_id;
        return Util.get(url);
    }
    
    public Store[] load_stores(String tag,String lat,String lng){
        String search_url = "http://soa1.papitomarket.com:9494/search?superprod=" + tag + "&lat=" + lat + "&lng=" + lng;
        String response = Util.get(search_url);
        ObjectMapper om = new ObjectMapper();
        
        
        ArrayList<Store> stores = new ArrayList<Store>();
        ArrayList<Product> products = new ArrayList<Product>();
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        try
	{
	        jp = f.createJsonParser(response);
                        JsonNode node = om.readTree(response);
                        Iterator<JsonNode> all = node.iterator();
                        JsonNode current;
                        for (int i=0;i<node.size();i++){
                          current = (JsonNode)node.get(i);
                          Store s = new Store();
                          s.setId(current.get("store").get(0).asText());
                          s.setCompanyname(current.get("store").get(5).asText());
                          s.setCompanylogo(current.get("store").get(6).asText());
                          s.setLat(current.get("store").get(8).asText());
                          s.setLng(current.get("store").get(9).asText());
                          JsonNode productsNode = current.get("products");
                          Iterator fields = productsNode.fieldNames();
                          while (fields.hasNext()){
                            String categoryName = (String)fields.next();
                            Category c = new Category();
                            c.name = categoryName;
                            
                            JsonNode currentCategory = productsNode.get(categoryName);
                            for (int k=0;k<currentCategory.size();k++){
  
                                   JsonNode currentProduct = currentCategory.get(k).get("product");
                                   Product p = new Product();
                                   p.id = currentProduct.get("id").asText();
                                   p.name = currentProduct.get("name").asText();
                                   p.price = currentProduct.get("price").asText();
                                   p.description = currentProduct.get("description").asText();
                                   p.orders = currentProduct.get("orders").asText();
                                   
                                   for (int m=0;m<currentProduct.get("pictures").size();m++){
                                     p.addPic(currentProduct.get("pictures").get(m).get("picture").get("url").asText());    
                                                                              
                                   }
                                   c.addProduct(p);
                                   s.addProduct(p);
                            }
                            s.addCategory(c);
                          } 
                          stores.add(s);
                        }   
                           return stores.toArray(new Store[stores.size()]);          
        }
        	catch (IOException e)
			{
				Log.e("JSON","Error parsing json");
			}
        
        
        
        
        return (new Store[0]);
        
    }
    
    public ArrayList<String> getAddrsFor(String addr){
    	// IE: http://maps.googleapis.com/maps/api/geocode/json?address=Av%20Corrientes%203722&sensor=false
    	// IE: http://soa1.papitomarket.com:9494/geolocation/mobile/addresses?term=Av Corrientes 3722
        // Result example: [{"value":"0","label":"Avenida Corrientes 3722, Buenos Aires, Autonomous City of Buenos Aires, Argentina"},{"value":"1","label":"Avenida Corrientes 3722, Posadas, Misiones Province, Argentina"}]
    	String search_url = "http://soa1.papitomarket.com:9494/geolocation/mobile/addresses?term=" + addr.replace(" ","%20");
        String response = Util.get(search_url);
        ObjectMapper om = new ObjectMapper();
        JsonFactory f = new JsonFactory();
        JsonParser jp;
        ArrayList<String> arr = new ArrayList<String>();
        
        try
	{
	        jp = f.createJsonParser(response);
	        JsonNode node = om.readTree(response);
            Iterator<JsonNode> all = node.iterator();
            JsonNode current;
            for (int i=0;i<node.size();i++){
            	current = (JsonNode)node.get(i);
            	arr.add(current.get("label").asText());
            }
	} catch (IOException e){
		Log.e("JSON","Error parsing json address");
	}
        return arr;
        
    }
    
    
}