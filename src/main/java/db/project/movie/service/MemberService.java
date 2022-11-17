package db.project.movie.service;

import db.project.movie.constant.Role;
import db.project.movie.dto.MemberDto;
import db.project.movie.entity.MemberEntity;
import db.project.movie.entity.MovieEntity;
import db.project.movie.entity.ReviewEntity;
import db.project.movie.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberEntity create(MemberDto memberDto){
        MemberEntity memberEntity = memberDto.toEntity();
        validateDuplicateMember(memberEntity);
        memberEntity.setRole(Role.ADMIN);
        return memberRepository.save(memberEntity);
    }

    private void validateDuplicateMember(MemberEntity memberEntity){
        MemberEntity findMember = memberRepository.findByEmail(memberEntity.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다."); // 예외 처리
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if(memberEntity == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .roles(memberEntity.getRole().toString())
                .build();
    }
}