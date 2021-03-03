package com.lambdaschool.watermyplants.models;

import javax.validation.constraints.Pattern;

public class UserMinimum
{
    private String username;
    private String password;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
