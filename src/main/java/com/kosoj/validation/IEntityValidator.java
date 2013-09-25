package com.kosoj.validation;

/**
 * User: machain
 * Date: 26.8.13
 * Time: 11:20
 */
public interface IEntityValidator {

	<E> ValidationResult<E> validate(E entity);

	<E> ValidationResult<E> validate(E entity, String rule);

}
