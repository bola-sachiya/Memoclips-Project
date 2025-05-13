package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PhotographerDto {

    private String photographerId;
    private String name;
    private String speciality;
    private String contact;
    private String availability;
}
