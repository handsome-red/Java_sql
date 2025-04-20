import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsRepository {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/education", "postgres", "123");
    }
    
    public void addStudent(String firstName, String lastName, String middleName) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, middle_name) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, middleName);
            stmt.executeUpdate();
        }
    }
    
    public void updateStudent(int studentId, String firstName, String lastName, String middleName) throws SQLException {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, middle_name = ? WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, middleName);
            stmt.setInt(4, studentId);
            stmt.executeUpdate();
        }
    }
    
    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }
    
    // Другие методы по необходимости
}