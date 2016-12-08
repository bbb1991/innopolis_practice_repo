package me.bbb1991.dbService;

import me.bbb1991.dao.DAO;
import me.bbb1991.model.Course;
import me.bbb1991.model.Lecture;
import me.bbb1991.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static me.bbb1991.helpers.Constants.ERROR_MSG_GENERAL;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class DBService {

    private static final Logger logger = LoggerFactory.getLogger(DBService.class);

    private DAO<Student> studentDAO;

    private DAO<Course> courseDAO;

    private DAO<Lecture> lectureDAO;

    private SessionFactory sessionFactory;

    private DBService() {
        sessionFactory = createSessionFactory(getH2Configuration());
    }


    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    public Course getCourse(int id) {
        return courseDAO.findById(id);
    }

    public void saveStudent(String sn, String fn, String ln, int i, Course course) {
        studentDAO.save(new Student(sn, fn, ln, i, course));
    }

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    public List<Lecture> getAllLectures() {
        return lectureDAO.findAll();
    }

    public void saveLecture(String name, String location, String beginDate) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
        try {
            lectureDAO.save(new Lecture(name, format.parse(beginDate), location));
        } catch (ParseException e) {
            logger.error("Error while parsing date", e);
            throw new RuntimeException(e);
        }
    }

    public Lecture getLecture(String id) {
        return lectureDAO.findById(Integer.parseInt(id));
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Lecture.class);
        configuration.addAnnotatedClass(Student.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:~/student_lecture_h2db");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", String.valueOf(true));
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.connection.autocommit", String.valueOf(true));
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        return configuration;
    }

    @Autowired
    public void setStudentDAO(DAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Autowired
    public void setCourseDAO(DAO<Course> courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Autowired
    public void setLectureDAO(DAO<Lecture> lectureDAO) {
        this.lectureDAO = lectureDAO;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
