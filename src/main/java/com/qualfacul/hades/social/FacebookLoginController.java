package com.qualfacul.hades.social;

import static com.qualfacul.hades.login.LoginOrigin.FACEBOOK;
import static org.springframework.http.HttpStatus.ACCEPTED;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qualfacul.hades.annotation.Post;
import com.qualfacul.hades.annotation.PublicEndpoint;
import com.qualfacul.hades.exceptions.InvalidFacebookTokenException;
import com.qualfacul.hades.exceptions.LoginFailureException;
import com.qualfacul.hades.login.LoginInfoDTO;

@RestController
public class FacebookLoginController {
	@Autowired
	private SocialService socialService;

	@Autowired
	private FacebookConnectionFactory connectionFactory;

	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookLoginController.class);
	
	@Post("/facebook/login")
	@PublicEndpoint
	@ResponseStatus(ACCEPTED)
	public LoginInfoDTO login(@RequestBody FacebookLoginDTO facebookLoginDTO) {
		Connection<Facebook> connection;
		String accessToken = facebookLoginDTO.getAccessToken();
		try {
			AccessGrant accessGrant = new AccessGrant(accessToken);
			connection = connectionFactory.createConnection(accessGrant);
		} catch (ExpiredAuthorizationException e) {
			LOGGER.info("Facebook Authorization has expired for token: {}", accessToken);
			throw e;
		} catch (InvalidAuthorizationException e) {
			throw new InvalidFacebookTokenException();
		}

		UserProfile userProfile = connection.fetchUserProfile();
		if (userProfile.getEmail() == null) {
			throw new LoginFailureException();
		}

		LoginInfoDTO loginInfoDTO = socialService.authenticate(userProfile, FACEBOOK);
		return loginInfoDTO;
	}
}
