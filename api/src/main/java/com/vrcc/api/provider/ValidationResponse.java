package com.vrcc.api.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidationResponse {

	private final Collection<Violation> constraintViolations = new ArrayList<>();

	private ValidationResponse() {
	}

	public static ValidationResponse from(ConstraintViolationException e) {
		final ValidationResponse response = new ValidationResponse();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			response.addField(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
		}
		return response;
	}

	public ValidationResponse addField(String name, String message) {
		constraintViolations.add(new Violation(name, message));
		return this;
	}

	public Collection<Violation> getConstraintViolations() {
		return Collections.unmodifiableCollection(constraintViolations);
	}

	static class Violation {

		private final String name;
		private final String message;

		public Violation(String name, String message) {
			this.name = name;
			this.message = message;
		}

		public String getName() {
			return name;
		}

		public String getMessage() {
			return message;
		}

	}

}
