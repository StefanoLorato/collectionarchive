package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.Item;

import java.time.LocalDate;

public class ItemDto {

        private int itemId;
        private int collectionId;
        private int userId;
        private String objectName;
        private String objectDescription;
        private String objectPhoto;
        private String condition;
        private LocalDate purchaseDate;
        private LocalDate releaseDate;
        private double purchasePrice;
        private double salePrice;
        private String objectVersion;
        private String objectEdition;
        private boolean forSale;
        private String visibilityStatus;

        public ItemDto() {
        }

    public ItemDto(int itemId, int collectionId, int userId, String objectName, String objectDescription,
                   String objectPhoto, String condition, LocalDate purchaseDate, LocalDate releaseDate,
                   double purchasePrice, double salePrice, String objectVersion, String objectEdition,
                   boolean forSale, String visibilityStatus) {
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.userId = userId;
        this.objectName = objectName;
        this.objectDescription = objectDescription;
        this.objectPhoto = objectPhoto;
        this.condition = condition;
        this.purchaseDate = purchaseDate;
        this.releaseDate = releaseDate;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.objectVersion = objectVersion;
        this.objectEdition = objectEdition;
        this.forSale = forSale;
        this.visibilityStatus = visibilityStatus;
    }

    public Item toItem(){
            Item o = new Item(itemId,null, null,
                    objectName, objectDescription,objectPhoto, condition,
                    purchaseDate,  releaseDate, purchasePrice,salePrice,
                    objectVersion, objectEdition, forSale,  visibilityStatus);
            return o;
        }

        public static ItemDto toDto(Item i){
            return new ItemDto(i.getItemId(),i.getCollection().getCollectionId(),
                    i.getUser().getUserId(), i.getObjectName(), i.getObjectDescription(),i.getObjectPhoto(),
                    i.getCondition(),i.getPurchaseDate(),i.getReleaseDate(),
                    i.getPurchasePrice(), i.getSalePrice(), i.getObjectVersion(),
                    i.getObjectEdition(), i.isForSale(), i.getVisibilityStatus());
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

        public void setCollection(Collection collection) {
            this.collectionId = collectionId;
        }

        public int getUser() {
            return userId;
        }

        public void setUser(Collection user) {
            this.userId = userId;
        }

        public String getObjectName() {
            return objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }

        public String getObjectDescription() {
            return objectDescription;
        }

        public void setObjectDescription(String objectDescription) {
            this.objectDescription = objectDescription;
        }

        public String getObjectPhoto() {
            return objectPhoto;
        }

        public void setObjectPhoto(String objectPhoto) {
            this.objectPhoto = objectPhoto;
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

        public double getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(double purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public String getObjectVersion() {
            return objectVersion;
        }

        public void setObjectVersion(String objectVersion) {
            this.objectVersion = objectVersion;
        }

        public String getObjectEdition() {
            return objectEdition;
        }

        public void setObjectEdition(String objectEdition) {
            this.objectEdition = objectEdition;
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
