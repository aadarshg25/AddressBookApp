package com.tit.addressbookapp.exception;

public class AddressBookNotFoundException extends RuntimeException {
    public AddressBookNotFoundException(String message) {
        super(message);
    }
}

