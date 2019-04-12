package com.smecosystem_rest.smecosystem_rest.services;

import com.smecosystem_rest.smecosystem_rest.model.User;
import com.smecosystem_rest.smecosystem_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String getWalletAddressById(Long userId, String password) throws IOException, CipherException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User foundUser = user.get();
            String walletAddress = foundUser.getWalletAddress();
            Credentials credentials = WalletUtils.loadCredentials(password, User.getWalletPath() + "/" + foundUser.getWalletAddress());
            return credentials.getAddress();
        }
        else {
            return "User or wallet not found, please create a wallet first";
        }
    }

    public Credentials getCredentials(String password, Long userId) throws IOException, CipherException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            User foundUser = user.get();
            String walletAddress = foundUser.getWalletAddress();
            return WalletUtils.loadCredentials(password, User.getWalletPath() + "/" + foundUser.getWalletAddress());
        }
        else {
            throw new NoResultException();
        }
    }
}
