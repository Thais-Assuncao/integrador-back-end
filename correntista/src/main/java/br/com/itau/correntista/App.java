package br.com.itau.correntista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.itau.correntista.models.Cliente;
import br.com.itau.correntista.repositories.impl.ClienteRepository;

public class App {
    
    public static void main(String[] args) {
    	ClienteRepository rep = new ClienteRepository();

    	Cliente cliente = new Cliente("0001", "93456-7", "Pedro da Silva","pedro@gama.com", "62987897984", BigDecimal.valueOf(350.0));
    	Cliente cliente2 = new Cliente("0002", "23456-7", "Jose da Silva","jose@gama.com", "62988121324", BigDecimal.valueOf(450.0));
    	Cliente cliente3 = new Cliente("0003", "33456-7", "Maria da Silva","maria@gama.com", "62789456892", BigDecimal.valueOf(550.0));
    	Cliente cliente4 = new Cliente("0004", "43456-7", "Lucas da Silva","lucas@gama.com", "62987654412", BigDecimal.valueOf(650.0));
    	Cliente cliente5 = new Cliente("0005", "53456-7", "Marcos da Silva","marcos@gama.com", "62867812364", BigDecimal.valueOf(750.0));
    	Cliente cliente6 = new Cliente("0006", "63456-7", "Mateus da Silva","matheus@gama.com", "629828856", BigDecimal.valueOf(850.0));
    	List<Cliente> novosClientes = new ArrayList<>();
    	novosClientes.addAll(Arrays.asList(cliente, cliente2, cliente3, cliente4, cliente5, cliente6));
    	rep.gravarCliente(novosClientes);
    	List<Cliente> clientes = rep.listarClientes();
    	for (Cliente c : clientes) {
			System.out.println(c.toFormatoArquivo());
		}
    }
    
    public static void apresentaMenu() {
    	
    }
    
}
