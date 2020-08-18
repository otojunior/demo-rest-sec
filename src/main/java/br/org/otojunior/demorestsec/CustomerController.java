/**
 * 
 */
package br.org.otojunior.demorestsec;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author otoju
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@Secured("ROLE_USER")
	public Customer getCustomer(@PathVariable final Long id) {
		return new Customer(id, "Custmomer " + id);
	}
}
