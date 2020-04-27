package org.alekskorn.manager.file;

import com.sun.javafx.binding.StringFormatter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    @FXML
    ListView<FileInfo> filesList;

//    public Controller() {
//        filesList.getItems().addAll("xczxc","zxczxczcx","sdrwefx");
//    }
    public void menuItemFileExitAction() {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Path root = Paths.get("1");
        filesList.getItems().addAll(scanFiles(root));
        filesList.setCellFactory(new Callback<ListView<FileInfo>, ListCell<FileInfo>>() {
            @Override
            public ListCell<FileInfo> call(ListView<FileInfo> param) {
                return new ListCell<FileInfo>() {
                    @Override
                    protected void updateItem(FileInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            String formattedFileName = String.format("%-30s",item.getName());
                            String formattedSize = String.format("%,d bytes", item.getLength());
                            if (item.getLength() == -1) {
                                formattedSize = String.format("%s", "[DIR]");
                            }
                            String text= String.format("%s %-20s", formattedFileName, formattedSize);
                            setText(text);
                        }
                    }
                };
            }
        });

    }
    public List<FileInfo> scanFiles (Path root) {
        try {
            return Files.list(root).map(FileInfo::new).collect(Collectors.toList());
        } catch (IOException ex) {
            ex.getStackTrace();
            return null;
        }
    }
}
