package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VideoSessionDto {
    private String videoId;
    private String bookingId;
    private String videographerId;
    private String duration;
    private String format;
}
