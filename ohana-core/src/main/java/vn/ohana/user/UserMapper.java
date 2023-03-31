package vn.ohana.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.ohana.entities.PostMedia;
import vn.ohana.entities.User;
import vn.ohana.google.dto.GooglePojo;
import vn.ohana.post.PostMediaService;
import vn.ohana.user.dto.BaseUser;
import vn.ohana.user.dto.UserResult;
import vn.rananu.shared.mappers.BaseMapper;

import java.util.Optional;


@Component
public class UserMapper extends BaseMapper<UserResult, User, BaseUser> {

    public void transferFieldsSkipNull(BaseUser updateParam, User category) {
        modelMapper.map(updateParam,category);
    }
}
