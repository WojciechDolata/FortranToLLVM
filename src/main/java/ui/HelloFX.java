package ui;

import fortran.CompilerInstance;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

public class HelloFX extends Application {
    @FXML
    private TextArea codeTextArea;
    private CompilerInstance compilerInstance = new CompilerInstance();
    private Stage stage;
    private File file;
    private File outputDirectory;
    private StringProperty program = new SimpleStringProperty();

    public void initialize() {
        codeTextArea.textProperty().bind(program);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        this.stage = stage;
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void compile() {
        if(file != null) {
            String program = compilerInstance.compileFromFile(file);
            this.program.setValue(program);
            try {
                String fileName;
                if(outputDirectory != null) {
                    fileName = outputDirectory.getAbsolutePath() + "/" + file.getName().replace(".f", ".ll");
                } else {
                    fileName = file.getParent() + "/" + file.getName().replace(".f", ".ll");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(program);
                writer.close();
            } catch (IOException ex) {
                System.out.println("Could not write to file");
            }
        }
    }

    public void pickFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Wybierz plik do kompilacji");
        var file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            this.file = file;
        }
    }

    public void pickTargetFolder() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Wybierz folder docelowy");
        File defaultDirectory = new File(".");
        chooser.setInitialDirectory(defaultDirectory);

        var folder = chooser.showDialog(this.stage);
        if (folder != null) {
            this.outputDirectory = file;
        }
    }


}