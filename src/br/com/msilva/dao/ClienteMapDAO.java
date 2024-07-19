package br.com.msilva.dao;

import br.com.msilva.domain.Cliente;


import java.util.*;


public class ClienteMapDAO implements  IClienteDAO {

    private Map<Long, Cliente> map;

    public ClienteMapDAO(){
        map = new TreeMap<>();
    }


    @Override
    //varifica se existe um cpf cadastrado
    public Boolean cadastrar(Cliente cliente) {
        if(map.containsKey(cliente.getCpf())){
            return false;
        }
        //caso não exista realiza o cadastro
        map.put(cliente.getCpf(), cliente);
            return true;
    }

    @Override
    public void excluir(Long cpf) {
        //criação de obj para receber o cpf inserido
        Cliente clienteCadastrado = map.get(cpf);
        //através da obj remove o cpf(key)
        map.remove(clienteCadastrado.getCpf(),clienteCadastrado);

    }

    @Override
    public void alterar(Cliente cliente) {
        //identifico o cliente que esta no mapa
        Cliente clienteCadastrado = map.get(cliente.getCpf());
        //altero suas informações
        clienteCadastrado.setNome(cliente.getNome());
        clienteCadastrado.setTelefone(cliente.getTelefone());
        clienteCadastrado.setNumero(cliente.getNumero());
        clienteCadastrado.setEndereco(cliente.getEndereco());
        clienteCadastrado.setCidade(cliente.getCidade());
        clienteCadastrado.setEstado(cliente.getEstado());
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
