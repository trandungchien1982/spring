package di_and_ioc.di;

import di_and_ioc.traditional.Item;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class StoreBean {

    @Autowired
    FieldBean fieldBean;

    ItemBean constructorBean = null;

    SetterBean setterBean = null;

    public StoreBean(ItemBean constructorBean) {
        this.constructorBean = constructorBean;
    }

    public void setSetterBean(SetterBean setterBean) {
        this.setterBean = setterBean;
    }
}
