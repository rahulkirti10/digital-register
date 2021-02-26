package com.docsapi.restcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.docsapi.entities.Election;
import com.docsapi.repository.ElectionRepository;
@Controller

public class WebController {	

	@Autowired
	ElectionRepository electionRepository;
	
	
	@GetMapping("/republic-day1")
	public String about(@RequestParam("name") String name, Model m)
	{
	m.addAttribute("name",name);
		return "greeting";
	}

	@GetMapping("/election1")
	public String elect(@RequestParam("u_id") long u_id, Model m)
	{
	Optional<Election> election=electionRepository.findById(u_id);
	Election e=election.get();
	m.addAttribute("fname",e.getFname());
	m.addAttribute("sname",e.getSname());
	return "election-campaign";
	}
	@GetMapping("/election-sharing1") 
	public String sharing(@RequestParam("fname") String fname,@RequestParam("sname") String sname, Model m)
	{
	
		Election election=new Election();
		election.setFname(fname);
		election.setSname(sname);
		electionRepository.save(election);
		long id=electionRepository.getId();
		System.out.println(id);
		m.addAttribute("fname",fname);
		m.addAttribute("sname",sname);
		m.addAttribute("u_id",id);
		return "election-sharing";
	}
	@GetMapping("/whatsapp-shared1")
	public String shared(@RequestParam("name") String name, Model m)
	{
	m.addAttribute("name",name);
		return "whatsapp-shared";
	}

}
