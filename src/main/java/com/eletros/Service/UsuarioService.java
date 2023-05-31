package com.eletros.Service;

import com.eletros.Model.Usuario;
import com.eletros.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    UsuarioRepository repository;
    BCryptPasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void create (Usuario u){
        u.setSenha(encoder.encode(u.getPassword()));
        this.repository.save(u);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> user = repository.findUsuarioByLogin(username);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException("Username not found");
        }

    }
    public List<Usuario> listAll(){
        return  repository.findAll();
    }




}
