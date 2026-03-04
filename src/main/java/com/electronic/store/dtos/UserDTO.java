package com.electronic.store.dtos;

import com.electronic.store.customAnnotations.ImageValidCustomAnnotation;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO

{

    private Integer userId;
    @Size(min = 3,max = 15 ,message = "Invalid name and name should be of min 3 character and max 15 character")
    private String userName;
   /* @Email(message = "Invalid user email")
    @NotBlank*/
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{3}$" , message = "Invalid email")
    private String userEmail;
    @NotBlank(message = "Password you need to enter ,It cannot be null")
    private String userPassword;
    @Size(min = 4,max = 6, message = "Invalid Gender,You can enter Male or Female as Gender")
    private String userGender;
    @NotBlank(message = "Need to write something ,It cannot be null")
    private String userAbout;
   @ImageValidCustomAnnotation
    private String userImageName;
/*    @Pattern
    Custom Validator*/

}
