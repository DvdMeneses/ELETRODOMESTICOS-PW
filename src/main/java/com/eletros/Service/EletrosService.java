package com.eletros.Service;

import com.eletros.Model.Eletros;
import com.eletros.Repository.EletrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EletrosService {

    private EletrosRepository repository;

    public EletrosService(EletrosRepository repository){
        this.repository = repository;
    }

    public void save (Eletros e){
        e.nomesMaiusculo();
        repository.save(e);
    }

    public Eletros getEletroById(Long id){
        return repository.getReferenceById(id);
    }

    public List<Eletros> findAll(){
        return repository.findAll();
    }

    public Optional<Eletros> findById(String id){
        return repository.findById(id);
    }
    /*
    public void delete(Optional<Eletros> e, String id) {

        repository.deleteById(id);
    }*/

    public void delete(Optional <Eletros> e , String id){
        var EletroAux  = this.findById(id);
        EletroAux.get().deletar();
        repository.save(EletroAux.get());

    }

}


