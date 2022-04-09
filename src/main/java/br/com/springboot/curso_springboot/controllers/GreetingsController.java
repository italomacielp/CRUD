package br.com.springboot.curso_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
