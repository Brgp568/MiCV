package dad.micv.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.micv.model.CV;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {
	
	// controllers
	
	private PersonalController personalController = new PersonalController();
	
	// model
	
	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();
	private File cvFile = null;
	
	// view
	
	@FXML
    private Tab conocimientosTab;

    @FXML
    private Tab contactoTab;

    @FXML
    private Tab experienciaTab;

    @FXML
    private Tab formacionTab;

    @FXML
    private Tab personalTab;

    @FXML
    private BorderPane view;
	
	public MainController() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personalTab.setContent(personalController.getView());
		
		
		cv.addListener(this::onCVChanged);
		
		cv.set(new CV());

	}

	public BorderPane getView() {
		return view;
	}

    @FXML
    void onAbrirAction(ActionEvent event) {
    	File cvFile = null;
    	try {
        	FileChooser loadDialog = new FileChooser();
        	loadDialog.getExtensionFilters().add(new ExtensionFilter("Curriculum Vitae (*.cv)", "*.cv"));
        	loadDialog.getExtensionFilters().add(new ExtensionFilter("Todos los archivos (*.*)", "*.*"));
        	cvFile = loadDialog.showOpenDialog(MiCVApp.primaryStage);
        	if (cvFile != null) {
        		cv.set(CV.load(cvFile));
        		this.cvFile = cvFile;
        	}
		} catch (Exception e) {
			Alert error = new Alert(AlertType.ERROR);
			error.initOwner(MiCVApp.primaryStage);
			error.setTitle("Error");
			error.setHeaderText("No se pudo abrir el archivo '" + cvFile.getName() + "'");
			error.setContentText(e.getMessage());
			error.showAndWait();
			e.printStackTrace();
		}
    }

    @FXML
    void onGuardarAction(ActionEvent event) {
    	if (cvFile == null) {
    		onGuardarComoAction(event);
    	} else {
        	try {
        		cv.get().save(cvFile);
    		} catch (Exception e) {
    			Alert error = new Alert(AlertType.ERROR);
    			error.initOwner(MiCVApp.primaryStage);
    			error.setTitle("Error");
    			error.setHeaderText("No se pudo guardar el archivo '" + cvFile.getName() + "'");
    			error.setContentText(e.getMessage());
    			error.showAndWait();
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void onGuardarComoAction(ActionEvent event) {
    	FileChooser loadDialog = new FileChooser();
    	loadDialog.getExtensionFilters().add(new ExtensionFilter("Curriculum Vitae (*.cv)", "*.cv"));
    	loadDialog.getExtensionFilters().add(new ExtensionFilter("Todos los archivos (*.*)", "*.*"));
    	File cvFile = loadDialog.showSaveDialog(MiCVApp.primaryStage);
    	if (cvFile != null) {
    		this.cvFile = cvFile; 
        	onGuardarAction(event);
    	}
    }

    @FXML
    void onNuevoAction(ActionEvent event) {
    	cv.set(new CV());
    }

    @FXML
    void onSalirAction(ActionEvent event) {
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.initOwner(MiCVApp.primaryStage);
    	confirm.setTitle("Salir");
    	confirm.setHeaderText("¿Seguro que quiere salir?");
    	confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
    	Optional<ButtonType> response = confirm.showAndWait();
    	if (response.isPresent() && response.get().equals(ButtonType.YES)) {
    		MiCVApp.primaryStage.close();
    	}
    }

	private void onCVChanged(ObservableValue<? extends CV> o, CV ov, CV nv) {
		
		if (ov != null) {
			personalController.personalProperty().unbind();
		}
		
		if (nv != null) {
			personalController.personalProperty().bind(cv.get().personalProperty());
		}
		
	}
    
}
