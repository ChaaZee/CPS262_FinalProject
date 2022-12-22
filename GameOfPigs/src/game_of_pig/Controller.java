package game_of_pig;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller{
	
	int player1Score = 0;
	int player2Score = 0;
	
	public ImageView image1;
	public ImageView image2;
	
	public Label score1;
	public Label score2;
	
	public Rectangle rect1;
	public Rectangle rect2;
	
	public Label winScore1;
	
	Boolean player1Turn = true;
	
	static ArrayList<GameRecord> records = new ArrayList<GameRecord>();
	ObservableList<GameRecord> r = FXCollections.observableArrayList(records);
	
	@FXML
    private Label player1wins;
	@FXML
    private Label player2wins;
    
    @FXML
    private TableView<GameRecord> recordsTable;
    
    @FXML
    private TableColumn<GameRecord, String> date;
    @FXML
    private TableColumn<GameRecord, String> name;
    @FXML
    private TableColumn<GameRecord, Integer> score;
    @FXML
    private TableColumn<GameRecord, String> winLoss;
    
    public CheckBox bot;
    
    public Button rollButton2;
    public Button holdButton2;
    
    public Label name2;
	
	public void roll1Clicked(ActionEvent event) throws IOException {
		if(player1Turn) {
			int rand = (int)(Math.random() * 6) + 1;
			
			if(rand == 1) {
				image1.setImage(new Image("DiceSide1.png"));
				player1Score = 0;
				player1Turn = false;
				rect1.setVisible(player1Turn);
				rect2.setVisible(!player1Turn);
				
				if(bot.isSelected())
					bot(event);
			}
			if(rand == 2) {
				image1.setImage(new Image("DiceSide2.png"));
				player1Score += rand;
			}
			if(rand == 3) {
				image1.setImage(new Image("DiceSide3.png"));
				player1Score += rand;
			}
			if(rand == 4) {
				image1.setImage(new Image("DiceSide4.png"));
				player1Score += rand;
			}
			if(rand == 5) {
				image1.setImage(new Image("DiceSide5.png"));
				player1Score += rand;
			}
			if(rand == 6) {
				image1.setImage(new Image("DiceSide6.png"));
				player1Score += rand;
			}
			
			score1.setText(player1Score + "");
			
			if(player1Score >= 100) {
				player1Wins(event);
				records.add(new GameRecord(true, "Player 1", player1Score));
				records.add(new GameRecord(false, "Player 2", player2Score));
			}
		}
	}
	
	public void roll2Clicked(ActionEvent event) throws IOException {
		if(!player1Turn) {
			int rand = (int)(Math.random() * 6) + 1;
			
			if(rand == 1) {
				image2.setImage(new Image("DiceSide1.png"));
				player2Score = 0;
				player1Turn = true;
				rect1.setVisible(player1Turn);
				rect2.setVisible(!player1Turn);
			}
			if(rand == 2) {
				image2.setImage(new Image("DiceSide2.png"));
				player2Score += rand;
			}
			if(rand == 3) {
				image2.setImage(new Image("DiceSide3.png"));
				player2Score += rand;
			}
			if(rand == 4) {
				image2.setImage(new Image("DiceSide4.png"));
				player2Score += rand;
			}
			if(rand == 5) {
				image2.setImage(new Image("DiceSide5.png"));
				player2Score += rand;
			}
			if(rand == 6) {
				image2.setImage(new Image("DiceSide6.png"));
				player2Score += rand;
			}
			if(player2Score >= 100) {
				player2Wins(event);
				records.add(new GameRecord(false, "Player 1", player1Score));
				records.add(new GameRecord(true, "Player 2", player2Score));
			}
			score2.setText(player2Score + "");
		}
	}
	
	public void bot(ActionEvent event) throws IOException {
		if(bot.isSelected()) {
			name2.setText("Player 2 (Bot)");
			holdButton2.setDisable(true);
			rollButton2.setDisable(true);

			roll2Clicked(event);
			roll2Clicked(event);
			hold2();
		} else {
			name2.setText("Player 2");
			holdButton2.setDisable(false);
			rollButton2.setDisable(false);
		}
	}
	
	public void displayRecords() {
    	int player1w = 0;
    	int player2w = 0;
    	
    	for(int i = 0; i < records.size(); i++) {
    		if(records.get(i).player == "Player 1" && records.get(i).win == true)
    			player1w++;
    		if(records.get(i).player == "Player 2" && records.get(i).win == true)
    			player2w++;
    	}
    	
    	player1wins.setText("Player 1 wins: " + player1w);
    	player2wins.setText("Player 2 wins: " + player2w);
    	
		name.setCellValueFactory(new PropertyValueFactory<GameRecord, String>("player"));
		score.setCellValueFactory(new PropertyValueFactory<GameRecord, Integer>("score"));
		date.setCellValueFactory(new PropertyValueFactory<GameRecord, String>("date"));
		winLoss.setCellValueFactory(new PropertyValueFactory<GameRecord, String>("win"));
		recordsTable.setItems(r);
    }
	
	public void hold1(ActionEvent event) throws IOException {
		player1Turn = false;
		rect1.setVisible(player1Turn);
		rect2.setVisible(!player1Turn);
		
		if(bot.isSelected())
			bot(event);
	}
	
	public void hold2() {
		player1Turn = true;
		rect1.setVisible(player1Turn);
		rect2.setVisible(!player1Turn);
	}
	
	public void play(ActionEvent event) throws IOException {
		Parent game = FXMLLoader.load(getClass().getResource("GUI.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(game));
		stage.show();
	}
	
	public void menu(ActionEvent event) throws IOException {
		Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(menu));
		stage.show();
	}
	
	public void records(ActionEvent event) throws IOException {
		Parent records = FXMLLoader.load(getClass().getResource("Records.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(records));
		stage.show();
		//displayRecords();
	}
	
	public void player1Wins(ActionEvent event) throws IOException {
		Parent player1Wins = FXMLLoader.load(getClass().getResource("Player1Wins.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(player1Wins));
		stage.show();
	}
	
	public void player2Wins(ActionEvent event) throws IOException {
		Parent player2Wins = FXMLLoader.load(getClass().getResource("Player2Wins.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(player2Wins));
		stage.show();
	}
}
