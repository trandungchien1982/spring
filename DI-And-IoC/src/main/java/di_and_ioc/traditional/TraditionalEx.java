package di_and_ioc.traditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraditionalEx {

    Logger log = LoggerFactory.getLogger(getClass());

    public void traditionalImpl() {
        Store store = new Store();
        Item item = store.getItem();

        log.info(" >> Traditional: item object = " + item);
        log.info(" >> Traditional: item.id = " + item.getId());
        log.info(" >> Traditional: item.name = " + item.getName());
    }
}
