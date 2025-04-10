package com.scm.helpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {

    @Builder.Default
    private MessageType type=MessageType.blue;
    private String content;

}
