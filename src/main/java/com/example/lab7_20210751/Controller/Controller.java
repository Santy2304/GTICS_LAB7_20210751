package com.example.lab7_20210751.Controller;

import com.example.lab7_20210751.Entity.User;
import com.example.lab7_20210751.Repository.ResourceRepository;
import com.example.lab7_20210751.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class Controller {

    final UserRepository userRepository;
    final ResourceRepository resourceRepository;

    public Controller(UserRepository userRepository,ResourceRepository resourceRepository) {
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    @PostMapping(value = {"/SDN/CrearContador"})
    public ResponseEntity<HashMap<String, Object>> crearContador(@RequestBody User user) {
        HashMap<String, Object> respuesta = new HashMap<>();
        user.setActive(true);
        if (user.getType().equals("Contador")) {
            user.setAuthorizedResource(resourceRepository.findById(5).get());
            userRepository.save(user);
            respuesta.put("estado", "creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
                if (user.getType().equals("Clientes")){
                    user.setAuthorizedResource(resourceRepository.findById(6).get());
                    userRepository.save(user);
                    respuesta.put("estado", "creado");
                    return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
                }else{
                    if(user.getType().equals("analista de promociones")){
                        user.setAuthorizedResource(resourceRepository.findById(7).get());
                        userRepository.save(user);
                        respuesta.put("estado", "creado");
                        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
                    }else{
                        if(user.getType().equals("analista logístico")){
                            user.setAuthorizedResource(resourceRepository.findById(8).get());
                            userRepository.save(user);
                            respuesta.put("estado", "creado");
                            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
                        }else{
                            respuesta.put("estado", "error");
                            respuesta.put("msg","Debe ingresar un tió válido");
                            return ResponseEntity.badRequest().body(respuesta);
                        }
                    }
                }

        }
    }





}
