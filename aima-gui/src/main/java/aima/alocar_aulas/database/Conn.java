package aima.alocar_aulas.database;

import java.sql.*;
/**
 *
 * @author mayron
 */
public class Conn {
    private String local;
    private String user;
    private String senha;
    private Connection c;
    private Statement statement;
    private String str_conexao;
    private String driverjdbc;

    public Conn(String bd, String local, String porta,
                   String banco, String user, String senha) {
        if (bd.equals("PostgreSql")){
            setStr_conexao("jdbc:postgresql://"+ local +":" + porta +"/"+ banco);
            setLocal(local);
            setSenha(senha);
            setUser(user);
            setDriverjdbc("org.postgresql.Driver");
        }else {
            if (bd.equals("MySql")) {
                setStr_conexao("jdbc:mysql://"+ local +":" + porta +"/"+ banco);
                setLocal(local);
                setSenha(senha);
                setUser(user);
                setDriverjdbc("com.mysql.jdbc.Driver");
            }
        }
    }

    public void configUser(String user, String senha) {
        setUser(user);
        setSenha(senha);
    }

    public void configLocal(String banco) {
        setLocal(banco);
    }

    //Conexão com o Banco de Dados
    public void connect(){
        try {
            Class.forName(getDriverjdbc());
            setC(DriverManager.getConnection(getStr_conexao(), getUser(), getSenha()));
            setStatement(getC().createStatement());
        }catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try {
            getC().close();
        }catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
    }

    public ResultSet query(String query){
        try {
            return getStatement().executeQuery(query);
        }catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // GETs AND SETs
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Connection getC() {
        return c;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getStr_conexao() {
        return str_conexao;
    }

    public void setStr_conexao(String str_conexao) {
        this.str_conexao = str_conexao;
    }

    public String getDriverjdbc() {
        return driverjdbc;
    }

    public void setDriverjdbc(String driverjdbc) {
        this.driverjdbc = driverjdbc;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return getC().prepareStatement(query);
    }
}