package ch.unibe.id.githubactionsdemo.controller;

import ch.unibe.id.githubactionsdemo.model.Item;
import ch.unibe.id.githubactionsdemo.service.MyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final MyService myService;

  @Autowired
  public Controller(MyService myService) {
    this.myService = myService;
  }

  @GetMapping(value = "item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Item> getItemById(@PathVariable("id") Integer id) {
    Item item = this.myService.getItemById(id);
    return item == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(item);
  }

  @GetMapping(value ="item", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Item>> getAllItems() {
    return ResponseEntity.ok(this.myService.getAllItems());
  }

  @PostMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Item> saveItem(@RequestBody Item item) {
    return ResponseEntity.ok(this.myService.saveItem(item));
  }

  @PutMapping(value = "item", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Item> updateItem(@RequestBody Item item) {
    Item savedItem = this.myService.updateItem(item);
    return savedItem == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(savedItem);
  }

  @DeleteMapping(value = "item/{id}")
  public ResponseEntity<Item> deletItemById(@PathVariable("id") Integer id) {
    Item deletedItem = this.myService.deleteItemById(id);
    return deletedItem == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(deletedItem);
  }
}
