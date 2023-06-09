package com.eletros.Controller;

import com.eletros.Model.Eletros;
import com.eletros.Service.EletrosService;
import com.eletros.Service.FileStorageService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.random.RandomGenerator;

@Controller
public class EletrosController {

    FileStorageService fileStorageService;
    EletrosService service;

    public EletrosController ( FileStorageService fileStorageService, EletrosService service){
        this.fileStorageService = fileStorageService;
        this.service = service;

    }
    @GetMapping("/doCadastrar")
        public String getCadastrarPage(Model model){
        Eletros E = new Eletros();
        model.addAttribute("eletros", E);

        return "cadastrarPage";
    }
    @GetMapping({"/", "/index", "/index.html"})
        public String getIndex (Model model, HttpServletRequest request) {
        List<Eletros> EletrosList = service.findAll();
        HttpSession sessao = request.getSession();
        var contador =sessao.getAttribute("contador");
        if(contador == null){
            contador = 0;
        }
        model.addAttribute("contador",contador);
        model.addAttribute("eletrosList", EletrosList);
        return "index.html";
    }


    @GetMapping({ "/indexAdmin", "/indexAdmin.html"})
    public String getAdminPage (Model model) {
        List<Eletros> EletrosList = service.findAll();
        model.addAttribute("eletrosList", EletrosList);
       // model.addAttribute()
        return "adminPage.html";
    }




    @PostMapping("/doSalvar")
    public String doSalvar(@ModelAttribute @Valid Eletros e, @RequestParam(name = "file") MultipartFile file, Errors errors, RedirectAttributes x) {
        var eletro = Math.random();// para concatenar no nome do file

        if (errors.hasErrors()) {
            x.addFlashAttribute("mensagem","Não foi possivel salvar :(");
            return "cadastrarPage";
        } else {

            String fileName = eletro+file.getOriginalFilename();
            e.setImageUri(fileName );
            this.fileStorageService.save(file,fileName);
            service.save(e);
            x.addFlashAttribute("mensagem","salvo com sucesso!");
            return "redirect:/adminPage";
        }
    }





    @GetMapping("/editarPage/{id}")
    public String getEditarPage(@PathVariable (name = "id") String id, Model model, RedirectAttributes x){
        Optional<Eletros> E = service.findById(id);//recebo o objeto que quero editar

        if (E.isPresent()) {

            model.addAttribute("eletros", E.get());//passo o objeto como parametro
            model.addAttribute("file", E.get().getImageUri());//indico que vim do editar
            x.addFlashAttribute("mensagem","Não foi possivel salvar :(");

        }

        return "editarPage";//retorno ele para o editar page
    }

    @GetMapping("/doDeletar/{id}")
    public String doDeletar(@PathVariable(name = "id") String id){
        Optional<Eletros> E = service.findById(id);
        if (E.isPresent()) {
            service.delete(E, id);
        }
        return "redirect:/index";
    }

// POSSIVEL ERRO NO ID , ESTAMOS PASSANDO COMO STRING MAS É LONG
    @GetMapping("/addCarrinho/{id}")
    public String addCarrinho(@PathVariable(name = "id") String id, HttpServletRequest request, Model model){

        //procurando a sessao

        HttpSession sessao = request.getSession(true);
        ArrayList<Eletros> carrinho = (ArrayList<Eletros>) sessao.getAttribute("carrinho");

        if (carrinho == null) {
            carrinho = new ArrayList<Eletros>();
            sessao.setAttribute("carrinho", carrinho);
        }
        //adiconando o eletros desejado a sessao
        Optional<Eletros> eletros;
        eletros = service.findById(id);

        if(eletros.isPresent()){
            Eletros eletroEncontrado = eletros.get();
            carrinho.add(eletroEncontrado);
        }
        sessao.setAttribute("contador", carrinho.size());




        return "redirect:/index";

    }
    @GetMapping("/verCarrinho")
    public String verCarrinho(Model model, HttpServletRequest request, RedirectAttributes x){
        HttpSession sessao = request.getSession(true);// geta a sessão

        ArrayList<Eletros> carrinhoList = (ArrayList<Eletros>) sessao.getAttribute("carrinho");// pega o atributo da sessao
        model.addAttribute("carrinho", carrinhoList);//coloca o array no model

        if(carrinhoList == null || carrinhoList.isEmpty()){
            x.addFlashAttribute("mensagem", "Não tem nada no carrinho");
            return "redirect:/index";
        }
        else{

        }
        return "verCarrinhoPage";
    }
    @GetMapping("/finalizarCompra")
    public String invalidate( HttpServletRequest request,RedirectAttributes x){
        HttpSession sessao = request.getSession(false);
        var carrinho = sessao.getAttribute("carrinho");
        System.out.println("aaaaaaaaaaaaaaaaa"+sessao);
        if(carrinho == null){
            x.addFlashAttribute("mensagem","O carrinho está vazio");

        }else{
            sessao.invalidate();
            x.addFlashAttribute("mensagem","COMPRA FINZALIZADA");

        }
        return "redirect:/index";


    }


    @GetMapping("/doCookie")
    public String criarCookie(HttpServletResponse response, Model model){
        Date dataHoraAcesso = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String valorCookie = formatter.format(dataHoraAcesso);


        Cookie cookie = new Cookie("visita", valorCookie);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        model.addAttribute("cookieValue", valorCookie);

        return "redirect:/index";
    }


}
