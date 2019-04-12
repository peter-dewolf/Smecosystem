package com.smecosystem_rest.smecosystem_rest.controller;


import com.smecosystem_rest.smecosystem_rest.exception.ResourceNotFoundException;
import com.smecosystem_rest.smecosystem_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCompileSolidity;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/blockchainRestService")
public class BlockChainController {

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/deploySmartContract")
    public ResponseEntity<String> deploySmartContract() {
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        Request<?, EthCompileSolidity> contract = web3j.ethCompileSolidity("pragma solidity ^0.4.22; contract helloWorld { function renderHelloWorld () public pure returns (string) { return 'helloWorld';}}");


        Credentials credentials = Credentials.create("privateKey", "public Key");

        return ResponseEntity.ok().body("deployed");
    }

    @GetMapping("/getAccounts")
    public ResponseEntity<List<String>> getAccounts() {
        Web3j web3j = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        List<String> accounts = new ArrayList<>();

        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/getBalanceByAddress/{address}")
    public ResponseEntity<EthGetBalance> getBalanceByAddress(@PathVariable(value = "address") String address) throws ResourceNotFoundException {

        // ideally this comes from the user repository

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

    @GetMapping("/transferEther/{targetWallet}/{amount}/{userId}")
    public ResponseEntity<String> createNewWallet(@PathVariable(value = "targetWallet") String targetWallet,
                                                         @PathVariable(value = "amount") Long amount,
                                                         @PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {

        return ResponseEntity.ok().body("");
    }




}
