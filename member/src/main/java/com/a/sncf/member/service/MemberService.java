package com.a.sncf.member.service;

import cn.hutool.core.collection.CollUtil;
import com.a.sncf.common.exception.BusinessException;
import com.a.sncf.common.exception.BusinessExceptionEnum;
import com.a.sncf.common.util.SnowUtil;
import com.a.sncf.member.domain.Member;
import com.a.sncf.member.domain.MemberExample;
import com.a.sncf.member.mapper.MemberMapper;
import com.a.sncf.member.request.MemberRegisterRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public Integer count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public Long register(MemberRegisterRequest memberRegisterRequest)  {
        String mobile = memberRegisterRequest.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> memberList = memberMapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(memberList)){
            //return memberList.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insertSelective(member);
        return member.getId();
    }
}
