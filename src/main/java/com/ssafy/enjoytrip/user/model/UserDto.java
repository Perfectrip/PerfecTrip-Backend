	package com.ssafy.enjoytrip.user.model;
	
	public class UserDto {
		private String id;
		private String password;
		private String email;
		private String name;
		private int age;
	
	
		public String getId() {
			return id;
		}
	
		public void setId(String id) {
			this.id = id;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public int getAge() {
			return age;
		}
	
		public void setAge(int age) {
			this.age = age;
		}
	
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("UserDto [id=").append(id).append(", password=").append(password).append(", email=")
					.append(email).append(", name=").append(name).append(", age=").append(age).append("]");
			return builder.toString();
		}
	
	}
