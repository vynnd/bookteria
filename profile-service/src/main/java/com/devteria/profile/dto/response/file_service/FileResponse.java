package com.devteria.profile.dto.response.file_service;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileResponse {

    String originalFileName;
    String url;
}
