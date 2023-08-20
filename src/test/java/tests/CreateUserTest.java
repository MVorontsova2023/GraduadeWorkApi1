package tests;

import dto.ValidUserRequest;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Creation of a new user")
public class CreateUserTest extends BaseTest{
    String endpoint = "/users";
    String userEmail = "example@gmail.com";
    @Test
    @DisplayName("Successful creation of a new user")
    public void successfulCreateUser() {
        userEmail = "example@gmail.com";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(userEmail)
                .full_name("SFGGJSH")
                .password("123456")
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 201, requestBody);
    }
    @Test
    @DisplayName("Create new user with empty full name")
    public void createUserWithoutFullName() {
        userEmail = "example@gmail.com";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(userEmail)
                .full_name("")
                .password("123456")
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 404, requestBody);
    }
    @Test
    @DisplayName("Create new user with empty password")
    public void createUserWithoutPassword() {
        userEmail = "example@gmail.com";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(userEmail)
                .full_name("SFGGJSH")
                .password("")
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 400, requestBody);
    }
    @Test
    @DisplayName("Create new user with empty email")
    public void createUserWithoutEmail() {
        userEmail = "";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(userEmail)
                .full_name("SFGGJSH")
                .password("123456")
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 400, requestBody);
    }

    @Test
    @DisplayName("Create new user without at")
    public void createUserWithInvalidInEmailWithout() {
        userEmail = "exampleexample.com";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(userEmail)
                .full_name("SFGGJSH")
                .password("123456")
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 404, requestBody);
    }
    @Test
    @DisplayName("Create new user without sings after at ")
    public void createUserWithInvalidInEmail() {
        userEmail = "example@";
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(userEmail)
                .full_name("SFGGJSH")
                .password("123456")
                .generate_magic_link(false)
                .build();
        postRequest(endpoint, 404, requestBody);
    }
        @Test
        @DisplayName("Create new user with long size email ")
        public void createUserWithInvalidInLongEmail() {
            userEmail = "exampleandexampleandexampleandexampleandexampleandexample@example.com";
            ValidUserRequest requestBody = ValidUserRequest.builder()
                    .email(userEmail)
                    .full_name("SFGGJSH")
                    .password("123456")
                    .generate_magic_link(false)
                    .build();
            postRequest(endpoint, 404, requestBody);
    }

    @AfterEach
    @DisplayName("Delete each user created in tests")
    public void deleteUserAfterEach() {
        if (userEmail != "") {
            deleteRequest(endpoint + "/" + userEmail, 200);
        }
    }
}
