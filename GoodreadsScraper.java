package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class GoodreadsScraper {
    private static final String BASE_URL = "https://www.goodreads.com/search?q=";

    public GoodreadsScraper(String query) {
        try {
            String searchUrl = BASE_URL + query.replace(" ", "+");
            System.out.println("Fetching results from: " + searchUrl);
            // Fetch initial page
            Document initialDoc = Jsoup.connect(BASE_URL).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*").get();

            // Loop through the first 3 pages (or less)
            for (int page = 1; page <= 3; page++) {
                String pageUrl = BASE_URL + "?page=" + page;
                System.out.println("Fetching page: " + pageUrl);
                Document pageDoc = Jsoup.connect(pageUrl).get();

                // Locate all book elements on the page
                Elements bookElements = pageDoc.select("tr[itemtype='http://schema.org/Book']");

                // Process each book element
                for (int i = 0; i < Math.min(3, bookElements.size()); i++) {
                    Element bookElement = bookElements.get(i);
                    try {
                        // Extract book info
                        String title = bookElement.select("a.bookTitle").text();
                        System.out.println("Title: " + title);
                        String author = bookElement.select("a.authorName").text();
                        System.out.println("Author: " + author);
                        String bookUrl = BASE_URL + bookElement.select("a.bookTitle").attr("href");
                        System.out.println("url: " + bookUrl);
                        System.out.println("=======================================================\n");


                    } catch (Exception e) {
                        System.out.println("Book not found.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurred during scraping: " + e.getMessage());
        }
    }
}
