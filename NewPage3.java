import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class NewPage3 extends Application {
    private String userName;
    private String userEmail;
    private String studentId;

    public NewPage3(String userName, String userEmail, String studentId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.studentId = studentId;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Todojo - Create Project");

        // Create main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new javafx.geometry.Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);

        // Project title input
        Label titleLabel = new Label("Project Title:");
        TextField titleField = new TextField();
        titleField.setPromptText("Enter project title");

        // Project description input
        Label descriptionLabel = new Label("Project Description:");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Enter project description");

        // Due date input
        Label dueDateLabel = new Label("Due Date:");
        DatePicker dueDatePicker = new DatePicker();

        // Team member inputs
        Label teamMembersLabel = new Label("Team Members (ID):");
        VBox teamMembersLayout = new VBox(5);
        TextField memberIdField = new TextField();
        memberIdField.setPromptText("Enter team member ID");
        Button addMemberButton = new Button("Add Member");
        List<String> teamMemberIds = new ArrayList<>();
        List<TextField> taskFields = new ArrayList<>();

        addMemberButton.setOnAction(e -> {
            String memberId = memberIdField.getText();
            if (!memberId.isEmpty()) {
                teamMemberIds.add(memberId);
                TextField taskField = new TextField();
                taskField.setPromptText("Enter task for " + memberId);
                teamMembersLayout.getChildren().add(new Label("Member ID: " + memberId));
                teamMembersLayout.getChildren().add(taskField);
                taskFields.add(taskField);
                memberIdField.clear();
            }
        });

        // Create project button
        Button createProjectButton = new Button("Create Project");
        createProjectButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            String dueDate = (dueDatePicker.getValue() != null) ? dueDatePicker.getValue().toString() : "";

            if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty() || teamMemberIds.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Fields are missing. Please fill all required fields.");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Successfully created project.");
                NewPage4 teamsPage = new NewPage4(userName, userEmail, studentId, title, description, dueDate, teamMemberIds, taskFields);
                try {
                    teamsPage.start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                primaryStage.close(); // Close the create project page
            }
        });

        // Back button to return to the dashboard
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            NewPage2 dashboardPage = new NewPage2(userName, userEmail, studentId);
            try {
                dashboardPage.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.close(); // Close the create project page
        });

        // Add components to main layout
        mainLayout.getChildren().addAll(titleLabel, titleField, descriptionLabel, descriptionArea,
                dueDateLabel, dueDatePicker, teamMembersLabel, memberIdField, addMemberButton, teamMembersLayout,
                createProjectButton, backButton);

        // Add scroll pane for scrolling feature
        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);

        // Set up the scene
        Scene scene = new Scene(scrollPane, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
