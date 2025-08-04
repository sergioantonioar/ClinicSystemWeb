package org.example.clinic_system.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class UriGeneric {
    public static URI CreateUri(String getEntity, UUID id){
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(getEntity)
                .buildAndExpand(id)
                .toUri();
    }
}
