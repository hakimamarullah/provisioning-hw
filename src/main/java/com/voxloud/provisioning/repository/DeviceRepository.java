package com.voxloud.provisioning.repository;

import com.voxloud.provisioning.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DeviceRepository extends JpaRepository<Device, String> {

    Optional<Device> findFirstByMacAddress(String macAddress);
}
