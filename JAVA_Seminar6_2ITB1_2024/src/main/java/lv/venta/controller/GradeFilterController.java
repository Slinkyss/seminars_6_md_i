package lv.venta.controller;

import lv.venta.service.IGradeFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("grade/filter")
public class GradeFilterController {

    @Autowired
    private IGradeFilterService gradeFilterService;


    @GetMapping("/grade")
    public String grade(@RequestParam("id")long id, Model model) {
        try{
            model.addAttribute("myobjs",gradeFilterService.selectGradesByStudentId(id));
            model.addAttribute("mytitle", "Filtered by Student");
            return "grade-filter";
        }catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }


    @GetMapping("/failed")
    public String failed(Model model) {

        try {
            model.addAttribute("myobjs",gradeFilterService.selectFailedGrades());
            model.addAttribute("mytitle", "Failed grades");
            return "grade-filter";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/avg")
    public String avg(@RequestParam("id")long id,Model model) {

        try {
            model.addAttribute("myobjs",gradeFilterService.calculateAVGGradeInCourseId(id));
            model.addAttribute("mytitle", "Avg Grade");
            return "avg-grade";
        }
        catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            return "error-page";
        }

    }


}
