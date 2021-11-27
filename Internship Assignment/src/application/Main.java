package application;

import javafx.application.Application;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;	
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.*;

public class Main extends Application
{
	private Button button1;
	private Button button2;
	private TextField textfield1;
	private TextField textfield2;
	private Label label1;
	private Label label2;
	private Label heading;
	private Font labelFont;
	private Font headingFont;
	private Font buttonFont;
	private Font textfieldFont;
	private Alert alert;
	private TableView<Person> table;
	private TableColumn<Person,String> column1;
	private TableColumn<Person,String> column2;
	private Map<String,String> infos;
	
	public static void main(String gg[])
	{
		launch();
	}
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setTitle("A Java FX GUI Application");	 	
		infos=new HashMap<>();
		
		table=new TableView<>();
		column1=new TableColumn<Person,String>("Name");
		column1.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
		column1.setMinWidth(100);
		
		column2=new TableColumn<Person,String>("Email");
		column2.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));
		column2.setMinWidth(200);
		
		table.getColumns().add(column1);
		table.getColumns().add(column2);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		Pane p=new Pane();
		
		label1=new Label("Name");
		label2=new Label("Email");
		heading=new Label("GC Consulting Pvt. Ltd.");
		
		labelFont = Font.font("Arial", FontWeight.BOLD, 22);
		headingFont = Font.font("Verdana", FontWeight.BOLD, 28);
		buttonFont = Font.font("Arial", FontWeight.SEMI_BOLD, 20);
		textfieldFont = Font.font("Arial", FontWeight.NORMAL, 18);
			
		button1=new Button("Submit");
		button1.setPrefWidth(120);
		button1.setPrefHeight(40);
		button1.relocate(140, 200);
		button1.setFont(buttonFont);
		
		button2=new Button("Show records");
		button2.setPrefWidth(100);
		button2.setPrefHeight(5);
		button2.relocate(295, 268);
		//button2.setFont(buttonFont);
		
		textfield1=new TextField();
		textfield2=new TextField();
		
		textfield1.setPrefSize(250, 20);
		textfield2.setPrefSize(250, 20);
		
		textfield1.setFont(textfieldFont);
		textfield2.setFont(textfieldFont);
		
		textfield1.relocate(90,90);
		textfield2.relocate(90,140);
		
		label1.setFont(labelFont);	
		label1.relocate(15,100+4-10);
		
		label2.setFont(labelFont);	
		label2.relocate(16,140+5);
		
		heading.setFont(headingFont);
		heading.relocate(20,20);
		heading.setTextFill(Color.RED);
		
		p.getChildren().addAll(label1,heading,textfield1,textfield2,button1,button2,label2);
			
		primaryStage.setScene(new Scene(p,400,300));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event)
			{
				if(textfield1.getText()=="" || textfield2.getText()=="")
				{
					alert=new Alert(AlertType.ERROR);
					alert.setContentText("Please add name and email.");
					alert.setTitle("GC Consulting");
					alert.setHeaderText("ERROR");
					alert.show();
															
				}
				else
				{
					if(addInTable(textfield1.getText(),textfield2.getText()))
					{
						alert=new Alert(AlertType.INFORMATION);
						alert.setContentText("Name : "+textfield1.getText()+" and Email : "+textfield2.getText()+" is added");
						alert.setHeaderText("Informaion saved");
						alert.setTitle("GC Consulting");
						alert.show();
					}
				}
			}
			
			
		});
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event)
			{
				if(infos.isEmpty())
				{
					alert=new Alert(AlertType.ERROR);
					alert.setContentText("No records");
					alert.setTitle("GC Consulting");
					alert.setHeaderText("ERROR");
					alert.show();
															
				}
				else
				{
					Stage s=new Stage();
					s.setTitle("Record log");
					s.setScene(new Scene(new Pane(table),300,400));
					s.setResizable(false);
					s.show();
				}
			}
			
			
		});
	
		
	}
	
	public boolean addInTable(String name,String email)
	{
		
		if(infos.containsKey(email)) 
		{
			alert=new Alert(AlertType.ERROR);
			alert.setContentText("Email : "+email+" already added.");
			alert.setHeaderText("Error");
			alert.setTitle("GC Consulting");
			alert.show();
			return false;
		}
		infos.put(email,name);
		table.getItems().add(new Person(email,name));
		return true;
	}	
}
