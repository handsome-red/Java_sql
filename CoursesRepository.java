import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesRepository {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/education", "user", "password");
    }
    
    public void addCourse(String title, String description, int teacherId) throws SQLException {
        String sql = "INSERT INTO courses (title, description, teacher_id) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, teacherId);
            stmt.executeUpdate();
        }
    }
    
    public void updateCourse(int courseId, String title, String description, int teacherId) throws SQLException {
        String sql = "UPDATE courses SET title = ?, description = ?, teacher_id = ? WHERE course_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, teacherId);
            stmt.setInt(4, courseId);
            stmt.executeUpdate();
        }
    }
    
    public void deleteCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.executeUpdate();
        }
    }
    
    public void enrollStudent(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        }
    }
    
    public void unenrollStudent(int studentId, int courseId) throws SQLException {
        String sql = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();
        }
    }
    
    public List<CourseInfo> getCoursesInfo() throws SQLException {
        List<CourseInfo> coursesInfo = new ArrayList<>();
        String sql = "SELECT c.title, t.first_name, t.last_name, t.middle_name, COUNT(sc.student_id) as student_count " +
                     "FROM courses c " +
                     "JOIN teachers t ON c.teacher_id = t.teacher_id " +
                     "LEFT JOIN student_courses sc ON c.course_id = sc.course_id " +
                     "GROUP BY c.course_id";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                CourseInfo info = new CourseInfo(
                    rs.getString("title"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("middle_name"),
                    rs.getInt("student_count")
                );
                coursesInfo.add(info);
            }
        }
        return coursesInfo;
    }
}