package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.*;


public class BooksToScraper {
    private static final String BASE_URL = "https://books.toscrape.com/index.html";

    public BooksToScraper(String query) {
        try {
            List<Product> products = new ArrayList<>();
            String searchUrl = BASE_URL + query.replace(" ", "+");
            // Fetch initial page
            Document initialDoc = Jsoup.connect(BASE_URL).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*").get();
            Elements productElements = initialDoc.select("li.product");

            // iterating over the list of HTML products
            for (Element productElement : productElements) {
                Product product = new Product();
                product.setName(productElement.selectFirst("h2").text());
                product.setPrice(productElement.selectFirst("span").text());
                products.add(product);
            }

            System.out.println("Book Not found on this site.");
        } catch (IOException e) {
            System.err.println("Exception encountered");
        }
    }
}

class Product {
    private String url;
    private String image;
    private String name;
    private String price;

    // getters and setters omitted for brevity...
    public String getUrl(){
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "{ \"url\":\"" + url + "\", "
                + " \"image\": \"" + image + "\", "
                + "\"name\":\"" + name + "\", "
                + "\"price\": \"" + price + "\" }";
    }
}
