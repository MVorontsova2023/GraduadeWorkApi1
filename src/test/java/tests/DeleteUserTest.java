package tests;

import dto.ValidUserRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@DisplayName("User deletion Test Class")
public class DeleteUserTest extends BaseTest{

    String endpoint = "/users/";
    String email = "hkkdsddffdsdasjh@gmail.com";
    @Test
    @DisplayName("Success delete user")
    public void successDelete(){
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(email)
                .full_name("SFGGJSH")
                .password("123456")
                .generate_magic_link(false)
                .build();
        Response response = postRequest(endpoint, 201, requestBody);
        deleteRequest(endpoint+email ,200);
    }

    @Test
    @DisplayName("Checking if the user was deleted successfully")
    public void deleteAfterDelete(){
        ValidUserRequest requestBody = ValidUserRequest.builder()
                .email(email)
                .full_name("SFGGJSH")
                .password("123456")
                .generate_magic_link(false)
                .build();
        Response response = postRequest(endpoint, 201, requestBody);
        deleteRequest(endpoint+email ,200);
        deleteRequest(endpoint+email ,404);
    }

}
