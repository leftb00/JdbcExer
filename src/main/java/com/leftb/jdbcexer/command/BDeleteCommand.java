package com.leftb.jdbcexer.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.leftb.jdbcexer.dao.BoardDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");

		BoardDao boardDao = new BoardDao();
		boardDao.delete(request.getParameter("bid"));
	}

}
