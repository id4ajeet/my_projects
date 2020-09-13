
package com.ajeet.impactt.persist.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="mailto:id4ajeet@gmail.com">Ajeet</a>
 */
@Entity
@Table(name = "phone_book")
public class PhoneBook {

    @Id
    private long number;
    private String name;
    private String email;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
