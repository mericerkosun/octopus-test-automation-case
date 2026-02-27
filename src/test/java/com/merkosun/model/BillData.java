package com.merkosun.model;

public class BillData {
    private final String payeeName;
    private final String address;
    private final String city;
    private final String state;
    private final String zipCode;
    private final String phone;
    private final String account;
    private final String amount;

    private BillData(Builder builder) {
        this.payeeName = builder.payeeName;
        this.address   = builder.address;
        this.city      = builder.city;
        this.state     = builder.state;
        this.zipCode   = builder.zipCode;
        this.phone     = builder.phone;
        this.account   = builder.account;
        this.amount    = builder.amount;
    }

    public String getPayeeName() { return payeeName; }
    public String getAddress()   { return address;   }
    public String getCity()      { return city;      }
    public String getState()     { return state;     }
    public String getZipCode()   { return zipCode;   }
    public String getPhone()     { return phone;     }
    public String getAccount()   { return account;   }
    public String getAmount()    { return amount;    }

    public static class Builder {
        private String payeeName;
        private String address;
        private String city;
        private String state;
        private String zipCode;
        private String phone;
        private String account;
        private String amount;

        public Builder payeeName(String payeeName) { this.payeeName = payeeName; return this; }
        public Builder address(String address)     { this.address   = address;   return this; }
        public Builder city(String city)           { this.city      = city;      return this; }
        public Builder state(String state)         { this.state     = state;     return this; }
        public Builder zipCode(String zipCode)     { this.zipCode   = zipCode;   return this; }
        public Builder phone(String phone)         { this.phone     = phone;     return this; }
        public Builder account(String account)     { this.account   = account;   return this; }
        public Builder amount(String amount)       { this.amount    = amount;    return this; }

        public BillData build() { return new BillData(this); }
    }
}
