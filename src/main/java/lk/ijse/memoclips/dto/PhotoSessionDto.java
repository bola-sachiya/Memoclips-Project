package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoSessionDto {

    private String sessionId;
    private String bookId;
    private String photographerId;
    private String sessionType;
    private String duration;
}
