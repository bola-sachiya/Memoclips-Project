package lk.ijse.memoclips.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AdminDto {
    private String adminId;
    private String name;
    private String password;
    private String role;

}
