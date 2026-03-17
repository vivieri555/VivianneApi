package com.VivianneApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "medlem")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

//    @Column(nullable = false, length = 100)
//    private String address;

    @Column(nullable = false, length = 110)
    private String email;

    @Column(length = 10)
    private String phone;

    @Column(nullable = false, unique = true, length = 12)
    private String dateOfBirth;

    //En adress kan kopplas till flera medlemmar
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "address_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_member_address")
    )
    private Address address;

    protected Member() {}

    public Member(String firstName, String lastName, Address address, String email, String phone, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() { return id; }

    @SuppressWarnings("används ej")
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName;}

    public String getLastName() { return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address;}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone;}
    public void setPhone(String phone) { this.phone = phone; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth;}

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
