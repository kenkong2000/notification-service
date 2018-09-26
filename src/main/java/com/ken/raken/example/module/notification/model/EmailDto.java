package com.ken.raken.example.module.notification.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

//import com.ken.raken.example.module.notification.validation.EmailBeanConstrain;

//@EmailBeanConstrain
public class EmailDto {
	
	@NotEmpty(message = "empty email")
	@Email(message = "invalid email")
	private String email;
	
	

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
