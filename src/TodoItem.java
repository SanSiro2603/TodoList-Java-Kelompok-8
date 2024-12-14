import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

// Encapsulation: Semua atribut private dengan getter dan setter
class TodoItem {
    private String description;
    private boolean isCompleted;

    public TodoItem(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

// Abstract Class / Interface: Menggunakan interface untuk tindakan TodoItem
interface TodoActions {
    void addItem(String description);
    void removeItem(int index);
    void toggleItem(int index);
}
