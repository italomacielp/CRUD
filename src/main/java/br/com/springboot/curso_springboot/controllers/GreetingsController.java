package br.com.springboot.curso_springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_springboot.model.Users;
import br.com.springboot.curso_springboot.repositories.UsersRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /*IC/CD ou CDI - Injeção de dependências (Instancia um objeto do tipo repository)*/
	private UsersRepository userRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
    @RequestMapping(value = "application/{name}", method = RequestMethod.GET) /*Mapeamento end-point,
     tudo após a porta que roda a aplicação será interceptada*/
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
    	
    	Users user = new Users();
    	user.setName(name);
    	
    	userRepository.save(user);
    	
        return "Hello " + name + "!";
    }
    
    @GetMapping(value = "listall") /*1º método da API - Buscar todos*/
    @ResponseBody /*Retorna os dados par ao corpo da resposta*/
    public ResponseEntity<List<Users>> listaUsuario(){
    	
    	List<Users> users = userRepository.findAll(); /*executa a consulta no banco de dados*/
    	
    	return new ResponseEntity<List<Users>>(users, HttpStatus.OK); /*Retorna a lista em JSON, o status
    	serve para indicar que deu tudo certo.*/
    	
    }
    
    @PostMapping(value = "save") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Users> save (@RequestBody Users user){ /*Recebe os dados para salvar, injetando na classe*/
    	
    	Users users = userRepository.save(user);
    	
    	return new ResponseEntity<Users>(users, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "update") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> update (@RequestBody Users user){ /*Recebe os dados para salvar, injetando na classe*/
    	
    	/*O tipo genérico é essencial, pois na operação de atualizar é necessário
    	 * que o valor do campo id exista, isto é tenha valor diferente de nulo.*/
    	
    	if(user.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
    	}
    	
    	
    	Users users = userRepository.saveAndFlush(user); // Salva e roda direto no banco de dados, sem precisar finalizar a requisição.
    	
    	return new ResponseEntity<Users>(users, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "delete") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser){ /*Recebe os dados para salvar, injetando na classe*/
    	
    	/*É somente necessário do parâmetro id para deletar um usuário.*/
    	
    	userRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deleted with sucess", HttpStatus.OK); /*Somente mostrará que a requisição de deletar foi realizada com sucesso.*/
    }
    
    @GetMapping(value = "findById") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Users> findId (@RequestParam(name = "iduser") Long iduser){ /*Recebe os dados para salvar, injetando na classe*/
    	
    	/*É somente necessário do parâmetro id para deletar um usuário.*/
    	
    	Users user = userRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Users>(user, HttpStatus.OK); /*Somente mostrará que a requisição de deletar foi realizada com sucesso.*/
    }
    
    @GetMapping(value = "findByName") /*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Users>> findByName (@RequestParam(name = "name") String name){ /*Recebe os dados para salvar, injetando na classe*/
    	
    	/*É somente necessário do parâmetro id para deletar um usuário.*/
    	
    	List<Users> users = userRepository.findByName(name.trim().toUpperCase()); //O trim retira o espaço.
    	
    	return new ResponseEntity<List<Users>>(users, HttpStatus.OK); /*Somente mostrará que a requisição de deletar foi realizada com sucesso.*/
    }
    
    
}
