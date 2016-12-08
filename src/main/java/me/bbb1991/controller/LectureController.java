package me.bbb1991.controller;

import me.bbb1991.dbService.DBService;
import me.bbb1991.model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bbb1991 on 11/29/16.
 *
 * @author Bagdat Bimaganbetov
 * @author bagdat.bimaganbetov@gmail.com
 */

@Controller
public class LectureController {

    @Autowired
    private DBService dbService;


    @RequestMapping(value = "/lectures", method = RequestMethod.GET)
    public String getAllLectures(Model model) {

        List<Lecture> lectures = dbService.getAllLectures();

        model.addAttribute("lectures", lectures);
        model.addAttribute("title", "All lectures");

        return "lectures";
    }

    @RequestMapping(value = "/add_lecture", method = RequestMethod.GET)
    public String addLecture(Model model) {

        model.addAttribute("title", "Add new lecture");

        return "add_lecture";
    }

    @RequestMapping(value = "/add_lecture", method = RequestMethod.POST)
    public String addLecture(HttpServletRequest request) {
        String title = request.getParameter("title");
        String location = request.getParameter("location");
        String beginDate = request.getParameter("begin_date");

       dbService.saveLecture(title, location, beginDate);

        return "redirect:/lectures";
    }

    @RequestMapping(value = "/check_visit", method = RequestMethod.GET)
    public String checkVisit(Model model, @RequestParam(name = "id") String id) {
        Lecture lecture = dbService.getLecture(id);

        model.addAttribute("lecture", lecture);
        model.addAttribute("students", dbService.getAllStudents());
        model.addAttribute("title", lecture.getName());

        return "check_visit";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRoot(Model model) {
        model.addAttribute("title", "index");
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
