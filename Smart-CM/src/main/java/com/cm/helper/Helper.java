package com.cm.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {

            // Explicitly define the types instead of using var
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");
                return oauth2User.getAttribute("email");
            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("get email using github");
                return oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email") : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }

            return "";
        } else {
            System.out.println("get email manually");
            return authentication.getName();
        }
    }
}
