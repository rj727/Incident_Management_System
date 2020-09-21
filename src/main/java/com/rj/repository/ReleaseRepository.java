package com.rj.repository;

import com.rj.entity.Release;
import org.springframework.data.repository.CrudRepository;

public interface ReleaseRepository extends CrudRepository<Release,Long> {
}
