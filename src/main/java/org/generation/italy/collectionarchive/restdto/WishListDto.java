package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.Collection;
import org.generation.italy.collectionarchive.models.entities.WishList;

import java.time.LocalDate;

public class WishListDto {
        private int desiredObjectId;
        private int collectionId;
        private String objectName;
        private String objectDescription;
        private LocalDate releaseDate;
        private String objectVersion;
        private String objectEdition;

        public WishListDto() {
        }

        public WishListDto(int desiredObjectId, int collectionId, String objectName,
                        String objectDescription, LocalDate releaseDate,
                           String objectVersion, String objectEdition) {
            this.desiredObjectId = desiredObjectId;
            this.collectionId = collectionId;
            this.objectName = objectName;
            this.objectDescription = objectDescription;
            this.releaseDate = releaseDate;
            this.objectVersion = objectVersion;
            this.objectEdition = objectEdition;
        }

        public WishList ToWishList(){
            WishList wl = new WishList(desiredObjectId, null, objectName, objectDescription,
                    releaseDate, objectVersion, objectEdition);
            return wl;
        }

        public static WishListDto toDto(WishList wishList){
            return new WishListDto(wishList.getDesiredObjectId(), wishList.getCollection().getCollectionId(),
                        wishList.getObjectName(), wishList.getObjectDescription(),
                        wishList.getReleaseDate(), wishList.getObjectVersion(), wishList.getObjectEdition());
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


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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
}

