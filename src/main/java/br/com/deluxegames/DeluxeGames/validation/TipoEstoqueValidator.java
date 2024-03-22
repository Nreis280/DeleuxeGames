package br.com.deluxegames.DeluxeGames.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TipoEstoqueValidator implements ConstraintValidator<TipoEstoque, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("ENTRADA") || value.equals("SAIDA");
    }

}