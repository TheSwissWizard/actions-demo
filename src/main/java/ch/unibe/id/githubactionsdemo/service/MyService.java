package ch.unibe.id.githubactionsdemo.service;

import ch.unibe.id.githubactionsdemo.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  private List<Item> items = new ArrayList<>();

  public Item getItemById(Integer id) {
    return this.items.stream()
        .filter(item -> item.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public List<Item> getAllItems() {
    return this.items;
  }

  public Item updateItem(Item newItem) {
    int index = findIfItemExists(newItem.getId());

    if (index == -1) {
      return null;
    }

    return this.items.set(index, newItem);
  }

  public Item saveItem(Item newItem) {
    int index = findIfItemExists(newItem.getId());

    if (index == -1) {
      this.items.add(newItem);
      return newItem;
    }
    return updateItem(newItem);
  }

  public Item deleteItemById(Integer id) {
    int index = findIfItemExists(id);

    if (index == -1) {
      return null;
    }
    return this.items.remove(index);
  }

  private int findIfItemExists(Integer id) {
    return IntStream.range(0, this.items.size())
        .filter(i -> this.items.get(i).getId().equals(id))
        .findFirst().orElse(-1);
  }
}
