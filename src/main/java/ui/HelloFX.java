package ui;

import fortran.CompilerInstance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloFX extends Application {

    private CompilerInstance compilerInstance = new CompilerInstance();
    private Stage stage;
    private File file;

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
            try {
                var fileName = file.getParent() + "/" + file.getName().replace(".f", ".llm");
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
        fileChooser.setTitle("Wybierz plik do kompilacji");
        var file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            this.file = file;
        }
    }



}