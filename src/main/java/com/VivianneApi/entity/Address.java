package com.VivianneApi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adress")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false, length = 75)
    private String street;

    @Column(nullable = false, length = 10)
    private int postalCode;

    @Column(nullable = false, length = 100)
    private String city;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_address_member")
    )
    private Member member;

    protected Address() {}

    public Address(String street, int postalCode, String city) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
    }

    @SuppressWarnings("används ej")
    public void setId(Long id) { this.id = id;}
    public Long getId() {return id; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getPostalCode() { return postalCode; }
    public void setPostalCode(int postalCode) { this.postalCode = postalCode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", member=" + member +
                '}';
    }
}
