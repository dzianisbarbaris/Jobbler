package by.savik.Jobbler.harvester.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Root {
    @JsonProperty("items")
    private ArrayList<ItemDto> items;

    @JsonProperty("found")
    private int found;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("page")
    private int page;

    @JsonProperty("per_page")
    private int per_page;

    public ArrayList<ItemDto> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDto> items) {
        this.items = items;
    }

    public int getFound() {
        return found;
    }

    public void setFound(int found) {
        this.found = found;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }
}