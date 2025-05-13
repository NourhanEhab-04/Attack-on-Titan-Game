package game.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.PriorityQueue;

import game.engine.Battle;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import game.engine.weapons.WallTrap;
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.Weapon;
import game.engine.weapons.WeaponRegistry;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application implements EventHandler<ActionEvent> {
	private  Button startButton;
	private RadioButton easyButton;
	private RadioButton hardButton;
	private ToggleGroup toggleGroup;
	private Scene StartScene;
	private Scene PlayScene;
	private Battle battle;
	private Label scoreLabel;
	private Label turnLabel;
	private Label phaseLabel;
	private Label resourcesLabel;
	private Button[] weaponShopButtons;
	private Label[] availableLanes;
	private HBox displayed;
	private HBox weapons;
	private HBox[] laneTitans;
	private HBox[] laneWeapons;
	private HBox weaponsLane;
	private VBox allweapons;
	private AnchorPane titansLane;
	private VBox alltitans;
	private Scene easyScene;
	private GridPane easy;
	private HBox[] allLanes;
	private Label s;
	private Button passButton;
	private Stage stage;
	private Scene hardScene;
	//private [] allwalls;
	private VBox layout;
	private GridPane hard;
	private Label h;
	private VBox values;
	private String[] w;
	private Label[] wallButtons;
	private AnchorPane second;
	private Label[] weaponinfo;
	private Button goB;
	private HBox Resth;
	private HBox Reste;
	private Boolean[] flags;

	
	
	public void setImageofScene(Image image,Stage primaryStage) {
		BackgroundImage background = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		StackPane current=new StackPane();
		current.setBackground(new Background(background));
		current.setPrefSize(800,600);
		Scene scene=new Scene(current);
		Button back=new Button("<-");
		back.setLayoutX(100);
		back.setLayoutY(150);
		current.getChildren().add(back);
		back.setOnAction(e->switchScene(PlayScene));
		primaryStage.setScene(scene);
       
	}
	public void switchScene(Scene scene2) {
		stage.setScene(scene2);
	}
	
	/**public void displayWeapon() {
		GridPane d=new GridPane();
		Label info=new Label();
		Scene scene=new Scene(d);
		
		 for (int j = 0; j < 4; j++) {
		        WeaponRegistry r=battle.getWeaponFactory().getWeaponShop().get(j+1);
		        //String inf="";
		        weaponShopButtons[j].setOnAction(e -> {
		            info.setText(r.getName()+"\n"+r.getPrice()+"\n"+r.getDamage());
		            //stage.setScene(scene);
		        });
		 }
			Button goback=new Button("Go Back");
		
			VBox buttons=new VBox();
			buttons.getChildren().addAll(goback);
			buttons.setSpacing(10);
//			buyButton.setOnAction(e->{
//			//	buyWeapon();
//				//FillLanes(availableLanes);
//				});

			GridPane.setConstraints(info,3,3);
			GridPane.setConstraints(buttons,0,0);
			scene=new Scene (d);
			if(easyButton.isSelected()) {
			goback.setOnAction(e->switchScene(easyScene));
			}
			else if (hardButton.isSelected()) {
				goback.setOnAction(e->switchScene(hardScene));
			}
			//easy.getChildren().addAll(info,buttons);
		
	
	}
	**/
	public String DistinguishWeapons(Weapon current) {
		if(current instanceof PiercingCannon)
			return "file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/cannon_4318721.png";
		if(current instanceof SniperCannon)
		  return "file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/cannon_10844853.png";
		if(current instanceof VolleySpreadCannon) 
			return "file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/gun_12975428.png";
		if(current instanceof WallTrap)
			return "file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/trap_10767141.png";
		return "";
	}
	//this method was generated using the help of  AI
	
	
	public ArrayList<Titan> iterateTitans(PriorityQueue<Titan> titans) {
		ArrayList<Titan> temp=new ArrayList<>();
		while(!titans.isEmpty()) {
			temp.add(titans.poll());
		}
		titans.addAll(temp);
		return temp;
		
	}
	public String DistinguishTitans(Titan t) {
		if(t instanceof AbnormalTitan)
			return"file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/Abnormal%20Titan.png";
		if(t instanceof ArmoredTitan)
			return "file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/Armored%20Titan.png";
		if(t instanceof ColossalTitan)
			return "file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/Colossal%20Titan.png";
		if(t instanceof PureTitan)
			return"file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/Pure%20Titan.png";
		return"";
			
	}
	public void FillLaneWeapons(Lane lane,int j) {
		int r=lane.getWeapons().size();
		for(int i=0;i<r;i++) {
			Weapon weapon=lane.getWeapons().get(i);
			ImageView imageView = new ImageView(new Image(DistinguishWeapons(weapon)));
	        imageView.setFitWidth(30);
	        imageView.setFitHeight(30);
	        Button button = new Button();
	        button.setGraphic(imageView);
	        button.setPrefHeight(30);
	        button.setPrefWidth(30);
	        
	        laneWeapons[j].getChildren().add(button);
		}
		//laneWeapons[j].getChildren().add(s);
		laneWeapons[j].setPrefSize(40, 120);
		
	}
	public void FillLaneTitans(Lane lane,int j) {

		for(Titan t:lane.getTitans()) {
			ImageView imageView=new ImageView(new Image(DistinguishTitans(t)));
			if(t instanceof AbnormalTitan) {
				imageView.setFitWidth(35);
				imageView.setFitHeight(10*2);
			}
		   if(t instanceof ArmoredTitan) {
				imageView.setFitWidth(35);
				imageView.setFitHeight(15*2);
		   }
		 if(t instanceof ColossalTitan) {
				imageView.setFitWidth(35);
				imageView.setFitHeight(60*2);
		 }
		 if(t instanceof PureTitan) {
				imageView.setFitWidth(35);
				imageView.setFitHeight(15*2);
		 }
			
			Text text=new Text(t.getCurrentHealth()+"\n"+t.getDistance()+"\n"+t.getResourcesValue()+"\n"+t.getSpeed());
			text.setLayoutX(t.getDistance()+35);
			text.setLayoutY(Math.random()*70);
			text.setFont(new Font("Arial",14));
			AnchorPane Pane=new AnchorPane();
			imageView.setLayoutX(t.getDistance());
		    imageView.setLayoutY(Math.random()*7);
			
			Pane.getChildren().addAll(text,imageView);
			laneTitans[j].getChildren().addAll(Pane);
	      	laneTitans[j].setSpacing(3);
		}
		
		
		
		
	}
  
	
	public void FillLanes() {
		int r=0;
		if(easyButton.isSelected())
			r=3;
		else if (hardButton.isSelected())
			r=5;
		
		 w=new String[r];
		 
		
		for(int i=0;i<r;i++) {
		    
			availableLanes[i]=new Label();
			availableLanes[i].setLayoutX(20);
			availableLanes[i].setLayoutY(200);
			availableLanes[i].setPrefSize(30, 350);
//			laneWeapons[i]=new HBox();
//			laneTitans[i]=new HBox();
			Lane lane=battle.getOriginalLanes().get(i);
			w[i]="Danger: "+lane.getDangerLevel()+"\n"+"Wall Health: "+lane.getLaneWall().getCurrentHealth();
			availableLanes[i].setText(w[i]);
			
		
		
			if(!lane.isLaneLost()) {
				
				laneWeapons[i].getChildren().clear();
				FillLaneWeapons(lane,i);
				laneTitans[i].getChildren().clear();				
				FillLaneTitans(lane,i);
			}
			
			
			}
		}


	//this method was generated using the help of AI
	
	public int getLaneFromUser() {
		 TextInputDialog dialog = new TextInputDialog();
         dialog.setTitle("Wanted Lane");
         dialog.setHeaderText("Please enter the Lane:");
         dialog.setContentText("Lane: ");
         Optional<String> result = dialog.showAndWait();
         return Integer.parseInt(result.get());
	}
	//generated using the help of AI
	
	public void end() {
			Label Lost=new Label();
			Lost.setText("YOU HAVE LOST");
			Lost.setFont(new Font("MedievalSharp",34));
			 AnchorPane.setTopAnchor(Lost,20.0);
	         AnchorPane.setLeftAnchor(Lost,20.0);
		     Image dor=new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/doneeeeee.jpg");
		     BackgroundImage b=new BackgroundImage(dor,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		     StackPane l=new StackPane();
		     l.setBackground(new Background(b));
			Label score=new Label();
			score.setText(battle.getScore()+"");
			score.setFont(new Font("MedievalSharp",50));
            score.setTextFill(Color.STEELBLUE);		
            AnchorPane.setTopAnchor(score,50.0);
            AnchorPane.setLeftAnchor(score,50.0);
            AnchorPane done=new AnchorPane();
            done.setBackground(new Background(b));
            done.getChildren().addAll(Lost,score);
			Scene ending=new Scene(done);
			Button goBack=new Button("<--");
			//goBack.setGraphic(new ImageView(new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/GUI/GUIsrc/application/return_7243450.png")));
			goBack.setOnAction(e->switchScene(StartScene));
			done.getChildren().add(goBack);
			stage.setScene(ending);	
		
	}
	public void AlertPop(String message) {
		Alert popup=new Alert(AlertType.ERROR);
		popup.setTitle("ERROR");
		popup.setHeaderText("ERROR");
		popup.setContentText("Invalid Action : "+message);
		popup.showAndWait();	
	}
	/**public void buyWeapon() {
		
		WeaponShop(stage);
		//int r= getLaneFromUser();
		 //displayWeapons(weaponShopButtons); 
		for(int i=0;i<weaponShopButtons.length;i++) {
			if(weaponShopButtons[i].isPressed()) {
				Lane lane=battle.getOriginalLanes().get(getLaneFromUser()-1);
				try {
					battle.purchaseWeapon(i+1, lane);
				}
				catch(Exception e){
					AlertPop(e.getMessage());	
				}
			}
		}
		
	}**/

public void hardMode() {
	try {
    	battle=new Battle(1,0,200,5,125);
    	}
    	catch(Exception es){
    		AlertPop(es.getMessage());
    	}
	availableLanes = new Label[5]; 
    hard=new GridPane();
  
    
	//VBox walls=new VBox();
	hardScene=new Scene(hard,800,800);
	//hardScene.setFill(Color.BEIGE);
	laneTitans=new HBox[5];
	laneWeapons=new HBox[5];
	allLanes=new HBox[5];
	availableLanes = new Label[5];
	for(int i=0;i<5;i++) {
       laneWeapons[i]=new HBox();
        laneTitans[i]=new HBox();
        allLanes[i]=new HBox();
	}
	
	
	
    hard.setPadding(new Insets(10));
    WeaponShop();
    passButton=new Button("Pass Turn");
	passButton.setOnAction(this);
	GridPane.setConstraints(passButton,7,0);
	Resth.getChildren().add(passButton);
   
	BattleValues();
	//hardScene.getRoot().requestLayout();
	 
    stage.setScene(hardScene);
	
    VBox layout=new VBox();

	// Button [] allWalls=new Button[5];
	allLanes=new HBox[5];

	 wallButtons=new Label[5];
	 allLanes=new HBox[5];
		HBox laneandtitans[]=new HBox[5];
	 for(int i=0;i<5;i++) {
			allLanes[i]=new HBox();
			wallButtons[i]=new Label();
			
			wallButtons[i].setPrefHeight(120);
			wallButtons[i].setPrefWidth(150);
			int r=i+1;
			laneandtitans[i]=new HBox();
			laneandtitans[i].setPrefSize(650,110);
			laneandtitans[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null),null,null,null));
			
			wallButtons[i].setText("Wall "+r+"\n"+availableLanes[i].getText());
			wallButtons[i].setFont(new Font("MedievalSharp",14));
			wallButtons[i].setPadding(new Insets(10));
			GridPane.setConstraints(wallButtons[i],0,i);
			hard.getChildren().add(wallButtons[i]);
			allLanes[i].getChildren().addAll(wallButtons[i]);
			laneandtitans[i].getChildren().addAll(laneTitans[i],laneWeapons[i]);
			GridPane.setConstraints(laneandtitans[i],1 ,i );
			wallButtons[i].setMinWidth(150);
			allLanes[i].setMaxWidth(650);
			allLanes[i].setMinWidth(650);
			allLanes[i].setMaxHeight(120);
			allLanes[i].setMinHeight(120);
			
			wallButtons[i].setMaxHeight(120);
			wallButtons[i].setMinHeight(120);
			laneandtitans[i].setMaxWidth(650);
			laneandtitans[i].setMinWidth(650);
			laneandtitans[i].setMaxHeight(150);
			allLanes[i].getChildren().add(laneandtitans[i]);
			
			
			layout.getChildren().add(allLanes[i]);	
		}
	GridPane.setConstraints(layout,0,4);
	//Resth.getChildren().add(layout);
	layout.getChildren().add(Resth);
	hard.getChildren().add(layout);

	
}
public  void BattleValues() {
	values=new VBox();
	VBox hvalues=new VBox();
	s.setText("Score: "+battle.getScore() +"\n"+"Turn: "+battle.getNumberOfTurns()+"\n"+"Phase: "+battle.getBattlePhase()+"\n"+"Resources: "+battle.getResourcesGathered());
	GridPane.setConstraints(s,12,20);
	h.setText("Score: "+battle.getScore() +"\n"+"Turn: "+battle.getNumberOfTurns()+"\n"+"Phase: "+battle.getBattlePhase()+"\n"+"Resources: "+battle.getResourcesGathered());
	
	values.getChildren().add(s);
	hvalues.getChildren().add(h);
	GridPane.setConstraints(h,12,20);
	Reste.getChildren().add(values);
	Resth.getChildren().add(hvalues);
	FillLanes();
}

	
	@Override
	public void start(Stage primaryStage) {
				try {
					this.stage=primaryStage;
					 BorderPane first=new BorderPane();
					 startButton=new Button("START");
				     startButton.setPrefWidth(120);
			         startButton.setPrefHeight(40);
					 first.setCenter(startButton);
					 
					 Image startImg= new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/startscene.jpg");
					 first.setBackground(new Background(new BackgroundImage(startImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize (600,400,true,true,true,true))));
					 StartScene=new Scene(first,600,400);
				      second=new AnchorPane();
					 easyButton=new RadioButton("Easy");
					s=new Label();					
					hardButton=new RadioButton("Hard");
						
					 ToggleGroup toggleGroup = new ToggleGroup();
				     easyButton.setToggleGroup(toggleGroup);
				     hardButton.setToggleGroup(toggleGroup);
				     
				     HBox modes=new HBox(10);
				     modes.getChildren().addAll(easyButton,hardButton);
				     AnchorPane.setTopAnchor(modes, 200.0); 
				     AnchorPane.setLeftAnchor(modes, 0.0);
				     AnchorPane.setRightAnchor(modes, 0.0);
				     second.getChildren().add(modes);
					h=new Label();
					Button instructions=new Button("How to play?");
					Image instructionImg=new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/instructions.jpg");
					VBox vbox=new VBox(20);
					
					vbox.getChildren().addAll(modes,instructions);
					AnchorPane.setTopAnchor(vbox, 0.0);
			        AnchorPane.setBottomAnchor(vbox, 0.0);
			        AnchorPane.setLeftAnchor(vbox, 0.0);
			        AnchorPane.setRightAnchor(vbox, 0.0);
			        second.getChildren().add(vbox);
					instructions.setOnAction(e->setImageofScene(instructionImg,stage));
			        Resth=new HBox();
			        Reste=new HBox();
					
					 PlayScene=new Scene (second,350,350);
					 displayed=new HBox();
					 BorderPane display=new BorderPane();
					 
					// allwalls=new Button[availableLanes.length];
					if(easyButton.isSelected()) {
					
					  easyButton.setOnAction(this); 
						 
					}
					else {
						
						hardButton.setOnAction(this);	

						}
					
					easy=new GridPane();
					hard=new GridPane();

				
					weaponShopButtons= new Button[4];
			
                   stage.setScene(StartScene);
                   startButton.setOnAction(e->switchScene(PlayScene));
                   easyButton.setOnAction(this);
                   primaryStage.show();          
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
   
	public void WeaponShop() {
		VBox weaponshop=new VBox();
			
			HBox ws1=new HBox();
			Scene Weapon=new Scene(weaponshop);
			Image[] allImages=new Image[4];
			
			Image image1=new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/cannon_4318721.png");
			Image image2=new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/cannon_10844853.png");
			Image image3=new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/gun_12975428.png");
			Image image4=new Image("file:///C:/Users/nourh/OneDrive/Desktop/csen%20403/Team62/src/game/gui/trap_10767141.png");
			allImages[0]=image1;
			allImages[1]=image2;
			allImages[2]=image3;
			allImages[3]=image4;
			
			for(int i=0;i<4;i++) {
				int j=i+1;
				weaponShopButtons[i]=new Button();
				ImageView r=new ImageView(allImages[j-1]);
				r.setFitHeight(40);
				r.setFitWidth(40);
		        weaponShopButtons[j-1].setGraphic(r);
		        ws1.getChildren().addAll(weaponShopButtons[j-1]);
				weaponShopButtons[i].setOnAction(e->{
					
					       try {
					    	  Lane lane=battle.getOriginalLanes().get(getLaneFromUser()-1);
					    	   try {								
							        battle.purchaseWeapon(j, lane);
							        //battle.passTurn();
								}
								catch(Exception a){
									AlertPop(a.getMessage());	
								}
					    	   
					    	 
					       }catch(Exception a) {
					    	   AlertPop(a.getMessage());
					       }
							
							
							
			});
			
				
			}
		    weaponinfo=new Label[4];
		    HBox wr=new HBox();
		    Button Weaponsh=new Button("Weapon Shop");
		    GridPane.setConstraints(Weaponsh,10,10 );
		    Weaponsh.setOnAction(e->switchScene(Weapon));
		    goB=new Button("GO TO GAME");
		    weaponshop.getChildren().add(goB);
		    
		    	
		    	
			for(int i=0;i<4;i++) {
				WeaponRegistry r=battle.getWeaponFactory().getWeaponShop().get(i+1);
				weaponinfo[i]=new Label();
				if(i==0)
					weaponinfo[i].setText(r.getName()+"\n"+r.getPrice()+"\n"+r.getDamage()+"\n"+"Piercing Cannon");
				if(i==1)
					weaponinfo[i].setText(r.getName()+"\n"+r.getPrice()+"\n"+r.getDamage()+"\n"+"Sniper Cannon");
				if(i==2)
					weaponinfo[i].setText(r.getName()+"\n"+r.getPrice()+"\n"+r.getDamage()+"\n"+"VolleySpread Cannon");
				if(i==3)
					weaponinfo[i].setText(r.getName()+"\n"+r.getPrice()+"\n"+r.getDamage()+"\n"+"Wall Trap");
				wr.getChildren().add(weaponinfo[i]);
			}
			
		
			//GridPane.setConstraints(ws1,5,0);
			//GridPane.setConstraints(wr,5,1);
			
			//GridPane.setConstraints(t,12,10);
			weaponshop.getChildren().addAll(ws1,wr);
			goB.setOnAction(this);
		if(easyButton.isSelected())
		{
			Reste.getChildren().add(Weaponsh);
		}
		else {
			Resth.getChildren().add(Weaponsh);	
		}
		
			
		}

	public void easyMode()  {
		try {
	    	battle=new Battle(1,0,200,3,250);
	    	}
	    	catch(Exception es){
	    		AlertPop(es.getMessage());
	    	}
		//Reste=new HBox();
	   
		availableLanes = new Label[3]; 
		easy=new GridPane();
		//VBox walls=new VBox();

		easyScene=new Scene(easy,800,800);
		laneTitans=new HBox[3];
		laneWeapons=new HBox[3];
		allLanes=new HBox[3];
		availableLanes = new Label[3];
		alltitans=new VBox();
		for(int i=0;i<3;i++) {

		
            laneWeapons[i]=new HBox();
	        laneTitans[i]=new HBox();
	        allLanes[i]=new HBox();
	        
		}
		
		
		
	    easy.setPadding(new Insets(10));
	    WeaponShop();
	    passButton=new Button("Pass Turn");
		passButton.setOnAction(this);
		 Reste.getChildren().add(passButton);
//		GridPane.setConstraints(passButton,9,9);
//		easy.getChildren().add(passButton);
	    
	    BattleValues();
	   // easyScene.getRoot().requestLayout();
	    
		 
	    stage.setScene(easyScene);
		
	    layout=new VBox();
		 
	
	    wallButtons=new Label[3];
		allLanes=new HBox[3];
	   HBox lanetitansandweapons[]=new HBox[3];
	  // Label[] lostLanes=new Label[3];
		
		for(int i=0;i<3;i++) {
			allLanes[i]=new HBox();
			wallButtons[i]=new Label();
		
			wallButtons[i].setPrefHeight(140);
			wallButtons[i].setPrefWidth(150);
			int r=i+1;
			wallButtons[i].setText("Wall "+r+"\n"+availableLanes[i].getText());
			wallButtons[i].setFont(new Font("MedievalSharp",14));
			wallButtons[i].setPadding(new Insets(10));
			wallButtons[i].setMaxHeight(140);
			wallButtons[i].setMinHeight(140);
			GridPane.setConstraints(wallButtons[i],0,i);
			lanetitansandweapons[i]=new HBox();
			lanetitansandweapons[i].setPrefSize(650,110 );
			//BorderStroke border=new BorderStroke(Color.BLUE,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(2));
			lanetitansandweapons[i].setMaxWidth(500);
			lanetitansandweapons[i].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null),null,null,null));
			//allLanes[i].setBorder(new Border(border));
			allLanes[i].getChildren().addAll(wallButtons[i]);
			wallButtons[i].setMinWidth(150);
			lanetitansandweapons[i].getChildren().addAll(laneWeapons[i],laneTitans[i]);
			Lane lane=battle.getOriginalLanes().get(i);

			GridPane.setConstraints(lanetitansandweapons[i],1,i);
			allLanes[i].getChildren().addAll(lanetitansandweapons[i]);	
			//allLanes[i].setMaxWidth(600);
			allLanes[i].setMinHeight(140);
			//allLanes[i].setMaxHeight(140);
			allLanes[i].setMaxHeight(140);
			//lanetitansandweapons[i].setMinHeight(150);
			lanetitansandweapons[i].setMaxHeight(150);
		
			lanetitansandweapons[i].setMaxWidth(650);
			lanetitansandweapons[i].setMinWidth(650);
			layout.getChildren().add(allLanes[i]);
		}
		layout.getChildren().add(Reste);
		GridPane.setConstraints(layout,0,4);
		easy.getChildren().add(layout);
	
		
		
	    
		
			
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void handle(ActionEvent arg0) {
		//if(arg0.getSource()==startButton) {
			
		
	    if(arg0.getSource()==easyButton) {
	    	easyMode();
	    	
	    }
	    	
	    else  if(arg0.getSource()==hardButton) {
	    	hardMode();
	    }
	    else if(arg0.getSource()==goB) {
	    	//BattleValues();
	    	if(easyButton.isSelected())
	    		switchScene(easyScene);
	    	else
	    		switchScene(hardScene);
	    	BattleValues();
	    }

	    else if(arg0.getSource()==passButton) {
	   
	    	battle.passTurn();
	    }
//	    else if (arg0.getSource()==weaponShopButtons[0]||arg0.getSource()==weaponShopButtons[1]||arg0.getSource()==weaponShopButtons[2]||arg0.getSource()==weaponShopButtons[3]) {
//	    	battle.passTurn();
//	    }
	    
	    
	  if(battle.isGameOver()) {
		  end();
		  
	  }
	  else {
		
		  
		  BattleValues();
		  if(easyButton.isSelected()) {
			  for(int i=0;i<3;i++) {
				  Lane lane=battle.getOriginalLanes().get(i);
				  if (!lane.isLaneLost())
				  wallButtons[i].setText(w[i]);
				  else
					  wallButtons[i].setText("X");
				 
				  }
			}
		  
		  if(hardButton.isSelected()) {
			  for(int i=0;i<5;i++) {
				  Lane lane=battle.getOriginalLanes().get(i);
				  if (!lane.isLaneLost())
				  wallButtons[i].setText(w[i]);
				  else
					  wallButtons[i].setText("X");
				     wallButtons[i].setFont(new Font("Arial",15));
				     
				  }
		  }
//		 easy.getChildren().add(s);
//		
//		hard.getChildren().add(h);
	  }
}
	}
