package com.cm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Entity(name="user")
@Table(name = "users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
	@Id
	private String userId;
	@Column(name="user_name")
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	private String about;
	@Column(length = 10000)
	private String pic;
	private String password;
	private String phoneNumber;
	
	//information
	private boolean enabled=false;
	private boolean emailVerified=false;
	private boolean phoneVerified=false;

	//self/google/github
	@Enumerated(value = EnumType.STRING)
	private Providers provider=Providers.SELF;
	private String provideUserId;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Contact>contacts=new ArrayList<>();
	
}
