package com.sg.superheroSightings.controllers;

import com.sg.superheroSightings.dao.LocationDao;
import com.sg.superheroSightings.dao.OrganizationDao;
import com.sg.superheroSightings.dao.SightingDao;
import com.sg.superheroSightings.dao.SuperDao;
import com.sg.superheroSightings.dto.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class SuperController {

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private SuperDao superDao;

    @Autowired
    private OrganizationDao orgDao;

    @Autowired
    private SightingDao sightingDao;

    Set<ConstraintViolation<Super>> violations = new HashSet<>();

    @GetMapping("supers")
    public String displaySuperCharacters(Model model) {
        List<Super> supers = superDao.getAllSupers();
        model.addAttribute("supers", supers);
        model.addAttribute("errors", violations);
        return "supers";
    }


    @PostMapping("addSuper")
    public String addSuper(HttpServletRequest request) {
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");
        String superPower = request.getParameter("superPower");
        String superStatus = request.getParameter("superStatus");

        Super superObj = new Super();
        superObj.setSuperName(superName);
        superObj.setSuperDescription(superDescription);
        superObj.setSuperPower(superPower);
        superObj.setSuperStatus(superStatus);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(superObj);

        if(violations.isEmpty()) {
            superDao.addSuper(superObj);
        }

        return "redirect:/supers";
    }

    @GetMapping("superDetail")
    public String superDetail(Integer id, Model model) {
        Super superObj = superDao.getSuperById(id);
        model.addAttribute("super", superObj);
        return "superDetail";
    }


    @GetMapping("deleteSuper")
    public String deleteSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superDao.deleteSuperById(id);

        return "redirect:/supers";
    }

    @GetMapping("editSuper")
    public String editSuper(HttpServletRequest request, Model model) {


        int id = Integer.parseInt(request.getParameter("id"));
        Super superObj = superDao.getSuperById(id);

        model.addAttribute("super", superObj);
        return "editSuper";
    }


    @PostMapping("editSuper")
    public String performEditSuper(@Valid Super superObj, HttpServletRequest request, BindingResult result) {

        if(result.hasErrors()) {
            return "editSuper";
        }

        int id = Integer.parseInt(request.getParameter("id"));
        superObj = superDao.getSuperById(id);

        superObj.setSuperName(request.getParameter("superName"));
        superObj.setSuperDescription(request.getParameter("superDescription"));
        superObj.setSuperPower(request.getParameter("superPower"));
        superObj.setSuperStatus(request.getParameter("superStatus"));


        superDao.updateSuper(superObj);


        return "redirect:/supers";
    }

}
