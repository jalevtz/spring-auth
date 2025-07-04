package org.com.singlefile.service.auth;

import org.com.singlefile.config.JwtTokenProvider;
import org.com.singlefile.domain.model.auth.MyUser;
import org.com.singlefile.domain.model.auth.SignInDTO;
import org.com.singlefile.domain.model.auth.SignUpDTO;
import org.com.singlefile.domain.model.auth.TokenDTO;
import org.com.singlefile.repository.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public UserServiceImpl(org.com.singlefile.repository.auth.UserRepository userRepository,
                           PasswordEncoder passwordEncoder, JwtTokenProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Mono<MyUser> signUp(SignUpDTO dto) {

        Mono<Boolean> userExists = userRepository.findByEmail(dto.email()).hasElement();
        return userExists
                .flatMap(exists -> exists ?
                        Mono.error(new Throwable("email already in use"))
                        : userRepository.save(new MyUser(dto.name(), dto.lastName(), dto.email(),
                        passwordEncoder.encode(dto.password()), true, "USER")));
    }

    @Override
    public Mono<TokenDTO> login(SignInDTO dto) {
        return  userRepository.findByEmail(dto.email())
                .filter(userDocument -> passwordEncoder.matches(dto.password(), userDocument.getPassword()))
                .map(userDocument -> new TokenDTO(jwtProvider.generateToken(userDocument)))
                .switchIfEmpty(Mono.error(new Throwable("bad credentials")));
    }
}
