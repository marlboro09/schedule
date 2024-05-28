package com.sparta.schedule.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "댓글 관리", description = "댓글을 등록, 수정, 삭제하실 수 있습니다")
@RestController
@RequestMapping("/api")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/comment")
	public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
		return commentService.createComment(requestDto);
	}
}
