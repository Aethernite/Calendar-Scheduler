package com.company.utils.validators;

import com.company.objects.Account;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountValidator {

    private Pattern passwordPattern;
    private Pattern usernamePattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,}$";
    private static final String USERNAME_PATTERN = "^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$";

    public AccountValidator(){
        passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        usernamePattern = Pattern.compile(USERNAME_PATTERN);
    }

    public boolean checkAccount(Account acc) {
        matcher = passwordPattern.matcher(acc.getPassword());
        boolean password = matcher.matches();
        matcher = usernamePattern.matcher(acc.getUsername());
        boolean username = matcher.matches();

        return (username && password);
    }
}
