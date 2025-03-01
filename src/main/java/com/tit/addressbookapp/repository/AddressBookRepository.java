package com.tit.addressbookapp.repository;

import com.tit.addressbookapp.model.AddressBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookEntry, Long> {
}

