package note.mvc.note.mvc.service.service;

import note.mvc.note.mvc.data.entity.Note;
import note.mvc.note.mvc.service.dto.NoteDto;
import note.mvc.note.mvc.service.exception.NoteNotFoundException;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    List<NoteDto> listAll();

    NoteDto add(NoteDto note);

    void deleteById(UUID id) throws NoteNotFoundException;

    void update(NoteDto note) throws NoteNotFoundException;

    NoteDto getById(UUID id) throws NoteNotFoundException;
}