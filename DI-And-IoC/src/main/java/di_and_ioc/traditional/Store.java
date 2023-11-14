package di_and_ioc.traditional;

import lombok.Data;
import lombok.Getter;

@Getter
public class Store {
    private Item item;
    public Store() {
        this.item = new Item();
    }
}
