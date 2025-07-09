package br.appLogin.appLogin.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.appLogin.appLogin.model.Usuario;
import br.appLogin.appLogin.repository.UsuarioRepository;
import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private UsuarioRepository ur;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
		return "index";
	}
	
	@PostMapping("/logar")
	public String login(Usuario usuario, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		Usuario usuarioLogado = this.ur.login(usuario.getEmail(), usuario.getPassword());
		if(usuarioLogado != null) {
			CookieService.setCookie(response, "usuarioId", String.valueOf(usuarioLogado.getId()), 10000);
			CookieService.setCookie(response, "nomeUsuario", String.valueOf(usuarioLogado.getName()), 10000);
			return "redirect:/";
		}
		model.addAttribute("erro", "Usuario invalido!");
		return "login";
	}
	
	
	@GetMapping("/sair")
	public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
		CookieService.setCookie(response, "usuarioId", "", 0);
		return "redirect:login";
	}	
		
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/cadastro")
	public String cadastro() {
		return "cadastro";
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public String cadastro(@Valid Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return "redirect:/cadastro";
		}
		
		ur.save(usuario);
		
		return "redirect:/login";
	}
}
