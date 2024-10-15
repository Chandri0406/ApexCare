package controller;

import models.Contract;

class ContractFactory {
    public String determineType(Contract contract) {
        return contract.getType();
    }

    public String determineResidency(Contract contract) {
        return contract.getResidency();
    }
}
