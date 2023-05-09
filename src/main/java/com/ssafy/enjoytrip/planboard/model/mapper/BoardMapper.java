package com.ssafy.enjoytrip.planboard.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;

@Mapper
public interface BoardMapper {
	void writeArticle(PlanBoardDto planBoardDto) throws SQLException;
	
	List<PlanBoardDto> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	PlanBoardDto getArticle(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	void modifyArticle(PlanBoardDto boardDto) throws SQLException;

	// void deleteFile(int articleNo) throws Exception;

	void deleteArticle(int articleNo) throws SQLException;
}
