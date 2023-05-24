package com.ssafy.enjoytrip.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.CommentDto;

@Mapper
public interface CommentMapper {
	int writeComment(CommentDto commentDto) throws SQLException;

	List<CommentDto> listComment(int contentId) throws SQLException;

	int getTotalCommentCount(int contentId) throws SQLException;

	int modifyComment(CommentDto commentDto) throws SQLException;

	int deleteComment(int commentId) throws SQLException;
}
