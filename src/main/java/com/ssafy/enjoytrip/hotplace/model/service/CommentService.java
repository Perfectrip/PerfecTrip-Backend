package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;

import com.ssafy.enjoytrip.hotplace.model.CommentDto;


public interface CommentService {
	boolean writeComment(CommentDto commentDto) throws Exception;

	List<CommentDto> listComment(int contentId) throws Exception;

	int getTotalCommentCount(int contentId) throws Exception;

	boolean modifyComment(CommentDto commentDto) throws Exception;

	boolean deleteComment(int commentId) throws Exception;
}
