package org.fundaciobit.formacio.formaciotiles.controller;

import org.fundaciobit.formacio.calculadoralib.Calculadora;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculadoraController {

    @RequestMapping("/calculadora")
    public ModelAndView ferOperacio(@RequestParam(value = "n1", required = false) Integer n1,
            @RequestParam(value = "n2", required = false) Integer n2,
            @RequestParam(value = "operacio", required = false) String operacio) {

        ModelAndView model = new ModelAndView("calculadora_tile");
        if (operacio != null && n1 != null && n2 != null) {
            Calculadora calculadora = new Calculadora();
            if (operacio.equals("suma")) {
                model.addObject("result", n1 + " + " + n2 + " = " + calculadora.suma(n1, n2));
            } else {
                model.addObject("result", n1 + " - " + n2 + " = " + calculadora.resta(n1, n2));
            }
        }
        return model;
    }
}
