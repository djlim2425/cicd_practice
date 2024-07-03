package com.umc.domain.board.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardController {

    private final BoardService boardService;

    @CrossOrigin
    @Operation(summary = "모든 게시판 조회 API")
    @GetMapping("/")
    public ApiResponse<List<BoardResponseDTO>> getALLBoards() {
        List<BoardResponseDTO> boards = boardService.getAllBoards();
        return ApiResponse.onSuccess(boards);
    }
}
