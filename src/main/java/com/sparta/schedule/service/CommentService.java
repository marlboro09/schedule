package com.sparta.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.requestdto.CommentRequestDto;
import com.sparta.schedule.dto.requestdto.CommentUpdateRequestDto;
import com.sparta.schedule.dto.responsedto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.exception.NotFoundException;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	public CommentResponseDto addComment(CommentRequestDto requestDto, User user) {
		Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
			.orElseThrow(() -> new NotFoundException("스케줄을 찾을 수 없습니다."));

		Comment comment = new Comment(requestDto.getDescription(), schedule, user);

		try {
			Comment savedComment = commentRepository.save(comment);
			return new CommentResponseDto(savedComment);
		} catch (Exception e) {
			log.error("댓글 저장 중 오류 발생: ", e);
			throw new RuntimeException("댓글 저장에 실패했습니다.");
		}
	}

	public CommentResponseDto updateComment(Long commentId, User user, CommentUpdateRequestDto requestDto) {
		log.debug("댓글 수정 중: ID: {}", commentId);
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new NotFoundException("댓글을 찾지 못했습니다."));

		if (!comment.getUser().getId().equals(user.getId()) && !user.isAdmin()) {
			log.error("댓글을 수정할 권한이 없습니다.", user.getUsername(), commentId);
			throw new RuntimeException("댓글 수정 권한이 없습니다.");
		}

		comment.setDescription(requestDto.getDescription());
		try {
			Comment updatedComment = commentRepository.save(comment);
			return new CommentResponseDto(updatedComment);
		} catch (Exception e) {
			log.error("댓글 수정 중 오류 발생: ", e);
			throw new RuntimeException("댓글 수정에 실패했습니다.");
		}
	}

	public void deleteComment(Long commentId, User user) {
		log.debug("댓글 삭제 중: ID: {}", commentId);
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new NotFoundException("댓글을 찾지 못했습니다."));

		if (!comment.getUser().getId().equals(user.getId()) && !user.isAdmin()) {
			log.error("댓글을 삭제할 권한이 없습니다.", user.getUsername(), commentId);
			throw new RuntimeException("댓글 삭제 권한이 없습니다.");
		}

		try {
			commentRepository.delete(comment);
		} catch (Exception e) {
			log.error("댓글 삭제 중 오류 발생: ", e);
			throw new RuntimeException("댓글 삭제에 실패했습니다.");
		}
	}
}