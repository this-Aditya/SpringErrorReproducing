
package org.base.server.exception;

import org.base.server.entity.Project;
import org.base.server.dto.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class InvalidProjectDetailsException extends RuntimeException {

    private static final long serialVersionUID = -432767934508766939L;

    public InvalidProjectDetailsException(String message) {
        super(message);
    }

    public InvalidProjectDetailsException(ProjectDto projectDto) {
        super("Invalid details supplied for the project " + projectDto);
    }

    public InvalidProjectDetailsException(ProjectDto projectDto, Throwable cause) {
        super("Invalid details supplied for the project " + projectDto, cause);
    }
}