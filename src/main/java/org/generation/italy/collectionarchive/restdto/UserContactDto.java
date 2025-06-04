package org.generation.italy.collectionarchive.restdto;

import jakarta.persistence.*;
import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.entities.UserComment;
import org.generation.italy.collectionarchive.models.entities.UserContact;

public class UserContactDto {
    private int contactId;
    private int userId;
    private String phone;

    public UserContactDto() {
    }

    public UserContactDto(int contactId, int userId, String phone) {
        this.contactId = contactId;
        this.userId = userId;
        this.phone = phone;
    }

    public UserContact toUserConcat(){
        UserContact uc = new UserContact( contactId, null, phone);
        return uc;
    }

    public static UserContactDto toDto(UserContact uc){
        return new UserContactDto(uc.getContactId(), uc.getUser().getUserId(), uc.getPhone());
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getUser() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
