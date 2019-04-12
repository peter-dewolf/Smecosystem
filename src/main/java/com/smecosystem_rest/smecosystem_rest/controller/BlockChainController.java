package com.smecosystem_rest.smecosystem_rest.controller;


import com.smecosystem_rest.smecosystem_rest.exception.ResourceNotFoundException;
import com.smecosystem_rest.smecosystem_rest.model.smartcontracts.HelloWorld;
import com.smecosystem_rest.smecosystem_rest.repositories.UserRepository;
import com.smecosystem_rest.smecosystem_rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/blockchainRestService")
public class BlockChainController {

    @Autowired
    private UserService userService;

    private final String DEFAULT_ADDRESS = "http://127.0.0.1:7545";

//    private final Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));

    @GetMapping("/getCurrentBlock")
    public ResponseEntity<EthBlockNumber> getCurrentBlock() throws ResourceNotFoundException {
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        EthBlockNumber result;
        try {
            result = web3j.ethBlockNumber()
                    .sendAsync()
                    .get();
            return ResponseEntity.ok().body(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new ResourceNotFoundException("Current block could not be found at the moment, try later");
        }
    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List<String>> getAccounts() {
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        List<String> accounts = new ArrayList<>();

        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/getBalanceByAddress/{address}")
    public ResponseEntity<EthGetBalance> getBalanceByAddress(@PathVariable(value = "address") String address) throws ResourceNotFoundException {
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        EthGetBalance result;
        try {
            result = web3j.ethGetBalance(address,
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();
            return ResponseEntity.ok().body(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new ResourceNotFoundException("Current block could not be found at the moment, try later");
        }
    }

    @GetMapping("/transferEther/{password}/{userId}/{targetAddress}/{amount}")
    public void transferEther(@PathVariable(value = "password") String password,
                                                @PathVariable(value = "userId") Long userId,
                                                @PathVariable(value = "targetAddress") String targetAddress,
                                                @PathVariable(value = "amount") Long amount) throws CipherException, IOException {
        Credentials cred = this.userService.getCredentials(password, userId);
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        try {
            TransactionReceipt transactionReceipt = Transfer.sendFunds(web3j, cred, targetAddress, BigDecimal.valueOf(amount), Convert.Unit.ETHER).send();
            System.out.println(transactionReceipt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/deployHelloWorldContract/{password}/{userId}")
    public ResponseEntity<String> transferEther(@PathVariable(value = "password") String password,
                                                @PathVariable(value = "userId") Long userId) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        Credentials cred = this.userService.getCredentials(password, userId);
        HelloWorld contract = HelloWorld.deploy(web3j, cred, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();

        return ResponseEntity.ok().body("The contract address is: " +contract.getContractAddress());
    }
}
