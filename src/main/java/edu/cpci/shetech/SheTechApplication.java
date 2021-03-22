package edu.cpci.shetech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.UsuarioService;

@SpringBootApplication
public class SheTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheTechApplication.class, args);
	}
/*	
	@Bean
    public CommandLineRunner demo(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {

        return (args) -> {
//            for (Integer i = 0; i < 10; ++i) {
//
//                Encuesta s = new Encuesta();
//                s.setNombre("encuesta" + i.toString());
//                repository.save(s);
//
//            }
//              Crear usuaro de prueba
            Usuario admin = usuarioService.getUsuarioByNombre("admin");
            if (admin == null){

                Usuario usuario = new Usuario();
                usuario.setUsername("admin");
                usuario.setPassword(passwordEncoder.encode("123"));
                usuario.setActivo(true);
                usuario.setEmail("admin@shetech.com");
                usuarioService.save(usuario);

            }
        };
    }
*/

}
