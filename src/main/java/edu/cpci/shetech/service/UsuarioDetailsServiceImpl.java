package edu.cpci.shetech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.cpci.shetech.entity.Rol;
import edu.cpci.shetech.entity.Usuario;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
    	System.out.println("loadUserByUsername");
        Usuario usr = this.usuarioService.getUsuarioByNombre(nombreUsuario);

        if(usr == null){
            System.out.println("Usuario no encontrado: "+ nombreUsuario);
            throw new UsernameNotFoundException("Usuario "+nombreUsuario+" no existe en la base de datos");

        }
        System.out.println("Usuario ("+nombreUsuario + ") econtrado.");
        // List<Rol> roles = this.rolService.getRolesByUsuario(usr);

        //List<String> roles = this.rolService.getNombreRoles(usr.getId());
        List<Rol> roles = this.rolService.getRolesByUser(usr.getUsuarioId());
        

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null){
            for (Rol r:roles){
                GrantedAuthority authority = new SimpleGrantedAuthority(r.getNombre());
                grantList.add(authority);
                System.out.println(r.getNombre());
            }
        }

        UserDetails userDetails = (UserDetails) new User(usr.getNombre(), usr.getPassword(), grantList);
        
        System.out.println("Username: "+userDetails.getUsername());
        System.out.println("Password: "+userDetails.getPassword());

        return userDetails;
    }

}
