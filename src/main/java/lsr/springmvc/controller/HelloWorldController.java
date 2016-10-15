
package lsr.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helloworld")
public class HelloWorldController {

	@RequestMapping("/*")
	public String springmvc_helloworld(){
		return "helloworld";
	}
	
}
