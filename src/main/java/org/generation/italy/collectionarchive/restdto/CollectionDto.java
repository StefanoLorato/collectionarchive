package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserLike;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CollectionDto {

    private int collectionId;
    private String collectionName;
    private Boolean completed;
    private Integer categoryId;
    private Integer userId;
    private String visibility;
    private String description;
    private LocalDate collectionDate;
    private LocalDateTime createdAt;
    private Boolean forSale;
    private Double salePrice;
    private String visibilityStatus;
    private String priceComparation;
    private boolean liked;
    private int numLikes;
    private boolean bookmarked;
    private Integer likeId;
    private Integer bookmarkId;

    public CollectionDto() {
    }

    public CollectionDto(int collectionId, String collectionName, Boolean completed,
                         Integer categoryId, Integer userId, String visibility, String description, LocalDate collectionDate,
                         LocalDateTime createdAt, Boolean forSale, Double salePrice, String visibilityStatus, boolean liked,
                         int numLikes, boolean bookmarked, Integer likeId, Integer bookmarkId) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.completed = completed;
        this.categoryId = categoryId;
        this.userId = userId;
        this.visibility = visibility;
        this.description = description;
        this.collectionDate = collectionDate;
        this.createdAt = createdAt;
        this.forSale = forSale;
        this.salePrice = salePrice;
        this.visibilityStatus = visibilityStatus;
        this.liked = liked;
        this.numLikes = numLikes;
        this.bookmarked = bookmarked;
        this.likeId = likeId;
        this.bookmarkId = bookmarkId;
    }

    public Collection toCollection(){
        Collection c = new Collection( collectionId, collectionName, completed,
         null, null, visibility,  description, collectionDate, createdAt,  forSale,
                salePrice, visibilityStatus);
        c.setVisibilityStatus(this.visibilityStatus != null ? this.visibilityStatus : "visible");
        c.setVisibility(this.visibility != null ? this.visibility : "visible");
        return c;
    }

    public static CollectionDto toDto(Collection c, User loggedUser){
        int numLikes = c.getLikes().size();
        boolean isLiked = loggedUser != null && c.getLikes()
                .stream().anyMatch(l -> l.getUser().equals(loggedUser));
        boolean isBookmarked = loggedUser != null && c.getCollectionBookmarks()
                .stream().anyMatch(b -> b.getUser().equals(loggedUser));
        Integer likeId = c.getLikes().stream().filter(l -> l.getUser().equals(loggedUser))
                .findFirst().map(UserLike::getLikeId).orElse(null);
        Integer bookmarkId = c.getCollectionBookmarks().stream().filter(b -> b.getUser().equals(loggedUser))
                .findFirst().map(Bookmark::getBookmarkId).orElse(null);
        return new CollectionDto(c.getCollectionId(),c.getCollectionName(),c.isCompleted(),
                                 c.getCategory().getCategoryId(), c.getUser().getUserId(),
                                 c.getVisibility(),c.getDescription(),c.getCollectionDate(),
                                 c.getCreatedAt(),c.isForSale(),c.getSalePrice(),c.getVisibilityStatus(),
                                isLiked, numLikes, isBookmarked, likeId, bookmarkId);
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

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Boolean isForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        return priceComparation;
    }

    public void setPriceComparation(String priceComparation) {
        this.priceComparation = priceComparation;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getForSale() {
        return forSale;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
