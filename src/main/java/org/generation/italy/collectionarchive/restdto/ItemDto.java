package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Item;

import java.time.LocalDate;

public class ItemDto {

        private int itemId;
        private int collectionId;
        private int userId;
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
        private boolean forSale;
        private String visibilityStatus;

        public ItemDto() {
        }

    public ItemDto(int itemId, int collectionId, int userId, String itemName, String itemDescription,
                   String itemPhoto, String condition, LocalDate purchaseDate, LocalDate releaseDate,
                   Double purchasePrice, Double salePrice, String itemVersion, String itemEdition,
                   boolean forSale, String visibilityStatus) {
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
    }

    public Item toItem(){
            Item i = new Item(itemId,null, null,
                    itemName, itemDescription, itemPhoto, condition,
                    purchaseDate,  releaseDate, purchasePrice,salePrice,
                    itemVersion, itemEdition, forSale,  visibilityStatus);
            i.setVisibilityStatus(this.visibilityStatus != null ? this.visibilityStatus : "visible");
            return i;
        }

        public static ItemDto toDto(Item i){
            return new ItemDto(i.getItemId(),i.getCollection().getCollectionId(),
                    i.getUser().getUserId(), i.getItemName(), i.getItemDescription(),i.getItemPhoto(),
                    i.getCondition(),i.getPurchaseDate(),i.getReleaseDate(),
                    i.getPurchasePrice(), i.getSalePrice(), i.getItemVersion(),
                    i.getItemEdition(), i.isForSale(), i.getVisibilityStatus());
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getCollection() {
            return collectionId;
        }

        public void setCollection(int collectionId) {
            this.collectionId = collectionId;
        }

        public int getUser() {
            return userId;
        }

        public void setUser(int userId) {
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

        public boolean isForSale() {
            return forSale;
        }

        public void setForSale(boolean forSale) {
            this.forSale = forSale;
        }

        public String getVisibilityStatus() {
            return visibilityStatus;
        }

        public void setVisibilityStatus(String visibilityStatus) {
            this.visibilityStatus = visibilityStatus;
        }
}
