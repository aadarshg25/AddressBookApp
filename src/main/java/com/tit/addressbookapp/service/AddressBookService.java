package com.tit.addressbookapp.service;

import com.tit.addressbookapp.dto.AddressBookDTO;
import com.tit.addressbookapp.model.AddressBookEntry;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AddressBookService {
    private Map<Long, AddressBookEntry> entries = new HashMap<>();
    private long idCounter = 1;

    public String createEntry(AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(idCounter, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        entries.put(idCounter, entry);
        return "Entry created with ID: " + idCounter++;
    }

    public Map<Long, AddressBookEntry> getAllEntries() {
        return entries;
    }

    public AddressBookEntry getEntryById(Long id) {
        return entries.get(id);
    }

    public String updateEntry(Long id, AddressBookDTO dto) {
        if (entries.containsKey(id)) {
            AddressBookEntry entry = new AddressBookEntry(id, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
            entries.put(id, entry);
            return "Entry updated";
        }
        return null;
    }

    public String deleteEntry(Long id) {
        return entries.remove(id) != null ? "Entry deleted" : null;
    }
}
