package dad.micv.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import dad.micv.json.LocalDateAdapter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<Personal>(new Personal());
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<Contacto>(new Contacto());
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(FXCollections.observableArrayList());

	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

	public final ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}

	public final ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}

	public final void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}

	// -----------------------------------------------
	
	private static Gson FXGSON = FxGson.fullBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
	
	/**
	 * Guarda el CV en un fichero en formato JSON
	 * @param cvFile fichero donde se almacena el CV
	 * @throws IOException En caso de error de escritura
	 */
	public void save(File cvFile) throws Exception {
		String json = FXGSON.toJson(this, CV.class);
		Files.writeString(
			cvFile.toPath(), 
			json, 
			StandardCharsets.UTF_8, 
			StandardOpenOption.CREATE
		);		
	}
	
	/**
	 * Carga un CV desde fichero
	 * @param cvFile fichero con el CV en JSON
	 * @return Instancia de CV
	 * @throws IOException En case de error de lectura
	 */
	public static CV load(File cvFile) throws Exception {
		return FXGSON.fromJson(
			new FileReader(cvFile, StandardCharsets.UTF_8), 
			CV.class
		);
	}
	
}
