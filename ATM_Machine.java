import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class ATM_Machine extends Application {
	ATM zaghloul_ATM = new ATM();
	User current_User;
	int navigator = 0;
	private boolean status_flag_prev = false;
	private boolean used = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		zaghloul_ATM.addUser(new User(1000, "AbdulRahman", 100.0, 1111));
		zaghloul_ATM.addUser(new User(2000, "Muhammad", 100.0, 2222));
		zaghloul_ATM.addUser(new User(3000, "Saad", 100.0, 3333));
		zaghloul_ATM.addUser(new User(4000, "Aly", 100.0, 4444));
		zaghloul_ATM.addUser(new User(5000, "Zaghloul", 100.0, 5555));
		primaryStage.setScene(getMainGUI(primaryStage));
		primaryStage.show();
	}
	
	Scene getBalance(Stage stage) {
		GridPane root=new GridPane();
TextField txt=new TextField();
root.add(txt, 0, 0);
return new Scene(root, 200 , 200);
	}

	Scene getDepositGUI(Stage stage) {
		GridPane root = new GridPane();
		Label cardLabel = new Label("Enter amount: ");
		TextField input = new TextField();
		Button save =new Button("save");
		root.add(input, 1, 0);
		root.add(cardLabel, 0, 0);
		root.add(save, 1, 1);
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Double amount;
			
				zaghloul_ATM.deposit(current_User, amount);
				stage.setScene(getMainMenuGUI(stage));
				stage.show();}
			});
		
		return new Scene(root, 300, 200);

	
	}

	Scene getMainMenuGUI(Stage stage) {
		Button choose = new Button("Deposit");
		Button submit = new Button("Withdraw");
		Button balance = new Button("show balance");
		Button nextButton = new Button("Next");
		Button prevButton = new Button("Previous");
		Label lbl = new Label("-----------------------");
		GridPane root = new GridPane();
		root.add(choose, 0, 0);
		root.add(submit, 2, 2);
		root.add(balance, 4, 4);
		root.add(nextButton, 0, 5);
		root.add(prevButton, 0, 6);
		root.add(lbl, 0, 7);
		
		balance.setOnAction(new EventHandler<ActionEvent>() {
	@Override
		public void handle(ActionEvent arg0) {
			stage.setScene(getBalance(stage));
				stage.show();
			
	});
		//}
			
		
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg) {
				Object[] transacationsObjects = zaghloul_ATM.getTransaction().getTransactions();
				status_flag_prev = false;
				if (used == true) {
					used = false;
					navigator = (navigator == transacationsObjects.length) ? transacationsObjects.length
							: (navigator + 1);
				}
				prevButton.setDisable(false);
				String txt = transacationsObjects[navigator++].toString();
				lbl.setText(txt);
				if (navigator == transacationsObjects.length)
					nextButton.setDisable(true);
			}
		});
		prevButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg) {
				used = true;
				if (status_flag_prev == false) {
					status_flag_prev = true;
					navigator = (navigator == 0) ? 0 : (navigator - 1);
				}
				nextButton.setDisable(false);
				Object[] transacationsObjects = zaghloul_ATM.getTransaction().getTransactions();
				String txt = transacationsObjects[--navigator].toString();
				lbl.setText(txt);
				if (navigator == 0)
					prevButton.setDisable(true);

			}
		});
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(getWithdrawGUI(stage));
				stage.show();
			}

		});

//		return new Scene(root, 400, 400);

	
		choose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage.setScene(getDepositGUI(stage));
				stage.show();
			}

		});

		return new Scene(root, 400, 400);}
	

	Scene getWithdrawGUI(Stage stage) {
		GridPane root = new GridPane();
		Label cardLabel = new Label("Enter amount: ");
		TextField input = new TextField();
		root.add(input, 1, 0);
		root.add(cardLabel, 0, 0);
		Button save = new Button("save");
		root.add(save, 1, 1);

		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Double var = Double.parseDouble(input.getText());
				if (var < current_User.getBalance()) {
					zaghloul_ATM.withDraw(current_User, var);
					stage.setScene(getMainMenuGUI(stage));
					stage.show();
				}

				else {
					Alert error = new Alert(AlertType.ERROR);
					error.setContentText("error !!");
					error.show();
				}
			}
		});
		return new Scene(root, 300, 200);
	}

	Scene getMainGUI(Stage stage) {

		Button submit = new Button("Submit");
		Label cardLabel = new Label("Enter card number: ");
		Label password = new Label("Enter your password: ");
		TextField card_input = new TextField();
		TextField password_input = new TextField();
		GridPane root = new GridPane();
		root.add(cardLabel, 0, 0);
		root.add(card_input, 1, 0);
		root.add(password, 0, 1);
		root.add(password_input, 1, 1);
		root.add(submit, 0, 2);
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				User test_User = new User(Integer.parseInt(card_input.getText()),
						Integer.parseInt(password_input.getText()));
				current_User = zaghloul_ATM.searchForUser(test_User);
				if (current_User != null) {
					stage.setScene(getMainMenuGUI(stage));
					stage.show();
				}

				else {
					Alert error = new Alert(AlertType.ERROR);
					error.setContentText("Wrong Card Number");
					error.show();
				}
			}
		});
		return new Scene(root, 400, 400);
	}
}