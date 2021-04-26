package com.springbank.user.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class FindUserByIdQuery {
    private String id;
}
