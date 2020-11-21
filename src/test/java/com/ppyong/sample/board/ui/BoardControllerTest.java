package com.ppyong.sample.board.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppyong.sample.board.domain.Board;
import com.ppyong.sample.board.domain.BoardAppService;
import com.ppyong.sample.global.constants.Const;
import com.ppyong.sample.global.constants.Profile;
import com.ppyong.sample.global.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ReflectionUtils;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@ActiveProfiles(Profile.TEST)
@TestMethodOrder(MethodOrderer.MethodName.class)
@WebMvcTest(value = BoardController.class)
class BoardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardAppService boardAppService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("게시글 등록 성공 테스트")
    void test001_createSuccessTest() throws Exception {
        CreateReq req = CreateReq.builder()
                .title("등록 테스트 제목")
                .content("내용")
                .build();

        Board board = ConverterUtil.map(req, Board.class);

        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Board.class, "createDt"));
        ReflectionUtils.setField(ReflectionUtils.findField(Board.class, "createDt"), board, LocalDateTime.now());
        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Board.class, "createBy"));
        ReflectionUtils.setField(ReflectionUtils.findField(Board.class, "createBy"), board, "temp");

        //given
        doNothing().when(boardAppService).create(any(CreateReq.class));

        //when, then
        mockMvc.perform(
                    post(Const.API_PREFIX + "/boards")
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("등록 테스트 제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.creator").value("user1"))
                .andExpect(jsonPath("$.createDt").exists())
                .andDo(print())
        ;
    }

    @Test
    @DisplayName("게시글 수정 성공 테스트")
    void test002_updateSuccessTest() throws Exception {
        UpdateReq req = UpdateReq.builder()
                .title("수정 테스트 제목")
                .content("내용")
                .build();

        Board board = ConverterUtil.map(req, Board.class);

        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Board.class, "createDt"));
        ReflectionUtils.setField(ReflectionUtils.findField(Board.class, "createDt"), board, LocalDateTime.now());
        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Board.class, "updateDt"));
        ReflectionUtils.setField(ReflectionUtils.findField(Board.class, "updateDt"), board, LocalDateTime.now());
        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Board.class, "createBy"));
        ReflectionUtils.setField(ReflectionUtils.findField(Board.class, "createBy"), board, "user1");
        ReflectionUtils.makeAccessible(ReflectionUtils.findField(Board.class, "updateBy"));
        ReflectionUtils.setField(ReflectionUtils.findField(Board.class, "updateBy"), board, "updater");

        //given
        doNothing().when(boardAppService).update(any(Long.class), any(UpdateReq.class));

        //when, then
        mockMvc.perform(
                    put(Const.API_PREFIX + "/boards/{boardId}", 1l)
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("수정 테스트 제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.createBy").value("user1"))
                .andExpect(jsonPath("$.createDt").exists())
                .andExpect(jsonPath("$.updateBy").value("updater"))
                .andExpect(jsonPath("$.updateDt").exists())
                .andDo(print())
        ;
    }

    @Test
    @DisplayName("게시글 삭제 성공 테스트")
    void test003_deleteSuccessTest() throws Exception {
        //given
        doNothing().when(boardAppService).delete(any(Long.class));

        //when, then
        mockMvc.perform(
                    delete(Const.API_PREFIX + "/boards/{boardId}", 1l)
                )
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }
}