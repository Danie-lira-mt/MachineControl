
package model;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
        
public class MaquinasDAO {
    
       Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    /*
    @param - Método para se conectar ao banco de dados.
    */
    
    public boolean Conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/machinecontrol","root","12345aA.");
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
    public int Salvar(Maquinas maquina){
        int status;
        
        try{
            st = conn.prepareStatement("INSERT INTO maquinas VALUES(?,?,?,?,?,?,?)");
            
            st.setInt(1, maquina.getId());
            st.setInt(2, maquina.getPatrimonio());
            st.setString(3, maquina.getTipo());
            st.setString(4, maquina.getMarca());
            st.setString(5, maquina.getFuncionario());
            st.setInt(6, maquina.getMatricula());
            st.setString(7, maquina.getData());
         
            
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
    @param - Método para atualizar dados do filme buscado pelo
    nome direto do banco de dados.
    
    public int atualizar(Filme filme){
        int status;
        try{
            st = conn.prepareStatement("UPDATE filmes SET nome =?, datalancamento =?, categoria =? WHERE id =?");
            
            st.setString(1, filme.getNome());
            st.setString(2, filme.getLancamento());
            st.setString(3, filme.getGenero());
            st.setInt(4, filme.getId());
            
            status = st.executeUpdate();
            return status;
        }catch(SQLException e){
            System.out.println(e.getErrorCode());
            return e.getErrorCode();
        }
    }
    */
    
    //=============================================================================
    
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
  public static List<Maquinas> listarTodos() {
        List<Maquinas> meds = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            //Armazenamos o comando MySQL em uma string
            String sql = "SELECT id, patrimonio, tipo, marca, funcionario, matricula, data FROM maquinas";
            
            
            //Preparamos o comando para ser executado no banco
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Maquinas med = new Maquinas();
                med.setId(rs.getInt("id"));
                med.setPatrimonio(rs.getInt("patrimonio"));
                med.setTipo(rs.getString("tipo"));
                med.setMarca(rs.getString("marca"));
                med.setFuncionario(rs.getString("funcionario"));
                med.setMatricula(rs.getInt("matricula"));
                med.setData(rs.getString("data"));

                meds.add(med);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar Maquinas: " + se.getMessage());
        }

        return meds;
    }
  
  public void excluirMaquina(int id) throws SQLException {
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
    Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conexaoJDBC.conectar();
        conn = conexaoJDBC.getConexao();

        String sql = "DELETE FROM maquinas WHERE patrimonio = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();

        System.out.println("Maquina excluída com sucesso.");
    } catch (SQLException e) {
        System.out.println("Erro ao excluir Maquina: " + e.getMessage());
        throw e;
    } finally {
        if (stmt != null) stmt.close();
        conexaoJDBC.desconectar();
    }
}

  public void atualizarMaquina(Maquinas maquina) throws SQLException {
    ConexaoJDBC conexaoJDBC = new ConexaoJDBC();
    PreparedStatement stmt = null;

    try {
        conexaoJDBC.conectar();
        Connection conn = conexaoJDBC.getConexao();

        String sql = "UPDATE maquinas SET patrimonio = ?, tipo = ?, marca = ?, funcionario = ?, matricula = ?, data = ?, relatorio = ? WHERE id_maquina = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, maquina.getPatrimonio());
        stmt.setString(2, maquina.getTipo());
        stmt.setString(3, maquina.getMarca());
        stmt.setString(4, maquina.getFuncionario());
        stmt.setInt(5, maquina.getMatricula());
        stmt.setString(6, maquina.getData());
        stmt.setString(7, maquina.getRelatorio());
        stmt.setInt(8, maquina.getId());

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Máquina atualizada com sucesso.");
        } else {
            System.out.println("Nenhuma máquina foi atualizada. Verifique o ID.");
        }
    } finally {
        if (stmt != null) stmt.close();
        conexaoJDBC.desconectar();
    }
}


}

    