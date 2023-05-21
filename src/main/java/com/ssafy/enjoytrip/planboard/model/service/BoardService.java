package com.ssafy.enjoytrip.planboard.model.service;

import java.util.List;

import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;

public interface BoardService {
	boolean writeArticle(PlanBoardDto boardDto) throws Exception;

	List<PlanBoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;

	// PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	PlanBoardDto getArticle(int articleNo) throws Exception;

	void updateHit(int articleNo) throws Exception;

	boolean modifyArticle(PlanBoardDto boardDto) throws Exception;

	// void deleteArticle(int articleNo, String path) throws Exception;
	
	boolean deleteArticle(int articleNo) throws Exception;
}
