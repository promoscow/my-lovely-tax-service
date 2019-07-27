package ru.xpendence.mylovelytaxservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Vyacheslav Chernyshov
 * Date: 27.07.19
 * Time: 11:45
 * e-mail: v.chernyshov@pflb.ru
 */
@Entity
@Table(name = "users")
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE users SET active = 0 WHERE id = ?")
@Where(clause = "active=1")
public class User extends AbstractEntity {

    private String username;
    private String password;
    private String name;
    private String email;
    private Long inn;

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "inn")
    public Long getInn() {
        return inn;
    }
}
