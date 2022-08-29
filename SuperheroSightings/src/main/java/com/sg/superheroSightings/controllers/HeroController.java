package com.sg.superheroSightings.controllers;

import com.sg.superheroSightings.dao.HeroDao;
import com.sg.superheroSightings.dao.SightingDao;
import com.sg.superheroSightings.dto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HeroController {

    @Autowired
    HeroDao heroDao;


    @GetMapping("heroes")
    public List<Hero> displayHeroes(Model model) {
        List<Hero> heroes = heroDao.getAllHeroes();
        //model.addAttribute("heroes", heroes);
        //return "heroes";
        return heroes;
    }


//    @PostMapping("deleteHero")
//    public String deleteHero(HttpServletRequest request) {
//        int id = Integer.parseInt(request.getParameter("heroId"));
//        heroDao.deleteHeroById(id);
//
//        return "redirect:/heroes";
//    }

}
