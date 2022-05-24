package com.sparta.homework.board.controller;

import com.sparta.homework.board.models.Board;
import com.sparta.homework.board.models.BoardRepository;
import com.sparta.homework.board.models.BoardRequestDto;
import com.sparta.homework.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @GetMapping("/api/boards")
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }


    @PutMapping("/api/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        boardService.update(id,requestDto);
        return id;
    }

    @DeleteMapping("/api/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) { // @PathVariable은 경로(주소)에 있는 변수를 받을 때 쓴다
        boardRepository.deleteById(id);
        return id;
    }

    @GetMapping("/api/boards/password/{id}")
    public String readPassword(@PathVariable Long id) {
        Board board =boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        return board.getPassword();
    }

}
