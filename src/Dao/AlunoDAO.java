package dao;
import factory.ConnectionFactory;
import java.sql.*;
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
}