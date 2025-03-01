package com.tit.addressbookapp.service;

import com.tit.addressbookapp.dto.AddressBookDTO;
import com.tit.addressbookapp.model.AddressBookEntry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j  // Enables Lombok Logging
@Service
public class AddressBookService {
    private final List<AddressBookEntry> entries = new ArrayList<>();
    private long idCounter = 1;

    public String createEntry(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(idCounter++, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        entries.add(entry);
        log.info("New entry created with ID: {}", entry.getId());
        return "Entry created with ID: " + entry.getId();
    }

    public List<AddressBookEntry> getAllEntries() {
        log.info("Fetching all address book entries.");
        return entries;
    }

    public AddressBookEntry getEntryById(Long id) {
        log.info("Fetching entry with ID: {}", id);
        return entries.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    public String updateEntry(Long id, AddressBookDTO dto) {
        Optional<AddressBookEntry> optionalEntry = entries.stream().filter(e -> e.getId().equals(id)).findFirst();
        if (optionalEntry.isPresent()) {
            AddressBookEntry entry = optionalEntry.get();
            entry.setName(dto.getName());
            entry.setAddress(dto.getAddress());
            entry.setPhone(dto.getPhone());
            entry.setEmail(dto.getEmail());
            log.info("Entry with ID {} updated successfully", id);
            return "Entry updated";
        }
        log.warn("Entry with ID {} not found for update", id);
        return null;
    }

    public String deleteEntry(Long id) {
        boolean removed = entries.removeIf(e -> e.getId().equals(id));
        if (removed) {
            log.info("Entry with ID {} deleted successfully", id);
            return "Entry deleted";
        }
        log.warn("Entry with ID {} not found for deletion", id);
        return null;
    }
}

