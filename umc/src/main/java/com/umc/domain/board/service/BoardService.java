package com.umc.domain.board.service;

import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private BoardResponseDTO convertToResponseDto(Board board) {
        BoardResponseDTO boardResponseDto = new BoardResponseDTO();
        boardResponseDto.setBoard_id(board.getId());
        boardResponseDto.setName(board.getName());
        return boardResponseDto;
    }
}
