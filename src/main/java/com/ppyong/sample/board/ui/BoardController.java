package com.ppyong.sample.board.ui;

import com.ppyong.sample.board.domain.BoardAppService;
import com.ppyong.sample.global.constants.Const;
import com.ppyong.sample.global.utils.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = Const.API_PREFIX)
@RequiredArgsConstructor
public class BoardController {
    private final BoardAppService boardAppService;

    @GetMapping("/boards")
    public ResponseEntity<?> search(@ModelAttribute SearchReq req, Pageable pageable){
        return ResponseEntity.ok(boardAppService.search(req, pageable));
    }

    @GetMapping("/boards/{boardId")
    public ResponseEntity<?> find(@PathVariable Long boardId){
        return CommonResponseUtil.ok(boardAppService.find(boardId));
    }

    @PostMapping("/boards")
    public ResponseEntity<?> create(@RequestBody @Valid CreateReq req){
        boardAppService.create(req);
        return CommonResponseUtil.create();
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<?> update(@PathVariable Long boardId, @RequestBody @Valid UpdateReq req){
        boardAppService.update(boardId, req);
        return CommonResponseUtil.ok();
    }

    @PatchMapping("/boards/{boardId}")
    public ResponseEntity<?> patch(@PathVariable Long boardId, @RequestBody @Valid PatchReq req){
        boardAppService.patch(boardId, req);
        return CommonResponseUtil.ok();
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<?> delete(@PathVariable Long boardId){
        boardAppService.delete(boardId);
        return CommonResponseUtil.ok();
    }
}
