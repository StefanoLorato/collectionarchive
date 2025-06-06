package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user_contacts")
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "contact_id")
    private int contactId;
    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
    private String phone;

    public UserContact() {
    }

    public UserContact(int contactId, User user, String phone) {
        this.contactId = contactId;
        this.user = user;
        this.phone = phone;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
