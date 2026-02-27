package com.merkosun.model;

public class UserData {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String phone;
    private final String ssn;
    private final String username;
    private final String password;

    private UserData(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName  = builder.lastName;
        this.address   = builder.address;
        this.city      = builder.city;
        this.state     = builder.state;
        this.zipCode   = builder.zipCode;
        this.phone     = builder.phone;
        this.ssn       = builder.ssn;
        this.username  = builder.username;
        this.password  = builder.password;
    }

    // Getter'lar
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName;  }
    public String getAddress()   { return address;   }
    public String getCity()      { return city;      }
    public String getState()     { return state;     }
    public String getZipCode()   { return zipCode;   }
    public String getPhone()     { return phone;     }
    public String getSsn()       { return ssn;       }
    public String getUsername()  { return username;  }
    public String getPassword()  { return password;  }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String state;
        private String zipCode;
        private String phone;
        private String ssn;
        private String username;
        private String password;

        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName)   { this.lastName  = lastName;  return this; }
        public Builder address(String address)     { this.address   = address;   return this; }
        public Builder city(String city)           { this.city      = city;      return this; }
        public Builder state(String state)         { this.state     = state;     return this; }
        public Builder zipCode(String zipCode)     { this.zipCode   = zipCode;   return this; }
        public Builder phone(String phone)         { this.phone     = phone;     return this; }
        public Builder ssn(String ssn)             { this.ssn       = ssn;       return this; }
        public Builder username(String username)   { this.username  = username;  return this; }
        public Builder password(String password)   { this.password  = password;  return this; }

        public UserData build() { return new UserData(this); }
    }
}
