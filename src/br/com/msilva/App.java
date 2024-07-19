package br.com.msilva;

import br.com.msilva.dao.ClienteMapDAO;
import br.com.msilva.dao.IClienteDAO;
import br.com.msilva.domain.Cliente;

import javax.swing.*;

/**
 *
 * @author marlon.silva
 */


public class App {

    private static IClienteDAO IClienteDAO;

    public static void main(String args[]){
        //instaciando a interface dando acesso aos dados
        IClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Green Dinner", JOptionPane.INFORMATION_MESSAGE);

        while(!isOpcaoValida(opcao)){
            if("".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção invalida digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Green Dinner", JOptionPane.INFORMATION_MESSAGE);
        }

        while(isOpcaoValida(opcao)){

            if(isOpcaoSair(opcao)){
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separado por virgula, conforme exemplo: Nome, CPF, Telefone, Endereco, Numero, Cidade, Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsulta(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "digite o Cpf do Cliente",
                        "Consulta Cliente", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if (isExclusao(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do CLiente",
                        "Excluir Cliente",JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            }else {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separado por virgula, conforme exemplo: Nome, CPF, Telefone, Endereco, Numero, Cidade, Estado",
                        "Atualização",JOptionPane.INFORMATION_MESSAGE);
                atualizar(dados);
            }

            opcao = JOptionPane.showInputDialog(null,
                    "Digite os dados do cliente separado por virgula, conforme exemplo: Nome, CPF, Telefone, Endereco, Numero, Cidade, Estado",
                    "Green dinner", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    private static void atualizar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        IClienteDAO.alterar(cliente);
    }


    private static void excluir(String dados) {
        IClienteDAO.excluir(Long.parseLong(dados));
        JOptionPane.showInputDialog(null, "cliente Exclcuido com Sucesso", "sucesso",JOptionPane.INFORMATION_MESSAGE);
    }

    private static void consultar(String dados) {
        Cliente cliente = IClienteDAO.consultar(Long.parseLong(dados));
        if (cliente != null){
            JOptionPane.showInputDialog(null, "Cliente encontrado com sucesso" + cliente.toString(), "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showInputDialog(null, "Cliente não encontrado ", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        Boolean isCadastrado = IClienteDAO.cadastrar(cliente);
        if (isCadastrado){
            JOptionPane.showInputDialog(null, "Cliente Cadastrado com sucesso", "Sucesso",JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showInputDialog(null, "Cliente já se encontra cadastrado", "Erro",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isExclusao(String opcao) {
        if("3".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isConsulta(String opcao) {
        if("2".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isCadastro(String opcao) {
        if("1".equals(opcao)){
            return true;
        }
        return false;
    }

    private static void sair() {
        String clienteCadstrado = "";
        for(Cliente cliente : IClienteDAO.buscarTodos()){
            clienteCadstrado += cliente.toString() + "\n";
        }
        JOptionPane.showInputDialog(null, "Cliente Cadstrado: " + clienteCadstrado,"Obrigado",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoSair(String opcao) {
        if("5".equals(opcao)){
            return true;
        }
        return false;
    }



    private static boolean isOpcaoValida(String opcao) {
        if("1".equals(opcao) || "2".equals(opcao)
                ||"3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)){
            return true;
        }
        return false;
    }


}
