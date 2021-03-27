package edu.cpci.shetech.utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioRestController {
	
	private UsuarioService userService;

    @GetMapping("/list")
    public Iterable<Usuario> list() {
        return userService.list();
    }
}
