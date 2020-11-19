package com.ppyong.test.board.ui;

import com.ppyong.test.board.command.BoardCreateCommand;
import com.ppyong.test.board.command.BoardSearchCommand;
import com.ppyong.test.board.command.BoardUpdateCommand;
import com.ppyong.test.board.domain.BoardAppService;
import com.ppyong.test.board.network.CreateReq;
import com.ppyong.test.board.network.SearchReq;
import com.ppyong.test.board.network.SearchRes;
import com.ppyong.test.board.network.UpdateReq;
import com.ppyong.test.global.constants.Const;
import com.ppyong.test.global.utils.ConverterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping(path = Const.API_PREFIX)
@RequiredArgsConstructor
public class BoardController {
    private final BoardAppService boardAppService;

    @GetMapping("/boards")
    public ResponseEntity<?> search(@RequestBody SearchReq req, Pageable pageable){
        log.debug(">> data: {}", req);

        BoardSearchCommand command = ConverterUtil.map(req, BoardSearchCommand.class);
        return ResponseEntity.ok(boardAppService.search(command, pageable));
    }

    @GetMapping("/boards/{boardId")
    public ResponseEntity<?> search(@PathVariable Long boardId){
        log.debug(">> id: {}", boardId);

        return ResponseEntity.ok(boardAppService.find(boardId));
    }

    @PostMapping("/boards")
    public ResponseEntity<?> create(@RequestBody CreateReq req){
        log.debug(">> data: {}", req);

        BoardCreateCommand command = ConverterUtil.map(req, BoardCreateCommand.class);
        return ResponseEntity.ok(boardAppService.create(command));
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<?> update(@PathVariable Long boardId, @RequestBody UpdateReq req){
        log.debug(">> id: {}, data: {}", boardId, req);

        BoardUpdateCommand command = ConverterUtil.map(req, BoardUpdateCommand.class);
        return ResponseEntity.ok(boardAppService.update(boardId, command));
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<?> delete(@PathVariable Long boardId){
        log.debug(">> id: {}", boardId);

        boardAppService.delete(boardId);
        return ResponseEntity.ok().build();
    }
}
