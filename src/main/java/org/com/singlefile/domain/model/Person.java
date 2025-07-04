package org.com.singlefile.domain.model;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.com.singlefile.domain.model.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public record Person(@Id String id,
                     @NotBlank(message = "{validation.notEmpty}") @Pattern(regexp = "^[a-zA-Z]*$") String name,
                     @Pattern(regexp = "[a-zA-Z]") String middleName,
                     @Nonnull @Pattern(regexp = "[a-zA-Z]") String paternalName,
                     @Pattern(regexp = "[a-zA-Z]") String maternalName,
                     List<String> phones, Gender gender,
                     @Email String mail) {

    Person(String name, String paternalName, List<String> phones, Gender gender, String mail) {
        this(null, name, null, paternalName, null, phones, gender, mail);
    }
}
