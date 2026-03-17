package com.VivianneApi.security;

import com.VivianneApi.entity.Member;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 80)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "app_user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    //Connecta member och user
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    protected AppUser() {}

    public AppUser(String username, String password, Set<Role> roles, Member member) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.member = member;
    }
    public AppUser(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public Long getId() { return id; }
    @SuppressWarnings("används ej")
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;}
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
}
