package com.eletros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eletros.Model.Eletros;

import java.time.LocalDateTime;
import java.util.List;

public interface EletrosRepository extends JpaRepository<Eletros, String>{
    List<Eletros> findEletrosById(Long id);

    Eletros  getReferenceById(Long id);


}
