/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.DbUtil;
import VO.Aplication;
import VO.Esquema;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juan Manuel
 */
public class crudApp {
    
    private Connection connection;

    public crudApp() throws URISyntaxException {
        connection = DbUtil.getConnection();
    }

    public void agregarApp(Aplication es) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Aplicacion(id,nombre,descripcion) values (?, ? )");
            // Parameters start with 1
            preparedStatement.setInt(1, es.getId());
            preparedStatement.setString(2, es.getName());
            preparedStatement.setString(3,es.getDescripcion());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Esquema> listarEs() {
        ArrayList<Esquema> esquemas = new ArrayList<Esquema>();
        try {
            System.out.println("LLegue hasta aca");
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from Esquemas");
            while (rs.next()) {
                Esquema es = new Esquema();

                es.setId_esquema(rs.getInt("id_Esquema"));
                es.setVarchar(rs.getString("nombre"));

                esquemas.add(es);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return esquemas;
    }

  
    public Esquema extraerApp(String nombre) {
		Esquema es=new Esquema();
		try {
			PreparedStatement preparedStatement = connection.
					prepareStatement("select id from Aplicacion where nombre = ? ");
			preparedStatement.setString(1, nombre);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				es.setId_esquema(rs.getInt("id"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return es;
	}
    
}
