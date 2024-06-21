package com.avijit.filestoragesystem.Repo;

import com.avijit.filestoragesystem.Controller.FileStorageControllers.FileStorage;
import com.avijit.filestoragesystem.Model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepo extends JpaRepository<FileInfo, Long>{
}
