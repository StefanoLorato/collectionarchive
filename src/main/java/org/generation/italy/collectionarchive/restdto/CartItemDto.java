package org.generation.italy.collectionarchive.restdto;

import org.generation.italy.collectionarchive.models.entities.CartItem;

import java.time.LocalDateTime;

public class CartItemDto {
    private int cartItemId;
    private int buyerId;
    private int sellerId;
    private int itemId;
    private int collectionId;
    private LocalDateTime expiresAt;

    public CartItemDto() {
    }

    public CartItemDto(int cartItemId, int buyerId, int sellerId, int itemId, int collectionId, LocalDateTime expiresAt) {
        this.cartItemId = cartItemId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.collectionId = collectionId;
        this.expiresAt = expiresAt;
    }

    public CartItem toCartItem(){
        return new CartItem(cartItemId, null, null, null, null, expiresAt);
    }

    public static CartItemDto toDto(CartItem c){
        return new CartItemDto(c.getCartItemId(), c.getBuyer().getUserId(), c.getSeller().getUserId(),
                                c.getItem().getItemId(), c.getCollection().getCollectionId(), c.getExpiresAt());
    }

    public int getCartItemId() {
        return cartItemId;
    }
    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
