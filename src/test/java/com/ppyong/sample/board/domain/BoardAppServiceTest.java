package com.ppyong.sample.board.domain;

import com.ppyong.sample.board.infra.BoardRepository;
import com.ppyong.sample.board.ui.CreateReq;
import com.ppyong.sample.board.ui.UpdateReq;
import com.ppyong.sample.global.constants.Profile;
import com.ppyong.sample.global.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ActiveProfiles(Profile.TEST)
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(classes = {BoardRepository.class})
public class BoardAppServiceTest {
    @InjectMocks
    private BoardAppService boardAppService;

    @Mock
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 생성 성공 테스트")
    void test001_createSuccessTest(){
        CreateReq req = CreateReq.builder()
                .title("제목 등록 테스트")
                .content("컨텐츠 등록 테스트")
                .build();

        //given
        when(boardRepository.save(any(Board.class))).thenReturn(ConverterUtil.map(req, Board.class));

        //when
        boardAppService.create(req);

        //then
        verify(boardRepository).save(any(Board.class));
    }

    @Test
    @DisplayName("게시글 수정 성공 테스트")
    void test002_updateSuccessTest(){
        long boardId = 1l;

        UpdateReq req = UpdateReq.builder()
                .title("제목 수정 테스트")
                .content("컨텐츠 수정 테스트")
                .build();

        Board board = ConverterUtil.map(req, Board.class);

        //given
        when(boardRepository.findById(boardId)).thenReturn(Optional.ofNullable(board));
        when(boardRepository.save(any(Board.class))).thenReturn(board);

        //when
        boardAppService.update(boardId, req);

        //then
        verify(boardRepository).save(any(Board.class));
    }

    @Test
    @DisplayName("게시글 삭제 성공 테스트")
    void test003_deleteSuccessTest(){
        long boardId = 1l;

        Board board = new Board("제목", "내용");

        //given
        when(boardRepository.findById(boardId)).thenReturn(Optional.ofNullable(board));

        //when
        boardAppService.delete(boardId);

        //then
        verify(boardRepository).delete(board);
    }
}
