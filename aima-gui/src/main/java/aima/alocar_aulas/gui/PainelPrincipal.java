package aima.alocar_aulas.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import aima.alocar_aulas.csp.AlocaTurma;
import aima.alocar_aulas.database.Conn;
import aima.alocar_aulas.model.ProfessorAndDisciplinas;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.Variable;

public class PainelPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	Conn conexao = new Conn("PostgreSql","localhost","5432","alocar_aulas","postgres","Qmasbsi45.");
	private String currentProfessor;
	private List<String> professores = new ArrayList<String>();
	private final String[] professoresPadroes = { "Walter", "Elena", "Evelyn", "Steve", "Mia", "Robert", "Lana" };
	private List<String> professores_query = new ArrayList<String>();
	private List<ProfessorAndDisciplinas> preferenciasProfessores = new ArrayList<ProfessorAndDisciplinas>();

	private List<ProfessorAndDisciplinas> habilidadesProfessores = new ArrayList<ProfessorAndDisciplinas>();

	private String currentDisciplina;
	private final String[] disciplinas = { "Banco de Dados I", "Eletronica I", "Engenharia de Software II",
			"Inteligencia Artificial", "Laboratorio de Redes de Computadores", "Programacao Paralela e Concorrente",
			"Sistemas Distribuidos" };

	private String currentSkillOrPref;
	private final String habilidade = "Habilidade";
	private final String preferencia = "Preferencia";
	private ResultSet professoresQueryResult;
	private String horaTurmaFixa;
	private final String[] horas = { "13:00-13:50", "15:00-15:50", "17:00-17:50" };

	private String diaTurmaFixa;
	private final String[] diasAula = { "SEG-QUA", "TER-QUI", "QUA-SEX" };

	private final String[] horarios = { "SEG_1300", "SEG_1350", "SEG_1500", "SEG_1550", "SEG_1700", "SEG_1750",
			"TER_1300", "TER_1350", "TER_1500", "TER_1550", "TER_1700", "TER_1750", "QUA_1300", "QUA_1350", "QUA_1500",
			"QUA_1550", "QUA_1700", "QUA_1750", "QUI_1300", "QUI_1350", "QUI_1500", "QUI_1550", "QUI_1700", "QUI_1750",
			"SEX_1300", "SEX_1350", "SEX_1500", "SEX_1550", "SEX_1700", "SEX_1750" };

	private JPanel painelPrincipal;
	public static JFrame frame;
	private JComboBox<String> cbProfessor;
	private JTextArea jScrollTextArea;

	List<JTextArea> resultTexts = new ArrayList<JTextArea>();

	public ProfessorAndDisciplinas getDisciplinaPr(List<ProfessorAndDisciplinas> listDp, int index) {
		for (ProfessorAndDisciplinas prefPr : listDp) {
			if (prefPr.getProfessor() == index) {
				return prefPr;
			}
		}
		ProfessorAndDisciplinas pAp = new ProfessorAndDisciplinas(index, new ArrayList<Integer>());
		listDp.add(pAp);
		return pAp;
	}

	public boolean profTemDisc(List<ProfessorAndDisciplinas> listDp, int prof, int pref) {
		if (listDp.size() == 0) {
			return false;
		}
		for (int prefPr : listDp.get(prof).getDisciplinas()) {
			if (prefPr == pref) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws SQLException {
		frame = new PainelPrincipal();
		frame.setVisible(true);
	}

	public String getProfessorAndDisciplina(ProfessorAndDisciplinas pAd, boolean isSkill) {

		String result = professores.get(pAd.getProfessor());

		if (isSkill)
			result += " possui " + habilidade + "(s) em ";
		else
			result += " possui " + preferencia + "(s) em ";
		System.out.println(result);
		for (int i = 0; i < pAd.getDisciplinas().size(); i++) {
			if (i == 0)
				result += disciplinas[pAd.getDisciplinas().get(i)];
			else
				result += ", " + disciplinas[pAd.getDisciplinas().get(i)];
			System.out.println(result);
		}
		return result;
	}

	private PainelPrincipal() throws SQLException {
		conexao.connect();

		professoresQueryResult = conexao.query("Select id, nome from professor");
		while (professoresQueryResult.next()) {
			professores_query.add(professoresQueryResult.getString(2));
		}

		for (int i = 0; i < professores_query.size(); i++) {
			List<Integer> habs = new ArrayList<Integer>();
			habs.add(professores_query.size() - 1 - i);
			professores.add(professores_query.get(i));
			String query = "" +
					"select habilidades.disciplina as disciplina_habilidade, " +
					"preferencias.disciplina as disciplina_preferencia\n" +
					"from professor \n" +
					"\tleft join habilidades on habilidades.professor_id = professor.id  \n" +
					"\tleft join preferencias on preferencias.professor_id = professor.id \n" +
					"where professor.nome = '" + professores_query.get(i) + "'";
			ResultSet result2 = conexao.query(query);

			ArrayList habilidades_professores = new ArrayList<Integer>();
			ArrayList preferencias_professores = new ArrayList<Integer>();
			while (result2.next()) {
				if (result2.getString(1) != null ){
					for (int j = 0; j < disciplinas.length; j++) {
						if (result2.getString(1).equals(disciplinas[j])){
							habilidades_professores.add(j);
						}
					}
				}
				else if (result2.getString(2) != null) {
					for (int j = 0; j < disciplinas.length; j++) {
					if (result2.getString(2).equals(disciplinas[j])){
						preferencias_professores.add(j);
					}}
				}
			}

			preferenciasProfessores.add(new ProfessorAndDisciplinas(i,preferencias_professores ));
			habilidadesProfessores.add(new ProfessorAndDisciplinas(i, habilidades_professores));
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alocar Disciplinas");
		setSize(1700, 720);
		setMinimumSize(getSize());
		setMaximumSize(getSize());

		createMenuBar();

		// Painel principal
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setLayout(null);
		painelPrincipal.setBackground(Color.lightGray);
		setContentPane(painelPrincipal);

		painelPrincipal.add(getPainelSelecao());
		painelPrincipal.add(getPainelInicial());
		painelPrincipal.add(getResultPainel());

		validate();
		conexao.disconnect();
	}

	public JPanel getPainelInicial() {
		JPanel painelInicial = new JPanel();
		painelInicial.setBounds(5, 75, 1700, 400);
		painelInicial.setLayout(new BoxLayout(painelInicial, BoxLayout.Y_AXIS));

		JLabel vazio = new JLabel("\n");
		painelInicial.add(vazio);

		JLabel labelInicial = new JLabel("Dados iniciais");
		painelInicial.add(labelInicial);

		vazio = new JLabel("\n");
		painelInicial.add(vazio);

		JLabel labelProfessores = new JLabel();
		painelInicial.add(labelProfessores);

		vazio = new JLabel("\n");
		painelInicial.add(vazio);

		JLabel labelDisciplinas = new JLabel();
		painelInicial.add(labelDisciplinas);

		vazio = new JLabel("\n");
		painelInicial.add(vazio);

		String professoresList = "Lista de professores: ";
		for (ProfessorAndDisciplinas pAd : habilidadesProfessores) {

			professoresList += professores.get(pAd.getProfessor()) + ", ";
			System.out.println(pAd.getDisciplinas());
			JLabel labelPreferencia = new JLabel(getProfessorAndDisciplina(pAd, true));
			painelInicial.add(labelPreferencia);
		}

		String disString = "Lista de disciplinas: ";
		for (String d : disciplinas) {
			disString += d + ", ";
		}

		labelDisciplinas.setText(disString.substring(0, disString.length() - 2) + "\n");

		vazio = new JLabel("\n");
		painelInicial.add(vazio);

		labelProfessores.setText(professoresList.substring(0, professoresList.length() - 2) + "\n");

		JLabel labelTurmaFixa = new JLabel(disciplinas[1] + " é uma turma fixa por ser de outro departamento");
		painelInicial.add(labelTurmaFixa);

		vazio = new JLabel("\n");
		painelInicial.add(vazio);

		jScrollTextArea = new JTextArea();
		jScrollTextArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(jScrollTextArea);
		painelInicial.add(scroll);
		
		return painelInicial;
	}

	public String getValueFormatted(String str) {
		int start = str.indexOf("=");
		return str.substring(start, str.length());
	}

	private JPanel getResultPainel() {
		JPanel painelResult = new JPanel();
		painelResult.setBounds(5, 485, 1700, 165);
		painelResult.setLayout(new BoxLayout(painelResult, BoxLayout.X_AXIS));

		for (int i = 0; i < disciplinas.length; i++) {
			JLabel vazio = new JLabel("\n");
			painelResult.add(vazio);

			JTextArea jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			resultTexts.add(jTextArea);
			painelResult.add(jTextArea);
		}
		
		return painelResult;
	}

	private void createMenuBar() {
		// Barra de menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);

		// Botao para fechar app
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		menu.add(sair);
	}

	public JPanel getPainelSelecao() {
		JPanel painelSelecao = new JPanel();
		final JPanel parent = painelSelecao;
		JButton button = new JButton();
		button.setText("Clique para adicionar professores");
		parent.add(button);
		parent.setVisible(true);
		/*
		 * Preferencias professor
		 */
		// ComboBox para selecionar professor
		String[] array = professores_query.toArray(new String[professores_query.size()]);
		cbProfessor = new JComboBox<String>(array);
		cbProfessor.setEditable(false);
		String query = " insert into professor (nome)"
				+ " values (?)";
		button.addActionListener(evt -> {
			conexao.connect();
			String name = JOptionPane.showInputDialog(parent,
					"Qual o nome do professor?", null);
			try {
				PreparedStatement preparedStmt = conexao.prepareStatement(query);
				preparedStmt.setString (1, name);
				preparedStmt.execute();
				cbProfessor.addItem(name);
			}catch (SQLException e) {
				e.printStackTrace();
			}
			conexao.disconnect();
		});
		painelSelecao.setBounds(5, 5, 1600, 35);

		if (professores_query.size() > 0) {
			currentProfessor = professores_query.get(0);
		}

		cbProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentProfessor = (String) cbProfessor.getSelectedItem();
			}
		});

		painelSelecao.add(cbProfessor);
		JButton button2 = new JButton();
		button2.setText("X");
		button2.setMargin(new Insets(0, 0, 0, 0));
		painelSelecao.add(button2);
		String query2 = " delete from professor where nome ="
				+ " ?";
		button2.addActionListener(evt -> {
			conexao.connect();
			try {
				PreparedStatement preparedStmt = conexao.prepareStatement(query2);
				preparedStmt.setString (1, (String) cbProfessor.getSelectedItem());
				preparedStmt.execute();
				cbProfessor.removeItem(cbProfessor.getSelectedItem());
			}catch (SQLException e) {
				e.printStackTrace();
			}
			conexao.disconnect();
		});

		// Label de preferencia do professor
		JLabel labelPreferencia = new JLabel("tem ");
		painelSelecao.add(labelPreferencia);

		// ComboBox para selecionar habilidade ou preferencia
		JComboBox<String> cbSkillPref = new JComboBox<String>();
		cbSkillPref.addItem(habilidade);
		cbSkillPref.addItem(preferencia);
		currentSkillOrPref = habilidade;

		cbSkillPref.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentSkillOrPref = (String) cbSkillPref.getSelectedItem();
			}
		});

		painelSelecao.add(cbSkillPref);

		// Label de referencia à disciplina
		JLabel labelConectivo = new JLabel("em ");
		painelSelecao.add(labelConectivo);

		// ComboBox para selecionar disciplina
		JComboBox<String> cbDisciplina = new JComboBox<String>(disciplinas);
		currentDisciplina = disciplinas[0];

		cbDisciplina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDisciplina = (String) cbDisciplina.getSelectedItem();
			}
		});

		painelSelecao.add(cbDisciplina);

		// Botao adicionar preferencias e/ou professor
		JButton botaoAdd = new JButton();
		botaoAdd.setText("Adicionar");
		botaoAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentProfessor != null) {
				addDisciplina();}
			}
		});
		painelSelecao.add(botaoAdd, BorderLayout.LINE_END);

		/*
		 * Turma fixa
		 */
		JLabel labelTurmaFixa = new JLabel("Turma Fixa: ");
		painelSelecao.add(labelTurmaFixa);

		// ComboBox para selecionar dia
		JComboBox<String> cbDia = new JComboBox<String>(diasAula);
		diaTurmaFixa = diasAula[0];
		cbDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				diaTurmaFixa = (String) cbDia.getSelectedItem();
			}
		});
		painelSelecao.add(cbDia);

		// ComboBox para selecionar hora
		JComboBox<String> cbHora = new JComboBox<String>(horas);
		horaTurmaFixa = horas[0];

		cbHora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				horaTurmaFixa = (String) cbHora.getSelectedItem();
			}
		});
		painelSelecao.add(cbHora);

		// Add botao iniciar
		JButton bttnInicar = new JButton();
		bttnInicar.setText("Iniciar");
		bttnInicar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		painelSelecao.add(bttnInicar, BorderLayout.LINE_END);

		return painelSelecao;
	}

	protected void addDisciplina() {
		if (currentSkillOrPref.equals(habilidade))
			addDisciplina(true, habilidadesProfessores);
		else
			addDisciplina(false, preferenciasProfessores);

	}

	protected void addDisciplina(boolean isSkill, List<ProfessorAndDisciplinas> listaD) {
		String msg = "";
		for (int i = 0; i < disciplinas.length; i++) {
			if (disciplinas[i].equals(currentDisciplina)) {
				int indexProfessor = professores.indexOf(currentProfessor);
				if (indexProfessor == -1) {
					List<Integer> preferenciasProfessor = new ArrayList<Integer>();
					List<Integer> habilidadesProfessor = new ArrayList<Integer>();

					if (isSkill)
						habilidadesProfessor.add(i);
					else
						preferenciasProfessor.add(i);

					professores.add(currentProfessor);

					int newProfIndex = professores.size() - 1;

					habilidadesProfessores.add(new ProfessorAndDisciplinas(newProfIndex, habilidadesProfessor));
					preferenciasProfessores.add(new ProfessorAndDisciplinas(newProfIndex, preferenciasProfessor));
					msg = currentProfessor + " adicionado(a) a professores e " + currentDisciplina
							+ " foi adicionada as suas " + currentSkillOrPref + "s";
				} else {
					if (!profTemDisc(listaD, indexProfessor, i)) {
						ProfessorAndDisciplinas prAndDisciplinas = getDisciplinaPr(listaD, indexProfessor);
						if (prAndDisciplinas.getDisciplinas().size() < 3) {
							prAndDisciplinas.getDisciplinas().add(i);
							if (isSkill) {
								habilidadesProfessores.set(indexProfessor, prAndDisciplinas);
								String query3 = " insert into habilidades (disciplina,professor_id)"
										+ " values (?, ?)";
								conexao.connect();
								try {
									PreparedStatement preparedStmt = conexao.prepareStatement(query3);
									preparedStmt.setString (1, currentDisciplina);
									preparedStmt.setInt (2, cbProfessor.getSelectedIndex() + 1);
									preparedStmt.execute();
								}catch (SQLException e) {
									e.printStackTrace();
								}
								conexao.disconnect();
							}
							else{
								preferenciasProfessores.set(indexProfessor, prAndDisciplinas);
								String query3 = " insert into preferencias (disciplina,professor_id)"
										+ " values (?, ?)";
								conexao.connect();
								try {
									PreparedStatement preparedStmt = conexao.prepareStatement(query3);
									preparedStmt.setString (1, currentDisciplina);
									preparedStmt.setInt (2, cbProfessor.getSelectedIndex() + 1);
									preparedStmt.execute();
								}catch (SQLException e) {
									e.printStackTrace();
								}
								conexao.disconnect();


								msg = currentDisciplina + " adicionada(a) as " + currentSkillOrPref + "s de "
									+ currentProfessor;
							}
						} else
							msg = currentProfessor + " antigiu o maximo de " + currentSkillOrPref + "s";
					} else
						msg = currentProfessor + " ja possui " + currentSkillOrPref + " em " + currentDisciplina;
				}
				jScrollTextArea.setText(jScrollTextArea.getText() + "   " + msg + "\n");
				System.out.println(msg);
				break;
			}
		}
	}

	public void start() {
		// 0x0 => 0, 1, 12, 13 // 0x1 => 6, 7, 18, 19 // 0x2 => 12, 13, 24, 25
		// 1x0 => 2, 3, 14, 15 // 1x1 => 8, 9, 20, 21 // 1x2 => 14, 15, 26, 27
		// 2x0 => 4, 5, 16, 17 // 2x1 => 10, 11, 22, 23 // 2x2 => 16, 17, 28, 29
		System.out.println("\nProfessores => " + professores + "\n");

		int first = 0;
		for (int i = 0; i < horas.length; i++) {
			if (horas[i].contains(horaTurmaFixa)) {
				for (int j = 0; j < diasAula.length; j++) {
					if (diasAula[j].contains(diaTurmaFixa)) {
						first = (2 * i) + (j * 6);
						System.out.println("Horarios turma fixa => " + horarios[first] + ", " + horarios[first + 1]
								+ ", " + horarios[first + 12] + ", " + horarios[first + 13] + "\n");
						break;
					}
				}
				break;
			}
		}

		double inicio = System.currentTimeMillis();
		FlexibleBacktrackingSolver<Variable, String> fBSolver = new FlexibleBacktrackingSolver<>();

		AlocaTurma alocaTurma = new AlocaTurma(professores, preferenciasProfessores, habilidadesProfessores,
				horarios[first], horarios[first + 1], horarios[first + 12], horarios[first + 13]);
		Optional<Assignment<Variable, String>> solucao = fBSolver.solve(alocaTurma);

		if (solucao.isPresent()) {
			String sol = solucao.get().toString().replace(", P", ":\n\nP").replace(", D", "\n\nD").replace(", ", "\n");
			String[] sov = solucao.get().toString().substring(1, sol.length() - 1).split(", ");
			String formatted = "";
			List<String> newSol = new ArrayList<String>();

			for (String string : sov) {
				switch (string.charAt(0)) {
				case 'D':
					if (formatted != "") {
						newSol.add(formatted.substring(0, formatted.length() - 1));
						formatted = "";
					}
					formatted += string.substring(3) + "\n";
					break;
				case 'P':
					formatted += string.substring(5) + "\n";
					break;
				case 'H':
					formatted += string.substring(6) + "\n";
					break;
				default:
					break;
				}
			}

			for (int i = 0; i < newSol.size(); i++) {
				resultTexts.get(i).setText(newSol.get(i) + "\n");
			}
			System.out.println(sol.substring(1, sol.length() - 1));
		}

		double fim = System.currentTimeMillis();
		System.out.println("\nTempo: " + (fim - inicio));
	}
}
