package com.sparta.schedule.dto.responsedto;

import java.time.LocalDateTime;

import com.sparta.schedule.entity.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
	private Long id;

	private String description;

	private String userId;

	private String nickname;

	private Long scheduleId;

	private LocalDateTime createdAt;

	public CommentResponseDto(Comment comment) {
		this.id = comment.getId();
		this.description = comment.getDescription();
		this.userId = comment.getUser().getUsername();
		this.nickname = comment.getUser().getNickname();
		this.scheduleId = comment.getSchedule().getId();
		this.createdAt = comment.getCreatedAt();
	}
}
