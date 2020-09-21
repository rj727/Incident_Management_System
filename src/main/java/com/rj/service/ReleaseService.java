package com.rj.service;

import com.rj.entity.Application;
import com.rj.entity.Release;

public interface ReleaseService {
    Iterable<Release> listReleases();
    void saveRelease(Release release);
    Release getRelease(long id);
    void deleteRelease(long id);
}
