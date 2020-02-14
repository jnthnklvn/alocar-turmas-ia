package aima.alocar_aulas.csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aima.alocar_aulas.constraint.AllDifferentConstraint;
import aima.alocar_aulas.constraint.HorarioDiasDiferentesConstraint;
import aima.alocar_aulas.constraint.HorarioDiasIguasConstraint;
import aima.alocar_aulas.constraint.HorarioDisciplinaConstraint;
import aima.alocar_aulas.constraint.PreferenciaDisciplinaProfessorConstraint;
import aima.alocar_aulas.constraint.TurmaProfessorConstraint;
import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.Variable;

public class AlocaTurma extends CSP<Variable, String> {

	// Dominio: Professores

	String[] professores = { "Walter", "Elena", "Evelyn", "Mia", "Robert" };

	// Dominio: Disciplinas

	String[] valoresDisciplina = { "Banco de Dados I", "Eletr√¥nica I", "Engenharia de Software II",
			"Intelig√™ncia Artificial", "Laborat√≥rio de Redes de Computadores", "Programa√ß√£o Paralela e Concorrente",
			"Sistemas Distribu√≠dos" };

	// Dominio: Horarios

	String[] valoresHorarios = { "SEG_1300", "SEG_1350", "SEG_1500", "SEG_1550", "SEG_1700", "SEG_1750", "TER_1300",
			"TER_1350", "TER_1500", "TER_1550", "TER_1700", "TER_1750", "QUA_1300", "QUA_1350", "QUA_1500", "QUA_1550",
			"QUA_1700", "QUA_1750", "QUI_1300", "QUI_1350", "QUI_1500", "QUI_1550", "QUI_1700", "QUI_1750", "SEX_1300",
			"SEX_1350", "SEX_1500", "SEX_1550", "SEX_1700", "SEX_1750" };

	// Dominio: dias de aula

	String[] dias = { "SEG", "TER", "QUA", "QUI", "SEX" };

	// Dominio: horarios de aula

	String[] horas = { "13:00", "13:50", "15:00", "15:50", "17:00", "17:50" };

	// Creditos por disciplina

	Integer[] valoresAula = { 4, 4, 4, 4, 2, 4, 4 };

	// Preferencias por disciplinas para cada professor

	HashMap<String, Integer[]> preferencias = new HashMap<String, Integer[]>();

	// Habilidades por disciplinas para cada professor

	HashMap<String, Integer[]> habilidades = new HashMap<String, Integer[]>();

	public AlocaTurma() {

		preferencias.put("Walter", new Integer[] { 4, 6 });
		preferencias.put("Elena", new Integer[] { 4 });
		preferencias.put("Evelyn", new Integer[] { 5, 0 });
		preferencias.put("Mia", new Integer[] { 0 });
		preferencias.put("Robert", new Integer[] { 2, 6 });

		habilidades.put("Walter", new Integer[] { 4, 6 });
		habilidades.put("Elena", new Integer[] { 3, 4 });
		habilidades.put("Evelyn", new Integer[] { 5, 0 });
		habilidades.put("Mia", new Integer[] { 0, 1 });
		habilidades.put("Robert", new Integer[] { 6, 2 });

		// Hor·rios

		List<Variable> horarios = new ArrayList<Variable>();

		// Disciplinas

		List<Variable> disciplinas = new ArrayList<Variable>();

		// Turmas: 1 professor, 1 disciplina, n horarios

		List<Turma> turmas = new ArrayList<Turma>();

		for (int i = 0; i < valoresDisciplina.length; i++) {

			// Add vari·vel para cada disciplina e seta seu dominio com todos disciplinas

			Variable disciplina = new Variable("D" + (i + 1));
			addVariable(disciplina);
			setDomain(disciplina, new Domain<String>(valoresDisciplina));

			disciplinas.add(disciplina);

			// Add vari·vel professor para cada disciplina, e seta seu dominio como
			// professores

			Variable professor = new Variable("P_" + disciplina.getName());
			addVariable(professor);
			setDomain(professor, new Domain<String>(professores));

			// Um professor sÛ pode ministrar uma disciplina que prefere ou tem habilidade

			addConstraint(new PreferenciaDisciplinaProfessorConstraint(professor, disciplina, preferencias, habilidades,
					valoresDisciplina));

			// Turma: 1 professor, 1 disciplina, n horarios

			Turma turma = new Turma(professor, disciplina);

			for (int j = 0; j < valoresAula[i]; j++) {

				// Hor·rio para cada aula

				Variable horario = new Variable("H" + (j + 1) + "_" + disciplina.getName());
				addVariable(horario);
				setDomain(horario, new Domain<String>(valoresHorarios));

				horarios.add(horario);
				turma.addHorario(horario);
			}

			turmas.add(turma);
		}

		// Cada disciplina sÛ pode ter uma turma

		addConstraint(new AllDifferentConstraint(disciplinas));

		// Os horarios de um professor n„o podem repetir

		for (int i = 0; i < professores.length; i++) {
			addConstraint(new TurmaProfessorConstraint(turmas, professores[i]));
		}

		for (Turma horario1 : turmas) {

			for (Turma horario2 : turmas) {

				if (!horario1.getDisciplina().getName().equals(horario2.getDisciplina().getName())) {

					for (int i = 0; i < horario1.getHorarios().size(); i++) {

						for (int j = 0; j < horario2.getHorarios().size(); j++) {

							// Disciplinas de mesmo n„o podem ter as aulas no mesmo hor·rio

							addConstraint(new HorarioDisciplinaConstraint(horario1.getDisciplina(),
									horario1.getHorarios().get(i), horario2.getDisciplina(), horario2.getHorarios().get(j)));
						}
					}
				}
			}
		}

		for (Turma turma : turmas) {

			for (int i = 0; i < turma.getHorarios().size(); i++) {

				Variable horario1 = turma.getHorarios().get(i);
				Variable horario2 = null;

				if ((i + 1) < turma.getHorarios().size()) {

					horario2 = turma.getHorarios().get(++i);

					// Uma turma tem que ter um minimo de aulas seguidas num dia

					addConstraint(new HorarioDiasIguasConstraint(horario1, horario2));
				}
			}

			for (int i = 0; i < turma.getHorarios().size(); i++) {

				Variable horario1 = turma.getHorarios().get(i);
				Variable horario2 = null;

				if (i == turma.getHorarios().size() - 1)
					break;

				if (i == turma.getHorarios().size() - 2)
					horario2 = turma.getHorarios().get(0);

				else if ((i + 2) < turma.getHorarios().size())
					horario2 = turma.getHorarios().get(2 + i);

				// Uma turma tem que ter um m·ximo de aulas seguidas num dia

				addConstraint(new HorarioDiasDiferentesConstraint(horario1, horario2, dias));
			}
		}
	}
}
