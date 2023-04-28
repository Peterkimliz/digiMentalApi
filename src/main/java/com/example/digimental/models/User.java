package com.example.digimental.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String email;
    private String username;
    private String phone;
    @JsonIgnore
    private String password;
    private String dob;
    private String gender;
    private String age;
    private String profileImage;
    private String type;
    private boolean isVerified = false;
    private String bio;
    private String country;
    private String county;
    private String subcounty;
    private int yearsOfExperience=0;
    private int consultationFee=0;
    private int totalReviews=0;
    private  int totalRatings=0;
    private String certurl;
    private  List<String>category=new ArrayList<>();
    private  List<String>workingDays=new ArrayList<>();
    private boolean isPhoneVerified = false;
    private Date createdAt;
    private Date updatedAt;
     Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
