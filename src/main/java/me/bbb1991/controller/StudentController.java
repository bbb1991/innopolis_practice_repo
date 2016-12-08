package me.bbb1991.controller;

import me.bbb1991.dbService.DBService;
import me.bbb1991.model.Course;
import me.bbb1991.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private DBService dbService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getAllStudents(Model model) {
        logger.info("Getting all users...");
        List<Student>  list = dbService.getAllStudents();
        model.addAttribute("title", "Students list");
        model.addAttribute("students", list);

        logger.info("Get students. Size is: {}", list.size());
        return "students";
    }

    @RequestMapping(value = "/add_student", method = RequestMethod.GET)
    public String addStudent(Model model) {
        List<Course> courses = dbService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("title", "Add new student");
        return "add_student";
    }

    @RequestMapping(value = "/add_student", method = RequestMethod.POST)
    public String add_student(HttpServletRequest request) {
        String sn = request.getParameter("sn");
        String fn = request.getParameter("fn");
        String ln = request.getParameter("ln");
        String sexId = request.getParameter("sex_id");
        String course_id = request.getParameter("course_id");

        Course course = dbService.getCourse(Integer.parseInt(course_id));

        dbService.saveStudent(sn, fn, ln, Integer.parseInt(sexId), course);

        return "redirect:/students";
    }
}
