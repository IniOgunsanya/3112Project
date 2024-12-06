package com.example;

public class Main {
        public static void main(String[] args) {
            UserInputHandler inputHandler = new UserInputHandler();
            String query = inputHandler.getUserInput();
            AmazonScraper scraper = new AmazonScraper("");

            System.out.println("Printing results from Goodreads.....");
            scraper.delay(2000);
            GoodreadsScraper goodScrape = new GoodreadsScraper(query);

            System.out.println("Printing results from BookScrape.....");
            scraper.delay(2000);
            BooksToScraper bookScrape = new BooksToScraper(query);

        }

}
