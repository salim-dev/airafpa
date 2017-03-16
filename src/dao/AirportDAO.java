/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import data.Airport;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class AirportDAO extends DAO<Airport, String> {

    public AirportDAO() {
        super();
    }

    @Override
    public Airport create(Airport airport) {

        Airport airportCreate = new Airport();
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO airports (aita,city,country) VALUES (?,?,?)";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, airport.getAita());
                pst.setString(2, airport.getCity());
                pst.setString(3, airport.getCountry());
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    airportCreate = airport;
                    return airportCreate;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return airportCreate;
            }

        } else {
            return airportCreate;
        }
        return airportCreate;
    }

    @Override
    public boolean update(Airport airport) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update airports set city = ?,country = ? WHERE aita= ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, airport.getCity());
                pst.setString(2, airport.getCountry());
                pst.setString(3, airport.getAita());
                // excute update row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;
    }

    @Override
    public boolean delete(Airport airport) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM airports WHERE aita= ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, airport.getAita());
                // excute delete row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;

    }

    @Override
    public ArrayList getAll() {
        ArrayList<Airport> listAirport = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                Statement st = this.bddmanager.getConnectionManager().createStatement();
                String requete = "SELECT * FROM airports";
                ResultSet rs = st.executeQuery(requete);

                while (rs.next()) {
                    Airport el = new Airport(rs.getString("aita"), rs.getString("city"), rs.getString("country"));
                    listAirport.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listAirport;
            }

        } else {
            return listAirport;
        }

        return listAirport;
    }

    @Override
    public Airport find(String primary_key) {
        Airport airport = new Airport();
        if (this.bddmanager.connect()) {

            try {
                Statement st = this.bddmanager.getConnectionManager().createStatement();
                String requete = "SELECT * FROM airports WHERE aita=\"" + primary_key + "\"";
                ResultSet rs = st.executeQuery(requete);
                while (rs.next()) {
                    airport.setAita(rs.getString("aita"));
                    airport.setCity(rs.getString("city"));
                    airport.setCountry(rs.getString("country"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return airport;
            }

        } else {
            return airport;
        }
        return airport;
    }

    /**
     * relationship table
     *
     * @param WithTable
     * @param forign_key
     * @return
     */
    public Object with(Object WithTable, Object forign_key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
