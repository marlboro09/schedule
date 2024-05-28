package com.sparta.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.exception.NotFoundException;
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
	 * 댓글 등록
	 */
	public CommentResponseDto createComment(CommentRequestDto requestDto) {
		// 입력 유효성 검사
		if (requestDto.getScheduleId() == null) {
			throw new NotFoundException("스케줄 ID를 입력해주세요.");
		}
		if (requestDto.getContents() == null || requestDto.getContents().isEmpty()) {
			throw new NotFoundException("댓글 내용을 입력해주세요.");
		}
		if (requestDto.getName() == null || requestDto.getName().isEmpty()) {
			throw new NotFoundException("이름을 입력해주세요.");
		}
		Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
			.orElseThrow(() -> new NotFoundException("스케줄이 존재하지 않습니다."));

		Comment comment = new Comment(requestDto, schedule);
		Comment savedComment = commentRepository.save(comment);
		return new CommentResponseDto(savedComment);
	}

	public List<CommentResponseDto> getComment(User user) {
		List<Comment> commentList = commentRepository.findByUser(user);
		List<CommentResponseDto> responseDtoList = new ArrayList<>();

		for (Comment comment : commentList) {
			responseDtoList.add(new CommentResponseDto(comment));
		}
		return responseDtoList;
	}
}
