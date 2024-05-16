package com.sparta.schedule.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;

@RestController
@RequestMapping("/api")
public class ScheduleController {
	private final JdbcTemplate jdbcTemplate;

	public ScheduleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 스케줄 등록
	 */
	@PostMapping("/schedule")
	public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto){
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
	/**
	 * 스케줄 조회
	 */
	@GetMapping("/schedule/list")
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


	/**
	 * 스케줄 수정
	 */
	@PutMapping("/schedule/{id}")
	public long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
		Schedule schedule = findById(id);
		if(schedule != null) {
			String sql = "UPDATE schedule SET title = ?, contents = ?, name = ?, date = ? WHERE id = ?";
			jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getName(), requestDto.getDate(), id);
			return id;
        } else {
			throw new IllegalArgumentException("선택한 스케줄은 존재하지 않습니다.");
		}
	}

	/**
     * 스케줄 삭제
     */
	@DeleteMapping("/schedule/{id}")
    public long deleteSchedule(@PathVariable Long id) {
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
