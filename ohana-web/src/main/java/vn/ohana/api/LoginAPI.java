package vn.ohana.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.ohana.user.UserService;
import vn.ohana.user.dto.LoginParam;
import vn.ohana.user.dto.LoginResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/login")
public class LoginAPI {

    @Autowired
    UserService userService;

    @GetMapping("/existsByPhoneOrEmail")
    public ResponseEntity<?> existsByPhoneOrEmail(String phoneOrEmail) throws IOException {
        boolean exists = userService.existsByPhoneOrEmail(phoneOrEmail);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> doLogin(@RequestBody LoginParam loginParam, @CookieValue(value = "loginUser", defaultValue = "0") String loginUser, HttpServletResponse response, HttpServletRequest request) {

        LoginResult userResult = userService.login(loginParam.getPhoneOrEmail(), loginParam.getPhoneOrEmail());
        if (userResult != null) {
            if ((loginParam.getPhoneOrEmail().equals(userResult.getEmail()) || loginParam.getPhoneOrEmail().equals(userResult.getPhone())) && loginParam.getPassword().equals(userResult.getPassword())) {

                ResponseCookie responseCookie = ResponseCookie.from("loginUser", userResult.getEmail()).httpOnly(false).secure(false).path("/").maxAge(60 * 1000).domain("localhost").build();

                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body("Đăng nhập thành công");
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/sign-out")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie responseCookie = ResponseCookie.from("loginUser", null).httpOnly(false).secure(false).path("/").maxAge(0).domain("localhost").build();
        return ResponseEntity.ok().location(URI.create("/ohaha")).header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body("Đăng xuất thành công");
    }

}
