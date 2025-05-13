package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoAlbumDto {
    private String albumId;
    private String bookingId;
    private String albumType;
    private String price;
}
