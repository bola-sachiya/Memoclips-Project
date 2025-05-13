package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data


public class AlbumDetailsDto {
private String detailId;
private String albumId;
private String numberOfPhotos;
private String coverType;
private String size;
private String paperQuantity;

}
