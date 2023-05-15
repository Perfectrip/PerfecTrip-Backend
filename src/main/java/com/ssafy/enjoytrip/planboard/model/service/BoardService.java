package com.ssafy.enjoytrip.planboard.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {
	void writeArticle(PlanBoardDto boardDto) throws Exception;

	List<PlanBoardDto> listArticle(Map<String, String> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	PlanBoardDto getArticle(int articleNo) throws Exception;

	void updateHit(int articleNo) throws Exception;

	void modifyArticle(PlanBoardDto boardDto) throws Exception;

	// void deleteArticle(int articleNo, String path) throws Exception;
	
	void deleteArticle(int articleNo) throws Exception;
}
