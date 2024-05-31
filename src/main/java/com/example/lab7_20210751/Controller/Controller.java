package com.example.lab7_20210751.Controller;

import com.example.lab7_20210751.Entity.User;
import com.example.lab7_20210751.Repository.ResourceRepository;
import com.example.lab7_20210751.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
                if (user.getType().equals("Cliente")){
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
                            respuesta.put("msg","Debe ingresar un tipo válido");
                            return ResponseEntity.badRequest().body(respuesta);
                        }
                    }
                }

        }
    }

    @GetMapping(value = { "/{id}"})
    public ResponseEntity<HashMap<String,Object>> listar(@PathVariable("id") int id) {
        HashMap<String, Object> respuesta = new HashMap<>();
        try {
            List<User> listaUsuarios = userRepository.findAll();

            List<User> listaAux = new ArrayList<>();

            for (int i=0;i<listaUsuarios.size();i++){

                if(listaUsuarios.get(i).getAuthorizedResource().getIdResource()==id){
                    listaAux.add(listaUsuarios.get(i));
                }
            }

            if (!listaUsuarios.isEmpty()){
                respuesta.put("result","success");
                respuesta.put("users", listaAux);
            } else {
                respuesta.put("result", "no hay usuarios para este recurso");
            }
            return ResponseEntity.ok().body(respuesta);
        } catch (Exception e) {
            respuesta.put("result",e.getMessage());
            return ResponseEntity.badRequest().body(respuesta);
        }

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request) {
        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar algo");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarPlayer(@PathVariable("id") String idStr){
        HashMap<String, Object> rpta = new HashMap<>();

        try{
            int id = Integer.parseInt(idStr);
            Optional<User> byId = userRepository.findById(id);
            if(byId.isPresent()){
                userRepository.deleteById(id);

                rpta.put("result","OK, borrado");
            }else{
                rpta.put("result","No OK");
                rpta.put("msg","el ID enviado no existe");
            }

            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping(value = { "/update"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizarPlayer(User userReci) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (userReci.getId() != 0 && userReci.getId() > 0) {

            Optional<User> byId = userRepository.findById(userReci.getId());
            if (byId.isPresent()) {
                User userFromDb = byId.get();

                if (userReci.getAuthorizedResource().equals("Contador")){
                    userFromDb.setAuthorizedResource(resourceRepository.findById(5).get());
                }
                if (userReci.getAuthorizedResource().equals("Cliente")){
                    userFromDb.setAuthorizedResource(resourceRepository.findById(6).get());
                }
                if (userReci.getAuthorizedResource().equals("Cliente")){
                    userFromDb.setAuthorizedResource(resourceRepository.findById(6).get());
                }
                rpta.put("result", "ok");
                return ResponseEntity.ok(rpta);
                } else {
                    rpta.put("result", "error");
                    rpta.put("msg", "El ID del player enviado no existe");
                    return ResponseEntity.badRequest().body(rpta);
                }
            } else {
                rpta.put("result", "error");
                rpta.put("msg", "El ID no existe");
                return ResponseEntity.badRequest().body(rpta);
            }

    }



}
