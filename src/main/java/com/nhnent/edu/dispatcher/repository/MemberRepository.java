package com.nhnent.edu.dispatcher.repository;

import com.nhnent.edu.dispatcher.model.Member;

import java.util.List;

public interface MemberRepository {
    Member exists(String id, String password);

    List<Member> list();

}
