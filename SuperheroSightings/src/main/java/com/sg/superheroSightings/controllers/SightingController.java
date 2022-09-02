package com.sg.superheroSightings.controllers;

import com.sg.superheroSightings.dao.LocationDao;
import com.sg.superheroSightings.dao.OrganizationDao;
import com.sg.superheroSightings.dao.SightingDao;
import com.sg.superheroSightings.dao.SuperDao;
import com.sg.superheroSightings.dto.Location;
import com.sg.superheroSightings.dto.Sighting;
import com.sg.superheroSightings.dto.Super;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SightingController {

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private SuperDao superDao;

    @Autowired
    private OrganizationDao orgDao;

    @Autowired
    private SightingDao sightingDao;

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Location>  locations = locationDao.getAllLocations();
        List<Super> supers = superDao.getAllSupers();
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(Sighting sighting, HttpServletRequest request) {

        String superId  = request.getParameter("superId");
        String locationId = request.getParameter("locationId");


        sighting.setSuperObj(superDao.getSuperById(Integer.parseInt(superId)));
        sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));

       sightingDao.addSighting(sighting);

        return "redirect:/sightings";
    }


    @GetMapping("sightingDetail")
    public String sightingDetail(Integer id, Model model) {
        Sighting sighting = sightingDao.getSightingById(id);
        model.addAttribute("sighting", sighting);
        return "sightingDetail";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(Integer id) {
        sightingDao.deleteSightingById(id);
        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(Integer id, Model model) {

        Sighting sighting = sightingDao.getSightingById(id);
        List<Super> supers = superDao.getAllSupers();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sighting", sighting);
        model.addAttribute("supers", supers);
        model.addAttribute("locations", locations);
        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(Sighting sighting, HttpServletRequest request) {

        String sightingDateString = request.getParameter("sightingDate");
        String locationId = request.getParameter("locationId");
        String superId = request.getParameter("superId");

        LocalDate sightingDate = LocalDate.parse(sightingDateString);
        sighting.setSightingDate(sightingDate);
        sighting.setLocation(locationDao.getLocationById(Integer.parseInt(locationId)));
        sighting.setSuperObj(superDao.getSuperById(Integer.parseInt(superId)));

        sightingDao.updateSighting(sighting);

        return "redirect:/sightings";
    }
}
