package com.leftb.jdbcexer.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.leftb.jdbcexer.dao.BoardDao;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String uphit = map.get("uphit").toString();
		
		BoardDao boardDao = new BoardDao();		
		model.addAttribute("dto", boardDao.view(request.getParameter("bid"), uphit.equals("1")));
	}

}
