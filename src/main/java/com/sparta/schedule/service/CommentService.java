package com.sparta.schedule.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.requestdto.CommentRequestDto;
import com.sparta.schedule.dto.requestdto.CommentUpdateRequestDto;
import com.sparta.schedule.dto.responsedto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	public CommentResponseDto addComment(CommentRequestDto requestDto) {
		Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
			.orElseThrow(() -> new RuntimeException("Schedule not found"));

		Comment comment = new Comment();
		comment.setContent(requestDto.getContent());
		comment.setUserId(requestDto.getUserId());
		comment.setCreatedDate(LocalDateTime.now());
		comment.setSchedule(schedule);

		Comment savedComment = commentRepository.save(comment);

		CommentResponseDto responseDto = new CommentResponseDto();
		responseDto.setId(savedComment.getId());
		responseDto.setContent(savedComment.getContent());
		responseDto.setUserId(savedComment.getUserId());
		responseDto.setScheduleId(savedComment.getSchedule().getId());
		responseDto.setCreatedDate(savedComment.getCreatedDate());

		return responseDto;
	}

	public CommentResponseDto updateComment(Long commentId, String currentUser, CommentUpdateRequestDto requestDto) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));

		if (!comment.getUserId().equals(currentUser)) {
			throw new RuntimeException("User not authorized to update this comment");
		}

		comment.setContent(requestDto.getContent());
		Comment updatedComment = commentRepository.save(comment);

		CommentResponseDto responseDto = new CommentResponseDto();
		responseDto.setId(updatedComment.getId());
		responseDto.setContent(updatedComment.getContent());
		responseDto.setUserId(updatedComment.getUserId());
		responseDto.setScheduleId(updatedComment.getSchedule().getId());
		responseDto.setCreatedDate(updatedComment.getCreatedDate());

		return responseDto;
	}

	public void deleteComment(Long commentId, String currentUser) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));

		if (!comment.getUserId().equals(currentUser)) {
			throw new RuntimeException("User not authorized to delete this comment");
		}

		commentRepository.delete(comment);
	}
}