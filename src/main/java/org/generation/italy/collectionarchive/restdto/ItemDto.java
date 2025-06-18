package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Bookmark;
import org.generation.italy.collectionarchive.models.entities.Item;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserLike;

import java.time.LocalDate;

public class ItemDto {

    private int itemId;
    private Integer collectionId;
    private Integer userId;
    private String itemName;
    private String itemDescription;
    private String itemPhoto;
    private String condition;
    private LocalDate purchaseDate;
    private LocalDate releaseDate;
    private Double purchasePrice;
    private Double salePrice;
    private String itemVersion;
    private String itemEdition;
    private Boolean forSale;
    private String visibilityStatus;
    private String priceComparation;
    private boolean liked;
    private int numLikes;
    private boolean bookmarked;
    private Integer likeId;
    private Integer bookmarkId;

    public ItemDto() {
    }

    public ItemDto(int itemId, Integer collectionId, Integer userId, String itemName, String itemDescription,
                   String itemPhoto, String condition, LocalDate purchaseDate, LocalDate releaseDate,
                   Double purchasePrice, Double salePrice, String itemVersion, String itemEdition,
                   Boolean forSale, String visibilityStatus, boolean liked,
                   int numLikes, boolean bookmarked, Integer likeId, Integer bookmarkId) {
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.userId = userId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPhoto = itemPhoto;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.releaseDate = releaseDate;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.itemVersion = itemVersion;
        this.itemEdition = itemEdition;
        this.forSale = forSale;
        this.visibilityStatus = visibilityStatus;
        this.liked = liked;
        this.numLikes = numLikes;
        this.bookmarked = bookmarked;
        this.likeId = likeId;
        this.bookmarkId = bookmarkId;
    }

    public Item toItem(){
        Item i = new Item(itemId,null, null,
                itemName, itemDescription, itemPhoto, condition,
                purchaseDate,  releaseDate, purchasePrice,salePrice,
                itemVersion, itemEdition, forSale,  visibilityStatus);
        i.setVisibilityStatus(this.visibilityStatus != null ? this.visibilityStatus : "visible");
        return i;
    }

    public static ItemDto toDto(Item i, User loggedUser){
        int numLikes = i.getItemsLikes().size();
        boolean isLiked = loggedUser != null && i.getItemsLikes()
                .stream().anyMatch(l -> l.getUser().equals(loggedUser));
        boolean isBookmarked = loggedUser != null && i.getItemBookmarks()
                .stream().anyMatch(b -> b.getUser().equals(loggedUser));
        Integer likeId = i.getItemsLikes().stream().filter(l -> l.getUser().equals(loggedUser))
                .findFirst().map(UserLike::getLikeId).orElse(null);
        Integer bookmarkId = i.getItemBookmarks().stream().filter(b -> b.getUser().equals(loggedUser))
                .findFirst().map(Bookmark::getBookmarkId).orElse(null);
        return new ItemDto(i.getItemId(),(i.getCollection() != null ? i.getCollection().getCollectionId() : null),
                i.getUser().getUserId(), i.getItemName(), i.getItemDescription(),i.getItemPhoto(),
                i.getCondition(),i.getPurchaseDate(),i.getReleaseDate(),
                i.getPurchasePrice(), i.getSalePrice(), i.getItemVersion(),
                i.getItemEdition(), i.isForSale(), i.getVisibilityStatus(), isLiked, numLikes, isBookmarked, likeId, bookmarkId);
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public void setItemVersion(String itemVersion) {
        this.itemVersion = itemVersion;
    }

    public String getItemEdition() {
        return itemEdition;
    }

    public void setItemEdition(String itemEdition) {
        this.itemEdition = itemEdition;
    }

    public Boolean isForSale() {
        return forSale;
    }

    public void setForSale(Boolean forSale) {
        this.forSale = forSale;
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

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }
}
