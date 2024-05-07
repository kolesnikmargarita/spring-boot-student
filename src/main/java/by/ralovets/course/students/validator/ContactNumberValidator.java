package by.ralovets.course.students.validator;

import by.ralovets.course.students.annotation.ContactNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        return value.matches("[0-9]+")
                && value.length() > 8
                && value.length() < 15;
    }
}
