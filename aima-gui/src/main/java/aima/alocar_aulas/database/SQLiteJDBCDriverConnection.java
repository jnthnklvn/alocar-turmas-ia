package aima.alocar_aulas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBCDriverConnection {
	private Connection connection;
	private Statement statement;

	public SQLiteJDBCDriverConnection() {
		connect();
	}

	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:banco.db");

			statement = connection.createStatement();

			// criando uma tabela
			statement.execute("DROP TABLE IF EXISTS Professor");
			statement.execute("DROP TABLE IF EXISTS Habilidades");
			statement.execute("DROP TABLE IF EXISTS Preferencias");

			statement.execute("CREATE TABLE Professor( id INTEGER PRIMARY KEY, nome  VARCHAR NOT NUll )");

			statement
					.execute("CREATE TABLE Habilidades" + "(id INTEGER PRIMARY KEY, disciplina VARCHAR (200) NOT NULL, "
							+ "  professor_id INTEGER NOT NULL, CONSTRAINT professor_fkey FOREIGN KEY (professor_id) "
							+ " REFERENCES Professor (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE)");

			statement.execute(
					"CREATE TABLE Preferencias" + "(id INTEGER PRIMARY KEY, disciplina VARCHAR (200) NOT NULL, "
							+ "  professor_id INTEGER NOT NULL, CONSTRAINT professor_fkey FOREIGN KEY (professor_id) "
							+ " REFERENCES Professor (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE CASCADE)");

			statement.execute("insert into professor (nome) values ('Walter')");
			statement.execute("insert into professor (nome) values ('Elena')");
			statement.execute("insert into professor (nome) values ('Evelyn')");
			statement.execute("insert into professor (nome) values ('Mia')");
			statement.execute("insert into professor (nome) values ('Robert')");

			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Banco de Dados I',1)");
			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Eletronica I',1)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Engenharia de Software II',1)");

			statement
					.execute("insert into Habilidades (disciplina,professor_id)  values ('Inteligencia Artificial',2)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Laboratorio de Redes de Computadores',2)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Programacao Paralela e Concorrente', 2)");
			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Sistemas Distribuidos', 2)");

			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Banco de Dados I',3)");
			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Eletronica I',3)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Engenharia de Software II',3)");

			statement
					.execute("insert into Habilidades (disciplina,professor_id)  values ('Inteligencia Artificial',4)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Laboratorio de Redes de Computadores',4)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Programacao Paralela e Concorrente', 4)");
			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Sistemas Distribuidos', 4)");

			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Banco de Dados I',5)");
			statement.execute("insert into Habilidades (disciplina,professor_id)  values ('Eletronica I',5)");
			statement.execute(
					"insert into Habilidades (disciplina,professor_id)  values ('Engenharia de Software II',5)");

			// lendo os registros
			PreparedStatement stmt = connection.prepareStatement("select * from Professor");
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");

				System.out.println(id + " - " + nome);
			}
			// lendo os registros
			stmt = connection.prepareStatement("select * from Habilidades");
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String disciplina = resultSet.getString("disciplina");
				Integer idProfessor = resultSet.getInt("professor_id");

				System.out.println(id + " - " + disciplina + " - " + idProfessor);
			}
			// lendo os registros
			stmt = connection.prepareStatement("select * from Preferencias");
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String disciplina = resultSet.getString("disciplina");
				Integer idProfessor = resultSet.getInt("professor_id");

				System.out.println(id + " - " + disciplina + " - " + idProfessor);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ResultSet query(String query) {
		try {
			return getStatement().executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void disconnect() {
		try {
			getConnection().close();
		} catch (SQLException ex) {
			System.err.println(ex);
			ex.printStackTrace();
		}
	}

	public PreparedStatement prepareStatement(String query) throws SQLException {
		return getConnection().prepareStatement(query);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
}