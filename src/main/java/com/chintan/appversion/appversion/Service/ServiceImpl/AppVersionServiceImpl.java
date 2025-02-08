package com.chintan.appversion.appversion.Service.ServiceImpl;

import com.chintan.appversion.appversion.Dto.AppVersionDto;
import com.chintan.appversion.appversion.Model.AppVersion;
import com.chintan.appversion.appversion.Repository.AppVersionRepository;
import com.chintan.appversion.appversion.Service.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionRepository appVersionRepository;

    @Override
    public AppVersionDto findLatestVersion() {
        AppVersion latestVersion = appVersionRepository.findLatestVersion();
        if(ObjectUtils.isEmpty(latestVersion)) {
            return null;
        }
        return modelToDto(latestVersion);
    }

    /**
     * Cases
     * 1. bug fixed 1.0.1 : purpose - 1
     * 2. deployed on qa 1.1.0 : purpose - 2
     * 3. deployed on uat 1.2.0 : purpose - 2
     * 4. deployed on prod 2.0.0 : purpose - 3
     */
    @Override
    public AppVersionDto addVersion(AppVersionDto appVersionDto, Integer purpose) {

        // TODO : how to handle appversion table in every environment
        if(purpose > 3) {
            throw new RuntimeException("Invalid Build Purpose");
        }

        AppVersion appVersion = appVersionRepository.findLatestVersion();
        AppVersion latestVersion = new AppVersion();

        // TODO : version empty in different environment
        if(ObjectUtils.isEmpty(appVersion)) {
            latestVersion.setVersion("1.0.0");
        } else {
            String lastVersion = appVersion.getVersion();
            String newVersion = incrementVersion(lastVersion, purpose);
            latestVersion.setVersion(newVersion);
        }
        latestVersion = dtoToModel(appVersionDto, latestVersion);
        latestVersion = appVersionRepository.save(latestVersion);
        return modelToDto(latestVersion);
    }

    public AppVersionDto modelToDto(AppVersion appVersion) {
        return AppVersionDto.builder()
                .id(appVersion.getId())
                .version(appVersion.getVersion())
                .environment(appVersion.getEnvironment())
                .updatedAt(appVersion.getUpdatedAt())
                .build();
    }

    public AppVersion dtoToModel(AppVersionDto appVersionDto, AppVersion appVersion) {
        // We need to create new entry on every execution that's why it's null

        appVersion.setEnvironment(appVersionDto.getEnvironment());
        appVersion.setUpdatedAt(new Date());

        return appVersion;
    }

    public String incrementVersion(String version, Integer type) {
        String[] parts = version.split("\\."); // Split into X, Y, Z
        int major = Integer.parseInt(parts[0]);
        int minor = Integer.parseInt(parts[1]);
        int patch = Integer.parseInt(parts[2]);

        if(type == 1) {
            patch++;
        } else if(type == 2) {
            minor++;
            patch = 0;
        } else {
            major++;
            patch = 0;
            minor = 0;
        }

        return major + "." + minor + "." + patch;
    }

}
