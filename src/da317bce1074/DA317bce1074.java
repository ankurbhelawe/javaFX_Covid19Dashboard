package DA317bce1074;
/**
 *
 * @author Ankur Bhelawe
 */

import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.scene.paint.*; 
import java.sql.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
         
public class DA317bce1074 extends Application {
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/coronadb";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "";
   
    Scene welcomeScene, dashboardScene, indiaDashboardScene;
    final String IDLE_BUTTON_STYLE = "-fx-background-color: white; -fx-border-color: #4CAF50; -fx-border-width: 2 2 2 2; -fx-font-size:20";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-color: #4CAF50; -fx-border-width: 2 2 2 2; -fx-font-size:20";
    final String IDLE_BUTTON_STYLE_BLUE = "-fx-background-color: white; -fx-border-color: #555555; -fx-border-width: 2 2 2 2; -fx-font-size:20";
    final String HOVERED_BUTTON_STYLE_BLUE = "-fx-background-color: #555555; -fx-text-fill: white; -fx-border-color: #555555; -fx-border-width: 2 2 2 2; -fx-font-size:20";
   
    public String getData(String state){
        String result="";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rss;
        try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);

           //STEP 4: Execute a query
           System.out.println("Creating statement...");
           stmt = conn.createStatement();
           String sql2;



           sql2 = "SELECT * FROM `table 3`";
           rss = stmt.executeQuery(sql2);
           //STEP 5: Extract data from result set
           while(rss.next()){
              //Retrieve by column name
              int cases  = rss.getInt(state);
              result+=cases+"@";
           }
           //STEP 6: Clean-up environment
           rss.close();
           stmt.close();
           conn.close();
        }catch(SQLException se){
           //Handle errors for JDBC
           se.printStackTrace();
        }catch(Exception e){
           //Handle errors for Class.forName
           e.printStackTrace();
        }finally{
           //finally block used to close resources
           try{
              if(stmt!=null)
                 stmt.close();
           }catch(SQLException se2){
           }// nothing we can do
           try{
              if(conn!=null)
                 conn.close();
           }catch(SQLException se){
              se.printStackTrace();
           }//end finally try
        }//end try
        return result;
    }
    @Override 
   public void start(Stage stage) {       
      //Creating a Text object 
      Text text = new Text(); 
      //Setting font to the text 
      text.setFont(new Font(45));         
      //Setting the text to be added. 
      text.setText("India Covid-19 Dashboard");
      Text text1 = new Text("Created by Ankur Bhelawe.");
      // set the text color 
      text1.setFill(Color.GRAY);
      
      Text conf= new Text("Confirmed");
      conf.setFont(new Font(20));
      conf.setFill(Color.RED);
      Text act= new Text("Active");
      act.setFont(new Font(20));
      act.setFill(Color.BLUE);
      Text rec= new Text("Recovered");
      rec.setFont(new Font(20));
      rec.setFill(Color.GREEN);
      Text deat= new Text("Death");
      deat.setFont(new Font(20));
      deat.setFill(Color.GRAY);
      Text dash= new Text("Dashboard");
      dash.setFont(new Font(40));
      Text menu= new Text("Menu");
      menu.setFont(new Font(40));
      
      Text dash2= new Text("Dashboard");
      dash2.setFont(new Font(40));
      Text menu2= new Text("Menu");
      menu2.setFont(new Font(40));
      Button button22 = new Button("Back");
      button22.setMaxWidth(Double.MAX_VALUE);
      button22.setOnAction(e -> stage.setScene(welcomeScene));
      Button statewise2 = new Button("State Wise Statistics");
      statewise2.setMaxWidth(Double.MAX_VALUE);
      statewise2.setOnAction(e -> stage.setScene(dashboardScene));
      Button india2 = new Button("India Statistics");
      india2.setOnAction(e -> stage.setScene(indiaDashboardScene));
      india2.setMaxWidth(Double.MAX_VALUE);
      
      india2.setStyle(IDLE_BUTTON_STYLE_BLUE);
      india2.setOnMouseEntered(e -> india2.setStyle(HOVERED_BUTTON_STYLE_BLUE));
      india2.setOnMouseExited(e -> india2.setStyle(IDLE_BUTTON_STYLE_BLUE));
      statewise2.setStyle(IDLE_BUTTON_STYLE_BLUE);
      statewise2.setOnMouseEntered(e -> statewise2.setStyle(HOVERED_BUTTON_STYLE_BLUE));
      statewise2.setOnMouseExited(e -> statewise2.setStyle(IDLE_BUTTON_STYLE_BLUE));
      button22.setStyle(IDLE_BUTTON_STYLE_BLUE);
      button22.setOnMouseEntered(e -> button22.setStyle(HOVERED_BUTTON_STYLE_BLUE));
      button22.setOnMouseExited(e -> button22.setStyle(IDLE_BUTTON_STYLE_BLUE));
    
      
      // Adding a button
      Button button1 = new Button("Enter");
      button1.setPrefSize(500, 50);
      button1.setMaxWidth(Double.MAX_VALUE);
      button1.setStyle(IDLE_BUTTON_STYLE);
      button1.setOnMouseEntered(e -> button1.setStyle(HOVERED_BUTTON_STYLE));
      button1.setOnMouseExited(e -> button1.setStyle(IDLE_BUTTON_STYLE));
      button1.setOnAction(e -> stage.setScene(dashboardScene));
      // Creating a grid object
      GridPane layout1 = new GridPane();
      GridPane.setHalignment(text1, HPos.RIGHT);
      layout1.add(text,1,0);
      layout1.add(button1,1,1);
      layout1.add(text1,1,2);
      //Setting the vertical gaps between the columns 
      layout1.setVgap(30); 
      //Setting the Grid alignment 
      layout1.setAlignment(Pos.CENTER);
      //Creating a scene object 
      welcomeScene = new Scene(layout1, 1300, 600);
      
      
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    xAxis.setLabel("Date");       
    final LineChart<String,Number> lineChart = 
            new LineChart<String,Number>(xAxis,yAxis);
    lineChart.setTitle("Total Coronavirus Cases in India");
    XYChart.Series series = new XYChart.Series();
    series.setName("Cases");
      
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs,rss;
    try{
       //STEP 2: Register JDBC driver
       Class.forName("com.mysql.jdbc.Driver");

       //STEP 3: Open a connection
       System.out.println("Connecting to database...");
       conn = DriverManager.getConnection(DB_URL,USER,PASS);

       //STEP 4: Execute a query
       System.out.println("Creating statement...");
       stmt = conn.createStatement();
       String sql;
       sql = "SELECT * FROM timeseries";
       rs = stmt.executeQuery(sql);
       //STEP 5: Extract data from result set
       while(rs.next()){
          //Retrieve by column name
          int cases  = rs.getInt("cases");
          String date = rs.getString("date");
          series.getData().add(new XYChart.Data(date, cases));
       }
       series.setName("Total Cases");
       rs.close();
       stmt.close();
       conn.close();
    }catch(SQLException se){
       //Handle errors for JDBC
       se.printStackTrace();
    }catch(Exception e){
       //Handle errors for Class.forName
       e.printStackTrace();
    }finally{
       //finally block used to close resources
       try{
          if(stmt!=null)
             stmt.close();
       }catch(SQLException se2){
       }// nothing we can do
       try{
          if(conn!=null)
             conn.close();
       }catch(SQLException se){
          se.printStackTrace();
       }//end finally try
    }//end try
      
    Label confirmed=new Label();
    confirmed.setTextFill(Color.RED);
    Label active=new Label();
    active.setTextFill(Color.BLUE);
    Label recovered=new Label();
    recovered.setTextFill(Color.GREEN);
    Label death=new Label();
    death.setTextFill(Color.GRAY);

    // Adding a button
    Button button2 = new Button("Back");
    button2.setMaxWidth(Double.MAX_VALUE);
    button2.setOnAction(e -> stage.setScene(welcomeScene));
    Button Maharashtra = new Button("Maharashtra");
    Maharashtra.setMaxWidth(Double.MAX_VALUE);
    Maharashtra.setOnAction(e -> {
        String res=getData("MH");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Gujarat = new Button("Gujarat");
    Gujarat.setMaxWidth(Double.MAX_VALUE);
    Gujarat.setOnAction(e -> {
        String res=getData("GJ");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Delhi = new Button("Delhi");
    Delhi.setMaxWidth(Double.MAX_VALUE);
    Delhi.setOnAction(e -> {
        String res=getData("DL");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Rajasthan = new Button("Rajasthan");
    Rajasthan.setMaxWidth(Double.MAX_VALUE);
    Rajasthan.setOnAction(e -> {
        String res=getData("RJ");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button MP = new Button("Madhya Pradesh");
    MP.setMaxWidth(Double.MAX_VALUE);
    MP.setOnAction(e -> {
        String res=getData("MP");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button TN = new Button("Tamil Nadu");
    TN.setMaxWidth(Double.MAX_VALUE);
    TN.setOnAction(e -> {
        String res=getData("TN");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button UP = new Button("Uttar Pradesh");
    UP.setMaxWidth(Double.MAX_VALUE);
    UP.setOnAction(e -> {
        String res=getData("UP");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button AP = new Button("Andhra Pradesh");
    AP.setMaxWidth(Double.MAX_VALUE);
    AP.setOnAction(e -> {
        String res=getData("AP");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Telangana = new Button("Telangana");
    Telangana.setMaxWidth(Double.MAX_VALUE);
    Telangana.setOnAction(e -> {
        String res=getData("TG");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button WB = new Button("West Bengal");
    WB.setMaxWidth(Double.MAX_VALUE);
    WB.setOnAction(e -> {
        String res=getData("WB");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Karnataka = new Button("Karnataka");
    Karnataka.setMaxWidth(Double.MAX_VALUE);
    Karnataka.setOnAction(e -> {
        String res=getData("KA");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button JK = new Button("Jammu and Kashmir");
    JK.setMaxWidth(Double.MAX_VALUE);
    JK.setOnAction(e -> {
        String res=getData("JK");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Kerala = new Button("Kerala");
    Kerala.setMaxWidth(Double.MAX_VALUE);
    Kerala.setOnAction(e -> {
        String res=getData("KL");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Punjab = new Button("Punjab");
    Punjab.setMaxWidth(Double.MAX_VALUE);
    Punjab.setOnAction(e -> {
        String res=getData("PB");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Haryana = new Button("Haryana");
    Haryana.setMaxWidth(Double.MAX_VALUE);
    Haryana.setOnAction(e -> {
        String res=getData("HR");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Bihar = new Button("Bihar");
    Bihar.setMaxWidth(Double.MAX_VALUE);
    Bihar.setOnAction(e -> {
        String res=getData("BR");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Odisha = new Button("Odisha");
    Odisha.setMaxWidth(Double.MAX_VALUE);
    Odisha.setOnAction(e -> {
        String res=getData("OR");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Jharkhand = new Button("Jharkhand");
    Jharkhand.setMaxWidth(Double.MAX_VALUE);
    Jharkhand.setOnAction(e -> {
        String res=getData("JH");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Uttarakhand = new Button("Uttarakhand");
    Uttarakhand.setMaxWidth(Double.MAX_VALUE);
    Uttarakhand.setOnAction(e -> {
        String res=getData("UT");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button HP = new Button("Himachal Pradesh");
    HP.setMaxWidth(Double.MAX_VALUE);
    HP.setOnAction(e -> {
        String res=getData("HP");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button CG = new Button("Chhattisgarh");
    CG.setMaxWidth(Double.MAX_VALUE);
    CG.setOnAction(e -> {
        String res=getData("CH");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Assam = new Button("Assam");
    Assam.setMaxWidth(Double.MAX_VALUE);
    Assam.setOnAction(e -> {
        String res=getData("AS");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button ANI = new Button("Andaman Nicobar");
    ANI.setMaxWidth(Double.MAX_VALUE);
    ANI.setOnAction(e -> {
        String res=getData("AN");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Chandigarh = new Button("Chandigarh");
    Chandigarh.setMaxWidth(Double.MAX_VALUE);
    Chandigarh.setOnAction(e -> {
        String res=getData("CT");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Ladakh = new Button("Ladakh");
    Ladakh.setMaxWidth(Double.MAX_VALUE);
    Ladakh.setOnAction(e -> {
        String res=getData("LD");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Meghalaya = new Button("Meghalaya");
    Meghalaya.setMaxWidth(Double.MAX_VALUE);
    Meghalaya.setOnAction(e -> {
        String res=getData("ML");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Puducherry = new Button("Puducherry");
    Puducherry.setMaxWidth(Double.MAX_VALUE);
    Puducherry.setOnAction(e -> {
        String res=getData("PY");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Goa = new Button("Goa");
    Goa.setMaxWidth(Double.MAX_VALUE);
    Goa.setOnAction(e -> {
        String res=getData("GA");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Manipur = new Button("Manipur");
    Manipur.setMaxWidth(Double.MAX_VALUE);
    Manipur.setOnAction(e -> {
        String res=getData("MN");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Tripura = new Button("Tripura");
    Tripura.setMaxWidth(Double.MAX_VALUE);
    Tripura.setOnAction(e -> {
        String res=getData("TR");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button ARP = new Button("Arunachal Pradesh");
    ARP.setMaxWidth(Double.MAX_VALUE);
    ARP.setOnAction(e -> {
        String res=getData("AR");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button Mizoram = new Button("Mizoram");
    Mizoram.setMaxWidth(Double.MAX_VALUE);
    Mizoram.setOnAction(e -> {
        String res=getData("MZ");
        String[] arr= res.split("@");
        confirmed.setText(arr[0]); 
        active.setText(arr[1]); 
        recovered.setText(arr[2]); 
        death.setText(arr[3]);});
    Button total = new Button("Total Cases in India");
    total.setOnAction(e -> stage.setScene(indiaDashboardScene));
    total.setMaxWidth(Double.MAX_VALUE);
    Button statewise = new Button("State Wise Statistics");
    statewise.setMaxWidth(Double.MAX_VALUE);
    statewise.setOnAction(e -> stage.setScene(dashboardScene));
    Button india = new Button("India Statistics");
    india.setOnAction(e -> stage.setScene(indiaDashboardScene));
    india.setMaxWidth(Double.MAX_VALUE);

    india.setStyle(IDLE_BUTTON_STYLE_BLUE);
    india.setOnMouseEntered(e -> india.setStyle(HOVERED_BUTTON_STYLE_BLUE));
    india.setOnMouseExited(e -> india.setStyle(IDLE_BUTTON_STYLE_BLUE));
    total.setStyle(IDLE_BUTTON_STYLE_BLUE);
    total.setOnMouseEntered(e -> total.setStyle(HOVERED_BUTTON_STYLE_BLUE));
    total.setOnMouseExited(e -> total.setStyle(IDLE_BUTTON_STYLE_BLUE));
    statewise.setStyle(IDLE_BUTTON_STYLE_BLUE);
    statewise.setOnMouseEntered(e -> statewise.setStyle(HOVERED_BUTTON_STYLE_BLUE));
    statewise.setOnMouseExited(e -> statewise.setStyle(IDLE_BUTTON_STYLE_BLUE));
    button2.setStyle(IDLE_BUTTON_STYLE_BLUE);
    button2.setOnMouseEntered(e -> button2.setStyle(HOVERED_BUTTON_STYLE_BLUE));
    button2.setOnMouseExited(e -> button2.setStyle(IDLE_BUTTON_STYLE_BLUE));

    // Creating a grid object
    GridPane layout2 = new GridPane();
    GridPane.setHalignment(dash, HPos.CENTER);
    GridPane.setHalignment(conf, HPos.CENTER);
    GridPane.setHalignment(act, HPos.CENTER);
    GridPane.setHalignment(rec, HPos.CENTER);
    GridPane.setHalignment(deat, HPos.CENTER);
    GridPane.setHalignment(confirmed, HPos.CENTER);
    GridPane.setHalignment(active, HPos.CENTER);
    GridPane.setHalignment(recovered, HPos.CENTER);
    GridPane.setHalignment(death, HPos.CENTER);
    layout2.add(menu, 0, 0, 2, 1);
    layout2.add(dash, 4, 0, 4, 1);
    layout2.add(button2,0,25,2,1);
    layout2.add(statewise,1,2);
    layout2.add(india,1,3);
    layout2.add(Maharashtra,2,15);
    layout2.add(Gujarat, 3, 15);
    layout2.add(Delhi,4,15);
    layout2.add(Rajasthan, 5, 15);
    layout2.add(MP,6,15);
    layout2.add(TN, 7, 15);
    layout2.add(UP,8,15);
    layout2.add(AP, 9, 15);
    layout2.add(Telangana,2,16);
    layout2.add(WB, 3, 16);
    layout2.add(Karnataka,4,16);
    layout2.add(JK, 5, 16);
    layout2.add(Kerala,6,16);
    layout2.add(Punjab, 7, 16);
    layout2.add(Haryana,8,16);
    layout2.add(Bihar, 9, 16);
    layout2.add(Odisha,2,17);
    layout2.add(Jharkhand, 3, 17);
    layout2.add(Uttarakhand,4,17);
    layout2.add(HP, 5, 17);
    layout2.add(CG,6,17);
    layout2.add(Assam, 7, 17);
    layout2.add(ANI,8,17);
    layout2.add(Chandigarh, 9, 17);
    layout2.add(Ladakh,2,18);
    layout2.add(Meghalaya, 3, 18);
    layout2.add(Puducherry,4,18);
    layout2.add(Goa, 5, 18);
    layout2.add(Manipur,6,18);
    layout2.add(Tripura, 7, 18);
    layout2.add(ARP,8,18);
    layout2.add(Mizoram, 9, 18);
    layout2.add(total, 4, 25, 4, 1);
    layout2.add(conf,4,5);
    layout2.add(act,5,5);
    layout2.add(rec,6,5);
    layout2.add(deat,7,5);
    layout2.add(confirmed,4,6);
    layout2.add(active,5,6);
    layout2.add(recovered,6,6);
    layout2.add(death,7,6);
    layout2.setAlignment(Pos.CENTER);
    //Setting the vertical and horizontal gaps between the columns 
    layout2.setVgap(5); 
    layout2.setHgap(5);

    //Creating a scene object 
    dashboardScene = new Scene(layout2, 1300, 600);

    GridPane layout3 = new GridPane();
    GridPane.setHalignment(dash2, HPos.CENTER);
    layout3.add(menu2, 0, 0, 2, 1);
    layout3.add(dash2, 2, 0, 8, 1);
    layout3.add(button22,0,17,2,1);
    layout3.add(statewise2,1,2);
    layout3.add(india2,1,3);

    layout3.setAlignment(Pos.CENTER);

    //Setting the vertical and horizontal gaps between the columns 
    layout3.setVgap(5); 
    layout3.setHgap(5);
      
   
    layout3.add(lineChart,2,2,8,15);
    lineChart.getData().add(series);
    lineChart.setMinSize(990, 20);
    indiaDashboardScene = new Scene(layout3, 1300, 600);
      
    //Setting title to the Stage 
    stage.setTitle("India Covid-19 Dashboard"); 

    //Adding scene to the stage 
    stage.setScene(welcomeScene); 

    //Displaying the contents of the stage 
    stage.show(); 
   }   
   public static void main(String args[]){ 
      launch(args); 
   } 
} 