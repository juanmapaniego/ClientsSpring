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

    @GetMapping({"/clients","","/"})
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

    @GetMapping("/clients/edit/{id}")
    public String modificar(@PathVariable Long id,Model model){
        Client client = new Client();
        client.setClientId(id);
        try {
            client = clientService.findById(client);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("client", client);
        return "clients/modify";
    }

    @PostMapping("/clients/delete/{id}")
    public String delete(@PathVariable Long id){
        Client client = new Client();
        client.setClientId(id);
        clientService.delete(client);
        return "redirect:/clients";
    }

    @PostMapping("/clients/edit")
    public String modificar(Client client) {
        clientService.modify(client);
        return "redirect:/clients";
    }
}
