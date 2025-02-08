package com.chintan.appversion.appversion.Service;


import com.chintan.appversion.appversion.Dto.AppVersionDto;

public interface AppVersionService {
    AppVersionDto findLatestVersion();
    AppVersionDto addVersion(AppVersionDto appVersionDto, Integer purpose);
}
