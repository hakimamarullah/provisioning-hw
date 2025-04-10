package com.voxloud.provisioning.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Optional;

@Entity
@Table(name = "device")
@Data
public class Device {

    @Id
    @Column(name = "mac_address")
    private String macAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceModel model;

    @Column(name = "override_fragment")
    private String overrideFragment;

    private String username;

    private String password;

    @Getter
    public enum DeviceModel {
        CONFERENCE("conference", ".json"),
        DESK("desk", ".properties");

        private final String type;
        private final String configFileExt;

        DeviceModel(String type, String configFileExt) {
            this.type = type;
            this.configFileExt = configFileExt;
        }
    }

    public String getModelType() {
        return Optional.ofNullable(model).map(DeviceModel::getType).orElse(null);
    }

    public String getConfigFileExt() {
        return Optional.ofNullable(model).map(DeviceModel::getConfigFileExt).orElse(null);
    }
}