package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.WishList;

import java.time.LocalDate;

public class WishListDto {
        private int desiredObjectId;
        private int collectionId;
        private String itemName;
        private String itemDescription;
        private LocalDate releaseDate;
        private String itemVersion;
        private String itemEdition;

        public WishListDto() {
        }

        public WishListDto(int desiredObjectId, int collectionId, String itemName,
                           String itemDescription, LocalDate releaseDate,
                           String itemVersion, String itemEdition) {
            this.desiredObjectId = desiredObjectId;
            this.collectionId = collectionId;
            this.itemName = itemName;
            this.itemDescription = itemDescription;
            this.releaseDate = releaseDate;
            this.itemVersion = itemVersion;
            this.itemEdition = itemEdition;
        }

        public WishList ToWishList(){
            WishList wl = new WishList(desiredObjectId, null, itemName, itemDescription,
                    releaseDate, itemVersion, itemEdition);
            return wl;
        }

        public static WishListDto toDto(WishList wishList){
            return new WishListDto(wishList.getDesiredItemId(), wishList.getCollection().getCollectionId(),
                        wishList.getItemName(), wishList.getItemDescription(),
                        wishList.getReleaseDate(), wishList.getItemVersion(), wishList.getItemEdition());
        }

    public int getDesiredObjectId() {
        return desiredObjectId;
    }

    public void setDesiredObjectId(int desiredObjectId) {
        this.desiredObjectId = desiredObjectId;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
}

