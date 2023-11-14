package di_and_ioc.di;

import di_and_ioc.traditional.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DIComponent {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    FieldBean fieldBean;

    @Autowired
    SetterBean setterBean;

    @Autowired
    StoreBean storeBean;

    public void diEx() {
        log.info("[DI] store object: " + storeBean);
        log.info("[DI] fieldBean: " + storeBean.getFieldBean());
        log.info("[DI] ConstructorBean: " + storeBean.getConstructorBean());
        log.info("[DI] setterBean: " + storeBean.getSetterBean());
    }

}
