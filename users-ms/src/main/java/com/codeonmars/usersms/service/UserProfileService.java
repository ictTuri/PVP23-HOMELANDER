package com.codeonmars.usersms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.usersms.remote.FilesApi;
import com.codeonmars.usersms.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserProfileService {

    private final UserRepository userRepository;
    private final FilesApi filesApi;

    public UserProfileService(UserRepository userRepository, FilesApi filesApi) {
        this.userRepository = userRepository;
        this.filesApi = filesApi;
    }

    public void updateProfilePic(String currentUUID, String newUUID) {
        var context = UserContextHolder.getContext();
        if (context.isPresent()) {
            var userContext = context.get();
            var user = userRepository.findByUsernameAndEmail(userContext.getUsername(), userContext.getEmail());
            user.ifPresent(u -> u.setProfilePicUUID(newUUID));
//            if(currentUUID != null){
//                filesApi.unlinkAndLinkFile(currentUUID, newUUID);
//            } else {
//                filesApi.linkFile(newUUID);
//            }
            user.ifPresent(userRepository::save);
        }
    }
}
