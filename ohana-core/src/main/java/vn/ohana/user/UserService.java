package vn.ohana.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import vn.ohana.entities.User;
import vn.ohana.google.dto.GooglePojo;
import vn.ohana.user.dto.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface UserService {
    Page<UserResult> getAll(Pageable pageable);

    UserResult update(UserUpdateParam updateParam);

    Page<UserResult> filter(UserFilterParam filter, Pageable pageable);

//   void deactivateAllByIds(Long[] ids);

    Map<String, List<Long>> modifyStatusByIds(Set<Long> ids, String status);

    @Transactional
    void modifyStatusById(Long id, String statusRaw);

    UserResult getById(Long id);

    UserResult signUpByGoogle(GooglePojo googlePojo);

    UserResult signUp(String url, SignUpParam signUpParam) throws MessagingException, UnsupportedEncodingException;

    boolean existsByPhoneOrEmail(String phoneOrEmail);


    LoginResult findByEmailAndPassword(String email, String password);

    LoginResult findByEmailAndPasswordMapper(String email, String password);

    UserResult findByEmailAndPasswordUserResult(String email, String password);

    boolean existsByPhoneOrEmail(String phone, String email);

    boolean existsByEmail(String email);

    boolean existsByPhoneAndIdNot(String phone, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    UserResult findByEmail(String email);

    UserUpdateParam findByEmailUpdate(String email);

    String findUserPasswordById(Long id);

    UserResult savePassword(UserUpdateParam userUpdateParam);

    User findById(Long userId);

    UserResult save(UserUpdateParam userUpdateParam);

    boolean findByCode(String code);


    void sendMailSignUp(String url, UserResult UserResult) throws MessagingException, UnsupportedEncodingException;

    void forgetPassword(UserResult UserResult) throws MessagingException, UnsupportedEncodingException;

    void sendMailForgetPassword(User user) throws MessagingException, UnsupportedEncodingException;
}
