package com.eletros.Controller;


import com.eletros.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    UsuarioService service;
    public LoginController(UsuarioService service) {
        this.service = service;
    }
    
    @GetMapping("/login")
    public String getLoginPage() {
        return "/loginPage";
    }
/*
    @PostMapping("/validar")
    public String validarLogin(@RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "action", required = false) String action,
                               RedirectAttributes redirectAttributes) {
        // Lógica de validação do usuário

        // Verificar se o usuário está cadastrado e a senha está correta
        if (usuarioCadastrado(username, password)) {
            // Verificar se o usuário é um admin e a ação é editar
            if (isAdmin(username) && "editar".equals(action)) {
                // Redirecionar para a página de edição passando o ID do elemento
                Long elementoId = getIdDoElementoParaEditar(); // Implemente o método para obter o ID do elemento a ser editado
                return "redirect:/editarPage/" + elementoId;
            }

            // Verificar se o usuário é um admin e a ação é deletar
            if (isAdmin(username) && "deletar".equals(action)) {
                // Redirecionar para a página de exclusão passando o ID do elemento
                Long elementoId = getIdDoElementoParaDeletar(); // Implemente o método para obter o ID do elemento a ser deletado
                return "redirect:/deletar/" + elementoId;
            }

            // Redirecionar para a página após o login bem-sucedido
            return "redirect:/paginaApósLogin";
        }

        // Caso as credenciais estejam incorretas ou o usuário não esteja cadastrado
        redirectAttributes.addFlashAttribute("error", "Credenciais inválidas");
        return "redirect:/loginPage";
    }
    */


}