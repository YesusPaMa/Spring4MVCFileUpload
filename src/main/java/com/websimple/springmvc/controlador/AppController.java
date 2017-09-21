package com.websimple.springmvc.controlador;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.websimple.springmvc.modelo.Archivo;
import com.websimple.springmvc.modelo.Usuario;
import com.websimple.springmvc.modelo.UsuarioDoc;
import com.websimple.springmvc.servicio.UsuarioDocService;
import com.websimple.springmvc.servicio.UsuarioService;
import com.websimple.springmvc.utilidades.ValidaArchivo;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioDocService usuarioDocService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	ValidaArchivo validaArchivo;
	
	@InitBinder("archivo")
	protected void initBinder(WebDataBinder binder) {
	   binder.setValidator(validaArchivo);
	}
	
	/**
	 * Este método lista todos los usuarios existentes.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap modelo) {
		List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();
		modelo.addAttribute("usuarios", usuarios);
		return "usuarioLista";
	}

	/**
	 * Este método agrega un nuevo usuario.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("editar", false);
		return "registro";
	}

	/**
	 * Este método será llamado en el formulario, solicitud POST para
	 * guardar el usuario en la base de datos. También valida la entrada del usuario
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid Usuario usuario, BindingResult resultado,
			ModelMap modelo) {

		if (resultado.hasErrors()) {
			return "registro";
		}

		/*
		 * La forma preferida de lograr la unicidad del campo [sso] debería implementar la anotación personalizada @Unique
		 * y aplicarlo en el campo [sso] de la clase [Usuario].
		 */
		if(!usuarioService.esUsuarioSSOUnico(usuario.getId(), usuario.getSsoId())){
			FieldError ssoError =new FieldError("usuario","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{usuario.getSsoId()}, Locale.getDefault()));
		    resultado.addError(ssoError);
			return "registro";
		}
		
		usuarioService.guardarUsuario(usuario);
		
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("success", "Usuario " + usuario.getNombre() + " "+ usuario.getApellido() + " registrado correctamente.");
		return "registroExito";
	}


	/**
	 * Este método actualiza un usuario existente.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap modelo) {
		Usuario usuario = usuarioService.buscarSSO(ssoId);
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("editar", true);
		return "registro";
	}
	
	/**
	 * Este método será llamado en el formulario, solicitud POST para
	 * actualizar usuario en base de datos. También valida la entrada del usuario
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid Usuario usuario, BindingResult resultado,
			ModelMap modelo, @PathVariable String ssoId) {

		if (resultado.hasErrors()) {
			return "registro";
		}

		usuarioService.actualizarUsuario(usuario);

		modelo.addAttribute("success", "Usuario " + usuario.getNombre() + " "+ usuario.getApellido() + " actualizado correctamente.");
		return "registroExito";
	}

	
	/**
	 * Este método eliminará un usuario por su valor SSOID.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		usuarioService.borrarUsuarioSSO(ssoId);
		return "redirect:/list";
	}
	
	
	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
	public String addDocuments(@PathVariable int userId, ModelMap modelo) {
		Usuario usuario = usuarioService.buscarId(userId);
		modelo.addAttribute("usuario", usuario);

		Archivo archivo = new Archivo();
		modelo.addAttribute("archivo", archivo);

		List<UsuarioDoc> documentos = usuarioDocService.buscarTodosUsuariosId(userId);
		modelo.addAttribute("documentos", documentos);
		
		return "adminDoc";
	}
	

	@RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response) throws IOException {
		UsuarioDoc documento = usuarioDocService.buscarId(docId);
		response.setContentType(documento.getTipo());
        response.setContentLength(documento.getContenido().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + documento.getNombre() +"\"");
 
        FileCopyUtils.copy(documento.getContenido(), response.getOutputStream());
 
 		return "redirect:/add-document-"+userId;
	}

	@RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
		usuarioDocService.borrarId(docId);
		return "redirect:/add-document-"+userId;
	}

	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid Archivo archivo, BindingResult resultado, ModelMap modelo, @PathVariable int userId) throws IOException{
		if (resultado.hasErrors()) {
			System.out.println("validación de errores");
			Usuario usuario = usuarioService.buscarId(userId);
			modelo.addAttribute("usuario", usuario);

			List<UsuarioDoc> documentos = usuarioDocService.buscarTodosUsuariosId(userId);
			modelo.addAttribute("documentos", documentos);
			
			return "adminDoc";
		} else {
			
			System.out.println("Recuperar archivo");
			
			Usuario usuario = usuarioService.buscarId(userId);
			modelo.addAttribute("usuario", usuario);

			saveDocument(archivo, usuario);

			return "redirect:/add-document-"+userId;
		}
	}
	
	private void saveDocument(Archivo archivo, Usuario usuario) throws IOException{
		
		UsuarioDoc documento = new UsuarioDoc();
		
		MultipartFile multipartFile = archivo.getArchivo();
		
		documento.setNombre(multipartFile.getOriginalFilename());
		documento.setDescripcion(archivo.getDescripcion());
		documento.setTipo(multipartFile.getContentType());
		documento.setContenido(multipartFile.getBytes());
		documento.setUsuario(usuario);
		usuarioDocService.guardarDoc(documento);
	}
	
}
