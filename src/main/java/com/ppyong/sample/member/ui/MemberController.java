package com.ppyong.sample.member.ui;

import com.ppyong.sample.global.constants.Const;
import com.ppyong.sample.global.utils.CommonResponseUtil;
import com.ppyong.sample.member.domain.MemberAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = Const.API_PREFIX)
@RequiredArgsConstructor
public class MemberController {
    private final MemberAppService memberAppService;

    @GetMapping("/members")
    public ResponseEntity<?> search(@ModelAttribute @Valid SearchReq req) {
        return CommonResponseUtil.ok(memberAppService.search(req));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> find(@PathVariable Long memberId) {
        return CommonResponseUtil.ok(memberAppService.find(memberId));
    }

    @PostMapping("/members")
    public ResponseEntity<?> create(@RequestBody @Valid CreateReq req) {
        memberAppService.create(req);
        return CommonResponseUtil.create();
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<?> update(@PathVariable Long memberId, @RequestBody @Valid UpdateReq req) {
        memberAppService.update(memberId, req);
        return CommonResponseUtil.ok();
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<?> patch(@PathVariable Long memberId, @RequestBody @Valid PatchReq req) {
        memberAppService.patch(memberId, req);
        return CommonResponseUtil.ok();
    }
}
