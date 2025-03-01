package com.tit.addressbookapp.controller;

import com.tit.addressbookapp.dto.AddressBookDTO;
import com.tit.addressbookapp.model.AddressBookEntry;
import com.tit.addressbookapp.service.AddressBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {
    private final AddressBookService addressBookService;

    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody AddressBookDTO dto) {
        String response = addressBookService.createEntry(dto);  // ✅ Call service layer
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AddressBookEntry>> getAllEntries() {
        return ResponseEntity.ok(addressBookService.getAllEntries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBookEntry> getEntryById(@PathVariable Long id) {
        AddressBookEntry entry = addressBookService.getEntryById(id);
        return entry != null ? ResponseEntity.ok(entry) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntry(@PathVariable Long id, @RequestBody AddressBookDTO dto) {
        String response = addressBookService.updateEntry(id, dto);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long id) {
        String response = addressBookService.deleteEntry(id);
        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }
}
