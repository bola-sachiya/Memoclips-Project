package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotoStorageDto {
private String photoId;
private String sessionId;
private String uploadDate;
private String uploadTime;

}
