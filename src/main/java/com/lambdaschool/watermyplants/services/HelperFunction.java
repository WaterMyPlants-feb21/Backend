package com.lambdaschool.watermyplants.services;

import com.lambdaschool.watermyplants.models.ValidationError;

import java.util.List;

public interface HelperFunction
{
    List<ValidationError> getConstraintViolation(Throwable cause);
}
