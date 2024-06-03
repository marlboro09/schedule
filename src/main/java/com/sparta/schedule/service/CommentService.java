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
import com.sparta.schedule.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public CommentResponseDto addComment(CommentRequestDto requestDto, User user) {
		Schedule schedule = scheduleService.getScheduleById(requestDto.getScheduleId());
		User persistentUser = userRepository.findById(user.getId())
			.orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

		Comment comment = new Comment(requestDto.getDescription(), schedule, persistentUser);

		try {
			Comment savedComment = commentRepository.save(comment);
			log.info("댓글 저장: {}", savedComment);
			return new CommentResponseDto(savedComment);
		} catch (Exception e) {
			log.error("댓글 저장 중 오류 발생: ", e);
			throw new RuntimeException("댓글 저장에 실패했습니다.");
		}
	}

	@Transactional
	public CommentResponseDto updateComment(Long commentId, User user, CommentUpdateRequestDto requestDto) {
		log.debug("댓글 수정 중: ID: {}", commentId);
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new NotFoundException("댓글을 찾지 못했습니다."));

		if (isNotAuthorizedUser(comment, user)) {
			throw new RuntimeException("댓글 수정 권한이 없습니다.");
		}

		comment.setDescription(requestDto.getDescription());
		try {
			Comment updatedComment = commentRepository.save(comment);
			log.info("댓글 수정: {}", updatedComment);
			return new CommentResponseDto(updatedComment);
		} catch (Exception e) {
			log.error("댓글 수정 중 오류 발생: ", e);
			throw new RuntimeException("댓글 수정에 실패했습니다.");
		}
	}

	@Transactional
	public void deleteComment(Long commentId, User user) {
		log.debug("댓글 삭제 중: ID: {}", commentId);
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new NotFoundException("댓글을 찾지 못했습니다."));

		if (isNotAuthorizedUser(comment, user)) {
			throw new RuntimeException("댓글 삭제 권한이 없습니다.");
		}

		try {
			commentRepository.delete(comment);
			log.info("댓글 삭제: {}", comment);
		} catch (Exception e) {
			log.error("댓글 삭제 중 오류 발생: ", e);
			throw new RuntimeException("댓글 삭제에 실패했습니다.");
		}
	}

	private boolean isNotAuthorizedUser(Comment comment, User user) {
		User persistentUser = userRepository.findById(user.getId())
			.orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));
		return !comment.getUser().equals(persistentUser) && !persistentUser.isAdmin();
	}
}
