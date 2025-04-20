public class CourseInfo {
    private String courseTitle;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherMiddleName;
    private int studentCount;
    
    // Конструктор, геттеры и сеттеры
    public CourseInfo(String courseTitle, String teacherFirstName, String teacherLastName, 
                     String teacherMiddleName, int studentCount) {
        this.courseTitle = courseTitle;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.teacherMiddleName = teacherMiddleName;
        this.studentCount = studentCount;
    }
    
    // Геттеры
    public String getCourseTitle() { return courseTitle; }
    public String getTeacherFirstName() { return teacherFirstName; }
    public String getTeacherLastName() { return teacherLastName; }
    public String getTeacherMiddleName() { return teacherMiddleName; }
    public int getStudentCount() { return studentCount; }
}