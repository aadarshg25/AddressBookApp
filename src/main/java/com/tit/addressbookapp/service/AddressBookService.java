package com.tit.addressbookapp.service;

import com.tit.addressbookapp.dto.AddressBookDTO;
import com.tit.addressbookapp.model.AddressBookEntry;
import com.tit.addressbookapp.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressBookService {

    private final AddressBookRepository addressBookRepository;

    public AddressBookService(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    public String createEntry(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(null, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        AddressBookEntry savedEntry = addressBookRepository.save(entry); // Save to DB
        log.info("New entry created with ID: {}", savedEntry.getId());
        return "Entry created with ID: " + savedEntry.getId();
    }

    public List<AddressBookEntry> getAllEntries() {
        log.info("Fetching all address book entries.");
        return addressBookRepository.findAll(); // Fetch from DB
    }

    public AddressBookEntry getEntryById(Long id) {
        log.info("Fetching entry with ID: {}", id);
        return addressBookRepository.findById(id).orElse(null); // Fetch from DB
    }

    public String updateEntry(Long id, AddressBookDTO dto) {
        Optional<AddressBookEntry> optionalEntry = addressBookRepository.findById(id);
        if (optionalEntry.isPresent()) {
            AddressBookEntry entry = optionalEntry.get();
            entry.setName(dto.getName());
            entry.setAddress(dto.getAddress());
            entry.setPhone(dto.getPhone());
            entry.setEmail(dto.getEmail());
            addressBookRepository.save(entry); // Save updated entry to DB
            log.info("Entry with ID {} updated successfully", id);
            return "Entry updated";
        }
        log.warn("Entry with ID {} not found for update", id);
        return null;
    }

    public String deleteEntry(Long id) {
        if (addressBookRepository.existsById(id)) {
            addressBookRepository.deleteById(id); // Delete from DB
            log.info("Entry with ID {} deleted successfully", id);
            return "Entry deleted";
        }
        log.warn("Entry with ID {} not found for deletion", id);
        return null;
    }
}



