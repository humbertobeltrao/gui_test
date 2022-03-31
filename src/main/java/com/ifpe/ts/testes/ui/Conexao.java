package com.ifpe.ts.testes.ui;

import java.sql.Connection;
import java.sql.DriverManager;

 
public class Conexao {
	
private Connection con;


    
    public void abrirConexao() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection)
                DriverManager.getConnection("jdbc:mysql://localhost:3306/pooii?useSSL=false", "root", "root");

        
    }
    
    public void fecharConexao() throws Exception {
        con.close();
    }
    
    
    
    
     public Connection getCon() {
        return con;
    }


}
