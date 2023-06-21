package com.leftb.jdbcexer.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.leftb.jdbcexer.dao.BoardDao;
import com.leftb.jdbcexer.dto.BoardDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardDto> dto_list = boardDao.list();

		model.addAttribute("list", dto_list);
		model.addAttribute("total", dto_list.size());
	}

}
