import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Random;

public class NewPage1 extends Application {
    private String userName;
    private String userEmail;
    private String studentId;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Todojo - Home");

        // Create main layout with scroll pane
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new javafx.geometry.Insets(20));
        
        // Scrollable layout
        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        
        // Header for website name and description
        VBox header = new VBox();  // Changed from HBox to VBox for vertical stacking
        header.setAlignment(Pos.TOP_RIGHT);
        
        // Website name
        Text websiteName = new Text(".ToDojo");
        websiteName.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Placeholder description
    // Placeholder description
Label description = new Label(
    "About Us\n" +
    "Welcome to .ToDojo!\n" +
    "Our platform is designed to solve the challenges of team task and project management, including missed deadlines, overlapping responsibilities, and limited collaboration.\n" +
    "We believe that with the right tools, teams can work more effectively, stay aligned on objectives, and achieve their goals.\n\n" +
    "What We Offer:\n\n" +
    "Project Planning: Define objectives and timelines to keep teams aligned.\n" +
    "Task Management: Assign tasks, track progress, and stay organized.\n" +
    "Facilitate collaboration and communication among team members.\n\n" +
    ".ToDojo empowers teams to stay on track and work together more effectively, leading to more productive and successful projects."
);
description.setWrapText(true);
description.setTranslateY(10); // Adds a bit of vertical spacing

        
        // Add name and description to header
        header.getChildren().addAll(websiteName, new Label(""), description);  // Adding an empty label to create space

        // User name input
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        // User email input
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your email");

        // Signup button with error handling for empty fields
        Button signupButton = new Button("Sign Up");
        signupButton.setOnAction(e -> {
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty()) {
                // Show error if fields are empty
                showErrorAlert("Fields missing", "Please fill in all fields before signing up.");
            } else {
                userName = nameField.getText();
                userEmail = emailField.getText();
                studentId = generateRandomStudentId();

                // Navigate to the dashboard
                NewPage2 dashboardPage = new NewPage2(userName, userEmail, studentId);
                try {
                    dashboardPage.start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                primaryStage.close(); // Close the signup page
            }
        });

        // Add components to main layout
        mainLayout.getChildren().addAll(header, nameLabel, nameField, emailLabel, emailField, signupButton);

        // Set up the scene
        Scene scene = new Scene(scrollPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String generateRandomStudentId() {
        Random random = new Random();
        return String.valueOf(10000 + random.nextInt(90000));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
