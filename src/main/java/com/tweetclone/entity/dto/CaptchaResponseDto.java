package com.tweetclone.entity.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Ivan Gordeev 10.05.2023
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CaptchaResponseDto {
    private boolean success;
    @JsonAlias("error-codes")
    private Set<String> errorCodes;
}
