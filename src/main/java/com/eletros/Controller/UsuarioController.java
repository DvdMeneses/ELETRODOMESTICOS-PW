package com.eletros.Controller;

import com.eletros.Model.Usuario;
import com.eletros.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class UsuarioController {
    UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/doCadastrarUsuario")
    public String getCadastrarUserPage(Model model) {
        Usuario u = new Usuario();

        model.addAttribute("usuario", u);
        return "cadastrarUsuarioPage";
    }


    @PostMapping("/doSalvarUsuario")
    public String doSalvarUsuario(@ModelAttribute Usuario u) {
        service.create(u);

        return "redirect:/index";
    }

    @GetMapping("/listUsuario")
    public String listAll(Model model) {
        List<Usuario> usuarioList = service.listAll();
        model.addAttribute("usuarioList",usuarioList);
        return "listUsuario";
    }

}












