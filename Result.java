package com.example;

public class Result {
    private String title;
    private String price;

    public Result(String title, String price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " - " + price;
    }
}
