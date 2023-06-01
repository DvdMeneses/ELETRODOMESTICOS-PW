package com.eletros.Controller;

import com.eletros.Model.Eletros;
import com.eletros.Model.Usuario;
import com.eletros.Service.EletrosService;
import com.eletros.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    UsuarioService service;
    EletrosService serviceEletros;
    public AdminController(UsuarioService service, EletrosService serviceEletros) {
        this.service = service;
        this.serviceEletros = serviceEletros;
    }


    @GetMapping({"/adminPage", "/adminPage.html"})
    public String getIndex (Model model) {
        List<Eletros> EletrosList = serviceEletros.findAll();
        model.addAttribute("eletrosList", EletrosList);
        return "/adminPage.html";
    }



    @GetMapping("/listUsuario")
    public String listAll(Model model) {
        List<Usuario> usuarioList = service.listAll();
        model.addAttribute("usuarioList",usuarioList);
        return "listUsuario";
    }





}
