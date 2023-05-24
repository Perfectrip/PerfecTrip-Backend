package com.ssafy.enjoytrip.hotplace.model;

public class CommentDto {
	private int commentId;
	private int contentId;
	private String userId;
	private String commentText;
	private String createdTime;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommentDto [commentId=").append(commentId).append(", contentId=").append(contentId)
				.append(", userId=").append(userId).append(", commentText=").append(commentText)
				.append(", createdTime=").append(createdTime).append("]");
		return builder.toString();
	}
}
