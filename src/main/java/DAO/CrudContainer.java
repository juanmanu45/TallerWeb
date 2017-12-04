/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.DbUtil;
import VO.ViewContainer;
import VO.ViewElement;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Manuel
 */
public class CrudContainer {

    private Connection connection;

    public CrudContainer() throws URISyntaxException {
        connection = DbUtil.getConnection();
    }

    public List<ViewContainer> findAll() throws SQLException {
        List<ViewContainer> cont = null;
        String query = "SELECT * FROM ViemContainer";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            String name = null;
            boolean isLandMark;
            boolean isDefault;
            boolean isXOR;
            boolean Protected;

            while (rs.next()) {
                if (cont == null) {
                    cont = new ArrayList<ViewContainer>();
                }

                ViewContainer registro = new ViewContainer();

                name = rs.getString("name");
                registro.setName(name);

                isLandMark = rs.getBoolean("isLandMark");
                registro.setIsLandMark(isLandMark);

                isDefault = rs.getBoolean("isDefault");
                registro.setIsDefault(isDefault);

                isXOR = rs.getBoolean("isXOR");
                registro.setIsXOR(isXOR);

                Protected = rs.getBoolean("Protected");
                registro.setProtected(Protected);

                cont.add(registro);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println("Problemas al obtener la lista de Containners");
            e.printStackTrace();
        }

        return cont;
    }

    public boolean insert(ViewContainer t) throws SQLException {
        boolean result = false;

        String query = " insert into ViemContainer (name,isLandMark,isDefault,isXOR,Protected) " + "values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        ViewElement t1 = new ViewElement() {
        };
        String name = String.valueOf(t1);
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setBoolean(2, t.isIsLandMark());
            preparedStmt.setBoolean(3, t.isIsDefault());
            preparedStmt.setBoolean(4, t.isIsXOR());
            preparedStmt.setBoolean(5, t.isProtected());
            result = preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ViewContainer seleccionarContainner(String nombre) {
        ViewContainer v = new ViewContainer();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select id from ViewContainer where nombre = ? ");
            preparedStatement.setString(1, nombre);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                v.setId(rs.getInt("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;

    }
}
