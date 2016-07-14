package com.nhnent.edu.dispatcher.repository;

import com.nhnent.edu.dispatcher.model.Member;

import java.util.*;

public class MemberRepositoryImpl implements MemberRepository {
    private static Map<String, Member> memberMap = new HashMap<>();
    static {
        memberMap.put("yankee", Member.newInstance("yankee", "12345"));
        memberMap.put("sunbi" , Member.newInstance("sunbi" , "67890"));
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

}
