package br.com.msilva.dao;


import br.com.msilva.domain.Cliente;


import java.util.Collection;

/**
 *
 * @author marlon.silva
 */

public interface IClienteDAO {

    public Boolean cadastrar(Cliente cliente);

    public void excluir (Long cpf);

    public void alterar (Cliente cliente);

    public Cliente consultar(Long cpf);

    public Collection<Cliente> buscarTodos();

}
