package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {

    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    // Método para conexão ao banco de dados.
    public void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/machinecontrol?useSSL=false&serverTimezone=UTC",
                "root",
                "12345aA."
            );
            System.out.println("SUCESSO DE CONEXÃO!");
        } catch (ClassNotFoundException cn) {
            System.out.println("Falha ao conectar com o banco: " + cn);
        } catch (SQLException sql) {
            System.out.println("Erro de SQL: " + sql);
        }
    }

    
    /*
    @param Método de desconectar do banco de dados. 
    */
    public void desconectar(){
        try {
            if(conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("DESCONECTADO!");
            }
        }catch(SQLException se) {
            System.out.println("Problema ao desconectar do banco: " + se);
        }
    }
    
}
