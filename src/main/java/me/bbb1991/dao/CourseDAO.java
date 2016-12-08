package me.bbb1991.dao;

import me.bbb1991.dbService.DBService;
import me.bbb1991.model.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */
public class CourseDAO implements DAO<Course> {

    @Autowired
    private DBService dbService;

    private static final Logger logger = LoggerFactory.getLogger(CourseDAO.class);


    @Override
    public Course findById(Integer id) {
        Session session = dbService.getSessionFactory().openSession();
        Course course =  session.find(Course.class, id);
        session.close();
        return course;

    }

    @Override
    public List<Course> findAll() {
        Session session = dbService.getSessionFactory().openSession();

        List<Course> courses =  (List<Course>) session.createQuery("from Course").getResultList();

        session.close();

        return courses;
    }

    @Override
    public void save(Course model) {

        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(model);
        transaction.commit();
        session.close();

    }

    @Override
    public void update(Course model) {
        Session session  = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(model);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Integer id) {



        Session session = dbService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Course course = findById(id);
        session.remove(course);

        transaction.commit();
        session.close();
    }

}
