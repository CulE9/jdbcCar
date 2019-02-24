package pl.cule.jdbccar;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "addcar")
public class CarGUI extends VerticalLayout {

    private TextField textFieldId;
    private TextField textFieldBrand;
    private TextField textFieldModel;
    private TextField textFieldColor;
    private Button button;
    private Dialog dialog = new Dialog();

    private CarDAO carDAO;

    @Autowired
    public CarGUI(CarDAO carDAO) {
        this.textFieldId = new TextField("Id:");
        this.textFieldBrand = new TextField("Brand:");
        this.textFieldModel = new TextField("Model:");
        this.textFieldColor = new TextField("Color:");
        this.button = new Button("Dodaj");
        this.carDAO = carDAO;
        setSpacing(false);
        dialog.add(new Label("Car added to database"));

        //TODO validation

        button.addClickListener(buttonClickEvent -> {
            Car car = new Car(Long.parseLong(textFieldId.getValue()), textFieldBrand.getValue(), textFieldModel
                    .getValue(), textFieldColor.getValue());
            carDAO.save(car);
            dialog.open();
        });

        add(textFieldId, textFieldBrand, textFieldModel, textFieldColor, button);
    }
}
