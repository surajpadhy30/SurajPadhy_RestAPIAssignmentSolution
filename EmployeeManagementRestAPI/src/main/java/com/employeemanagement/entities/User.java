package com.employeemanagement.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="User1")
public class User {

		@Id
	    private String userName;
	    private String password;
	    
	    @Override
		public String toString() {
			return "User [userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
		}

		@OneToOne
	    @JoinColumn(name = "id")
	    private Roles roles;
	 
	    public User() {
	  
	    }

		public User(String userName, String password, Roles roles) {
			super();
			this.userName = userName;
			this.password = password;
			this.roles = roles;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Roles getRoles() {
			return roles;
		}

		public void setRoles(Roles roles) {
			this.roles = roles;
		}
		
}
