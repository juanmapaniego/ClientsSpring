package com.jmpaniego.controllers;

import com.jmpaniego.services.JokeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping("/joke")
    public String showJoke(Model model){
        model.addAttribute("joke",jokeService.getJoke());
        return "joke";
    }

    @GetMapping("/jokeRefresh")
    public ModelAndView showJoke(){
        ModelAndView mv= new ModelAndView("joke::joke");
        mv.addObject("joke",jokeService.getJoke());
        return mv;
    }
}
