package com.ssafy.enjoytrip.planboard.model;

public class PlanBoardDto {
	private int articleNo;
	private String userId;
	private String title;
	private String order;
	private String content;
	private int hit;
	private String registerTime;
	
	
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlanBoardDto [articleNo=").append(articleNo).append(", userId=").append(userId)
				.append(", title=").append(title).append(", order=").append(order).append(", content=").append(content)
				.append(", hit=").append(hit).append(", registerTime=").append(registerTime).append("]");
		return builder.toString();
	}
	
	
}
