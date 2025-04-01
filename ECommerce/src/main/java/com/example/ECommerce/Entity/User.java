package com.example.ECommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name="app_user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;
    
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = true, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;



   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<Address> address = new ArrayList<>();

   @Embedded
   @ElementCollection
   @CollectionTable(name = "paymentinfo", joinColumns = @JoinColumn(name = "user_id"))
   private List<PaymentInfo> paymentInfo = new ArrayList<>();


   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   @JsonIgnore
   private List<Rating> ratings = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();


    private LocalDateTime createdAt;
    
    @Override
	public String getUsername() {
		return phoneNumber;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}


}
