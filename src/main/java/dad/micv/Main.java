package dad.micv;

import java.io.IOException;

import dad.micv.ui.MiCVApp;
import javafx.application.Application;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Application.launch(MiCVApp.class, args);
		
//		CV cv = new CV();
//		cv.getPersonal().setIdentificacion("12345678Z");
//		cv.getPersonal().setNombre("Chuck");
//		cv.getPersonal().setApellidos("Norris");
//		cv.getPersonal().setFechaNacimiento(LocalDate.of(1945, 10, 25));
//		cv.getPersonal().getNacionalidades().add(new Nacionalidad("estadounidense"));
//		cv.getPersonal().getNacionalidades().add(new Nacionalidad("mexicana"));
//		cv.getContacto().getTelefonos().add(new Telefono(TipoTelefono.DOMICILIO, "922102030"));
//		cv.save(new File("cnorris.cv"));
//		
//		CV cv2 = CV.load(new File("cnorris.cv"));
//		System.out.println(cv2.getPersonal().getIdentificacion());
		
	}

}
