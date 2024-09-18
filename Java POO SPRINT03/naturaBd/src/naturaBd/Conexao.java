package naturaBd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

import classs.Cliente;
import classs.Consultora;
import classs.Produto;
import dao.ClienteDAO;
import dao.ConsultoraDAO;
import dao.ProdutoDAO;

public class Conexao {

	private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521/ORCL";
	private static final String USER = "rm94947";
	private static final String PASSWORD = "051203";
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("Conexão estabelecida com sucesso.");

			Date hoje = Date.valueOf(LocalDate.now().toString());

			ConsultoraDAO consultoraDAO = new ConsultoraDAO(connection);
			ClienteDAO clienteDAO = new ClienteDAO(connection);
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);

			while (true) {
				System.out.println("O que você deseja fazer?");
				System.out.println("[1] Criar uma tabela");
				System.out.println("[2] Inserir em uma tabela");
				System.out.println("[3] Verificar a tabela");
				System.out.println("[4] Atualizar");
				System.out.println("[5] Excluir da tabela");
				System.out.println("[6] Excluir tabela");
				System.out.println("[7] Realizar venda");
				System.out.println("[8] Sair");
				int escolha = entrada.nextInt();

				entrada.nextLine();

				switch (escolha) {
				case 1:
					System.out.println("Criar tabela: [1]Consultora [2]Cliente [3]Produto");
					int alter = entrada.nextInt();
					entrada.nextLine();

					switch (alter) {
					case 1:
						consultoraDAO.createTableConsultora();
						break;
					case 2:
						clienteDAO.createTableCliente();
						break;
					case 3:
						produtoDAO.createTableProduto();
						break;
					default:
						System.out.println("Entrada inválida.");
					}
					break;

				case 2:
					System.out.println("Quem você deseja adicionar na tabela? [1]Consultora [2]Cliente [3]Produto");
					alter = entrada.nextInt();
					entrada.nextLine();

					switch (alter) {
					case 1:
						System.out.println("Nome da nova consultora:");
						String nomeConsultora = entrada.nextLine();

						System.out.println("Email da nova consultora:");
						String emailConsultora = entrada.nextLine();

						System.out.println("Telefone da nova consultora:");
						String telefoneConsultora = entrada.nextLine();

						Consultora novaConsultora = new Consultora(0, nomeConsultora, emailConsultora,
								telefoneConsultora, hoje, null);
						consultoraDAO.addConsultora(novaConsultora);
						System.out.println("Consultora adicionada com sucesso.");
						break;

					case 2:
						System.out.println("Nome da nova cliente:");
						String nomeCliente = entrada.nextLine();

						System.out.println("Email da nova cliente:");
						String emailCliente = entrada.nextLine();

						System.out.println("Telefone da nova cliente:");
						String telefoneCliente = entrada.nextLine();

						System.out.println("Endereço da nova cliente:");
						String enderecoCliente = entrada.nextLine();

						System.out.println("Alguma consultora responsável pela cliente? S/N");
						String booleanS = entrada.nextLine().toUpperCase();

						while (!booleanS.equals("S") && !booleanS.equals("N")) {
							System.out.println("Digite apenas S ou N");
							booleanS = entrada.nextLine().toUpperCase();
						}

						int consultoraResp = 0;
						if (booleanS.equals("S")) {
							List<Consultora> consultoras = consultoraDAO.listarConsultoras();
							for (Consultora consultora : consultoras) {
								System.out.println("id:[" + consultora.getCod_consultora() + "] "
										+ consultora.getNome_consultora());
							}

							while (true) {
								System.out.println("Qual a consultora responsável? [Escolha pelo id]");
								consultoraResp = entrada.nextInt();
								entrada.nextLine();

								boolean consultoraEncontrada = false;
								for (Consultora consultora : consultoras) {
									if (consultora.getCod_consultora() == consultoraResp) {
										consultoraEncontrada = true;
										break;
									}
								}

								if (consultoraEncontrada == true) {
									break;
								} else {
									System.out.println("codigo invalido.");
								}
							}
						}

						Cliente cliente = new Cliente(0, nomeCliente, emailCliente, telefoneCliente, enderecoCliente,
								consultoraResp);
						clienteDAO.insertTableCliente(cliente);
						System.out.println("Cliente adicionado com sucesso.");
						break;
					case 3:
						System.out.println("Nome do novo Produto: ");
						String nomeProduto = entrada.nextLine();

						System.out.println("Desc do novo produto: ");
						String descProduto = entrada.nextLine();

						System.out.println("Novo preco: ");
						double precoProduto = entrada.nextDouble();
						entrada.nextLine();

						System.out.println("Quantidade em estoque: ");
						int estoque = entrada.nextInt();

						Produto prod = new Produto(0, nomeProduto, descProduto, precoProduto, estoque);
						produtoDAO.insertProduto(prod);
						System.out.println("Produto adicionado com sucesso.");
						break;
					default:
						System.out.println("Entrada inválida.");
					}
					break;

				case 3:
					System.out.println("Verificar tabela de [1]Consultoras [2]Clientes [3]Produtos");
					alter = entrada.nextInt();
					entrada.nextLine();
					switch (alter) {
					case 1:
						System.out.println("=============== Consultoras ===============");
						List<Consultora> consultoras = consultoraDAO.listarConsultoras();
						for (Consultora consultora : consultoras) {
							System.out.println(
									"id:[" + consultora.getCod_consultora() + "] " + consultora.getNome_consultora());
						}
						System.out.println("=============== ========== ===============");
						break;
					case 2:
						System.out.println("=============== Clientes ===============");
						List<Cliente> ClientesAtt = clienteDAO.listarCliente();
						for (Cliente cliente : ClientesAtt) {
							System.out.println("id:[" + cliente.getCod_Cliente() + "] " + cliente.getNome_cliente());
						}
						System.out.println("=============== ========== ===============");
						break;
					case 3:
						System.out.println("=============== Produtos ===============");
						List<Produto> Produtos = produtoDAO.listarProduto();
						for (Produto produto : Produtos) {
							System.out.println("id:[" + produto.getCod_Produto() + "] " + produto.getNome_Produto());
						}
						System.out.println("=============== ========== ===============");
						break;
					default:
						System.out.println("Entrada invalida");
						break;
					}

					break;

				case 4:
					System.out.println("Atualizar: [1]Consultoras [2]Clientes [3]Produtos");
					alter = entrada.nextInt();
					switch (alter) {
					case 1:
						List<Consultora> consultorasAtt = consultoraDAO.listarConsultoras();
						for (Consultora consultora : consultorasAtt) {
							System.out.println("id:[" + consultora.getCod_consultora() + "] " + "Nome: "
									+ consultora.getNome_consultora());
						}

						System.out.println("Escolha a consultora pelo id:");
						int id = entrada.nextInt();
						// errp de buffer precisa repetir entrada

						entrada.nextLine();

						System.out.println("Digite o novo nome:");
						String nome = entrada.nextLine();

						System.out.println("Digite o novo email:");
						String email = entrada.nextLine();

						System.out.println("Digite o novo telefone:");
						String telefone = entrada.nextLine();
						
						
						System.out.println("Digite a data em que foi encerrada a parceria: 2024-12-25:");
						String dataString = entrada.nextLine();
						Date data = Date.valueOf(dataString);

						consultoraDAO.update(id, nome, email, telefone, data);
						System.out.println("Consultora atualizada com sucesso.");

						break;
					case 2:
						List<Cliente> ClientesAtt = clienteDAO.listarCliente();
						for (Cliente cliente : ClientesAtt) {
							System.out.println(
									"id:[" + cliente.getCod_Cliente() + "] " + "Nome: " + cliente.getNome_cliente());
						}

						System.out.println("Escolha a consultora pelo id:");

					
						id = entrada.nextInt();

						entrada.nextLine();

						System.out.println("Digite o novo nome:");
						nome = entrada.nextLine();

						System.out.println("Digite o novo email:");
						email = entrada.nextLine();

						System.out.println("Digite o novo telefone:");
						telefone = entrada.nextLine();

						System.out.println("Digite o novo Endereco:");
						String endereco = entrada.nextLine();

						System.out.println("Qual a nova consultora responsavel? [Escolha por id]");
						for (int i = 0; i < consultoraDAO.listarConsultoras().size(); i++) {

							System.out.println("[" + consultoraDAO.listarConsultoras().get(i).getCod_consultora()
									+ "] " + consultoraDAO.listarConsultoras().get(i).getNome_consultora());

						}

						int consultoraId = entrada.nextInt();
						entrada.nextLine();

						clienteDAO.atualizarCliente(id, nome, email, telefone, endereco, consultoraId);
						System.out.println("Consultora atualizada com sucesso.");
						break;

					case 3:
						List<Produto> produtos = produtoDAO.listarProduto();
						for (Produto produto : produtos) {
							System.out.println("id:[" + produto.getCod_Produto() + "] " + produto.getNome_Produto());
						}

						System.out.println("Escolha o produto pelo id:");

						
						id = entrada.nextInt();

						entrada.nextLine();

						System.out.println("Digite o novo nome do produto:");
						nome = entrada.nextLine();

						System.out.println("Digite a nova descricao:");
						String desc = entrada.nextLine();

						System.out.println("Digite o novo preco:");
						double preco = entrada.nextDouble();

						System.out.println("Digite a quantidade atualiizada em estoque:");
						int estoque = entrada.nextInt();

						produtoDAO.atualizarProduto(nome, desc, preco, estoque, id);
						
						break;
					default:
						System.out.println("Entrada invalida");
						
					}break;

				case 5:
					System.out.println("Excluir [1]Consultora [2]Cliente [3]Produto");
					alter = entrada.nextInt();
					entrada.nextLine();

					switch (alter) {
					case 1:
						List<Consultora> consultorasExc = consultoraDAO.listarConsultoras();
						for (Consultora consultora : consultorasExc) {
							System.out.println("id:[" + consultora.getCod_consultora() + "] " + "Nome: "
									+ consultora.getNome_consultora());
						}

						System.out.println("Escolha o id a ser excluido:");
						int idExc = entrada.nextInt();
						// att buffer
						entrada.nextLine();

						consultoraDAO.apagarConsultora(idExc);
						System.out.println("Consultora excluída com sucesso.");
						break;
					case 2:
						System.out.println("Escolha a cliente a ser excluido pelo [id]");
						List<Cliente> Clientes = clienteDAO.listarCliente();
						for (Cliente cliente : Clientes) {
							System.out.println(
									"id:[" + cliente.getCod_Cliente() + "] " + "Nome: " + cliente.getNome_cliente());
						}

						idExc = entrada.nextInt();
						entrada.nextLine();
						clienteDAO.deleteCliente(idExc);
						break;
					case 3:
						System.out.println("Escolha o produto a ser excluido pelo [id]");
						List<Produto> produtos = produtoDAO.listarProduto();
						for (Produto produto : produtos) {
							System.out.println("id:[" + produto.getCod_Produto() + "] " + produto.getNome_Produto());
						}

						idExc = entrada.nextInt();
						entrada.nextLine();
						produtoDAO.deleteProduto(idExc);
						break;
					default:
						break;
					}

					break;

				case 6:
					System.out.println("Dropar table: [1]Consultora [2]Cliente [3]Cliente");
					alter = entrada.nextInt();
					switch (alter) {
					case 1:
						consultoraDAO.dropTable();
						break;
					case 2:
						clienteDAO.dropTableCliente();
						break;
					case 3:
						produtoDAO.dropTableProduto();
						break;
					default:
						System.out.println("Entrada invalida");
						break;
					}

					break;
				case 7:
					System.out.println("============= REALIZAR VENDA =============");

					// Selecionar cliente
					System.out.println("Qual cliente vai comprar? [Selecione pelo id]");
					List<Cliente> clientes = clienteDAO.listarCliente();
					for (Cliente cliente : clientes) {
						System.out.println("id:[" + cliente.getCod_Cliente() + "] " + cliente.getNome_cliente());
					}
					int idCliente = entrada.nextInt(); // Sem -1

					// Selecionar consultora
					System.out.println("Qual consultora é responsável pela compra? [Selecione pelo id]");
					List<Consultora> consultoras = consultoraDAO.listarConsultoras();
					for (Consultora consultora : consultoras) {
						System.out.println(
								"id:[" + consultora.getCod_consultora() + "] " + consultora.getNome_consultora());
					}
					int idConsultora = entrada.nextInt();

					Cliente clienteSelecionado = null;
					Consultora consultoraSelecionada = null;

					for (Cliente cliente : clientes) {
						if (cliente.getCod_Cliente() == idCliente) {
							clienteSelecionado = cliente;
							break;
						}
					}

					for (Consultora consultora : consultoras) {
						if (consultora.getCod_consultora() == idConsultora) {
							consultoraSelecionada = consultora;
							break;
						}
					}

					if (clienteSelecionado != null && consultoraSelecionada != null) {
						realizarVenda(clienteSelecionado, consultoraSelecionada, produtoDAO);
						System.out.println("[ Obrigado pela preferência :D ]");
					} else {
						System.out.println("Cliente ou consultora não encontrados.");
					}
					break;

				case 8:
					return;
				default:
					System.out.println("Entrada invalida");
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void realizarVenda(Cliente cliente, Consultora consultora, ProdutoDAO produtoDAO) {

		System.out.println("[                  Produtos                  ]");
		List<Produto> produtos = produtoDAO.listarProduto();
		for (Produto produto : produtos) {

			if (produto.getEstoque() >= 0) {
				System.out.println("id:[" + produto.getCod_Produto() + "] " + produto.getNome_Produto() + " ("
						+ produto.getDesc_Produto() + ") R$" + produto.getPreco_Produto());
			} else {

				System.out.println("id:[" + produto.getCod_Produto() + "] " + produto.getNome_Produto() + " ("
						+ produto.getDesc_Produto() + ") R$" + produto.getPreco_Produto() + "[ESGOTADO]");
			}
		}
		boolean certeza = false;
		do {
		System.out.println("Qual o id do protudo escolhido: ");
		int idProduto = entrada.nextInt();
		
		 Produto produtoEscolhido = null;
		    for (Produto produto : produtos) {
		        if (produto.getCod_Produto() == idProduto) {
		            produtoEscolhido = produto;
		            break;
		        }
		    }
		    System.out.println("Quantos você deseja comprar?");
		    int qtdComprada = entrada.nextInt();
		    System.out.println("Valor total da compra: R$"+qtdComprada*produtoEscolhido.getPreco_Produto());
		    System.out.println("confirma? [1]Sim [2]Nao");
		    int escolha =  entrada.nextInt();
		    switch (escolha) {
			case 1:
				certeza = true;
				produtoDAO.atualizarProdutoVenda(qtdComprada, idProduto);
				break;
			case 2:
				
				break;

			default:System.out.println("entrada invalida");
				break;
			}
		}while(!certeza);
		    
	}
}