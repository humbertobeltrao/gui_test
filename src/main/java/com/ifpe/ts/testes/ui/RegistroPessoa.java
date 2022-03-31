package com.ifpe.ts.testes.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Humberto
 */
public class RegistroPessoa {

	private List<Pessoa> cadastro;

	public RegistroPessoa() {
		cadastro = new ArrayList<Pessoa>();
	}

	public void adicionar(Pessoa p) {
		cadastro.add(p);
	}

	public boolean remover(String nome) {
		for(Pessoa p : cadastro) {
			if(p.getNome().equals(nome)) { // if(p.getNome() == nome)
				cadastro.remove(p);
				return true;
			}
		}
		return false;
	}

	public String listar() {
		//        Collections.sort(cadastro);
		String resultado = "";
		for(Pessoa p : cadastro) {
			resultado += p.getNome()+" | "+p.getIdade()+"\n";
		}
		return resultado;
	}

	public void criarArquivo() {
		File file = new File("registroIF.txt");

		if(!file.exists()) {
			try {
				file.createNewFile();


				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(listar());
				bw.close();
			} catch (IOException ex) {
				Logger.getLogger(RegistroPessoa.class.getName()).log(Level.SEVERE, null, ex);
			}
		} 

	}


}

