package com.nhnent.edu.dispatcher.repository;

import com.nhnent.edu.dispatcher.model.Member;
import com.nhnent.edu.dispatcher.model.MemberType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("memberRepository")
public class MemberRepositoryImpl implements MemberRepository {
    private static Map<String, Member> memberMap = new HashMap<>();
    static {
        memberMap.put("yankee", Member.newInstance("yankee", "12345", MemberType.GUEST));
        memberMap.put("sunbi" , Member.newInstance("sunbi" , "67890", MemberType.MEMBER));
    }


    @Override
    public Member exists(String id, String password) {
        Member member = memberMap.get(id);
        if (member == null) {
            return null;
        }

        if (!Objects.equals(member.getPassword(), password)) {
            return null;
        }

        return member;
    }

    @Override
    public List<Member> list() {
        return new ArrayList<>(memberMap.values());
    }

    // TODO: 4. MemberType에 맞는 멤버 목록 가져오는 메쏘드 구현
    @Override
    public List<Member> list(MemberType memberType) {
        return memberMap.values().stream()
                .filter(member -> member.getType() == memberType)
                .collect(Collectors.toList());
    }

}
