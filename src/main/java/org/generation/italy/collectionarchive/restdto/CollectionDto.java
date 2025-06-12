package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.repositories.specifications.ItemSpecification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CollectionDto {

    private int collectionId;
    private String collectionName;
    private boolean completed;
    private int categoryId;
    private int userId;
    private String visibility;
    private String description;
    private LocalDate collectionDate;
    private LocalDateTime createAt;
    private boolean forSale;
    private Double salePrice;
    private String visibilityStatus;
    private String PriceComparation;

    public CollectionDto() {
    }

    public CollectionDto(int collectionId, String collectionName, boolean completed,
                         int categoryId, int userId, String visibility, String description, LocalDate collectionDate,
                         LocalDateTime createAt, boolean forSale, Double salePrice, String visibilityStatus) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.completed = completed;
        this.categoryId = categoryId;
        this.userId = userId;
        this.visibility = visibility;
        this.description = description;
        this.collectionDate = collectionDate;
        this.createAt = createAt;
        this.forSale = forSale;
        this.salePrice = salePrice;
        this.visibilityStatus = visibilityStatus;
    }

    public Collection toCollection(){
        Collection c = new Collection( collectionId, collectionName, completed,
         null, null, visibility,  description, collectionDate,  createAt,  forSale,
                salePrice, visibilityStatus);
        c.setVisibilityStatus(this.visibilityStatus != null ? this.visibilityStatus : "visible");
        c.setVisibility(this.visibility != null ? this.visibility : "visible");
        return c;
    }

    public static CollectionDto toDto(Collection c){
        return new CollectionDto(c.getCollectionId(),c.getCollectionName(),c.isCompleted(),
                                 c.getCategory().getCategoryId(), c.getUser().getUserId(),
                                 c.getVisibility(),c.getDescription(),c.getCollectionDate(),
                                 c.getCreateAt(),c.isForSale(),c.getSalePrice(),c.getVisibilityStatus());
    }


    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(LocalDate collectionDate) {
        this.collectionDate = collectionDate;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getVisibilityStatus() {
        return visibilityStatus;
    }

    public void setVisibilityStatus(String visibilityStatus) {
        this.visibilityStatus = visibilityStatus;
    }

    public String getPriceComparation() {
        return PriceComparation;
    }

    public void setPriceComparation(String priceComparation) {
        PriceComparation = priceComparation;
    }
}
