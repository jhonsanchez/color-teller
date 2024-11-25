package com.example.colorteller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ColorTellerController {
    private final ColorProperties colorProperties;

    public ColorTellerController(ColorProperties colorProperties) {
        this.colorProperties = colorProperties;
    }

    @GetMapping("/color")
    public String greeting(Model model) {
        model.addAttribute("colorCode", colorProperties.code());
        return "color";
    }
}
