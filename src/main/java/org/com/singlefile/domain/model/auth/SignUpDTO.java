package org.com.singlefile.domain.model.auth;

public record SignUpDTO (String name,
                         String lastName,
                         String email,
                         String password){
}
