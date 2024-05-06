package note.mvc.note.mvc.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import note.mvc.note.mvc.data.entity.Note;
import note.mvc.note.mvc.data.repository.NoteRepository;
import note.mvc.note.mvc.service.dto.NoteDto;
import note.mvc.note.mvc.service.exception.NoteNotFoundException;
import note.mvc.note.mvc.service.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        log.info("Getting all notes from repository");
        return noteMapper.toNoteDtos(noteRepository.findAll());
    }

    @Override
    public NoteDto add(NoteDto note) {
        log.info("Adding note: {}", note);
        Note entity = noteMapper.toNoteEntity(note);
        entity.setId(null);
        return noteMapper.toNoteDto(noteRepository.save(noteMapper.toNoteEntity(note)));
    }

    @Override
    public void deleteById(UUID id) throws NoteNotFoundException {
        log.info("Deleting note with id: {}", id);
        if (Objects.isNull(id)) {
            throw new NoteNotFoundException("Not found note Id");
        }
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException("Not found note Id: " + id);
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void update(NoteDto note) throws NoteNotFoundException {
        log.info("Updating note: {}", note);
        if (!noteRepository.existsById(note.getId())) {
            throw new NoteNotFoundException("Not found note Id: " + note.getId());
        }
        noteRepository.save(noteMapper.toNoteEntity(note));
    }

    @Override
    public NoteDto getById(UUID id) throws NoteNotFoundException {
        log.info("Getting note with id: {}", id);
        Optional<Note> opNote = noteRepository.findById(id);
        return noteMapper.toNoteDto(opNote.orElseThrow(() -> new NoteNotFoundException(String.format("Note with id %s not found", id))));
    }
}