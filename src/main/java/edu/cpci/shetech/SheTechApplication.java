package edu.cpci.shetech;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@SpringBootApplication
public class SheTechApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SheTechApplication.class, args);
	}
	
	@SuppressWarnings("unchecked")
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
	            parametroList.forEach( parametro -> parseParametroObject( (JSONObject) parametro, parametroService) );
	            empresasList.forEach( empresa -> parseEmpresaObject( (JSONObject) empresa, empresaService) );
	            usuariosList.forEach( usuarios -> parseUsuarioObject( (JSONObject) usuarios, usuarioService) );
	            posteosList.forEach( posteos -> parsePosteoObject( (JSONObject) posteos, posteoService, usuarioService, empresaService, parametroService) );
	            //System.out.println(usuariosList.toJSONString());
	            
	            
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
        Usuario usuarioSave =new Usuario();   
        String username = (String) usuarioObject.get("username");  
        String password = (String) usuarioObject.get("password");    
        String email = (String) usuarioObject.get("email");    
        Boolean isActivo = (boolean) usuarioObject.get("isActivo");  
        usuarioSave.setUsername(username);
        usuarioSave.setPassword(password);
        usuarioSave.setEmail(email);
        usuarioSave.setActivo(isActivo);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(usuarioSave);
            System.out.println("USUARIO GUARDADO: "+json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        usuarioService.AddUser(usuarioSave);
    }
	
	private static void parseParametroObject(JSONObject parametro, ParametroService parametroService) 
	{
        JSONObject parametroObject = (JSONObject) parametro.get("parametro");
        Parametro parametroSave = new Parametro();
        String tipoParametro = (String) parametroObject.get("tipoParametro");  
        String descripcion = (String) parametroObject.get("descripcion");
        parametroSave.setTipoParametro(tipoParametro);
        parametroSave.setDescripcion(descripcion);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(parametroSave);
            System.out.println("PARAMETRO GUARDADO: "+json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        parametroService.save(parametroSave);
	}
	
	private static void parseEmpresaObject(JSONObject empresa, EmpresaService empresaService) 
	{
        JSONObject empresaObject = (JSONObject) empresa.get("empresa");
        Empresa empresaSave = new Empresa();
        String nombreEmpresa = (String) empresaObject.get("nombre");
        String descripcionEmpresa = (String) empresaObject.get("descripcion");
        String direccionEmpresa = (String) empresaObject.get("direccion");
        empresaSave.setNombre(nombreEmpresa);
        empresaSave.setDescripcion(descripcionEmpresa);
        empresaSave.setDireccion(direccionEmpresa);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(empresaSave);
            System.out.println("EMPRESA GUARDADO: "+json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        empresaService.save(empresaSave);
	}
	
	private static void parseRolObject(JSONObject empresa, RolService rolService) 
	{
        JSONObject rolObject = (JSONObject) empresa.get("rol");
        Rol rolSave = new Rol();
        String rol = (String) rolObject.get("nombre");  
        rolSave.setNombre(rol);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(rolSave);
            System.out.println("ROL GUARDADO: "+json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rolService.save(rolSave);
	}
	
	private static void parsePosteoObject(	JSONObject posteo,
											PosteoService posteoService, 
											UsuarioService usuarioService, 
											EmpresaService empresaService, 
											ParametroService parametroService) 
	{
        JSONObject posteoObject = (JSONObject) posteo.get("posteo");
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
        Usuario usuarioPostObject = usuarioService.getUsuarioByNombre(usuarioPost);
        posteoSave.setUsuario(usuarioPostObject);
        Parametro parametroPostObject = parametroService.getEstadoPostByDescripcion(estadoPost);
        posteoSave.setEstado(parametroPostObject);
        Empresa empresaPostObject = empresaService.getByNombre(empresaPost);
        posteoSave.setEmpresa(empresaPostObject);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(posteoSave);
            System.out.println("POSTEO GUARDADO: "+json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        posteoService.save(posteoSave);
	}
}