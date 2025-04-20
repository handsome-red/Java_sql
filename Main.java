import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Инициализация репозиториев
            StudentsRepository studentsRepo = new StudentsRepository();
            CoursesRepository coursesRepo = new CoursesRepository();
            TeachersRepository teachersRepo = new TeachersRepository();
            
            // Примеры CRUD операций
            
            // Добавление преподавателя
            teachersRepo.addTeacher("Иван", "Петров", "Сергеевич");
            
            // Добавление курса
            coursesRepo.addCourse("Математика", "Основы математики", 1);
            
            // Добавление студента
            studentsRepo.addStudent("Алексей", "Смирнов", "Дмитриевич");
            
            // Запись студента на курс
            coursesRepo.enrollStudent(1, 1);
            
            // Получение информации о курсах
            List<CourseInfo> coursesInfo = coursesRepo.getCoursesInfo();
            for (CourseInfo info : coursesInfo) {
                System.out.printf("Курс: %s, Преподаватель: %s %s %s, Студентов: %d%n",
                        info.getCourseTitle(),
                        info.getTeacherLastName(),
                        info.getTeacherFirstName(),
                        info.getTeacherMiddleName(),
                        info.getStudentCount());
            }
            
            // Обновление данных
            studentsRepo.updateStudent(1, "Алексей", "Смирнов", "Викторович");
            coursesRepo.updateCourse(1, "Высшая математика", "Продвинутый курс", 1);
            
            // Удаление данных
            coursesRepo.unenrollStudent(1, 1);
            studentsRepo.deleteStudent(1);
            coursesRepo.deleteCourse(1);
            teachersRepo.deleteTeacher(1);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}