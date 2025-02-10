package com.chintan.appversion.appversion.Repository;

import com.chintan.appversion.appversion.Model.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {

    @Query("SELECT s FROM AppVersion s ORDER BY s.id DESC LIMIT 1")
    AppVersion findLatestVersion();

    @Query("SELECT s.version FROM AppVersion s ORDER BY s.id DESC LIMIT 1")
    String findLastVersion();
}
