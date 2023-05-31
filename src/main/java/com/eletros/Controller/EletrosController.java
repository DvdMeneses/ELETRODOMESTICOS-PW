package com.eletros.Controller;

import com.eletros.Model.Eletros;
import com.eletros.Service.EletrosService;
import com.eletros.Service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
        public String getIndex (Model model) {
        List<Eletros> EletrosList = service.findAll();
        model.addAttribute("eletrosList", EletrosList);
        return "index.html";
    }




    @PostMapping("/doSalvar")
    public String doSalvar(@ModelAttribute @Valid Eletros e, @RequestParam(name = "file") MultipartFile file, Errors errors) {
        if (errors.hasErrors()) {
            return "cadastrarPage";
        } else {
            System.out.println("ENTREI KLRH");
            String fileName = file.getOriginalFilename();
            e.setImageUri(fileName);
            this.fileStorageService.save(file);
            service.save(e);
            return "redirect:/index";
        }
    }



    @GetMapping("/editarPage/{id}")
    public String getEditarPage(@PathVariable (name = "id") String id, Model model){
        Optional<Eletros> E = service.findById(id);
        System.out.println(E.getClass());
            if (E.isPresent()) {
                model.addAttribute("eletros", E.get());
            }

            return "editarPage";
    }

    @GetMapping("/doDeletar/{id}")
    public String doDeletar(@PathVariable(name = "id") String id){
        Optional<Eletros> E = service.findById(id);
        if (E.isPresent()) {
            service.delete(E, id);
        }
        return "redirect:/index";
    }








}
