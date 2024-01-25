package com.example.shoppingworld.dto.ResponseDto;

import com.example.shoppingworld.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {
    String name;
    String emailId;
    String mobileNo;
    Gender gender;
}
