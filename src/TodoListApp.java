
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Asus
 */
// Inheritance: Kelas utama TodoListApp mewarisi Application
public class TodoListApp extends Application implements TodoActions {

    private static int itemCount = 0; // Static Modifier: Counter untuk menghitung total item
    private final ArrayList<TodoItem> todoItems = new ArrayList<>();
    private final ListView<String> listView = new ListView<>();

    @Override
    public void start(Stage primaryStage) {
        // Layout utama
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Header
        Label header = new Label("Todo List");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #0066cc;");
        root.setTop(header);
        BorderPane.setMargin(header, new Insets(10, 0, 20, 0));

        // List View
        listView.setStyle("-fx-font-size: 14px;");
        root.setCenter(listView);

        // Input Field dan Tombol
        TextField inputField = new TextField();
        inputField.setPromptText("Enter a new task");
        inputField.setStyle("-fx-font-size: 14px;");

        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button toggleButton = new Button("Toggle Complete");

        addButton.setStyle("-fx-background-color: #009933; -fx-text-fill: white; -fx-font-size: 14px;");
        removeButton.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-size: 14px;");
        toggleButton.setStyle("-fx-background-color: #0066cc; -fx-text-fill: white; -fx-font-size: 14px;");

        HBox controls = new HBox(10, addButton, removeButton, toggleButton);
        controls.setPadding(new Insets(10, 0, 0, 0));

        VBox inputSection = new VBox(10, inputField, controls);
        root.setBottom(inputSection);

        // Event Handlers
        addButton.setOnAction(e -> {
            String description = inputField.getText().trim();
            if (!description.isEmpty()) {
                addItem(description);
                inputField.clear();
            } else {
                showAlert("Error", "Todo item cannot be empty.", Alert.AlertType.ERROR);
            }
        });

        removeButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                removeItem(selectedIndex);
            } else {
                showAlert("Error", "Please select an item to remove.", Alert.AlertType.ERROR);
            }
        });

        toggleButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                toggleItem(selectedIndex);
            } else {
                showAlert("Error", "Please select an item to toggle.", Alert.AlertType.ERROR);
            }
        });

        // Scene dan Stage
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Todo List App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Static Method: Mengembalikan jumlah item
    public static int getItemCount() {
        return itemCount;
    }

    // Implementasi metode dari interface TodoActions
    @Override
    public void addItem(String description) {
        TodoItem newItem = new TodoItem(description);
        todoItems.add(newItem);
        listView.getItems().add(description);
        itemCount++;
    }

    @Override
    public void removeItem(int index) {
        todoItems.remove(index);
        listView.getItems().remove(index);
        itemCount--;
    }

    @Override
    public void toggleItem(int index) {
        TodoItem item = todoItems.get(index);
        item.setCompleted(!item.isCompleted());
        String updatedDescription = item.isCompleted() ? "[Completed] " + item.getDescription() : item.getDescription();
        listView.getItems().set(index, updatedDescription);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
