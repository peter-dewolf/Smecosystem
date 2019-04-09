package com.smecosystem_rest.smecosystem_rest.controller;


import com.smecosystem_rest.smecosystem_rest.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/blockchainRestService")
public class BlockChainController {

    private final String DEFAULT_ADDRESS = "http://127.0.0.1:7545";


    @GetMapping("/getCurrentBlock")
    public ResponseEntity<EthBlockNumber> getCurrentBlock() throws ResourceNotFoundException {
        Web3j web3 = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        EthBlockNumber result;
        try {
            result = web3.ethBlockNumber()
                    .sendAsync()
                    .get();
            return ResponseEntity.ok().body(result);
        } catch (InterruptedException e) {
            throw new ResourceNotFoundException("Current block could not be found at the moment, try later");
        } catch (ExecutionException e) {
            throw new ResourceNotFoundException("Current block could not be found at the moment, try later");
        }
    }



    @GetMapping("/deploySmartContract")
    public ResponseEntity<String> deploySmartContract() {
        Web3j web3 = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        Credentials credentials = Credentials.create("privateKey", "public Key");


        return ResponseEntity.ok().body("deployed");
    }


    @GetMapping("/getAccounts")
    public ResponseEntity<List<String>> getAccounts() {
        Web3j web3 = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        List<String> accounts = new ArrayList<>();

        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/getBalanceByAddress")
    public ResponseEntity<EthGetBalance> getBalanceByAddress() throws ResourceNotFoundException {
        // ideally this comes from the user repository
        String address = "0x2499316Ba3F9fbB9f52EdeEbF4eD998f625Fa44a";

        Web3j web3 = Web3j.build(new HttpService(DEFAULT_ADDRESS));
        EthGetBalance result;
        try {
            result = web3.ethGetBalance(address,
                    DefaultBlockParameter.valueOf("latest"))
                    .sendAsync()
                    .get();
            return ResponseEntity.ok().body(result);
        } catch (InterruptedException e) {
            throw new ResourceNotFoundException("Current block could not be found at the moment, try later");
        } catch (ExecutionException e) {
            throw new ResourceNotFoundException("Current block could not be found at the moment, try later");
        }
    }
}
