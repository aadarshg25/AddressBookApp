package com.tit.addressbookapp.controller;

import com.tit.addressbookapp.dto.AddressBookDTO;
import com.tit.addressbookapp.model.AddressBookEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private Map<Long, AddressBookEntry> entries = new HashMap<>();
    private long idCounter = 1;

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody AddressBookDTO dto) {
        AddressBookEntry entry = new AddressBookEntry(idCounter, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
        entries.put(idCounter, entry);
        return ResponseEntity.ok("Entry created with ID: " + idCounter++);
    }

    @GetMapping
    public ResponseEntity<Map<Long, AddressBookEntry>> getAllEntries() {
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntry> getEntryById(@PathVariable Long id) {
        return entries.containsKey(id) ? ResponseEntity.ok(entries.get(id)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        if (entries.containsKey(id)) {
            AddressBookEntry entry = new AddressBookEntry(id, dto.getName(), dto.getAddress(), dto.getPhone(), dto.getEmail());
            entries.put(id, entry);
            return ResponseEntity.ok("Entry updated");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        return entries.remove(id) != null ? ResponseEntity.ok("Entry deleted") : ResponseEntity.notFound().build();
    }
}

