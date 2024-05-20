package org.fundaciobit.formacio.formaciospringmvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.formacio.calculadoralib.Calculadora;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView test(HttpServletResponse response) throws IOException {
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("nom", "Pep Gonella");
        return mav;
    }
}
