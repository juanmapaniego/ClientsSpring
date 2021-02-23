package com.jmpaniego.controllers;

import com.jmpaniego.entities.Client;
import com.jmpaniego.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String inicio(Model model){
        var clients = clientService.findAll();
        log.info("Ejecutando el controlador: " + clients.size());
        double total = clients.stream().map(c -> c.getBalance()).mapToDouble(Double::doubleValue).sum();
        model.addAttribute("totalBalance", total);
        model.addAttribute("totalClients", clients.size());
        model.addAttribute("clients", clients);
        return "index";
    }

    @PostMapping("/clients")
    public String agregar(Client client){
        clientService.add(client);
        return "redirect:/clients";
    }

    @PutMapping("/clients/{id}")
    public String modificar(Client client){
        return "modificar";
    }

    @PostMapping("/clients/delete/{id}")
    public String delete(@PathVariable Integer id){
        Client client = new Client();
        client.setClientId(id);
        clientService.delete(client);
        return "redirect:/clients";
    }



}
