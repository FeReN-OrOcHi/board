package me.ferrous.controller;

import lombok.extern.slf4j.Slf4j;
import me.ferrous.Board;
import me.ferrous.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    public final BoardRepository boardRepository;

    @Autowired
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("boardList", boardRepository.findAll());
        return "list";
    }

    @GetMapping
    public String board(@RequestParam(defaultValue = "0") Integer index, Model model) {
        Board board = boardRepository.findById(index).orElse(new Board());
        model.addAttribute("board", board);
        return "form";
    }

    @PostMapping
    public ResponseEntity<Board> postBoard(@RequestBody Board board) {
        boardRepository.save(board);
        return ResponseEntity
                .created(URI.create("/list"))
                .body(board);
    }

    @PutMapping("/{index}")
    public ResponseEntity<Board> putBoard(@PathVariable("index") Integer index, @RequestBody Board board) {
        Board updateBoard = boardRepository.findById(index).orElseThrow(IllegalStateException::new);
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());
        updateBoard.setUpdateDate(new Date(System.currentTimeMillis()));
        boardRepository.save(updateBoard);
        return ResponseEntity.ok().body(updateBoard);
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<String> deleteBoard(@PathVariable("index") Integer index) {
        boardRepository.deleteById(index);
        return ResponseEntity.ok().body("{}");
    }


}
