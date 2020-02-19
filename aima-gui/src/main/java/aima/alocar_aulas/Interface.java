package aima.alocar_aulas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Dominio: Professores

	String[] professores = { "Walter", "Elena", "Evelyn", "Mia", "Robert" };

	// Dominio: Disciplinas

	String[] disciplinas = { "Banco de Dados I", "Eletronica I", "Engenharia de Software II",
			"Inteligencia Artificial", "Laboratorio de Redes de Computadores", "Programacao Paralela e Concorrente",
			"Sistemas Distribuidos" };

	// Dominio: Horarios

	String[] horarios = { "SEG_1300", "SEG_1350", "SEG_1500", "SEG_1550", "SEG_1700", "SEG_1750", "TER_1300",
			"TER_1350", "TER_1500", "TER_1550", "TER_1700", "TER_1750", "QUA_1300", "QUA_1350", "QUA_1500", "QUA_1550",
			"QUA_1700", "QUA_1750", "QUI_1300", "QUI_1350", "QUI_1500", "QUI_1550", "QUI_1700", "QUI_1750", "SEX_1300",
			"SEX_1350", "SEX_1500", "SEX_1550", "SEX_1700", "SEX_1750" };

	// Dominio: dias de aula

	String[] dias = { "SEG", "TER", "QUA", "QUI", "SEX" };

	// Dominio: horarios de aula

	String[] horas = { "13:00", "13:50", "15:00", "15:50", "17:00", "17:50" };
	
	// Creditos por disciplina

	Integer[] creditos = { 4, 4, 4, 4, 2, 4, 4 };
	
	static JFrame frame;
	JLabel result;
	String currentProfessor;
	String currentDisciplina;
	String currentHora;
	String currentDia;
	String currentHorario;

	public Interface() {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		currentProfessor = professores[0];
		currentDisciplina = disciplinas[0];
		currentHorario = horarios[0];
		currentDia = dias[0];
		currentHora = horas[0];

		// Set up the UI for selecting a pattern.
		JLabel patternLabel1 = new JLabel("Enter the pattern string or");
		JLabel patternLabel2 = new JLabel("select one from the list:");

		JComboBox<String> patternList = new JComboBox<String>(professores);
		patternList.setEditable(true);
		patternList.addActionListener(this);

		// Create the UI for displaying result.
		JLabel resultLabel = new JLabel("Current Date/Time", JLabel.LEADING); // == LEFT
		result = new JLabel(" ");
		result.setForeground(Color.black);
		result.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		// Lay out everything.
		JPanel patternPanel = new JPanel();
		patternPanel.setLayout(new BoxLayout(patternPanel, BoxLayout.PAGE_AXIS));
		patternPanel.add(patternLabel1);
		patternPanel.add(patternLabel2);
		patternList.setAlignmentX(Component.LEFT_ALIGNMENT);
		patternPanel.add(patternList);

		JPanel resultPanel = new JPanel(new GridLayout(0, 1));
		resultPanel.add(resultLabel);
		resultPanel.add(result);

		patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		add(patternPanel);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(resultPanel);

		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		reformat();
	} // constructor

	public void actionPerformed(ActionEvent e) {
		JComboBox<String>  cb = (JComboBox<String>) e.getSource();
		String newSelection = (String) cb.getSelectedItem();
		currentProfessor = newSelection;
		reformat();
	}

	/** Formats and displays today's date. */
	public void reformat() {
		try {
			result.setForeground(Color.black);
			result.setText(currentProfessor);
		} catch (IllegalArgumentException iae) {
			result.setForeground(Color.red);
			result.setText("Error: " + iae.getMessage());
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("ComboBoxDemo2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new Interface();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}