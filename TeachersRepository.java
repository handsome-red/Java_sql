import java.sql.*;

public class TeachersRepository {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/education", "user", "password");
    }
    
    public void addTeacher(String firstName, String lastName, String middleName) throws SQLException {
        String sql = "INSERT INTO teachers (first_name, last_name, middle_name) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, middleName);
            stmt.executeUpdate();
        }
    }
    
    public void updateTeacher(int teacherId, String firstName, String lastName, String middleName) throws SQLException {
        String sql = "UPDATE teachers SET first_name = ?, last_name = ?, middle_name = ? WHERE teacher_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, middleName);
            stmt.setInt(4, teacherId);
            stmt.executeUpdate();
        }
    }
    
    public void deleteTeacher(int teacherId) throws SQLException {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            stmt.executeUpdate();
        }
    }
}