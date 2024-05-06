package note.mvc.note.mvc.controller.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import note.mvc.note.mvc.controller.requset.CreateNoteRequest;
import note.mvc.note.mvc.controller.requset.UpdateNoteRequest;
import note.mvc.note.mvc.controller.response.NoteResponse;
import note.mvc.note.mvc.data.entity.Note;
import note.mvc.note.mvc.service.dto.NoteDto;
import note.mvc.note.mvc.service.mapper.NoteMapper;
import note.mvc.note.mvc.service.service.NoteService;
import note.mvc.note.mvc.service.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @GetMapping("/list")
    public ResponseEntity<List<NoteResponse>> noteList() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteMapper.toNoteResponses(noteService.listAll()));
    }

    @GetMapping("/edit")
    public ResponseEntity<NoteResponse> getNoteById(@RequestParam("id") UUID id) throws NoteNotFoundException {
        NoteDto noteDto = noteService.getById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(noteMapper.toNoteResponse(noteDto));
    }

    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(@Valid @NotNull @RequestBody CreateNoteRequest request) {
        NoteDto newNote = noteService.add(noteMapper.toNoteDto(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteMapper.toNoteResponse(newNote));
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public void updateNote(
            @RequestBody @Valid @NotNull UpdateNoteRequest request) throws NoteNotFoundException {
        noteService.update(noteMapper.toNoteDto(request.getId(), request));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNoteById(@RequestBody UpdateNoteRequest request) throws NoteNotFoundException {
        noteService.deleteById(request.getId());
    }
}