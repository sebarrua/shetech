package edu.cpci.shetech;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import edu.cpci.shetech.entity.Empresa;
import edu.cpci.shetech.entity.Parametro;
import edu.cpci.shetech.entity.Posteo;
import edu.cpci.shetech.entity.Rol;
import edu.cpci.shetech.entity.Usuario;
import edu.cpci.shetech.service.EmpresaService;
import edu.cpci.shetech.service.ParametroService;
import edu.cpci.shetech.service.PosteoService;
import edu.cpci.shetech.service.RolService;
import edu.cpci.shetech.service.UsuarioService;
import lombok.Value;

@SpringBootApplication
public class SheTechApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SheTechApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(	UsuarioService usuarioService, 
								PosteoService posteoService, 
								EmpresaService empresaService, 
								ParametroService parametroService,
								RolService rolService) {
		return args -> {
	
	        JSONParser jsonParser = new JSONParser();
	        try
	        {
	        	FileReader usuariosReader = new FileReader("src/main/resources/static/json/usuarios.json");
	        	FileReader posteosReader = new FileReader("src/main/resources/static/json/posteos.json");
	        	FileReader empresaReader = new FileReader("src/main/resources/static/json/empresas.json");
	        	FileReader parametroReader = new FileReader("src/main/resources/static/json/parametros.json");
	        	FileReader rolesReader = new FileReader("src/main/resources/static/json/roles.json");
	            Object usuarioObj = jsonParser.parse(usuariosReader);
	            Object posteoObj = jsonParser.parse(posteosReader);
	            Object empresaObj = jsonParser.parse(empresaReader);
	            Object parametroObj = jsonParser.parse(parametroReader);
	            Object rolObj = jsonParser.parse(rolesReader);
	            JSONArray usuariosList = (JSONArray) usuarioObj;
	            JSONArray posteosList = (JSONArray) posteoObj;
	            JSONArray empresasList = (JSONArray) empresaObj;
	            JSONArray parametroList = (JSONArray) parametroObj;
	            JSONArray rolList = (JSONArray) rolObj;
	            rolList.forEach( rol -> parseRolObject( (JSONObject) rol, rolService) );
	            System.out.println("1");
	            parametroList.forEach( parametro -> parseParametroObject( (JSONObject) parametro, parametroService) );
	            System.out.println("2");
	            empresasList.forEach( empresa -> parseEmpresaObject( (JSONObject) empresa, empresaService) );
	            System.out.println("3");
	            usuariosList.forEach( usuarios -> parseUsuarioObject( (JSONObject) usuarios, usuarioService) );
	            System.out.println("4");
	            posteosList.forEach( posteos -> parsePosteoObject( (JSONObject) posteos, posteoService, usuarioService, empresaService, parametroService) );
	            System.out.println("5");
	            
	            
	            
	        } catch (FileNotFoundException e) {
	        	System.out.println("FileNotFoundException");
	            e.printStackTrace();
	        } catch (IOException e) {
	        	System.out.println("IOException");
	            e.printStackTrace();
	        } catch (ParseException e) {
	        	System.out.println("ParseException");
	            e.printStackTrace();
	        }
		};
	}
	
	private static void parseUsuarioObject(JSONObject usuario, UsuarioService usuarioService) 
	{
        JSONObject usuarioObject = (JSONObject) usuario.get("usuario");
        System.out.println(usuarioObject);
        Usuario usuarioSave =new Usuario();
        //Long usuarioId = (long) usuarioObject.get("usuarioId");    
        String username = (String) usuarioObject.get("username");  
        String password = (String) usuarioObject.get("password");    
        String email = (String) usuarioObject.get("email");    
        Boolean isActivo = (boolean) usuarioObject.get("isActivo");  
        //usuarioSave.setUsuarioId(usuarioId);
        usuarioSave.setUsername(username);
        usuarioSave.setPassword(password);
        usuarioSave.setEmail(email);
        usuarioSave.setActivo(isActivo);
        usuarioService.AddUser(usuarioSave);
    }
	
	private static void parseParametroObject(JSONObject parametro, ParametroService parametroService) 
	{
        JSONObject parametroObject = (JSONObject) parametro.get("parametro");
        System.out.println(parametroObject);
        Parametro parametroSave = new Parametro();
        String tipoParametro = (String) parametroObject.get("tipoParametro");  
        String descripcion = (String) parametroObject.get("descripcion");
        parametroSave.setTipoParametro(tipoParametro);
        parametroSave.setDescripcion(descripcion);
        parametroService.save(parametroSave);
	}
	
	private static void parseEmpresaObject(JSONObject empresa, EmpresaService empresaService) 
	{
        JSONObject empresaObject = (JSONObject) empresa.get("empresa");
        System.out.println(empresaObject);
        Empresa empresaSave = new Empresa();
        String nombreEmpresa = (String) empresaObject.get("nombre");  
        empresaSave.setNombre(nombreEmpresa);
        empresaService.save(empresaSave);
	}
	
	private static void parseRolObject(JSONObject empresa, RolService rolService) 
	{
        JSONObject rolObject = (JSONObject) empresa.get("rol");
        System.out.println(rolObject);
        Rol rolSave = new Rol();
        String rol = (String) rolObject.get("nombre");  
        rolSave.setNombre(rol);
        rolService.save(rolSave);
	}
	
	private static void parsePosteoObject(	JSONObject posteo,
											PosteoService posteoService, 
											UsuarioService usuarioService, 
											EmpresaService empresaService, 
											ParametroService parametroService) 
	{
        JSONObject posteoObject = (JSONObject) posteo.get("posteo");
        System.out.println(posteoObject);
        Posteo posteoSave = new Posteo();
        String tituloPost = (String) posteoObject.get("titulo");
        String descripcionPost = (String) posteoObject.get("descripcion");
        String textoPost = (String) posteoObject.get("texto");
        String usuarioPost = (String) posteoObject.get("usuario");
        String empresaPost = (String) posteoObject.get("empresa");
        String estadoPost = (String) posteoObject.get("estado");
        posteoSave.setTitulo(tituloPost);
        posteoSave.setDescripcion(descripcionPost);
        posteoSave.setTexto(textoPost);
        Date datePost = new Date();
        posteoSave.setFechaPosteo(datePost);
        Usuario usuarioPostObject = usuarioService.getusuarioByNombreObject(usuarioPost);
        System.out.println("####"+usuarioPostObject);
        posteoSave.setUsuario(usuarioPostObject);
        Parametro parametroPostObject = parametroService.getEstadoPostByDescripcion(estadoPost);
        System.out.println("$$$$"+parametroPostObject);
        posteoSave.setEstado(parametroPostObject);
        Empresa empresaPostObject = empresaService.getByNombre(empresaPost);
        System.out.println("%%%%"+empresaPostObject);
        posteoSave.setEmpresa(empresaPostObject);
        posteoService.save(posteoSave);
	}
}