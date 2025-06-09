package org.generation.italy.collectionarchive.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name= "user_preferred_categories")
public class UserPreferredCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "preference_id")
    private int preferenceId;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    public UserPreferredCategory() {
    }

    public UserPreferredCategory(int preferenceId, User user, Category category) {
        this.preferenceId = preferenceId;
        this.user = user;
        this.category = category;
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
