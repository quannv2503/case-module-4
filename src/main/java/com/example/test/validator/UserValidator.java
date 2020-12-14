package com.example.test.validator;

import com.example.test.model.Admin;
import com.example.test.service.login.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
    @Autowired
    private AdminService adminSevice;

    @Override
    public boolean supports(Class<?> aClass) {
        return Admin.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Admin admin = (Admin) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (admin.getUsername().length() < 6 || admin.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (this.adminSevice.findByUsername(admin.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (admin.getPassword().length() < 8 || admin.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!admin.getPasswordConfirm().equals(admin.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}