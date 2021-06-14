/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author guilherme.nsantos
 */
public class ConexaoConteiner {
    
    
//        private BasicDataSource banco;
//
//    public void ConexaoMysql() {
//        this.banco = new BasicDataSource();
//        banco.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        banco.setUrl("jdbc:mysql://172.17.0.1:3306/bd_gsn");
//        banco.setUsername("root");
//        banco.setPassword("urubu100");
//    }
//
//    public BasicDataSource getBanco() {
//        return banco;
//    }

    Connection conn;

    public Connection conexaoConteiner() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://172.17.0.1:3306/bd_gsn";
            conn = DriverManager.getConnection(url, "root", "urubu100");
            System.out.println("Funcionou");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e);

        }
        return conn;
    }
}