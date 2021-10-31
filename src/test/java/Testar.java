import br.com.fiap.resource.bo.UltimaCeiaBO;

public class Testar {

	public static void main(String[] args) throws ClassNotFoundException {
		UltimaCeiaBO ucb = new UltimaCeiaBO();
//		UltimaCeiaTO uct = new UltimaCeiaTO(1, "caviar", "dinheiro", 10);
//		ucb.inserir(uct);
		
		
		System.out.println(ucb.listarTodos());

	}

}
