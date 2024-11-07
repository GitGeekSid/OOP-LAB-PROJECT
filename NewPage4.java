import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList; // Add this import statement
import java.util.List;

public class NewPage4 extends Application {
    private String userName;
    private String userEmail;
    private String studentId;

    // Project details to display
    private String projectTitle;
    private String projectDescription;
    private String dueDate;
    private List<String> teamMemberIds;
    private List<String> tasks;

    public NewPage4(String userName, String userEmail, String studentId, String projectTitle, String projectDescription, String dueDate, List<String> teamMemberIds, List<TextField> taskFields) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.studentId = studentId;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.dueDate = dueDate;
        this.teamMemberIds = teamMemberIds;
        
        // Extracting tasks from the taskFields
        this.tasks = new ArrayList<>();
        for (TextField taskField : taskFields) {
            tasks.add(taskField.getText());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Todojo - Teams");

        // Create main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new javafx.geometry.Insets(20));

        // Project details
        Label projectTitleLabel = new Label("Project Title: " + projectTitle);
        Label projectDescriptionLabel = new Label("Description: " + projectDescription);
        Label dueDateLabel = new Label("Due Date: " + dueDate);

        // Team members and their tasks
        Label teamMembersLabel = new Label("Team Members and Tasks:");
        VBox teamMembersLayout = new VBox(5);

        for (int i = 0; i < teamMemberIds.size(); i++) {
            String memberId = teamMemberIds.get(i);
            String task = tasks.size() > i ? tasks.get(i) : "No task assigned"; // Check if task exists
            HBox memberTaskLayout = new HBox(10);
            CheckBox taskCheckBox = new CheckBox(task);
            Label memberIdLabel = new Label("Member ID: " + memberId);
            memberTaskLayout.getChildren().addAll(memberIdLabel, taskCheckBox);
            teamMembersLayout.getChildren().add(memberTaskLayout);
        }

        // Back button to return to the dashboard
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            NewPage2 dashboardPage = new NewPage2(userName, userEmail, studentId);
            try {
                dashboardPage.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            primaryStage.close(); // Close the teams page
        });

        // Add components to main layout
        mainLayout.getChildren().addAll(projectTitleLabel, projectDescriptionLabel, dueDateLabel,
                teamMembersLabel, teamMembersLayout, backButton);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}