package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Tarefa;
import com.example.demo.repository.TarefaRepository;

@Controller
public class IndexControler {

	@Autowired
	private TarefaRepository csr;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		return "form";
	}

	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String form(Tarefa usuario) {
		csr.save(usuario);
		return "redirect:/";
	}
	
	@RequestMapping("/rcrud")
	public ModelAndView listarTarefas() {
		
		ModelAndView mv = new ModelAndView("/rcrud");
		
		Iterable<Tarefa> tarefa = csr.findAll();
		mv.addObject("tarefa", tarefa);
		
		return mv;
		
	}
	
	@RequestMapping(value="/alterarTarefa/{codigoTarefa}", method=RequestMethod.GET)
	public ModelAndView formAlterarTarefa(@PathVariable("codigoTarefa") long codigoTarefa) {
		
		Tarefa tarefa = csr.findByCodigoTarefa(codigoTarefa);
		
		ModelAndView mv = new ModelAndView("/alterar-tarefa");
		
		mv.addObject("tarefa", tarefa);
		
		return mv;
		
	}
	
	@RequestMapping(value="/alterarTarefa/{codigoTarefa}", method=RequestMethod.POST)
	public String alterarTarefa(@Validated Tarefa tarefa, BindingResult result, RedirectAttributes attributes) {
		
		csr.save(tarefa);
		return "redirect:/rcrud";
		
	}
	
	@RequestMapping("/confirmarExclusaoTarefa/{codigoTarefa}")
	public ModelAndView confirmarExclusaoTarefa(@PathVariable("codigoTarefa") long codigoTarefa) {
		
		Tarefa tarefa = csr.findByCodigoTarefa(codigoTarefa);
		
		ModelAndView mv = new ModelAndView("/excluir-tarefa");
		
		mv.addObject("tarefa", tarefa);
		
		return mv;
		
	}
	
	@RequestMapping("/excluirTarefa")
	public String excluirTarefa(long codigoTarefa) {
		
		Tarefa tarefa = csr.findByCodigoTarefa(codigoTarefa);
		csr.delete(tarefa);
		
		return "redirect:/rcrud";
		
	}
}
