package edu.cpci.shetech.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cpci.shetech.entity.Empresa;
import edu.cpci.shetech.service.EmpresaService;

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	
	@GetMapping(value="/listEmpresa")
	public String ListaEmpresas(Model model, Principal principal) {
		
		List<Empresa> listEmpresas = this.empresaService.getAll();
		model.addAttribute("listaEmpresas", listEmpresas);
		
		return "listaEmpresasPage";
	}
	
	@GetMapping(value="/addEmpresa")
	public String NewEmpresa(Model model, Principal principal) {
		String vista="addEmpresa";
		//Empresa empresa = new Empresa();
		//model.addAttribute("Empresa", empresa);
		System.out.println("CONTROLADOR NEW EMPRESA");
		return vista;
	}

}
