package com.sg.superheroSightings.controllers;

import com.sg.superheroSightings.dao.LocationDao;
import com.sg.superheroSightings.dao.OrganizationDao;
import com.sg.superheroSightings.dao.SightingDao;
import com.sg.superheroSightings.dao.SuperDao;
import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("supers")
    public String displaySuperCharacters(Model model) {
        List<Super> supers = superDao.getAllSupers();
        model.addAttribute("supers", supers);
        return "supers";
    }


    @PostMapping("addSuper")
    public String addSuper(HttpServletRequest request) {
        String superName = request.getParameter("superName");
        String superDescription = request.getParameter("superDescription");
        String superPower = request.getParameter("superPower");
        String superStatus = request.getParameter("superStatus");

        Super sp = new Super();
        sp.setSuperName(superName);
        sp.setSuperDescription(superDescription);
        sp.setSuperPower(superPower);
        sp.setSuperStatus(superStatus);

        superDao.addSuper(sp);

        return "redirect:/supers";
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
    public String performEditSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super superObj = superDao.getSuperById(id);

        superObj.setSuperName(request.getParameter("superName"));
        superObj.setSuperDescription(request.getParameter("superDescription"));
        superObj.setSuperPower(request.getParameter("superPower"));
        superObj.setSuperStatus(request.getParameter("superStatus"));

        superDao.updateSuper(superObj);

        return "redirect:/supers";
    }




}
