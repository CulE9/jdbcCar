package pl.cule.jdbccar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Route(value = "brands")
public class BrandGUI extends VerticalLayout {

    private TextField textFieldBrand;
    private TextArea textArea;
    private Button button;
    private CarDAO carDAO;

    @Autowired
    public BrandGUI(CarDAO carDAO) {
        this.textFieldBrand = new TextField("Brand:");
        this.textArea = new TextArea();
        this.button = new Button("Pokaż");
        this.carDAO = carDAO;
        setSpacing(false);

        // TODO validation

        button.addClickListener(x -> {
            List<Map<String, Object>> maps = carDAO.showByBrand(textFieldBrand.getValue());
            textArea.setValue(maps.toString());
        });

        textFieldBrand.setWidth("100px"); // or %%
        textArea.setWidth("400px");
        button.setWidth("100px");
        add(textFieldBrand, textArea, button);
    }
}
