package com.tit.addressbookapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
class AddressBookController {
    private Map<Long, String> entries = new HashMap<>();
    private long idCounter = 1;

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody String entry) {
        entries.put(idCounter, entry);
        return ResponseEntity.ok("Entry created with ID: " + idCounter++);
    }

    @GetMapping
    public ResponseEntity<Map<Long, String>> getAllEntries() {
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getEntryById(@PathVariable Long id) {
        return entries.containsKey(id) ? ResponseEntity.ok(entries.get(id)) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable Long id, @RequestBody String entry) {
        if (entries.containsKey(id)) {
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

