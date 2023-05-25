package com.ssafy.enjoytrip.planboard.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.planboard.model.BoardParameterDto;
import com.ssafy.enjoytrip.planboard.model.PlanBoardDto;
import com.ssafy.enjoytrip.planboard.model.mapper.BoardMapper;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	public boolean writeArticle(PlanBoardDto boardDto) throws Exception {
		if (boardDto.getTitle() == null || boardDto.getContent() == null) {
			throw new Exception();
		}
		return boardMapper.writeArticle(boardDto) == 1;
	}

	@Override
	public List<PlanBoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception {
		int start = boardParameterDto.getPg() == 0 ? 0 : (boardParameterDto.getPg() - 1) * boardParameterDto.getSpp();
		boardParameterDto.setStart(start);
		return boardMapper.listArticle(boardParameterDto);
	}

	@Override
	public PlanBoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);
	}

	@Override
	public boolean modifyArticle(PlanBoardDto boardDto) throws Exception {
		return boardMapper.modifyArticle(boardDto) == 1;
	}

	@Override
	public boolean deleteArticle(int articleNo) throws Exception {
		return boardMapper.deleteArticle(articleNo) == 1;
	}

}
