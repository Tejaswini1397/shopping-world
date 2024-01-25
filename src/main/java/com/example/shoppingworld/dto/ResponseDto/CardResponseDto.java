package com.example.shoppingworld.dto.ResponseDto;

import com.example.shoppingworld.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String customerName;
    String cardNo;
    Date validTill;
    CardType cardType;
}
