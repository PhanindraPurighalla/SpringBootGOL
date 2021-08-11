package com.aarnasolutions.learning.gameoflife.business.domain;

import com.aarnasolutions.learning.gameoflife.data.entity.Career;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraversedCell {
    private long cellId;
    private long cellActionId;
    private String cellActionDesc;
}
