package dad.micv.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.micv.model.Personal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PersonalController implements Initializable {

	// model

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>();

	// view

	@FXML
	private TextField apellidosText;

	@FXML
	private TextField nombreText;

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
		
	}

	public BorderPane getView() {
		return view;
	}
	
	// listeners
	
	private void onPersonalChange(ObservableValue<? extends Personal> o, Personal ov, Personal nv) {
		
		if (ov != null) {
			nombreText.textProperty().unbindBidirectional(ov.nombreProperty());
			apellidosText.textProperty().unbindBidirectional(ov.apellidosProperty());
		}
		
		if (nv != null) {
			nombreText.textProperty().bindBidirectional(nv.nombreProperty());
			apellidosText.textProperty().bindBidirectional(nv.apellidosProperty());
		}
		
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
