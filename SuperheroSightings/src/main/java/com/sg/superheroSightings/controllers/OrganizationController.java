package com.sg.superheroSightings.controllers;

import com.sg.superheroSightings.dao.LocationDao;
import com.sg.superheroSightings.dao.OrganizationDao;
import com.sg.superheroSightings.dao.SightingDao;
import com.sg.superheroSightings.dao.SuperDao;
import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Organization;
import com.sg.superheroSightings.dto.Sighting;
import com.sg.superheroSightings.dto.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrganizationController {

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private SuperDao superDao;

    @Autowired
    private OrganizationDao orgDao;

    @Autowired
    private SightingDao sightingDao;

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> orgs = orgDao.getAllOrganizations();
        List<Location> locations = locationDao.getAllLocations();
        List<Super> supers = superDao.getAllSupers();

        model.addAttribute("orgs", orgs);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization org, HttpServletRequest request) {

        String locationId = request.getParameter("locationId");
        String[] superIds = request.getParameterValues("superId");

        System.out.println("Hee: " + org.getHeroOrVillainOrg());

        org.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));

        List<Super> supers= new ArrayList<>();
        for(String superId : superIds) {
            supers.add(superDao.getSuperById(Integer.parseInt(superId)));
        }
        org.setMembers(supers);
        orgDao.addOrganization(org);

        return "redirect:/organizations";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(Integer id, Model model) {
        Organization org = orgDao.getOrganizationById(id);
        model.addAttribute("org", org);
        return "organizationDetail";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(Integer id) {
        orgDao.deleteOrganizationById(id);
        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(Integer id, Model model) {
        Organization org = orgDao.getOrganizationById(id);

        List<Super> supers = superDao.getAllSupers();
        List<Location> locations = locationDao.getAllLocations();

        model.addAttribute("org", org);
        model.addAttribute("supers", supers);
        model.addAttribute("locations", locations);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(Organization org, HttpServletRequest request) {
        String locationId = request.getParameter("locationId");
        String[] superIds = request.getParameterValues("superId");

        org.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));

        List<Super> superList = new ArrayList<>();
        for(String superId : superIds) {
            superList.add(superDao.getSuperById(Integer.parseInt(superId)));
        }
        org.setMembers(superList);
        orgDao.updateOrganization(org);

        return "redirect:/organizations";
    }

    @GetMapping("superIdToFindOrgs")
    public String getOrgsForAsuperChar(Model model) {
        return "superIdToFindOrgs";
    }

    @PostMapping("orgsForAsuper")
    public String getOrgsForAsuper(HttpServletRequest request, Model model) {

//        if(result.hasErrors()) {
//            return "getSuperId";
//        }

        int superId = Integer.parseInt(request.getParameter("superId"));
        List<Organization> orgList = new ArrayList<>();
        Super superObj = superDao.getSuperById(superId);

        orgList = orgDao.getOrgsForSuper(superObj);

        model.addAttribute("orgs", orgList);

        //return "redirect:/sightings";
        return "orgsForAsuper";
    }



}
