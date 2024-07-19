package br.com.msilva.dao;

import br.com.msilva.domain.Cliente;


import java.util.*;

public class ClienteSetDAO implements IClienteDAO{

    private Set<Cliente> set;

    private ClienteSetDAO(){
        set = new HashSet<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {
        return this.set.add(cliente);
    }

    @Override
    public void excluir(Long cpf) {
        Cliente clienteEncontrado = null;
        //realizando interaração do mapa
        for(Cliente cliente : this.set){
            //caso cliente seja encontrado variavel recebe cliente
            if(cliente.getCpf().equals(cpf)){
                clienteEncontrado = cliente;
                break;
            }
            //removendo cliente
            if(clienteEncontrado != null){
                this.set.remove(clienteEncontrado);
            }
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        //verificando se o cliente existe no mapa
        if(this.set.contains(cliente)){
            // varrendo o mapa
            for(Cliente clienteCadastrado : this.set){
                //verioficando se o cliente que esta no mapa é o mesmo do inserido
                if(clienteCadastrado.equals(cliente)){
                    //alterando informações
                    clienteCadastrado.setNome(cliente.getNome());
                    clienteCadastrado.setTelefone(cliente.getTelefone());
                    clienteCadastrado.setNumero(cliente.getNumero());
                    clienteCadastrado.setEndereco(cliente.getEndereco());
                    clienteCadastrado.setCidade(cliente.getCidade());
                    clienteCadastrado.setEstado(cliente.getEstado());
                    break;
                }
            }
        }

    }

    @Override
    public Cliente consultar(Long cpf) {
      for(Cliente clienteCadastrado : this.set){
          if(clienteCadastrado.getCpf().equals(cpf)){
              return clienteCadastrado;
          }
      }
      return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.set;
    }
}
