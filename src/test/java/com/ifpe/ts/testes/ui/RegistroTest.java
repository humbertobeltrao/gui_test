package com.ifpe.ts.testes.ui;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import com.ifpe.ts.testes.ui.Conexao;
import com.ifpe.ts.testes.ui.Pessoa;

public class RegistroTest {
	
	Conexao con;
	
	@Before
	public void init() {
		con = new Conexao();
	}
	
	@Test
	public void insertTest() throws Exception {
		Pessoa p = new Pessoa("Humberto", 30);
		con.abrirConexao();
        Statement stmt = (Statement) con.getCon().createStatement();
        ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Pessoa");
        r.next();
        int count = r.getInt("rowcount");
        String query = "INSERT INTO Pessoa (nome, idade) VALUES('"+p.getNome()+"','"
                +p.getIdade()+"');";
        stmt.executeUpdate(query);
        ResultSet r2 = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM Pessoa");
        r2.next();
        int count2 = r2.getInt("rowcount");
        con.fecharConexao();
        assertTrue(count2 > count);
	}
	
	@Test
	public void encontraValorTest() throws Exception {
		con.abrirConexao();
		Statement stmt = (Statement) con.getCon().createStatement();
        
        String query = "SELECT * FROM Pessoa;";
        ResultSet resultSet = stmt.executeQuery(query);
        String nomes = "";
        while(resultSet.next()) {
        	nomes += resultSet.getString("nome") + "\n";
        }
        assertTrue(nomes.contains("Joao"));
	}
}
