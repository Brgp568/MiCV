package dad.micv.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {

	private ListProperty<Telefono> telefonos = new SimpleListProperty<Telefono>(FXCollections.observableArrayList());

	public final ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}

	public final ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}

	public final void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}

}
