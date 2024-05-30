package com.sparta.schedule.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.requestdto.CommentRequestDto;
import com.sparta.schedule.dto.requestdto.CommentUpdateRequestDto;
import com.sparta.schedule.dto.responsedto.CommentResponseDto;
import com.sparta.schedule.security.UserDetailsImpl;
import com.sparta.schedule.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "댓글 관리", description = "댓글 관리 API입니다")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/comment")
	public ResponseEntity<CommentResponseDto> addComment(@Valid @RequestBody CommentRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		CommentResponseDto responseDto = commentService.addComment(requestDto, userDetails.getUser());
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/comment/{id}")
	public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
		@Valid @RequestBody CommentUpdateRequestDto requestDto,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		CommentResponseDto responseDto = commentService.updateComment(id, userDetails.getUser(), requestDto);
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/comment/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {
		commentService.deleteComment(id, userDetails.getUser());
		return ResponseEntity.noContent().build();
	}
}