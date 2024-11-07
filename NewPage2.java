import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class NewPage2 extends Application {
    private String userName;
    private String userEmail;
    private String studentId;

    public NewPage2(String userName, String userEmail, String studentId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.studentId = studentId;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Todojo - Dashboard");

        // Main layout with horizontal borders for left, center, and right sections
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new javafx.geometry.Insets(20));

        // Top layout: Website name and "Dashboard"
        VBox topLayout = new VBox();
        topLayout.setAlignment(Pos.TOP_RIGHT);
        Label websiteLabel = new Label(".ToDojo");
        websiteLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label dashboardLabel = new Label("Dashboard");
        topLayout.getChildren().addAll(websiteLabel, dashboardLabel);

        // Left section with buttons
        VBox leftLayout = new VBox(20);
        leftLayout.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-border-width: 1;");
        leftLayout.setAlignment(Pos.CENTER_LEFT);
        Button createProjectButton = new Button("Create Project");
        Button teamsButton = new Button("View Teams");

        createProjectButton.setOnAction(e -> {
            NewPage3 createProjectPage = new NewPage3(userName, userEmail, studentId);
            try {
                createProjectPage.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.close();
        });

        teamsButton.setOnAction(e -> {
            String projectTitle = "Task manager";
            String projectDescription = "Creating an application to manage tasks effectively";
            String dueDate = "15-11-2024";
            List<String> teamMemberIds = List.of("12345", "67890");
            List<TextField> taskFields = List.of(new TextField("Task for 12345"), new TextField("Task for 67890"));

            NewPage4 teamsPage = new NewPage4(userName, userEmail, studentId, projectTitle, projectDescription, dueDate, teamMemberIds, taskFields);
            try {
                teamsPage.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.close();
        });

        leftLayout.getChildren().addAll(createProjectButton, teamsButton);

        // Center section with "About Us" placeholder
        VBox centerLayout = new VBox();
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setStyle("-fx-padding: 10;");
        Label aboutUsLabel = new Label("About Us");
        aboutUsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
// Placeholder description
Label aboutUsDescription = new Label(
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
// description.setWrapText(true);
// description.setTranslateY(10); // Adds a bit of vertical spacing

        aboutUsDescription.setWrapText(true);

        centerLayout.getChildren().addAll(aboutUsLabel, aboutUsDescription);

        // Right section with user profile details
        VBox rightLayout = new VBox(10);
        rightLayout.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-border-width: 1;");
        rightLayout.setAlignment(Pos.TOP_LEFT);

        Label userLabel = new Label("Name: " + userName);
        Label emailLabel = new Label("Email: " + userEmail);
        Label idLabel = new Label("Student ID: " + studentId);
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            NewPage1 signupPage = new NewPage1();
            try {
                signupPage.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.close();
        });

        rightLayout.getChildren().addAll(userLabel, emailLabel, idLabel, logoutButton);

        // Set sections in BorderPane
        mainLayout.setTop(topLayout);
        mainLayout.setLeft(leftLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setRight(rightLayout);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
