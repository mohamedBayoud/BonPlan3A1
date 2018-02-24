/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServicePartage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class StatisticsController implements Initializable {

	private BarChart<Integer, Integer> barChart;
	@FXML
	private Button btnLoad;

	private Connection connection;

	private ObservableList data;
	@FXML
	private BarChart<String, Integer> BarChart;
	@FXML
	private Button Retour;
	@FXML
	private TextField role;
	@FXML
	private TextField id_client;
	@FXML
	private TextField email_client;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

	}

	@FXML
	private void LoadChart(ActionEvent event) throws SQLException {
		String query = "SELECT COUNT(idPartage),DateCommentaire FROM Partage group by DateCommentaire";
		XYChart.Series<String, Integer> series = new XYChart.Series<>();

		try {
			connection = connexionBD();
			System.out.println("test");
			ResultSet rs = connection.createStatement().executeQuery(query);

			while (rs.next()) {
				series.getData().add(new XYChart.Data<>(rs.getString(2), rs.getInt(1)));
			}
			BarChart.getData().addAll(series);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Connection connexionBD() throws SQLException {
		try {
			String dbString = "jdbc:mysql://localhost:3306/BonPlan";
			String user = "root";
			String password = "";
			Connection con = DriverManager.getConnection(dbString, user, password);
			System.out.println("Connexion okay");
			return con;
		} catch (SQLException ex) {
			Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	
		@FXML
	private void ReturnButton(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAvis.fxml"));
        Parent root = loader.load();
        ListeAvisController frc = loader.getController();
        Retour.getScene().setRoot(root);
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
	}

	 public void setRole(String role) {

        this.role.setText(role);
    }

    public void setEmail(String email) {
        this.email_client.setText(email);
    }

    public void setIDClient(int id) {
        String s = "";
        s = s + id;
        this.id_client.setText(s);
    }

	
	 
}
