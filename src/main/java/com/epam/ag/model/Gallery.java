package com.epam.ag.model;

import java.util.ArrayList;
import java.util.List;

public class Gallery extends BaseEntity {

    private String title;
    private List<GalleryItem> items = new ArrayList<>();

    public Gallery() {
    }

    public Gallery(String title) {
        this.title = title;
    }
    public Gallery(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Gallery(long id) {
        this.id = id;
    }

    public void addImage(GalleryItem item) {
        items.add(item);
    }

    public void removeImage(int idx) {
        items.remove(idx);
    }

    public GalleryItem getItem(int idx) {
        return this.items.get(idx);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int size() {
        return items.size();
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id='" + getId() + '\'' +
                ", title='" + title + '\'' +
                ", items.size=" + (items == null ? 0 : items.size()) +
                ", items: " + items + "'" +
                '}';
    }

    public void addItems(List<GalleryItem> items) {
        this.items.addAll(items);
    }
}
