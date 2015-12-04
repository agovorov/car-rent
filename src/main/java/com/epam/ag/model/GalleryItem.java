package com.epam.ag.model;

import java.io.File;

/**
 * @author Govorov Andrey
 */
public class GalleryItem extends BaseEntity {

    private Long galleryId;
    private String fullImagePath;
    private String thumbPath;
    private boolean isMainImage;

    public GalleryItem(String fullImagePath, String thumbPath, boolean isMainImage, Long galleryId) {
        this.fullImagePath = fullImagePath;
        this.thumbPath = thumbPath;
        this.galleryId = galleryId;
        this.isMainImage = isMainImage;
    }

    public GalleryItem(String fullImagePath, String thumbPath, boolean isMainImage) {
        this.fullImagePath = fullImagePath;
        this.thumbPath = thumbPath;
        this.isMainImage = isMainImage;
    }

    public GalleryItem(Long id, String fullImagePath, String thumbPath, boolean isMainImage, Long galleryId) {
        this.id = id;
        this.fullImagePath = fullImagePath;
        this.thumbPath = thumbPath;
        this.galleryId = galleryId;
        this.isMainImage = isMainImage;
    }

    public GalleryItem() {

    }

    public GalleryItem(Long id, String fullImagePath, String thumbPath, boolean isMainImage) {
        this.id = id;
        this.fullImagePath = fullImagePath;
        this.thumbPath = thumbPath;
        this.isMainImage = isMainImage;
    }

    public String getFullImagePath() {
        return fullImagePath;
    }

    public void setFullImagePath(String fullImagePath) {
        this.fullImagePath = fullImagePath;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public boolean isMainImage() {
        return this.isMainImage;
    }

    public void setMainImage(boolean mainImage) {
        this.isMainImage = mainImage;
    }

    public Long getGalleryId() {
        return galleryId != null ?galleryId : 0;
    }

    /**
     * This should be called when we are saving Gallery entity
     *
     * @param id
     */
    public void setGalleryId(Long id) {
        this.galleryId = id;
    }

    @Override
    public String toString() {
        return "GalleryItem{" +
                "id=" + getId() +
                ", galleryId=" + galleryId +
                ", fullImagePath=" + fullImagePath +
                ", thumbPath=" + thumbPath +
                ", isMainImage=" + isMainImage +
                '}';
    }
}
