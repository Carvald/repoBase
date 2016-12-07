/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esquema.controlador;

/**
 *
 * @author roberto.espinoza
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class Controlador {

	/*
        @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HelloweenResponse> hello(Principal principal) {

		return new ResponseEntity<HelloweenResponse>(
				new HelloweenResponse("Happy Halloween, " + principal.getName() + "!"), HttpStatus.OK);
	}

	public static class HelloweenResponse {
		private String message;
		public HelloweenResponse(String message) {
			this.message = message;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
        */
        
	@RequestMapping("/esquema")
	public String index() throws Exception {
            return "index";
	}
}
