package com.a.sncf.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.a.sncf.common.exception.BusinessException;
import com.a.sncf.common.exception.BusinessExceptionEnum;
import com.a.sncf.common.util.SnowUtil;
import com.a.sncf.member.domain.Member;
import com.a.sncf.member.domain.MemberExample;
import com.a.sncf.member.mapper.MemberMapper;
import com.a.sncf.member.request.MemberRegisterRequest;
import com.a.sncf.member.request.MemberSendCodeRequest;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

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
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeRequest memberSendCodeRequest) {
        String mobile = memberSendCodeRequest.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> memberList = memberMapper.selectByExample(memberExample);

        if(CollUtil.isEmpty(memberList)){
            LOG.info("Member with mobile {} does not exist, creating new member", mobile);
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("Member with mobile {} already exists", mobile);
        }

        //String code = RandomUtil.randomString(6);
        String code = "888888"; // Generate a 6-digit numeric code
        LOG.info("Sending verification code {} to mobile {}", code, mobile);
        //保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间等
        LOG.info("Saving SMS record for mobile {} with code {}", mobile, code);
        //对接短信通道，发送短信
    }
}
