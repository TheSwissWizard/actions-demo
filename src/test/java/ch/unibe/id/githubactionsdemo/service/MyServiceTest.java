package ch.unibe.id.githubactionsdemo.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ch.unibe.id.githubactionsdemo.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyServiceTest {

  @Autowired
  private MyService myService;

  @Test
  public void testSaveItem() {
    Item item = new Item();
    item.setId(1);
    item.setDescription("Hello World");

    Item item2 = new Item();
    item.setId(2);
    item.setDescription("Hello Hell");

    this.myService.saveItem(item);

    assertThat(this.myService.getAllItems().size()).isEqualTo(1);

    this.myService.saveItem(item2);

    assertThat(this.myService.getAllItems().size()).isEqualTo(2);
  }
}
