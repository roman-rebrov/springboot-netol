package com.education.springbootconditional.profiles;

public class DevProfile implements SystemProfile{
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
