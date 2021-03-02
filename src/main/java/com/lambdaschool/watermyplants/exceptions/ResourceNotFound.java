package com.lambdaschool.watermyplants.exceptions;

public class ResourceNotFound extends RuntimeException
{
    public ResourceNotFound(String message)
    {
        super("Error in waterplants " + message);
    }
}
