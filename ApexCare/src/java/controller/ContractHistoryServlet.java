package controller;

import models.Contract;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/contractHistory")
public class ContractHistoryServlet extends HttpServlet {
    private List<Contract> contracts;
    private ContractFactory contractFactory;

    @Override
    public void init() throws ServletException {
        contractFactory = new ContractFactory();
        contracts = new ArrayList<>();
        // Sample contract data
        contracts.add(new Contract("000001", 11, new Date(), new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000), "active", "Warranty", "house"));
        contracts.add(new Contract("000002", 11, new Date(), new Date(System.currentTimeMillis() + 15 * 24 * 60 * 60 * 1000), "on hold", "Commercial", "house"));
        contracts.add(new Contract("000003", 11, new Date(), new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000), "active", "Private", "apartment"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedID = request.getParameter("selectedID");
        Contract selectedContract = null;

        if (selectedID != null) {
            for (Contract contract : contracts) {
                if (contract.getContractID().equals(selectedID)) {
                    selectedContract = contract;
                    break;
                }
            }
        }

        request.setAttribute("contracts", contracts);
        request.setAttribute("selectedContract", selectedContract);
        request.getRequestDispatcher("/WEB-INF/views/contractHistory.jsp").forward(request, response);
    }
}