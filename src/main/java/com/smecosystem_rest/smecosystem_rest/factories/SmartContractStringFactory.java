package com.smecosystem_rest.smecosystem_rest.factories;

public class SmartContractStringFactory {

    private static final String solidityVersion = "pragma solidity ^0.4.22; \n";
    private static final String contractTitle = "contract helloWorld { \n";

    public static String generateSmartContract(String[] args) {

        StringBuilder solidityContractBuilder = new StringBuilder();

        //
        solidityContractBuilder.append(solidityVersion);
        solidityContractBuilder.append(contractTitle);

        solidityContractBuilder.append("function renderHelloWorld () public pure returns (string) {\n" +
                "   return 'helloWorld';\n" +
                " }");

        solidityContractBuilder.append("}");

        return solidityContractBuilder.toString();
    }
}
