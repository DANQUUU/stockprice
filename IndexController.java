package com.dan.stockapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By Dan on 2017/10/20
 */

@Controller
public class IndexController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false,defaultValue="world") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";

    }


}
