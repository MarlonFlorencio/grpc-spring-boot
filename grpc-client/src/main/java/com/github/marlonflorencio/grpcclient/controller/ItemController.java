package com.github.marlonflorencio.grpcclient.controller;

import com.github.marlonflorencio.grpcclient.model.Item;
import com.github.marlonflorencio.grpcclient.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

//    @GetMapping()
//    public ResponseEntity<?> getAllUsers() {
//        Iterable<User> users = userSpringDataService.findAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @PostMapping()
   public ResponseEntity<Item> createItem(@RequestBody Item request) throws IOException {
        Item item = this.itemService.create(request);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> get(@PathVariable("id") String id)  {
        Item item = this.itemService.get(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("{id}/list")
    public ResponseEntity<List<Item>> getItems(@PathVariable("id") String id)  {
        List<Item> items = this.itemService.getItems(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}

