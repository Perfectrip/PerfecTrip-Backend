package com.ssafy.enjoytrip.planboard.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;

@Mapper
public interface BoardMapper {
	int writeArticle(PlanBoardDto planBoardDto) throws SQLException;
	
	List<PlanBoardDto> listArticle(BoardParameterDto boardParameterDto) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	PlanBoardDto getArticle(int articleNo) throws SQLException;

	void updateHit(int articleNo) throws SQLException;

	int modifyArticle(PlanBoardDto boardDto) throws SQLException;

	// void deleteFile(int articleNo) throws Exception;

	int deleteArticle(int articleNo) throws SQLException;
}
