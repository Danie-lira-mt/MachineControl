package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RelatorioDAO {
    
       Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    /*
    @param - Método para se conectar ao banco de dados.
    */
    
    public boolean Conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/machinecontrol?useSSL=false&serverTimezone=UTC",
                "root",
                "12345aA."
            );
            return true;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error ao se conectar ao banco: "+e.getMessage());
            return false;
        }
    }
    
    /*
    @param - Método para cadastrar novas maquinas no banco de 
    dados.
    */
    public int Salvar(Relatorio relatorio){
        int status;
        
        try{
            st = conn.prepareStatement("INSERT INTO relatorios VALUES(?,?,?,?,?,?,?,?)");
            
            st.setInt(1, relatorio.getId());
            st.setString(2, relatorio.getData());
            st.setString(3, relatorio.getNome());
            st.setInt(4, relatorio.getMatricula());
            st.setString(5, relatorio.getMaquina());
            st.setInt(6, relatorio.getPatrimonio());
            st.setString(7, relatorio.getMarca());
            st.setString(8, relatorio.getRelatorio());
         
            
            status = st.executeUpdate();
            return status;
            
            
        }catch(SQLException e){
            System.out.println("Erro ao conectar2: "+e.getMessage());
            return e.getErrorCode();
        }
    }
    
    /*
    @param - Método para consultar as especificações da maquina 
    cadastrada no banco de dados, apartir do patrimonio
    */
    public Maquinas consultar(String patrimonio){
        try{
            Maquinas maquina = new Maquinas();
            st = conn.prepareStatement("SELECT*FROM maquinas WHERE patrimonio =?");
            
            st.setString(1, patrimonio);
            rs = st.executeQuery();
                if(rs.next()){
                    maquina.setId(rs.getInt("id"));
                    maquina.setPatrimonio(rs.getInt("patrimonio"));
                    maquina.setTipo(rs.getString("tipo"));
                    maquina.setMarca(rs.getString("marca"));
                    maquina.setFuncionario(rs.getString("funcionario"));
                    maquina.setMatricula(rs.getInt("matricula"));
                    maquina.setData(rs.getString("data"));
                    maquina.setRelatorio(rs.getString("relatorio"));
                    return maquina;
                    
                }else{
                    return null;
                }
        }catch(SQLException e){
            System.out.println("Error ao conectar: "+e.getMessage());
            return null;
        }
    }
    
    /*
    @param - Método para exlcluir do banco de dados a maquina
    buscado apartir do patrimonio da maquina.
    */
    public boolean excluir(String patrimonio){
        try{
            st = conn.prepareStatement("DELETE FROM maquinas WHERE patrimio =?");
            
            st.setString(1, patrimonio);
            st.executeUpdate();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    /*
    @param - Método para atualizar dados do Relatorio buscado pelo
    nome direto do banco de dados.
    */
    
   
    /*
    @param - Método para deconsctar do banco de dados.
    */
    public void desconectar(){
        try{
            conn.close();
        }catch(SQLException e){
            
        }
    }
    /*
    @param - Método para listar dos os filmes cadastrados no banco de dados.
    */
  public static List<Relatorio> listarTodosRelatorio() {
        List<Relatorio> meds = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            //Armazenamos o comando MySQL em uma string
            String sql = "SELECT id, data, funcionario, matricula, maquina, patrimonio, marca, relatorio FROM relatorios";
            
            
            //Preparamos o comando para ser executado no banco
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(rs.getInt("id"));
                relatorio.setData(rs.getString("data"));
                relatorio.setNome(rs.getString("funcionario"));
                relatorio.setMatricula(rs.getInt("matricula"));
                relatorio.setMaquina(rs.getString("maquina"));
                relatorio.setPatrimonio(rs.getInt("patrimonio"));
                relatorio.setMarca(rs.getString("marca"));
                relatorio.setRelatorio(rs.getString("relatorio"));
               

                meds.add(relatorio);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar Relatórios: " + se.getMessage());
        }

        return meds;
    }
  
  
 public void excluirConsulta(int id) throws SQLException {
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conexaoJDBC.conectar();
        conn = conexaoJDBC.getConexao();

        String sql = "DELETE FROM relatorios WHERE id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();

        System.out.println("Consulta excluída com sucesso.");
    } catch (SQLException e) {
        System.out.println("Erro ao excluir consulta: " + e.getMessage());
        throw e;
    } finally {
        if (stmt != null) stmt.close();
        conexaoJDBC.desconectar();
    }
}
 
 public List<Maquinas> buscarPorPatrimonio(int patrimonio) throws SQLException {
    List<Maquinas> lista = new ArrayList<>();
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        String sql = "SELECT * FROM relatorios WHERE patrimonio = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, patrimonio);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Maquinas m = new Maquinas();
            m.setId(rs.getInt("id"));
            m.setPatrimonio(rs.getInt("patrimonio"));
            m.setTipo(rs.getString("tipo"));
            m.setMarca(rs.getString("marca"));
            m.setFuncionario(rs.getString("funcionario"));
            m.setMatricula(rs.getInt("matricula"));
            m.setData(rs.getString("data"));
            lista.add(m);
        }

        rs.close();
        stmt.close();
        conexaoJDBC.desconectar();
    } catch (SQLException e) {
        throw e;
    }
    return lista;
}


}