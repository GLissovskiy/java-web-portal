package info.javalab.customer;

public record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age
){
}
