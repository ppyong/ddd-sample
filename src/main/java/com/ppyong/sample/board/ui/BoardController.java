package com.ppyong.sample.board.ui;

import com.ppyong.sample.board.command.BoardCreateCommand;
import com.ppyong.sample.board.command.BoardSearchCommand;
import com.ppyong.sample.board.command.BoardUpdateCommand;
import com.ppyong.sample.board.domain.BoardAppService;
import com.ppyong.sample.board.network.CreateReq;
import com.ppyong.sample.board.network.SearchReq;
import com.ppyong.sample.board.network.UpdateReq;
import com.ppyong.sample.global.constants.Const;
import com.ppyong.sample.global.utils.ConverterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
