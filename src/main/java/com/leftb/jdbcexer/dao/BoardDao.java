package com.leftb.jdbcexer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.leftb.jdbcexer.dto.BoardDto;
import com.leftb.jdbcexer.util.Constant;

public class BoardDao {

	DataSource dataSource;
	JdbcTemplate template;

	public BoardDao() {
		template = Constant.template;
	}

	public void write(final String bname, final String btitle, final String bcontent) {
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				String sql = "INSERT INTO mvc_board(bid, bname, btitle, bcontent) "
						   + "VALUES (mvc_board_seq.nextval,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);

				return pstmt;
			}
		});
	}

	public ArrayList<BoardDto> list() {
		String sql = "SELECT bid,bname,btitle,bcontent,bdate,bhit "
				   + "FROM mvc_board ORDER BY bdate DESC";
		
		ArrayList<BoardDto> dtos
			= (ArrayList<BoardDto>)template.query(sql,
				new BeanPropertyRowMapper(BoardDto.class));

		return dtos;
	}

	public BoardDto view(String bid, boolean uphit) {
		String sql;
		
		if(uphit) {
			sql = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=" + bid;
			template.update(sql);
		}
		sql = "SELECT bid,bname,btitle,bcontent,bdate,bhit "
			+ "FROM mvc_board WHERE bid=" + bid;
		BoardDto dto = template.queryForObject(sql, new BeanPropertyRowMapper(BoardDto.class));

		return dto;
	}

	public void modify(final String bid, final String btitle, final String bcontent) {
		String sql = "UPDATE mvc_board SET btitle=?, bcontent=? " 
				   + "WHERE bid=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, btitle);
				pstmt.setString(2, bcontent);
				pstmt.setString(3, bid);
			}
		});
	}

	public void delete(final String bid) {
		String sql = "DELETE FROM mvc_board WHERE bid=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, bid);
			}
		});
	}

}
