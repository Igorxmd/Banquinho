package dao;
import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Aluno;

public class AlunoDAO {
    private Connection connection;
    
    public AlunoDAO() throws SQLException{
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Aluno aluno){
        String sql = "INSERT INTO usuario(nome, sexo, nota1, nota2, media, situacao, apagado)VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getSexo());
            stmt.setDouble(3, aluno.getNota1());
            stmt.setDouble(4, aluno.getNota2());
            stmt.setDouble(5, aluno.getMedia());
            stmt.setInt(6, aluno.getSituacao());
            stmt.setInt(7, aluno.getApagado());
            stmt.execute();
            stmt.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<Aluno> lerTudo(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> listAlunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos WHERE apagado=0";
        try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("cod"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getInt("sexo"));
                aluno.setNota1(rs.getDouble("nota1"));
                aluno.setNota2(rs.getDouble("nota2"));
                aluno.setMedia(rs.getDouble("media"));
                aluno.setSituacao(rs.getInt("situacao"));
                aluno.setApagado(rs.getInt("apagado"));
                listAlunos.add(aluno);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro na leitura do banco: "+ex,"ERRO",JOptionPane.ERROR_MESSAGE);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listAlunos;
    }
    public void exclui(int cod){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "UPDATE alunos set apagado = 1 WHERE cod = ?";
        try{
            stmt = con.preparedStatement(sql);
            stmt = set.Int(1, cod);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao exluir"+e);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}