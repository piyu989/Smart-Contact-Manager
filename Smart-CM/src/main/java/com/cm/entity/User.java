package com.cm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name="user")
@Table(name = "users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User implements UserDetails {
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<SimpleGrantedAuthority>role=roles_list.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		return role;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Id
	private String userId;
//	@Column(name="user_name")
	private String name;
	@Column(unique = true,nullable = false,name="user_name")
	private String email;
	private String about;
	@Column(length = 10000)
	private String pic;
	private String password;
	private String phoneNumber;
	
	//information
	private boolean enabled=true;
	private boolean emailVerified=false;
	private boolean phoneVerified=false;

	//self/google/github
	@Enumerated(value = EnumType.STRING)
	private Providers provider=Providers.SELF;
	private String provideUserId;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Contact>contacts=new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String>roles_list=new ArrayList<>();
	
}
