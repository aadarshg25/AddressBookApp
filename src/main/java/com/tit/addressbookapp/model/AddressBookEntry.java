package com.tit.addressbookapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookEntry {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
}

