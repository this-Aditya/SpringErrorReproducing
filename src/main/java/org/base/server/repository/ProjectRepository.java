
package org.base.server.repository;

import java.util.Optional;

import org.base.server.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByProjectId(String projectId);

    Boolean existsByProjectId(String projectId);
}