package br.com.itau.correntista;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import br.com.itau.correntista.exceptions.BusinessException;
import br.com.itau.correntista.models.Cliente;
import br.com.itau.correntista.services.IClienteService;
import br.com.itau.correntista.services.impl.ClienteService;

public class App {
	static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
    	IClienteService service = new ClienteService();
    	while(true) {
    		apresentaMenu();
        	int op = scanner.nextInt();
        	if(op < 1 || op > 4) {
        		System.out.println("Informe uma opção válida (entre 1 e 4)");
        		continue;
        	}
        	switch (op) {
	    		case 1: {
	    			try {
	    				service.gravarEmMemoria(montaCliente());	    				
	    				break;
	    			} catch(BusinessException e) {
	    				System.err.println(e.getMessage());
	    				break;
	    			}
	    		}
	    		case 2: {
	    			try {
						List<Cliente> listarDaMemoria = service.listarDaMemoria();
						listarDaMemoria.forEach(c -> {
							System.out.println("-------------");
							System.out.println("Nome: " + c.getNomeCliente());
							System.out.println("Agência: " + c.getAgencia());
							System.out.println("Conta: " + c.getConta());
							System.out.println("Email: " + c.getEmail());
							System.out.println("Saldo: " + c.getSaldo());
						});
					} catch (BusinessException e) {
						System.err.println(e.getMessage());
					}
	    			break;
	    		}
	    			
	    		case 3: {
						service.gravar();
		    			break;
	    		}
				case 4:
					try {
						List<Cliente> listarDoArquivo = service.listarClientes();
						listarDoArquivo.forEach(c -> {
							System.out.println("-------------");
							System.out.println("Nome: " + c.getNomeCliente());
							System.out.println("Agência: " + c.getAgencia());
							System.out.println("Conta: " + c.getConta());
							System.out.println("Email: " + c.getEmail());
							System.out.println("Saldo: " + c.getSaldo());
						});
					} catch (BusinessException e) {
						System.err.println(e.getMessage());
					}
	    			break;
	    		case 5:
	    			System.exit(1);
	    			return;
	
	    		default:
	    			break;
    		}
        	
    	}
    	
//    	ClienteRepository rep = new ClienteRepository();
//
//    	Cliente cliente = new Cliente("0001", "93456-7", "Pedro da Silva","pedro@gama.com", "62987897984", BigDecimal.valueOf(350.0));
//    	Cliente cliente2 = new Cliente("0002", "23456-7", "Jose da Silva","jose@gama.com", "62988121324", BigDecimal.valueOf(450.0));
//    	Cliente cliente3 = new Cliente("0003", "33456-7", "Maria da Silva","maria@gama.com", "62789456892", BigDecimal.valueOf(550.0));
//    	Cliente cliente4 = new Cliente("0004", "43456-7", "Lucas da Silva","lucas@gama.com", "62987654412", BigDecimal.valueOf(650.0));
//    	Cliente cliente5 = new Cliente("0005", "53456-7", "Marcos da Silva","marcos@gama.com", "62867812364", BigDecimal.valueOf(750.0));
//    	Cliente cliente6 = new Cliente("0006", "63456-7", "Mateus da Silva","matheus@gama.com", "629828856", BigDecimal.valueOf(850.0));
//    	List<Cliente> novosClientes = new ArrayList<>();
//    	novosClientes.addAll(Arrays.asList(cliente, cliente2, cliente3, cliente4, cliente5, cliente6));
//    	rep.gravarCliente(novosClientes);
//    	List<Cliente> clientes = rep.listarClientes();
//    	for (Cliente c : clientes) {
//			System.out.println(c.toFormatoArquivo());
//		}
    }
    
    public static void apresentaMenu() {
    	System.out.println(" #### SISTEMA ICARROS #### ");
    	System.out.println("1 - Cadastrar Clientes");
    	System.out.println("2 - Listar Clientes");
    	System.out.println("3 - Gravar arquivo texto");
    	System.out.println("4 - Consultar arquivo texto");
    	System.out.println("Informe uma das opções: ");
    }
    
    public static Cliente montaCliente() {
    	System.out.println("Informe os dados do cliente: ");
    	System.out.print("Nome:");
    	String nome = scanner.next();
    	System.out.print("Agência:");
    	String agencia = scanner.next();
    	System.out.print("Conta:");
    	String conta = scanner.next();
    	System.out.print("E-mail:");
    	String email = scanner.next();
    	System.out.print("Telefone:");
    	String telefone = scanner.next();
    	System.out.print("Saldo:");
    	Double saldoD = scanner.nextDouble();
    	BigDecimal saldo = new BigDecimal(saldoD);
    	Cliente cliente = new Cliente(agencia, conta, nome, email, telefone, saldo);
    	return cliente;
    }
    

    
}
