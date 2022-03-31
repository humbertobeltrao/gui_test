package com.ifpe.ts.testes.ui;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.awt.EventQueue;
import java.util.logging.Logger;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.text.JTextComponent;
import javax.swing.UnsupportedLookAndFeelException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author Humberto
 */
public class GUI extends JFrame{

	public GUI() throws MalformedURLException, IOException {
		initComponents();
	}

	private void initComponents() throws MalformedURLException, IOException {
		//initLookAndFeel();
		setTitle("Aula Testes UI");
		RegistroPessoa registro = new RegistroPessoa();



		//JFrame jFrame = new JFrame("Aula Testes UI");

		setBounds(0, 0, 500, 400);
		setLocationRelativeTo(null);


		JMenuBar barraMenu = new JMenuBar();

		//Menu do aluno
		JMenu menuCadastro = new JMenu("Pessoa");
		JMenuItem itemMenuCadastro = new JMenuItem("Cadastrar");
		menuCadastro.add(itemMenuCadastro);

		barraMenu.add(menuCadastro);

		setJMenuBar(barraMenu);

		setBackground(Color.WHITE);
		setContentPane(new JLabel(new ImageIcon(ImageIO.read(new URL("https://www.ifpe.edu.br/campus/barreiros/noticias/atualizada-lista-de-candidatos-ao-consup/ifpe.png/@@images/ac082237-50ce-49d0-81f2-d90c6664cf71.png")))));

		itemMenuCadastro.addActionListener((ActionEvent e) -> {
			JPanel jPanel = new JPanel();
			
			jPanel.setPreferredSize(new Dimension(500, 400));
			JLabel lblNomePessoa = new JLabel("Nome da Pessoa: ", SwingConstants.CENTER);
			JTextField txtNomePessoa = new JTextField("", 30);
			txtNomePessoa.setText("Digite um nome");
			JLabel lblIdadePessoa = new JLabel("Idade da Pessoa: ", SwingConstants.CENTER);
			JTextField txtIdadePessoa = new JTextField("", 30);
			//JButton btnAdd = new JButton("Adicionar");
			JButton btnAdicionar = new JButton("Adicionar");
			JButton btnAdicionarBd = new JButton("Adicionar BD");
			
			JButton btnRemover = new JButton("Remover");
			JButton btnListar = new JButton("Listar");
			JButton btnArquivo = new JButton("Criar arquivo");
			JTextField textField = new JTextField();
			textField.setName("textField"); //utilizado para realizar teste de UI
			
			
			
			textField.setEditable(false);
			textField.setHorizontalAlignment(JTextField.CENTER);
			textField.setPreferredSize(new Dimension(200, 150));
			textField.setBackground(new Color(255, 255, 255));
			jPanel.add(lblNomePessoa);
			jPanel.add(txtNomePessoa);
			jPanel.add(lblIdadePessoa);
			jPanel.add(txtIdadePessoa);
			
			jPanel.add(btnAdicionar);
			jPanel.add(btnRemover);
			jPanel.add(btnListar);
			jPanel.add(btnArquivo);
			jPanel.add(btnAdicionarBd);
			jPanel.add(textField);
			//clique no botÃ£o Adicionar
			btnAdicionar.addActionListener((ActionEvent e1) -> {
				if(!"".equals(txtNomePessoa.getText()) && !"".equals(txtIdadePessoa.getText())) {
					Pessoa p = new Pessoa(txtNomePessoa.getText(), Integer.parseInt(txtIdadePessoa.getText()));
					registro.adicionar(p);
					txtNomePessoa.setText("");
					txtIdadePessoa.setText("");
					JOptionPane.showMessageDialog(jPanel, "Inserção realizada com sucesso!", "Cadastro IFPE", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(jPanel, "Verifique os campos a serem preenchidos", "Cadastro IFPE", JOptionPane.ERROR_MESSAGE);
				}
			});
			//clique no botÃ£o de Remover
			btnRemover.addActionListener((ActionEvent e1) -> {
				String nome = JOptionPane.showInputDialog(jPanel, "Informe o nome a ser removido", "Cadastro IFPE", JOptionPane.QUESTION_MESSAGE);
				if(!"".equals(nome)) {
					if(registro.remover(nome)) { // if(true) { -> achou o nome que deseja remover
						JOptionPane.showMessageDialog(jPanel, "Remoção realizada com sucesso!", "Cadastro IFPE", JOptionPane.INFORMATION_MESSAGE);
					} else { //nÃ£o achou no cadastro de pessoas o nome informado
						JOptionPane.showMessageDialog(jPanel, "Pessoa nÃ£o encontrada no registro", "Cadastro IFPE", JOptionPane.ERROR_MESSAGE);
					}
				} else { //nÃ£o passou nome pra remover
					JOptionPane.showMessageDialog(jPanel, "VocÃª precisa informar o nome a ser removido", "Cadastro IFPE", JOptionPane.ERROR_MESSAGE);
				}
			});

			//clique no botÃ£o Listar
			btnListar.addActionListener((ActionEvent e1) -> {
				//JOptionPane.showMessageDialog(jPanel, registro.listar(), "Cadastro IFPE", JOptionPane.INFORMATION_MESSAGE);
				Conexao con = new Conexao();
				try {
					con.abrirConexao();
					Statement stmt = (Statement) con.getCon().createStatement();

					String query = "SELECT * FROM Pessoa;";
					ResultSet resultSet = stmt.executeQuery(query);
					String nomes = "";
					while(resultSet.next()) {
						nomes += resultSet.getString("nome") + "\n";
					}
					textField.setText(nomes);
					//JOptionPane.showMessageDialog(jPanel, nomes, "Cadastro IFPE", JOptionPane.INFORMATION_MESSAGE);
					con.fecharConexao();

				} catch (Exception ex) {
					Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
				}


			});

			btnArquivo.addActionListener((ActionEvent e1) -> {
				registro.criarArquivo();
				JOptionPane.showMessageDialog(jPanel, "Arquivo criado com sucesso.", "Cadastro IFPE", JOptionPane.INFORMATION_MESSAGE);
			});

			btnAdicionarBd.addActionListener((ActionEvent e1) -> {
				Conexao con = new Conexao();
				try {
					con.abrirConexao();
					Statement stmt = (Statement) con.getCon().createStatement();

					String query = "INSERT INTO Pessoa (nome, idade) VALUES('"+txtNomePessoa.getText()+"','"
							+Integer.parseInt(txtIdadePessoa.getText())+"');";
					stmt.executeUpdate(query);
					con.fecharConexao();
					txtNomePessoa.setText("");
					txtIdadePessoa.setText("");
					JOptionPane.showMessageDialog(jPanel, "Registro inserido com sucesso.", "Cadastro IFPE", JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception ex) {
					Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
				}

			});
			//jFrame.setContentPane(textoAluno);
			setContentPane(jPanel); //alterar o painel
			validate(); //atualizar a tela
		});


		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	}
	private void initLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			System.exit(0);
		}
	}


	public static void main(String[] args) throws MalformedURLException, IOException {
		EventQueue.invokeLater(() -> {
			try {
				new GUI().setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});


	}


}
