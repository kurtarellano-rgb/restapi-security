
package com.spring.spring_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents an authorized user in the database.
 * Stores user credentials and assigned authorization roles.
 */
@Entity
@Table(name = "auth_user")
public class Auth_User {

    /** Unique identifier for the authorized user. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Username used for authentication. */
    private String username;

    /** Encoded password of the authorized user. */
    private String password;

    /**
     * Defines the many-to-many relationship between users and roles.
     * Uses a join table to associate users with their assigned roles.
     */
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "authusers_authroles",
            joinColumns = @JoinColumn(
                    name = "authuser_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "authorize_id",
                    referencedColumnName = "id"
            )
    )
    private List<Auth_Role> roles = new ArrayList<>();

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the encoded password of the user.
     *
     * @return the encoded password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the roles assigned to the user.
     *
     * @return the list of assigned roles
     */
    public List<Auth_Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles assigned to the user.
     *
     * @param roles the list of roles
     */
    public void setRoles(List<Auth_Role> roles) {
        this.roles = roles;
    }
}
