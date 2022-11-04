package com.challenge4.FilmApp.service.impl;

import com.challenge4.FilmApp.entity.Customer;
import com.challenge4.FilmApp.repository.UserRepo;
import com.challenge4.FilmApp.service.UserService;
import com.challenge4.FilmApp.utils.Config;
import com.challenge4.FilmApp.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserImpl.class);

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public Response response;

    @Override
    public Map save(Customer customer) {
        try{
            if (customer.getUsername() == null){
                return response.error("Wajib isi username", Config.ERROR_401);
            }

            if (customer.getPassword() == null){
                return response.error("Wajib isi password", Config.ERROR_401);
            }

            Customer doSave = userRepo.save(customer);
            return response.sukses(doSave);

        }catch (Exception e){
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Customer customer) {

        try{
            if (customer.getUsername() == null){
                return  response.error("Harus Menyertakan username .", Config.ERROR_401);
            }

            if (userRepo.getUserById(customer.getId()) == null){
                return  response.error("username not found.", Config.ERROR_404);
            }

            Customer doUpdate = userRepo.save(customer);
            return response.sukses(doUpdate);
        }catch (Exception e){
            logger.error("Eror save,{} " + e);
            return response.error("eror update: " + e.getMessage(), Config.ERROR_500);
        }

    }

    @Override
    public Map delete(Customer customer) {
        try {


            if (userRepo.getUserById(customer.getId()) == null) {
                return   response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            userRepo.delete(customer);
            return response.sukses("sukses");
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror delete: " + e.getMessage(), Config.ERROR_500);
        }
    }
}
