package controller;

import dbService.DBService;
import model.Course;
import model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bbb1991 on 11/28/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */

@Controller
public class StudentController {

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getAllStudents(Model model) {
        List<Student>  list = DBService.getInstance().getAllStudents();
        model.addAttribute("title", "Students list");
        model.addAttribute("students", list);
        return "students";
    }

    @RequestMapping(value = "/add_student", method = RequestMethod.GET)
    public String addStudent(Model model) {
        List<Course> courses = DBService.getInstance().getAllCourses();
        model.addAttribute("courses", courses);
        return "add_student";
    }

    @RequestMapping(value = "/add_student", method = RequestMethod.POST)
    public String add_student(HttpServletRequest request) {
        String sn = request.getParameter("sn");
        String fn = request.getParameter("fn");
        String ln = request.getParameter("ln");
        String sexId = request.getParameter("sex_id");
        String course_id = request.getParameter("course_id");

        Course course = DBService.getInstance().getCourse(Integer.parseInt(course_id));

        DBService.getInstance().saveStudent(sn, fn, ln, Integer.parseInt(sexId), course);

        return "redirect:/students";
    }



}
