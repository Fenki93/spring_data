package note.mvc.note.mvc.controller.requset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNoteRequest {

    @NotNull(message = "id cannot be empty")
    private UUID id;

    @NotBlank
    @Size(min = 2, max = 200)
    private String title;

    @NotBlank
    @Size(min = 2, max = 2000)
    private String content;

}