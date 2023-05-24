package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.model.CommentDto;
import com.ssafy.enjoytrip.hotplace.model.mapper.CommentMapper;

@Service
public class CommentServicImpl implements CommentService {

	private CommentMapper commentMapper;

	@Autowired
	public CommentServicImpl(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}

	@Override
	public boolean writeComment(CommentDto commentDto) throws Exception {
		if (commentDto.getCommentText() == null) {
			throw new Exception();
		}
		return commentMapper.writeComment(commentDto) == 1;
	}

	@Override
	public List<CommentDto> listComment(int contentId) throws Exception {
		return commentMapper.listComment(contentId);
	}

	@Override
	public int getTotalCommentCount(int contentId) throws Exception {
		return commentMapper.getTotalCommentCount(contentId);
	}

	@Override
	public boolean modifyComment(CommentDto commentDto) throws Exception {
		return commentMapper.modifyComment(commentDto) == 1;
	}

	@Override
	public boolean deleteComment(int commentId) throws Exception {
		return commentMapper.deleteComment(commentId) == 1;
	}

}
