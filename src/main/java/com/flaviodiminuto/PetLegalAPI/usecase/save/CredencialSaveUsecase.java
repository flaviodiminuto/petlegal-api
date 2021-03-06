package com.flaviodiminuto.PetLegalAPI.usecase.save;

import com.flaviodiminuto.PetLegalAPI.model.entity.CredencialEntity;
import com.flaviodiminuto.PetLegalAPI.usecase.save.SaveUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Base64Utils;

import static com.flaviodiminuto.PetLegalAPI.util.TraceUtil.logTrace;

public class CredencialSaveUsecase implements SaveUsecase<CredencialEntity> {
    @Autowired
    CrudRepository<CredencialEntity,Long> repository;

    @Override
    public boolean save(CredencialEntity credencial) throws NoSuchMethodException {
        String encrypt = Base64Utils.encodeToString(credencial.getPasse().getBytes());
        credencial.setPasse(encrypt);
        logMetodoAtual(credencial.toString(), "Salvando");
        return repository.save(credencial).getId() != null;
    }

    @Override
    public void logMetodoAtual(String mensagem, String metodo) throws NoSuchMethodException {
        logTrace(getClass(),getClass().getMethod(metodo, CredencialEntity.class).getName(), mensagem);
    }
}
