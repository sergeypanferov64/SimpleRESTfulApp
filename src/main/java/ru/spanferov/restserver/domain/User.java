package ru.spanferov.restserver.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 100, unique = true, nullable = false)
    @Email(message = "incorrect email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "firstName is required field")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "lastName is required field")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "picture uri is required field")
    private String uri;

    @Column(nullable = false)
    private Boolean status;

    @Column(name = "crtdttm", insertable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Generated(value=GenerationTime.INSERT)
    private Date crtdttm;

    @Column(name = "updttm", insertable = false, updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @Generated(value=GenerationTime.ALWAYS)
    private Date updttm;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCrtdttm() {
        return crtdttm;
    }

    public void setCrtdttm(Date crtdttm) {
        this.crtdttm = crtdttm;
    }

    public Date getUpdttm() {
        return updttm;
    }

    public void setUpdttm(Date updttm) {
        this.updttm = updttm;
    }
    
}
