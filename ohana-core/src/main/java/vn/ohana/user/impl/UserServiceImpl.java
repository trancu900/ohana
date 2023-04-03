package vn.ohana.user.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ohana.entities.PostMedia;
import vn.ohana.entities.Role;
import vn.ohana.entities.User;
import vn.ohana.entities.UserStatus;
import vn.ohana.google.dto.GooglePojo;
import vn.ohana.post.PostMediaService;
import vn.ohana.user.UserFilterRepository;
import vn.ohana.user.UserRepository;
import vn.ohana.user.UserService;
import vn.ohana.user.dto.*;
import vn.rananu.shared.exceptions.NotFoundException;
import vn.rananu.shared.mappers.BaseMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFilterRepository userFilterRepository;

    @Override
    public UserResult signUp(SignUpParam signUpParam) {
        return null;
    }

    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMediaService postMediaService;


    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category.exception.notFound"));
    }


    public Page<UserResult> getAll(Pageable pageable) {
        Page<User> page = findAll(pageable);
        return toDtoPage(page);
    }
    @Override
    @Transactional
    public UserResult update(UserUpdateParam updateParam) {
        User user = findById(updateParam.getId());
        userMapper.transferFieldsSkipNull(updateParam, user);
        return userMapper.toDTO(user);
    }

    @Override
    public Page<UserResult> filter(UserFilterParam filter, Pageable pageable) {
        Page<User> page = userFilterRepository.findAllByFilters(filter, pageable);
        return toDtoPage(page);
    }

    @Override
    @Transactional
    public void deactivateAllByIds(Long[] ids) {
        for (Long id : ids) {
            userRepository.findById(id)
                    .map(user -> user.setStatus(UserStatus.NOT_ACTIVATED))
                    .orElseThrow(() -> new NotFoundException("user.notFound"));
        }
    }

    @Override
    @Transactional
    public void activateAllByIds(Long[] ids) {
        for (Long id : ids) {
            userRepository.findById(id)
                    .map(user -> user.setStatus(UserStatus.ACTIVATED))
                    .orElseThrow(() -> new NotFoundException("user.notFound"));
        }
    }

    private Page<UserResult> toDtoPage(Page<User> page) {
        return page.map(entity -> userMapper.toDTO(entity));
    }


    @Override
    public LoginResult saveGoogleEmail(GooglePojo googlePojo) {
        User userCheck = userRepository.findByEmail(googlePojo.getEmail());
        if (userCheck != null) {
            return userMapper.toLoginResultDTO(userCheck);
        } else {
            User user = userMapper.toGooglePojo(googlePojo);
            user.setStatus(UserStatus.ACTIVATED);
            user.setRole(Role.USER);
            user.setPassword(googlePojo.getPassword());
            PostMedia postMedia = new PostMedia();
            postMedia.setFileUrl(googlePojo.getPicture());
            postMediaService.save(postMedia);
            user.setThumbnailId(postMedia.getId());
            User userResult = userRepository.save(user);
            return userMapper.toLoginResultDTO(userResult);
        }
    }
}


