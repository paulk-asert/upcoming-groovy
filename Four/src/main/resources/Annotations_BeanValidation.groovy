@Grab('org.hibernate.validator:hibernate-validator:7.0.1.Final')
@Grab('org.hibernate.validator:hibernate-validator-cdi:7.0.1.Final')
@Grab('org.glassfish:jakarta.el:4.0.0')
import jakarta.validation.constraints.*
import jakarta.validation.*
import groovy.transform.*

@Canonical
class Car {
    @NotNull @Size(min = 2, max = 14) String make
    @Min(1L) int seats
    List<@NotBlank String> owners
}

def validator = Validation.buildDefaultValidatorFactory().validator

def violations = validator.validate(new Car(make: 'T', seats: 1))
assert violations*.message == ['size must be between 2 and 14']

violations = validator.validate(new Car(make: 'Tesla', owners: ['']))
assert violations*.message.toSet() == ['must be greater than or equal to 1', 'must not be blank'] as Set

violations = validator.validate(new Car(make: 'Tesla', owners: ['Elon'], seats: 2))
assert !violations
