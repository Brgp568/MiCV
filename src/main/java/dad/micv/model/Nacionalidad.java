package dad.micv.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nacionalidad {

	private StringProperty denominacion = new SimpleStringProperty();
	
	public Nacionalidad() {}

	public Nacionalidad(String denominacion) {
		setDenominacion(denominacion);
	}

	public final StringProperty denominacionProperty() {
		return this.denominacion;
	}

	public final String getDenominacion() {
		return this.denominacionProperty().get();
	}

	public final void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	
	@Override
	public String toString() {
		return getDenominacion();
	}
	
	public static List<Nacionalidad> loadNacionalidades() {
		try {
			InputStream is = Nacionalidad.class.getResourceAsStream("/csv/nacionalidades.csv");
			BufferedInputStream bis = new BufferedInputStream(is);
			String content = new String(bis.readAllBytes(), StandardCharsets.UTF_8);		
			String [] lines = content.split("\n");
			return Arrays.asList(lines)
				.stream()
				.map(String::trim)
				.map(Nacionalidad::new)
				.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static List<String> loadPaises() {
		try { 
			InputStream is = Nacionalidad.class.getResourceAsStream("/csv/paises.csv");
			BufferedInputStream bis = new BufferedInputStream(is);
			String content = new String(bis.readAllBytes(), StandardCharsets.UTF_8);		
			String [] lines = content.split("\n");
			return Arrays.asList(lines)
				.stream()
				.map(String::trim)
				.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
