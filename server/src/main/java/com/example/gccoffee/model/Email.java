package com.example.gccoffee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;


@Embeddable
@NoArgsConstructor
@Getter
public class Email {

    private String email;

    public Email(String address) {
        Assert.notNull(address, "address should not be null");
        Assert.isTrue(address.length() >= 4 && address.length() <= 50, "address length must be between 4 and 50 characters.");
        Assert.isTrue(checkAddress(address),"Invalid email address");
        this.email = address;
    }

    private static boolean checkAddress(String address) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address);
    }

}
