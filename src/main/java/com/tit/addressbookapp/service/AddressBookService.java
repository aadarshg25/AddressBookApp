package com.tit.addressbookapp.service;

import com.tit.addressbookapp.dto.AddressBookDTO;
import com.tit.addressbookapp.model.AddressBookEntry;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {
    private final List<AddressBookEntry> entries = new ArrayList<>();
    private long idCounter = 1;

    public String createEntry(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(idCounter++, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        entries.add(entry);
        return "Entry created with ID: " + entry.getId();
    }

    public List<AddressBookEntry> getAllEntries() {
        return entries;
    }

    public AddressBookEntry getEntryById(Long id) {
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
            return "Entry updated";
        }
        return null;
    }

    public String deleteEntry(Long id) {
        return entries.removeIf(e -> e.getId().equals(id)) ? "Entry deleted" : null;
    }
}
