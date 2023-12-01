package dad.micv.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.micv.model.Telefono;
import dad.micv.model.TipoTelefono;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TelefonoDialog extends Dialog<Telefono> implements Initializable {
	
	private static final ButtonType ADD_BUTTON = new ButtonType("Añadir", ButtonData.OK_DONE);
	
	// model
	
	private StringProperty numero = new SimpleStringProperty();
	private ObjectProperty<TipoTelefono> tipo = new SimpleObjectProperty<TipoTelefono>();
	
	// view
	
    @FXML
    private TextField numeroText;

    @FXML
    private ComboBox<TipoTelefono> tipoCombo;

    @FXML
    private GridPane view;

	public TelefonoDialog() {
		super();
		setTitle("Nuevo diálogo");
		setHeaderText("Introduzca el nuevo número de teléfono");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelefonoDialogView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().setAll(ADD_BUTTON, ButtonType.CANCEL);
		setResultConverter(button -> {
			if (button == ADD_BUTTON) {
				Telefono telefono = new Telefono();
				telefono.setNumero(numero.get());
				telefono.setTipo(tipo.get());
				return telefono;
			}
			return null;
		});
		Button addButton = (Button) getDialogPane().lookupButton(ADD_BUTTON);
		addButton.disableProperty().bind(numero.isEmpty().or(tipo.isNull()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// cargamos el combo con los tipos de teléfono
		
		tipoCombo.getItems().setAll(TipoTelefono.values());

		// bindings
		
		numero.bind(numeroText.textProperty());
		tipo.bind(tipoCombo.getSelectionModel().selectedItemProperty());
		
	}
	
}
