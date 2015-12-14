package com.epam.ag.model;

/**
 * @author Govorov Andrey
 */
public class GalleryItem extends BaseEntity {

    private Long galleryId;
    private boolean isMainImage;

    public GalleryItem(boolean isMainImage, Long galleryId) {
        this.galleryId = galleryId;
        this.isMainImage = isMainImage;
    }

    public GalleryItem(boolean isMainImage) {
        this.isMainImage = isMainImage;
    }

    public GalleryItem(Long id, boolean isMainImage, Long galleryId) {
        this.id = id;
        this.galleryId = galleryId;
        this.isMainImage = isMainImage;
    }

    public GalleryItem() {

    }

    public GalleryItem(Long id, boolean isMainImage) {
        this.id = id;
        this.isMainImage = isMainImage;
    }

    public boolean isMainImage() {
        return this.isMainImage;
    }

    public void setMainImage(boolean mainImage) {
        this.isMainImage = mainImage;
    }

    public Long getGalleryId() {
        return galleryId != null ? galleryId : 0;
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
                ", isMainImage=" + isMainImage +
                '}';
    }
}
