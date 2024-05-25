package com.sparta.schedule.service;

import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;

@Service
public class CommentService {
	private final CommentRepository commentRepository;
	private final ScheduleRepository scheduleRepository;

	public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
		this.scheduleRepository = scheduleRepository;
	}

	/**
	 * 댓들 등록
	 */
	public CommentResponseDto createComment(CommentRequestDto requestDto) {
		Comment comment = new Comment(requestDto, scheduleRepository);
		Comment saveComment = commentRepository.save(comment);
		return new CommentResponseDto(saveComment);
	}
}
