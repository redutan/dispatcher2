package com.nhnent.edu.dispatcher.repository;

import com.nhnent.edu.dispatcher.model.Member;
import com.nhnent.edu.dispatcher.model.MemberType;

import java.util.List;

public interface MemberRepository {
    Member exists(String id, String password);

    List<Member> list();

    // TODO: 3. MemberType에 맞는 멤버 목록 가져오는 메쏘드 추가
    List<Member> list(MemberType memberType);

}
