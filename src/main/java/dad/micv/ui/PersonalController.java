package dad.micv.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.model.Nacionalidad;
import dad.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PersonalController implements Initializable {

	// model

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();
	private ObjectProperty<Nacionalidad> nacionalidadSeleccionada = new SimpleObjectProperty<Nacionalidad>();

	// view

	@FXML
	private TextField apellidosText;

	@FXML
	private TextField nombreText;
	
    @FXML
    private Button addNacionalidadButton;


    @FXML
    private ListView<Nacionalidad> nacionalidadesList;

    @FXML
    private Button removeNacionalidadButton;

	@FXML
	private BorderPane view;

	public PersonalController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personal.addListener(this::onPersonalChange);

		nacionalidadSeleccionada.bind(nacionalidadesList.getSelectionModel().selectedItemProperty());
		
		removeNacionalidadButton.disableProperty().bind(nacionalidadSeleccionada.isNull());
				
	}

	public BorderPane getView() {
		return view;
	}
	
	// listeners
	
	private void onPersonalChange(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {
		
		if (ov != null) {
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
			nacionalidadesList.itemsProperty().unbind();
		}
		
		if (nv != null) {
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
			nacionalidadesList.itemsProperty().bind(nv.nacionalidadesProperty());
		}
		
	}

    @FXML
    void onAddNacionalidadAction(ActionEvent event) {
    	
//    	ObservableList<Nacionalidad> nacionalidades = personal.get().getNacionalidades();
//
//    	ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<Nacionalidad>();
//    	dialog.initOwner(MiCVApp.primaryStage);
//    	dialog.setTitle("Añadir nacionalidad");
//    	dialog.setHeaderText("Añadir una nueva nacionalidad a '" + personal.get().getNombreCompleto() + "'");
//    	dialog.setContentText("Nacionalidades:");
//    	dialog.getItems().addAll(Nacionalidad.loadNacionalidades());
//    	dialog.getItems().removeAll(nacionalidades);
////    	dialog.showAndWait().ifPresent(nacionalidad -> nacionalidades.add(nacionalidad));
//    	dialog.showAndWait().ifPresent(nacionalidades::add);
    	
    	TelefonoDialog dialog = new TelefonoDialog();
    	dialog.showAndWait().ifPresent(telefono -> {
    		System.out.println(telefono);
    	});
    	
    }

    @FXML
    void onRemoveNacionalidadAction(ActionEvent event) {
    	
    	Alert confirmacion = new Alert(AlertType.CONFIRMATION);
    	confirmacion.initOwner(MiCVApp.primaryStage);
    	confirmacion.setTitle("Eliminar nacionalidad");
    	confirmacion.setHeaderText("Está eliminando la nacionalidad '" + nacionalidadSeleccionada.get() + "'");
    	confirmacion.setContentText("¿Desea continuar?");
    	confirmacion.showAndWait()
    		.ifPresent(button -> {
    			if (button == ButtonType.OK) {
    				Nacionalidad nacionalidad = nacionalidadSeleccionada.get();
    				personal.get().getNacionalidades().remove(nacionalidad);
    			}
    		});
    	    	
    }

	// getters y setters

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

}
