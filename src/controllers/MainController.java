package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import pdfaux.ImportFiles;
import java.io.File;
import java.util.Iterator;


public class MainController {
    String version = "0.0.1";
    @FXML
    Label lbVersion;

    @FXML
    Tab tbConfDados,tbOutConfig;
    
    @FXML
    TextField txtFilePath;

    @FXML
    Button btnConfirm;

    FileChooser fileChooser = new FileChooser();

    @FXML
    TableView<PDField> tbForms;
    @FXML
    TableColumn<PDField, String> colFieldName,colFieldType,colFieldLocation,colFieldDesc,colFieldOptions,colFieldValue,
            colFieldDefaultValue;
    private PDDocument pdDocument;

    @FXML
    public void initialize(){
        lbVersion.setText("Version: "+version);
        colFieldName.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));
        colFieldType.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));
        colFieldLocation.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));
        colFieldDesc.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));
        colFieldOptions.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));
        colFieldValue.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));
        colFieldDefaultValue.setCellValueFactory(new PropertyValueFactory<PDField,String>("FullyQualifiedName"));

    }
    public void importUrlFile(){
        //Implementar download de ficheiro
        System.out.println("Importing URL file...");
    }
    public void importLocalFile(){
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            txtFilePath.setText(file.getPath());
            try {
                pdDocument = ImportFiles.importPDF(file);
                tbForms.setItems(getFields());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
        if(isFileImported()){
            tbConfDados.setDisable(false);
        }
    }
    public boolean isFileImported(){
        return txtFilePath.getText().length()!=0;
    }
    public void confirm(){
        tbOutConfig.setDisable(false);
    }

    public ObservableList<PDField> getFields(){
        ObservableList<PDField> fields = FXCollections.observableArrayList();
        Iterator<PDField> temp = pdDocument.getDocumentCatalog().getAcroForm().getFieldIterator();
        while (temp.hasNext()){
            fields.add(temp.next());
        }
        return fields;
    }



}
