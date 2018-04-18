package com.securedweb.security;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
    	 String base64encodedString="";
		try {
			base64encodedString = Base64.getEncoder().encodeToString(rawPassword.toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return base64encodedString;
    }
    @Override
    public boolean matches(CharSequence rawPassword, String base64encodedString) {
 
    	byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString.toString());
		String oldPassword="";
		try {
			oldPassword = new String(base64decodedBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	return oldPassword.equals(rawPassword.toString());
    }
}
