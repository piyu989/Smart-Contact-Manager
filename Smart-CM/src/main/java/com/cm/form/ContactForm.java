package com.cm.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {
    @NotBlank(message = "name is required")
    private String name;
    @Email(message = "invalid email address")
    private String email;
    @NotBlank(message = "address  is required")
    private String address;
    private String description;
    @Pattern(regexp = "^[0-9]{10}$",message = "invalid phone number")
    private String phoneNumber;
    private String websiteLink;
    private String linkedInLink;
    private boolean favourite;
    private MultipartFile contactImage; 
}
