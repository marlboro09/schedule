package com.sparta.schedule.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.schedule.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {
	private Long scheduleId;

	private Long id;

	private String contents;

	private String name;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime createtime;

	public CommentResponseDto(Comment comment) {
		this.scheduleId = comment.getSchedule().getId();
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.name = comment.getName();
        this.createtime = comment.getSchedule().getTime();
	}
}
