package com.eletros.Controller;

import org.springframework.security.core.authority.AuthorityUtils;
import com.eletros.Model.Usuario;
import com.eletros.Service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {

    UsuarioService service;
    public LoginController(UsuarioService service) {
        this.service = service;
    }
    
    @GetMapping("/login" )
    public String getLoginPage() {

        return "/loginPage";
    }

    @PostMapping("/validar")
    public String validarLogin(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               RedirectAttributes redirectAttributes, BCryptPasswordEncoder encoder) {

        var UserName = username;
        var Password = password;
        Usuario usuario = service.getUsuarioByLogin(username);
        
        System.out.println("USER RECEBIDO COMO PARAMETRO PRA TESTE "+UserName);
        System.out.println("senha RECEBIDO COMO PARAMETRO PRA TESTE "+Password);

        // Verificar se o usuário está cadastrado e a senha está correta
         if (usuario.getUsername().equals(UserName)) {
             System.out.println("senha  COMO user PRA TESTE "+usuario.getPassword());
             System.out.println("login  COMO user PRA TESTE "+usuario.getLogin());


             if (encoder.matches(Password, usuario.getPassword())) {

                 if (usuario.getIsadmin()) {


                     System.out.println("CERTIN");
                     return "redirect:/indexAdmin";

                 }
             }

         }






        // Caso as credenciais estejam incorretas ou o usuário não esteja cadastrado
        redirectAttributes.addFlashAttribute("mensagem", "Credenciais inválidas ou Acesso negado");
        return "redirect:/login";
    }



}