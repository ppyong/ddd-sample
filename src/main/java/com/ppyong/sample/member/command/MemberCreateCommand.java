package com.ppyong.sample.member.command;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCreateCommand {
    private String loginId;

    private String name;
}
