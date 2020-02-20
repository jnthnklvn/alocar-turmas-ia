package aima.alocar_aulas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aima.core.util.datastructure.XYLocation;

public class PainelPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;

	private String currentProfessor;
	private List<String> professores = new ArrayList<String>();
	private final String[] professoresPadroes = { "Walter", "Elena", "Evelyn", "Mia", "Robert" };

	private List<XYLocation> preferenciasProfessores = new ArrayList<XYLocation>();

	private String currentDisciplina;
	private final String[] disciplinas = { "Banco de Dados I", "Eletronica I", "Engenharia de Software II",
			"Inteligencia Artificial", "Laboratorio de Redes de Computadores", "Programacao Paralela e Concorrente",
			"Sistemas Distribuidos" };

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

	public static void main(String[] args) {
		frame = new PainelPrincipal();
		frame.setVisible(true);
	}

	private PainelPrincipal() {
		for (String professor : professoresPadroes) {
			professores.add(professor);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alocar Disciplinas");
		setSize(1000, 600);
		setMinimumSize(getSize());

		createMenuBar();

		// Painel principal
		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelPrincipal.setLayout(null);
		painelPrincipal.setBackground(Color.white);
		setContentPane(painelPrincipal);

		painelPrincipal.add(getPainelSelecao());

		validate();
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
		painelSelecao.setBounds(5, 5, 975, 35);
		painelSelecao.setBackground(Color.LIGHT_GRAY);

		/*
		 * Preferencias professor
		 */
		// ComboBox para selecionar professor
		JComboBox<String> cbProfessor = new JComboBox<String>(professoresPadroes);
		cbProfessor.setEditable(true);
		currentProfessor = professoresPadroes[0];

		cbProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentProfessor = (String) cbProfessor.getSelectedItem();
			}
		});
		painelSelecao.add(cbProfessor);

		// Label de preferencia do professor
		JLabel labelPreferencia = new JLabel("prefere");
		painelSelecao.add(labelPreferencia);

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
				add();
			}
		});
		painelSelecao.add(botaoAdd, BorderLayout.LINE_END);

		/*
		 * Turma fixa
		 */
		JLabel labelTurmaFixa = new JLabel("        Turma Fixa: ");
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

	protected void add() {
		for (int i = 0; i < disciplinas.length; i++) {
			if (disciplinas[i].equals(currentDisciplina)) {
				int indexProfessor = professores.indexOf(currentProfessor);
				if (indexProfessor == -1) {
					professores.add(currentProfessor);
					preferenciasProfessores.add(new XYLocation(professores.size() - 1, i));
					System.out.println(currentProfessor + " adicionado(a) e " + currentDisciplina
							+ " adicionada as suas preferencias");
				} else {
					XYLocation xyLocation = new XYLocation(indexProfessor, i);
					if (preferenciasProfessores.indexOf(xyLocation) == -1) {
						int countPrefsProfessor = 0;
						for (int j = 0; j < preferenciasProfessores.size(); j++) {
							if (preferenciasProfessores.get(j).getX() == indexProfessor) {
								countPrefsProfessor += 1;
							}
						}
						if (countPrefsProfessor < 3) {
							preferenciasProfessores.add(xyLocation);
							System.out.println(
									currentDisciplina + " adicionada(a) as preferencias de " + currentProfessor);
						} else
							System.out.println(currentProfessor + " antigiu o maximo de preferencias");
					} else
						System.out.println(currentProfessor + " ja prefere " + currentDisciplina);
				}
				break;
			}
		}
		System.out.println(preferenciasProfessores);
		System.out.println(professores + "\n");
	}

	public void start() {
		// 0x0 => 0, 1, 12, 13 // 0x1 => 6, 7, 18, 19 // 0x2 => 12, 13, 24, 25
		// 1x0 => 2, 3, 14, 15 // 1x1 => 8, 9, 20, 21 // 1x2 => 14, 15, 26, 27
		// 2x0 => 4, 5, 16, 17 // 2x1 => 10, 11, 22, 23 // 2x2 => 16, 17, 28, 29

		System.out.println(preferenciasProfessores);
		System.out.println(professores + "\n");
		int first;
		for (int i = 0; i < horas.length; i++) {
			if (horas[i].contains(horaTurmaFixa)) {
				for (int j = 0; j < diasAula.length; j++) {
					if (diasAula[j].contains(diaTurmaFixa)) {
						first = (2 * i) + (j * 6);
						System.out.println(horarios[first]);
						System.out.println(horarios[first + 1]);
						System.out.println(horarios[first + 12]);
						System.out.println(horarios[first + 13]);
						break;
					}
				}
				break;
			}
		}
	}
}
