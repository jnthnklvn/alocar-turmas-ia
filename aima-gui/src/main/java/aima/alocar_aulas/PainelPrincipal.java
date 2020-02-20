package aima.alocar_aulas;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import aima.alocar_aulas.database.Conn;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PainelPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;



	String currentProfessor;
	String[] professores = { "Walter", "Elena", "Evelyn", "Mia", "Robert" };

	String currentDisciplina;
	String[] disciplinas = { "Banco de Dados I", "Eletronica I", "Engenharia de Software II", "Inteligencia Artificial",
			"Laboratorio de Redes de Computadores", "Programacao Paralela e Concorrente", "Sistemas Distribuidos" };

	String currentHora;
	String[] horas = { "13:00-13:50", "15:00-15:50", "17:00-17:50" };

	String currentDia;
	String[] selectDias = { "SEG-QUA", "TER-QUI", "QUA-SEX" };
	String[] dias = { "SEG", "TER", "QUA", "QUI", "SEX" };

	String currentHorario;
	String[] horarios = { "SEG_1300", "SEG_1350", "SEG_1500", "SEG_1550", "SEG_1700", "SEG_1750", "TER_1300",
			"TER_1350", "TER_1500", "TER_1550", "TER_1700", "TER_1750", "QUA_1300", "QUA_1350", "QUA_1500", "QUA_1550",
			"QUA_1700", "QUA_1750", "QUI_1300", "QUI_1350", "QUI_1500", "QUI_1550", "QUI_1700", "QUI_1750", "SEX_1300",
			"SEX_1350", "SEX_1500", "SEX_1550", "SEX_1700", "SEX_1750" };

	private JPanel contentPane;
	public static JFrame frame;

	public static void main(String[] args) {
		frame = new PainelPrincipal();
		frame.setVisible(true);
	}

	public PainelPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alocar Disciplinas");
		setSize(1000, 600);
		setMinimumSize(getSize());

		// Create Menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);

		// Menu exit this application
		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		menu.add(sair);

		// Panel main (painting on this panel)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel);

		JPanel panel1 = new JPanel();
		FlowLayout flowLayout1 = (FlowLayout) panel1.getLayout();
		flowLayout1.setAlignment(FlowLayout.LEFT);
		panel1.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 5, 750, 70);
		contentPane.add(panel1);

		// ComboBox para selecionar professor
		JComboBox<String> cbProfessor = new JComboBox<String>(professores);
		currentProfessor = professores[0];
		
		// ComboBox para selecionar professor
		JComboBox<String> cbProfessor1 = new JComboBox<String>(professores);
		currentProfessor = professores[0];
		panel1.add(cbProfessor1);

		cbProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentProfessor = (String) cbProfessor.getSelectedItem();
			}
		});
		panel.add(cbProfessor);

		// Label de preferencia do professor
		JLabel labelPreferencia = new JLabel("prefere");
		panel.add(labelPreferencia);

		// ComboBox para selecionar disciplina
		JList<String> cbDisciplina = new JList<String>(disciplinas);
		currentDisciplina = disciplinas[0];

		
		panel.add(cbDisciplina);

		// ComboBox para selecionar horario
		// JComboBox<String> cbHorario = new JComboBox<String>(horarios);
		// currentHorario = horarios[0];
		//
		// cbHorario.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// currentHorario = (String) cbHorario.getSelectedItem();
		// }
		// });
		// panel.add(cbHorario);

		// Label de turma fixa
		JLabel labelTurmaFixa = new JLabel("        Turma Fixa: ");
		panel.add(labelTurmaFixa);

		// ComboBox para selecionar dia
		JComboBox<String> cbDia = new JComboBox<String>(selectDias);
		currentDia = selectDias[0];
		cbDia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentDia = (String) cbDia.getSelectedItem();
			}
		});
		panel.add(cbDia);

		// ComboBox para selecionar hora
		JComboBox<String> cbHora = new JComboBox<String>(horas);
		currentHora = horas[0];

		cbHora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentHora = (String) cbHora.getSelectedItem();
			}
		});
		panel.add(cbHora);

		// Add botao iniciar
		JButton bttnInicar = new JButton();
		bttnInicar.setText("Iniciar");
		bttnInicar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		panel.add(bttnInicar, BorderLayout.LINE_END);

		// Create new Component paint to paint with mouse
		contentPane.add(new PainelDesenho(), BorderLayout.CENTER);
		validate();
	}

	public void start() {
		// 0x0 => 0, 1, 12, 13 // 0x1 => 6, 7, 18, 19 // 0x2 => 12, 13, 24, 25
		// 1x0 => 2, 3, 14, 15 // 1x1 => 8, 9, 20, 21 // 1x2 => 14, 15, 26, 27
		// 2x0 => 4, 5, 16, 17 // 2x1 => 10, 11, 22, 23 // 2x2 => 16, 17, 28, 29

		System.out.println(currentProfessor);
		System.out.println(currentDisciplina + "\n");

		int first;
		for (int i = 0; i < horas.length; i++) {
			if (horas[i].contains(currentHora)) {
				for (int j = 0; j < selectDias.length; j++) {
					if (selectDias[j].contains(currentDia)) {
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
