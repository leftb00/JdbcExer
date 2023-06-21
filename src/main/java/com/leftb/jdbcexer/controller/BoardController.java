package com.leftb.jdbcexer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leftb.jdbcexer.util.Constant;
import com.leftb.jdbcexer.command.BWriteCommand;
import com.leftb.jdbcexer.command.BListCommand;
import com.leftb.jdbcexer.command.BContentCommand;
import com.leftb.jdbcexer.command.BDeleteCommand;
import com.leftb.jdbcexer.command.BModifyCommand;
import com.leftb.jdbcexer.command.BCommand;

@Controller
public class BoardController {

	BCommand command = null;
	private JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		Constant.template = this.template = template;
	}

	@RequestMapping(value = {"/", "/list"})
	public String index(Model model) {
		command = new BListCommand();
		command.execute(model);

		return "list";
	}

	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}

	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping(value = "/view")
	public String view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("uphit", "1");
		command = new BContentCommand();
		command.execute(model);

		return "view";
	}

	@RequestMapping(value = "/modify_form")
	public String modify_form(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("uphit", "0");
		command = new BContentCommand();
		command.execute(model);

		return "modify_form";
	}

	@RequestMapping(value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);

		return "redirect:list";
	}
}
