package com.sparta.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sparta.schedule.dto.requestdto.CommentRequestDto;
import com.sparta.schedule.dto.requestdto.CommentUpdateRequestDto;
import com.sparta.schedule.dto.responsedto.CommentResponseDto;
import com.sparta.schedule.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "댓글 관리", description = "댓글 관리 API입니다")
@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/comments")
	public ResponseEntity<CommentResponseDto> addComment(@Valid @RequestBody CommentRequestDto requestDto) {
		CommentResponseDto responseDto = commentService.addComment(requestDto);
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id, @RequestAttribute("username") String currentUser, @Valid @RequestBody CommentUpdateRequestDto requestDto) {
		CommentResponseDto responseDto = commentService.updateComment(id, currentUser, requestDto);
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestAttribute("username") String currentUser) {
		commentService.deleteComment(id, currentUser);
		return ResponseEntity.noContent().build();
	}
}