package com.sparta.schedule.service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;

public class ScheduleService {
	private final JdbcTemplate jdbcTemplate;

	public ScheduleService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	/**
     * 스케줄 등록
     */
	public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
		Schedule schedule = new Schedule(requestDto);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO schedule (title, contents, name, password, date) VALUES(?,?,?,?,?)";
		jdbcTemplate.update( con -> {
				PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, schedule.getTitle());
				preparedStatement.setString(2, schedule.getContents());
				preparedStatement.setString(3, schedule.getName());
				preparedStatement.setString(4, schedule.getPassword());
				preparedStatement.setString(5, schedule.getDate());
				return preparedStatement;
			},
			keyHolder);

		Long id = keyHolder.getKey().longValue();
		schedule.setId(id);

		ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

		return scheduleResponseDto;
	}

	public List<ScheduleResponseDto> getSchedules() {
		String sql = "SELECT id, title, contents, name, password, date FROM schedule";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Long id = rs.getLong("id");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			String name = rs.getString("name");
			String password = rs.getString("password");
			String date = rs.getString("date");

			return new ScheduleResponseDto(id, title, contents, name, password, date);
		});
	}

	public ScheduleResponseDto checkSchedule(Long id, ScheduleRequestDto requestDto) {
		Schedule schedule = findById(id);
		if (schedule != null) {
			return new ScheduleResponseDto(schedule);
		} else {
			throw new IllegalArgumentException("해당 스케줄은 존재하지 않습니다.");
		}
	}

	public long updateSchedule(Long id, ScheduleRequestDto requestDto) {
		Schedule schedule = findById(id);
		if(schedule != null) {
			String sql = "UPDATE schedule SET title = ?, contents = ?, name = ?, date = ? WHERE id = ?";
			jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getName(), requestDto.getDate(), id);
			return id;
		} else {
			throw new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.");
		}
	}

	public long deleteSchedule(Long id) {
		Schedule schedule = findById(id);
		if(schedule != null){
			String sql = "DELETE FROM schedule WHERE id =?";
			jdbcTemplate.update(sql, id);
			return id;
		} else {
			throw new IllegalArgumentException("해당 스케줄은 존재하지 않습니다.");
		}
	}

	private Schedule findById(Long id){
		String sql = "SELECT * FROM schedule WHERE id = ?";

		return jdbcTemplate.query(sql, resultSet -> {
			if(resultSet.next()) {
				Schedule schedule = new Schedule();
				schedule.setTitle(resultSet.getString("title"));
				schedule.setContents(resultSet.getString("contents"));
				schedule.setName(resultSet.getString("name"));
				schedule.setDate(resultSet.getString("date"));
				return schedule;
			} else {
				return null;
			}
		}, id);
	}


}
