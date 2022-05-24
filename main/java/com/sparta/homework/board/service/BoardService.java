package com.sparta.homework.board.service;

import com.sparta.homework.board.models.Board;
import com.sparta.homework.board.models.BoardRepository;
import com.sparta.homework.board.models.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        board.update(requestDto);
        return board.getId();
    }
    public Board boardView(Long id) {

        return boardRepository.findById(id).get();
    }
}
